package controllers

import akka.actor.ActorSystem
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future, Promise}
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.dbio.DBIOAction
import slick.driver.MySQLDriver.api._
import models._
import models.Tables._
import play.api.Play.current
import play.api.i18n.Messages.Implicits._

@Singleton
class LitterController @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit exec: ExecutionContext) extends Controller {
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val userForm = Form(
    mapping(
      "username" -> nonEmptyText,
      "passwd"   -> nonEmptyText
    )(UserLogin.apply)(UserLogin.unapply)
  )

  def index = Action{ Ok(views.html.userLogin(userForm)) }

  def login = Action.async { implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => { Future {
        BadRequest("Invalid data. Try again.") }},
      userData       => {
        val userid: Future[Int] = getUserId(userData.username, userData.passwd)
        for {
          id <- userid
          litters <- getLitters
        } yield { Ok(views.html.litter(litters)).withSession("userid" -> id.toString) }
      }
    )
  }
  def newUser = Action.async { implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => {Future{ BadRequest("Invalid Data") }},
      userData => {
        val userid: Future[Int] = dbConfig.db.run( Users.map(u => (u.username, u.password)).returning(Users.map(_.id)) += (Some(userData.username),Some(userData.passwd)))
        for {
          id <- userid
          litters <- getLitters
        } yield { Ok(views.html.litter(litters)).withSession("userid" -> id.toString) }
      }
    )
  }
  def getUserId(username: String, passwd: String): Future[Int] = dbConfig.db.run(Users.filter(x => (x.username === username && x.password === passwd)).map(_.id).take(1).result.head)
  def getLitters: Future[Seq[(String,String)]] = {
    val litters = dbConfig.db.run(Litters.sortBy(_.time.desc).map(l => (l.litterText, l.name, l.time)).result)
    litters.map(_.flatMap(l => 
        for {
          text <- l._1
          name <- l._2
          datetime <- l._3
        } yield { (text, "-- " + name + " @ " + datetime.toString())  }
    ))
  }

  def addLitter = Action.async { implicit request =>
    val litterForm = Form(mapping("litterText" -> text, "signature" -> text)(LitterAdd.apply)(LitterAdd.unapply))
    val date = new java.util.Date()
    val currentTime = new java.sql.Timestamp(date.getTime)
    litterForm.bindFromRequest.fold(
      formWithErrors => { Future { BadRequest("Invalid data") }},
      litterData => {
        request.session.get("userid").map( userid =>
          dbConfig.db.run( Litters += LittersRow(Some(userid.toInt), Some(litterData.signature), Some(currentTime), Some(litterData.litterText)) )
        ).getOrElse(
          Future{ Unauthorized("Unauthorized access") }
        )
      }
    )
    getLitters.map(l => Ok(views.html.litter(l)).withSession(request.session))

  }

}
