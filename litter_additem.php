<html>
  <p>
    Litter added to the stream!
  </p>
  <br>
</html>

<?php
  session_start();
  include("litter_config.php");

  $userid = $_SESSION['id'];
  $item = $_POST['newitem'];
  $sig = $_POST['signature'];

  //echo $userid;
  //echo $item;

  if(!($item == "")) {

    $sql = "INSERT INTO litters VALUES ($userid,'$sig',now(),'$item')";
    //echo $sql;
    $result = mysqli_query($link, $sql);
  }

  if(!$result) header("Location: litter_stream.php");
  else header("Location: litter_stream.php");
 ?>
