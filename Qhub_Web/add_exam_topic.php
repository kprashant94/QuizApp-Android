<?php
require_once __DIR__ . '/db_connect.php';
$db = db_connect();

$eId = $_POST['eId'];
$tId = $_POST['tId'];

if($eId != '' & $tId != ''){
	mysqli_query($db, "insert into exams_topics (eId, tId) values('$eId', '$tId')");
}
	header("Location:exam_detail.php?eId=$eId");
?>