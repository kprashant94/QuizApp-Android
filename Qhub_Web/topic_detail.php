<?php

require_once __DIR__ . '/db_connect.php';

$db = db_connect();

echo "<a href='exams.php'>Exams</a><br>";
echo "<a href='topics.php'>Topics</a><br>";
echo "<a href='questions.php'>Questions</a><br><br><br>";

$eId = $_GET['eId'];
$tId = $_GET['tId'];

$queryTopics = mysqli_query($db, "SELECT * FROM topics where id = '$tId' ");
while($topic = mysqli_fetch_array($queryTopics)){
	echo "<h1>$topic[name]";
}

$queryExams = mysqli_query($db, "SELECT * FROM exams where id = '$eId' ");
while($exam = mysqli_fetch_array($queryExams)){
	echo " - $exam[name]";
}
echo "</h1>";

if($eId != ''){
	$queryQuestions = mysqli_query($db, "select q.id as id, q.question as question from questions as q
											inner join topics_questions as tq
											on q.id = tq.qId
											where tq.tId = '$tId' and q.id not in(select qId from exams_topics_questions where eId = '$eId' and tId = '$tId')");
}
else{
	$queryQuestions = mysqli_query($db, "select *from questions where id not in(select qId from topics_questions)");

}

echo "<form method= 'post' action = 'add_exam_topic_question.php'>";
	echo "<input type='hidden' name='eId' value='$eId'>";
	echo "<input type='hidden' name='tId' value='$tId'>";
	echo "<select name='qId'>";
		echo "<option value=''>Question</option>";
		while($question = mysqli_fetch_array($queryQuestions)){
			echo "<option value='$question[id]'>$question[id] - $question[question]</option>";
		}
	echo "</select><br><br>";
	echo "<input type='submit' value='Add'>";
echo "</form>";

if($eId != ''){
	$queryQuestions1 = mysqli_query($db, "SELECT * from questions as q
											inner join exams_topics_questions as etq
											on q.id = etq.qId
											where etq.eId = '$eId' and etq.tId = '$tId'");
}
else{
	$queryQuestions1 = mysqli_query($db, "SELECT * from questions as q
											inner join topics_questions as tq
											on q.id = tq.qId
											where tq.tId = '$tId'");
}

while($question = mysqli_fetch_array($queryQuestions1)){

	echo "<a href='http://localhost/Qhub/remove_exam_topic_question.php?eId=$eId&tId=$tId&qId=$question[qId]'>Remove</a> :-: ";
	echo "<a href='http://localhost/Qhub/update_question.php?qId=$question[qId]'>$question[qId] - $question[question]</a><br>";
}

?>