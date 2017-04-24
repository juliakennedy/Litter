package utility

object CodeGen extends App {
  slick.codegen.SourceCodeGenerator.main(
    Array("slick.driver.MySQLDriver", "com.mysql.jdbc.Driver", 
        "jdbc:mysql://127.0.0.1/litter?user=root&password=mypassword", 
        "/home/stephen/documents/webapps-csci3345/html/litter/app/", "models", "root", "mypassword")
  )
}
