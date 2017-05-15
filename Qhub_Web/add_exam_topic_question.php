<?php
require_once __DIR__ . '/db_connect.php';
$db = db_connect();

$eId = $_POST['eId'];
$tId = $_POST['tId'];
$qId = $_POST['qId'];

if($eId != '' and $tId != '' and $qId != ''){
	mysqli_query($db, "insert into exams_topics_questions (eId, tId, qId) values('$eId', '$tId', '$qId')");
	mysqli_query($db, "insert into topics_questions (tId, qId) values('$tId', '$qId')");
}
else if($tId != '' and $qId != ''){
	mysqli_query($db, "insert into topics_questions (tId, qId) values('$tId', '$qId')");
}
	header("Location:topic_detail.php?eId=$eId&tId=$tId");
?>