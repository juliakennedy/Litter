<html>
  <p>
    Litter will be avaliable campus-wide soon!
  </p>
</html>

<?php
  session_start();
  include("config.php");

  echo $_SESSION['loginname'];
  echo " has been logged out!";
  mysqli_close($link);
  session_destroy();
 ?>

<html>
  <p>
    <a href="litter.html">Back to login!</a>
  </p>
</html>
