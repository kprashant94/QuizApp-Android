<?php

require_once __DIR__ . '/db_connect.php';

$db = db_connect();

echo "<a href='exams.php'>Exams</a><br>";
echo "<a href='topics.php'>Topics</a><br>";
echo "<a href='questions.php'>Questions</a><br><br><br>";

echo "<form method= 'post' action = 'add_topic.php'>";
		echo "Topic : <input type= 'text' name = 'name' value=''><br><br>";
		echo "<input type='submit' value='Add'>";

echo "</form>";

$queryTopics = mysqli_query($db, "SELECT *from topics");

$eId = '';
while($topic = mysqli_fetch_array($queryTopics)){
	echo "<a href='http://localhost/Qhub/update_topic.php?tId=$topic[id]'>Edit</a> :-: ";
	echo "<a href='http://localhost/Qhub/delete_topic.php?tId=$topic[id]'>Delete</a> :-: ";
	echo "<a href='http://localhost/Qhub/topic_detail.php?eId=$eId&tId=$topic[id]'>$topic[id] - $topic[name]</a><br>";

}

?>