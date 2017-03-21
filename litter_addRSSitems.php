<?php
session_start();
include("litter_config.php");

$userid = -1;
$item = $_POST['item'];
$sig = "Hacker News";

//echo $userid;
//echo $item;

if(!($item == "")) {
  $check = "SELECT ID FROM litters WHERE litter_text = '$item'";
  $results = mysqli_query($link, $check) or trigger_error(mysql_error. "in" .$sql);
  if(!$results) {echo "BAD QUERY";}
  $count = mysqli_num_rows($results);
  if($count == 0) {
    $sql = "INSERT INTO litters VALUES ($userid,'$sig',now(),'$item')";
    //echo $sql;
    $result = mysqli_query($link, $sql);

  }

}

 ?>
