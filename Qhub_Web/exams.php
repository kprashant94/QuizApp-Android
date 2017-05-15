<?php

require_once __DIR__ . '/db_connect.php';
$db = db_connect();

echo "<a href='exams.php'>Exams</a><br>";
echo "<a href='topics.php'>Topics</a><br>";
echo "<a href='questions.php'>Questions</a><br><br><br>";

echo "<form method= 'post' action = 'add_exam.php'>";
		echo "Exam : <input type= 'text' name = 'name' value=''><br><br>";
		echo "<input type='submit' value='Add'>";

echo "</form>";

$queryExams = mysqli_query($db, "SELECT * from exams");

while($exam = mysqli_fetch_array($queryExams)){
	echo "<a href='http://localhost/Qhub/update_exam.php?eId=$exam[id]'>Edit</a> :-: ";
	echo "<a href='http://localhost/Qhub/delete_exam.php?eId=$exam[id]'>Delete</a> :-: ";
	echo "<a href='http://localhost/Qhub/exam_detail.php?eId=$exam[id]'>$exam[id] - $exam[name]</a><br>";
}

?>