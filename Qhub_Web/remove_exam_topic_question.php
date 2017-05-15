<?php
require_once __DIR__ . '/db_connect.php';
$db = db_connect();

$eId = $_GET['eId'];
$tId = $_GET['tId'];
$qId = $_GET['qId'];

if($eId != ''){
	mysqli_query($db, "delete from exams_topics_questions where eId = '$eId' and tId = '$tId' and qId = '$qId'");
}
else{
	mysqli_query($db, "delete from topics_questions where tId = '$tId' and qId = '$qId'");
	mysqli_query($db, "delete from exams_topics_questions where tId = '$tId' and qId = '$qId'");
}

header("Location:topic_detail.php?eId=$eId&tId=$tId");
?>