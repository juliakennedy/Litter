<html>
  <p>
    Item Deleted!
  </p>
  <br>
</html>

<?php
  session_start();
  include("litter_config.php");

  $userid = $_SESSION['userid'];
  $deleteme = $_POST['indextodelete'];

  //echo $userid;
  //echo $deleteme;

  //echo $userid;
  //echo $item;

  $sql = "SELECT item FROM todolist WHERE userid = $userid";
  //echo $sql;
  $result = mysqli_query($link, $sql);

  $counter = 1;
  $tasktodelete = "";
  while($row = mysqli_fetch_assoc($result)) {
    if($counter == $deleteme) {
      $tasktodelete = $row['item'];
    }
    $counter++;
    //echo $counter;
  }

  $moresql = "DELETE FROM todolist WHERE item='$tasktodelete' AND UserID = $userid";
  mysqli_query($link, $moresql);

  echo $tasktodelete;
  echo " is now complete! Congrats!"

 ?>

<html>
 <p>
   <a href="todo.php">Return to to-do list.</a>
 </p>
</html>
