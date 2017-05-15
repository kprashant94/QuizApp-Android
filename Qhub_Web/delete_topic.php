<?php
require_once __DIR__ . '/db_connect.php';
$db = db_connect();

if($_SERVER['REQUEST_METHOD'] == 'GET'){
	$tId = $_GET['tId'];
}
else{
	$tId = $_POST['tId'];
}
mysqli_query($db, "delete from topics where id = '$tId'");
mysqli_query($db, "delete from exams_topics where tId = '$tId'");
mysqli_query($db, "delete from topics_questions where tId = '$tId'");
mysqli_query($db, "delete from exams_topics_questions where tId = '$tId'");
header("Location:topics.php");
?>