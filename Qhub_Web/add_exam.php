<?php
require_once __DIR__ . '/db_connect.php';
$db = db_connect();

$name = $_POST['name'];

if($name != ''){
	mysqli_query($db, "insert into exams (name) values('$name')");
}
	header("Location:exams.php");
?>