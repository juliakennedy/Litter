<html>
  <p> Welcome! </p>
  <p> Items on your to-do list: </p>
</html>

<?php
  session_start();
  include("litter_config.php");
  $login = $_SESSION['loginname'];
  //echo $login;
  $sql = "SELECT ID FROM task5 WHERE username = '$login'";
  //echo $sql;

  //$sql = "SELECT username FROM task5";

  $result = mysqli_query($link, $sql);
  if(!$result) {
    echo FAILEDQUERY;
  }

  $foundid = mysqli_fetch_assoc($result);
  $idfound = $foundid['ID'];
  /*
  while($row = mysqli_fetch_assoc($result)) {
    $idfound = $row['ID'];
    //echo " ";
  }
  */
  //echo $idfound;
  $grab = "SELECT item FROM todolist WHERE userid = $idfound";
  $items = mysqli_query($link, $grab);

  $counter = 1;

  while($row = mysqli_fetch_assoc($items)) {
    echo $counter;
    echo " ";
    echo $row['item'];
    echo nl2br("\n");
    $counter = $counter + 1;
    //echo " ";
  }

  $_SESSION['userid'] = $idfound;
?>

  <html>
  <br>
  <form action="additem.php"  method="post">
    Add To-Do Item: <input type="text" name="newitem">
    <input type="submit">
    <br>
  </form>
  <form action="deleteitem.php" method="post">
    Delete Item #: <input type="text" name="indextodelete">
    <input type="submit">
    <br>
  </form>
  <a href="logout.php">Log Out</a>
</html>
