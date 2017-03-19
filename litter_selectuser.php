<?php
    session_start();
    include("litter_config.php");
	if ($_SERVER['SERVER_NAME'] != "dias11.cs.trinity.edu") {
    	echo "<p>You must access this page from on campus through dias11.</p>";
    	die ();
	}
	if(isset($_POST['desiredusername'])){
    echo 'username posted';
		$user = $_POST['desiredusername'];
		$pass = $_POST['desiredpassword'];
		$user = mysqli_real_escape_string($link, $user);
    $pass = mysqli_real_escape_string($link, $pass);
    $exists = "SELECT * FROM users WHERE username = '$user'";
    $existsresults = mysqli_query($link, $exists) or trigger_error(mysql_error. "in" .$exists);
    if(!$existsresults){
      $sql = "INSERT INTO users VALUES(Null,'$user','$pass')";
      $results = mysqli_query($link, $sql) or trigger_error(mysql_error. "in" .$sql);
      header("Location: litter_useraccepted.php");
    } else {
      header("Location: litter_userfailed.php");
    }
    /*if(!$results) {echo "BAD QUERY";}
		$row = mysqli_fetch_assoc($results);
    $_SESSION['id'] = $row['id'];
    $count = mysqli_num_rows($results);
    if($count == 1) {
      header("Location: litter_useraccepted.php");
    } else {
      header("Location: litter_userfailed.php");
    }
	}else { $user = null; $password = null;}
  */
  }
?>
