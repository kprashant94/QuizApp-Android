<?php
require_once __DIR__ . '/db_connect.php';
$db = db_connect();

if($_SERVER['REQUEST_METHOD'] == 'GET'){
	$qId = $_GET['qId'];
}
else{
	$qId = $_POST['qId'];
}
mysqli_query($db, "delete from questions where id = '$qId'");
mysqli_query($db, "delete from topics_questions where qId = '$qId'");
mysqli_query($db, "delete from exams_topics_questions where qId = '$qId'");
header("Location:questions.php");
?>