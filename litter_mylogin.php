<?php
   session_start();
   include("litter_config.php");



   if ($_SERVER['SERVER_NAME'] != "dias11.cs.trinity.edu") {
    echo "<p>You must access this page from on campus through dias11.</p></body></html>";
    die ();
   }


   if(isset($_POST['username'])) {
      // username and password sent from form

      $foundusername = mysqli_real_escape_string($link, $_POST['username']);
      $foundpassword = mysqli_real_escape_string($link, $_POST['password']);

      //echo $_POST['username'];
      //echo $_POST['password'];

      $sql = "SELECT password FROM task5 WHERE username = '$foundusername'";

      $result = mysqli_query($link, $sql);
      if(!$result) {
        echo FAILEDQUERY;
      }
      $row = mysqli_fetch_assoc($result);
      if(!$row) {
        echo SLOWDOWN;
      }
      $actualpassword = $row['password'];
      //echo $actualpassword;

      /*
      if($foundpassword == $actualpassword) {
        echo PASSWORDMATCH;
      } else {
        echo FAILURE;
      }
      */

      $_SESSION['password'] = $actualpassword;

      if($foundpassword == $actualpassword) {
         //session_register("myusername");
         $_SESSION['loginname'] = $foundusername;
         header("location: ajaxhelper.php");
      }else {
         //$error = "Your Login Name or Password is invalid";
         header("location: loginfailure.php");
      }
   }

?>
