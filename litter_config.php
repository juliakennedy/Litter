<?php
$link = mysqli_connect("127.0.0.1", "root", "mypassword", "litter");
if (mysqli_connect_errno()) {
    echo ('Could not connect: ' . mysqli_connect_error());
}
 //echo 'Connected successfully';
?>
