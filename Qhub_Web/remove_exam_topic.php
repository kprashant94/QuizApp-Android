<?php
require_once __DIR__ . '/db_connect.php';
$db = db_connect();

$eId = $_GET['eId'];
$tId = $_GET['tId'];

mysqli_query($db, "delete from exams_topics where eId = '$eId' and tId = '$tId'");
mysqli_query($db, "delete from exams_topics_questions where eId = '$eId' and tId = '$tId'");
header("Location:exam_detail.php?eId=$eId");
?>