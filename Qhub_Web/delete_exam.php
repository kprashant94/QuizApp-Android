<?php
require_once __DIR__ . '/db_connect.php';
$db = db_connect();

if($_SERVER['REQUEST_METHOD'] == 'GET'){
	$eId = $_GET['eId'];
}
else{
	$eId = $_POST['eId'];
}
mysqli_query($db, "delete from exams where id = '$eId'");
mysqli_query($db, "delete from exams_topics where eId = '$eId'");
mysqli_query($db, "delete from exams_topics_questions where eId = '$eId'");
header("Location:exams.php");
?>