<?php

require_once __DIR__ . '/db_connect.php';

$db = db_connect();

echo "<a href='exams.php'>Exams</a><br>";
echo "<a href='topics.php'>Topics</a><br>";
echo "<a href='questions.php'>Questions</a><br><br><br>";

$eId = $_GET['eId'];

$queryExams = mysqli_query($db, "SELECT * FROM exams where id = '$eId' ");
while($exam = mysqli_fetch_array($queryExams)){
	echo "<h1>$exam[name]</h1>";
}

$queryTopics = mysqli_query($db, "select *from topics where id not in(select tId from exams_topics where eId = '$eId')");

echo "<form method= 'post' action = 'add_exam_topic.php'>";
	echo "<input type='hidden' name='eId' value='$eId'>";
	echo "<select name='tId'>";
		echo "<option value=''>Topic</option>";
		while($topic = mysqli_fetch_array($queryTopics)){
			echo "<option value='$topic[id]'>$topic[id] - $topic[name]</option>";
		}
	echo "</select><br><br>";
	echo "<input type='submit' value='Add'>";

echo "</form>";

$queryTopics2 = mysqli_query($db, "SELECT * from topics as t
									inner join exams_topics as et
									on t.id=et.tId 
									where et.eId = '$eId'");
while($topic = mysqli_fetch_array($queryTopics2)){
	echo "<a href='http://localhost/Qhub/remove_exam_topic.php?eId=$eId&tId=$topic[tId]'>Remove </a> :-: ";
	echo "<a href='http://localhost/Qhub/topic_detail.php?eId=$eId&tId=$topic[tId]'>$topic[tId] - $topic[name]</a><br>";
}

?>