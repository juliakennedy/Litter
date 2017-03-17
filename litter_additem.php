<html>
  <p>
    Item added!
  </p>
  <br>
</html>

<?php
  session_start();
  include("config.php");

  $userid = $_SESSION['userid'];
  $item = $_POST['newitem'];

  //echo $userid;
  //echo $item;

  $sql = "INSERT INTO todolist VALUES ($userid,'$item')";
  //echo $sql;
  $result = mysqli_query($link, $sql);


 ?>

<html>
 <p>
   <a href="todo.php">Return to to-do list.</a>
 </p>
</html>
