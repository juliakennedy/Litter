<html>
  <title> Litter of Today! </title>
  <p> Welcome! </p>
  <p> Here is some of today's kitty litter! </p>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
  <style>
  body,h1 {font-family: "Raleway", sans-serif}
  body, html {height: 100%}
  .bgimg {
  	background-image: url('litterbackground.jpg');
  	min-height: 100%;
  	background-position: center;
  	background-size: cover;
  }
  </style>
</html>

<?php
  session_start();
  include("litter_config.php");
  $login = $_SESSION['loginname'];
  //echo $login;
  /*$sql = "SELECT ID FROM task5 WHERE username = '$login'";
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
  $grab = "SELECT litter_text FROM litters";
  $items = mysqli_query($link, $grab);

  if(!$items) {
    echo FAILEDQUERY;
  }

  $counter = 1;

  while($row = mysqli_fetch_assoc($items)) {
    echo $counter;
    echo " ";
    echo $row['litter_text'];
    echo nl2br("\n");
    $counter = $counter + 1;
    //echo " ";
  }

  $_SESSION['userid'] = $idfound;
?>

  <html>
  <br>
  <form action="litter_additem.php"  method="post">
    Add Litter: <input type="text" name="newitem">
    Signature: <input type-"text" name="signature">
    <input type="submit">
    <br>
  </form>
  <a href="litter_logout.php">Log Out</a>
</html>
