<?php
$link = mysqli_connect("localhost", "litter", "DontMessWithCS", "litter");
if (mysqli_connect_errno()) {
    echo ('Could not connect: ' . mysqli_connect_error());
}
 //echo 'Connected successfully';
?>
