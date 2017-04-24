package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Litters.schema ++ Test.schema ++ Users.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Litters
   *  @param id Database column ID SqlType(INT), Default(None)
   *  @param name Database column name SqlType(VARCHAR), Length(20,true), Default(None)
   *  @param time Database column time SqlType(DATETIME), Default(None)
   *  @param litterText Database column litter_text SqlType(VARCHAR), Length(420,true), Default(None) */
  case class LittersRow(id: Option[Int] = None, name: Option[String] = None, time: Option[java.sql.Timestamp] = None, litterText: Option[String] = None)
  /** GetResult implicit for fetching LittersRow objects using plain SQL queries */
  implicit def GetResultLittersRow(implicit e0: GR[Option[Int]], e1: GR[Option[String]], e2: GR[Option[java.sql.Timestamp]]): GR[LittersRow] = GR{
    prs => import prs._
    LittersRow.tupled((<<?[Int], <<?[String], <<?[java.sql.Timestamp], <<?[String]))
  }
  /** Table description of table litters. Objects of this class serve as prototypes for rows in queries. */
  class Litters(_tableTag: Tag) extends Table[LittersRow](_tableTag, "litters") {
    def * = (id, name, time, litterText) <> (LittersRow.tupled, LittersRow.unapply)

    /** Database column ID SqlType(INT), Default(None) */
    val id: Rep[Option[Int]] = column[Option[Int]]("ID", O.Default(None))
    /** Database column name SqlType(VARCHAR), Length(20,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(20,varying=true), O.Default(None))
    /** Database column time SqlType(DATETIME), Default(None) */
    val time: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("time", O.Default(None))
    /** Database column litter_text SqlType(VARCHAR), Length(420,true), Default(None) */
    val litterText: Rep[Option[String]] = column[Option[String]]("litter_text", O.Length(420,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Litters */
  lazy val Litters = new TableQuery(tag => new Litters(tag))

  /** Entity class storing rows of table Test
   *  @param id Database column id SqlType(INT), Default(None)
   *  @param name Database column name SqlType(VARCHAR), Length(20,true), Default(None)
   *  @param time Database column time SqlType(DATETIME), Default(None)
   *  @param litterText Database column litter_text SqlType(VARCHAR), Length(420,true), Default(None) */
  case class TestRow(id: Option[Int] = None, name: Option[String] = None, time: Option[java.sql.Timestamp] = None, litterText: Option[String] = None)
  /** GetResult implicit for fetching TestRow objects using plain SQL queries */
  implicit def GetResultTestRow(implicit e0: GR[Option[Int]], e1: GR[Option[String]], e2: GR[Option[java.sql.Timestamp]]): GR[TestRow] = GR{
    prs => import prs._
    TestRow.tupled((<<?[Int], <<?[String], <<?[java.sql.Timestamp], <<?[String]))
  }
  /** Table description of table test. Objects of this class serve as prototypes for rows in queries. */
  class Test(_tableTag: Tag) extends Table[TestRow](_tableTag, "test") {
    def * = (id, name, time, litterText) <> (TestRow.tupled, TestRow.unapply)

    /** Database column id SqlType(INT), Default(None) */
    val id: Rep[Option[Int]] = column[Option[Int]]("id", O.Default(None))
    /** Database column name SqlType(VARCHAR), Length(20,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(20,varying=true), O.Default(None))
    /** Database column time SqlType(DATETIME), Default(None) */
    val time: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("time", O.Default(None))
    /** Database column litter_text SqlType(VARCHAR), Length(420,true), Default(None) */
    val litterText: Rep[Option[String]] = column[Option[String]]("litter_text", O.Length(420,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Test */
  lazy val Test = new TableQuery(tag => new Test(tag))

  /** Entity class storing rows of table Users
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(VARCHAR), Length(100,true), Default(None)
   *  @param password Database column password SqlType(VARCHAR), Length(100,true), Default(None) */
  case class UsersRow(id: Int, username: Option[String] = None, password: Option[String] = None)
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[Int], <<?[String], <<?[String]))
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends Table[UsersRow](_tableTag, "users") {
    def * = (id, username, password) <> (UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), username, password).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(VARCHAR), Length(100,true), Default(None) */
    val username: Rep[Option[String]] = column[Option[String]]("username", O.Length(100,varying=true), O.Default(None))
    /** Database column password SqlType(VARCHAR), Length(100,true), Default(None) */
    val password: Rep[Option[String]] = column[Option[String]]("password", O.Length(100,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
}
