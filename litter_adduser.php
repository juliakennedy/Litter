<html>
<!DOCTYPE html>
<html>
<title>Litter Login</title>
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
<body>

<div class="bgimg w3-display-container w3-animate-opacity w3-text-white">
<div class="w3-display-topleft w3-padding-large w3-xlarge">
	Litter by JSM
</div>
<div class="w3-display-middle">
	<h1 class="w3-jumbo w3-animate-top">Register for Litter</h1>
	<hr class="w3-border-grey" style="margin:auto;width:40%">
	<h1> Enter Desired Log-In Credentials </h1>
	<form action="litter_selectuser.php"  method="post">
		Desired Username: <input type="text" name="desiredusername"><br>
		<br>
		Desired Password: <input type="text" name="desiredpassword"><br>
		<br>
		<input type="submit">
	</form>
</div>
</div>

</body>
</html>
