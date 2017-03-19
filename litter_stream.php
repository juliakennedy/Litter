  <html>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
  <style>
  body,h1 {font-family: "Raleway", sans-serif, text-align: center}
  h2 {font-family: "Times New Roman", Times, serif}
  body, html {height: 100%}
  .bgimg {
  	background-image: url('litterbackground.jpg');
  	min-height: 100%;
  	background-position: center;
  	background-size: cover;
  }
  .menu li {
    padding: 8px;
    margin-bottom: 8px;
    background-color: #33b5e5;
    color: #ffffff;
  }
  .menu li:hover {
      background-color: #0099cc;
  }
  </style>
  <div class="bgimg w3-display-container w3-animate-opacity w3-text-white">
  <div class="w3-display-topleft w3-padding-large w3-xlarge">
    <h1>
    	Litter as of <script language="javascript">
 var today = new Date();
 document.write(today);
 </script>
 </h1>
  </div>
  <div>
  <h2>
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
  $grab = "SELECT * FROM litters ORDER BY time DESC";
  $items = mysqli_query($link, $grab);

  if(!$items) {
    echo FAILEDQUERY;
  }

  $counter = 1;

  echo nl2br("\n");
  echo nl2br("\n");

  while($row = mysqli_fetch_assoc($items)) {
    //echo '<html><p>';
    //echo $counter;
    //echo '</p></html>'
    echo $row['litter_text'];
    echo nl2br("\n");
    echo '-- ';
    echo $row['name'];
    echo ' @ ';
    echo $row['time'];
    //echo '</body>'
    echo nl2br("\n");
    //$counter = $counter + 1;
    //echo " ";
  }

  $_SESSION['userid'] = $idfound;
?>

  </h2>
</div>
  <br>
  <h1>
  <form action="litter_additem.php"  method="post">
    Add Litter: <input type="text" name="newitem">
    Signature: <input type-"text" name="signature">
    <input type="submit">
    <br>
  </form>
  <a href="litter_logout.php">Log Out</a>
</h1>
</html>
