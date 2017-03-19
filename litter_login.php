<?php
    session_start();
    include("litter_config.php");
    echo 'HERE';
	if ($_SERVER['SERVER_NAME'] != "dias11.cs.trinity.edu") {
    	echo "<p>You must access this page from on campus through dias11.</p>";
    	die ();
	}
  echo 'HERE';
	if(isset($_POST['username'])){
    echo 'username posted';
		$user = $_POST['username'];
		$pass = $_POST['password'];
		$user = mysqli_real_escape_string($link, $user);
    $pass = mysqli_real_escape_string($link, $pass);

    $sql = "select id from users where username='$user' and password ='$pass'";
    $results = mysqli_query($link, $sql) or trigger_error(mysql_error. "in" .$sql);
    if(!$results) {echo "BAD QUERY";}
		$row = mysqli_fetch_assoc($results);
    $_SESSION['id'] = $row['id'];
    $count = mysqli_num_rows($results);
    if($count == 1) {
      echo "you did it";
      //header("Location: task5/todo.php");
    } else {
      echo "sucks";
      //header("Location: task5/Task5LogOut.php");
    }
	}else { $user = null; $password = null;}
?>


<html>
	<body>
		<br>
		<br>
		Welcome <?php echo $user;?><br>
		Your password is <?php echo $pass;?>
	</body>
</html>
