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
import scala.io.Source

@Singleton
class LitterController @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit exec: ExecutionContext) extends Controller {
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val userForm = Form(
    mapping(
      "username" -> nonEmptyText,
      "passwd"   -> nonEmptyText
    )(UserLogin.apply)(UserLogin.unapply)
  )

  def index = Action{
    Ok(views.html.userLogin(userForm))
  }
  def badlogin = Action{ Ok(views.html.badlogin()) }

  def getHackerNews(): Unit = {
    val url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20html%20where%20url%3D%22https%3A%2F%2Fnews.ycombinator.com%2F%22%20and%20xpath%20%3D%20%27%2F%2Ftd[%20%40class%3D%22title%22]%2Fa[%40class%3D%22storylink%22]%27&diagnostics=true"
    val xml: scala.xml.Elem = scala.xml.XML.loadString(Source.fromURL(url).mkString)
    val as = xml \\ "a"
    val titles = as.map(_.text)
    titles.foreach{ t => 
      val res: Future[Boolean] = dbConfig.db.run( Litters.filter(_.litterText === t).exists.result )
      res.map{x =>
        if(!x){
          val date = new java.util.Date()
          val currentTime = new java.sql.Timestamp(date.getTime)
          dbConfig.db.run( Litters += LittersRow(None, Some("Hacker News"), Some(currentTime), Some(t)) )
        }
      }
    }
  }

  def login = Action.async { implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => { Future { BadRequest(views.html.badlogin()) }},
      userData       => {
        val userid: Future[Option[Int]] = getUserId(userData.username, userData.passwd)
        userid.flatMap{ i =>
          i match {
            case None => Future{ Ok(views.html.badlogin()) }
            case Some(id) => {
              getHackerNews()
              getLitters.map(l => Ok(views.html.litter(l)).withSession("userid" -> id.toString))
            }
          }
        }
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
  def getUserId(username: String, passwd: String): Future[Option[Int]] = dbConfig.db.run(Users.filter(x => (x.username === username && x.password === passwd)).map(_.id).result.headOption)
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
