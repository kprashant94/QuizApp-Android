<?php

require_once __DIR__ . '/db_connect.php';

$db = db_connect();

echo "<a href='exams.php'>Exams</a><br>";
echo "<a href='topics.php'>Topics</a><br>";
echo "<a href='questions.php'>Questions</a><br><br><br><br>";

@$question = $_POST['question'];
@$answer = $_POST['answer'];
@$explanation = $_POST['explanation'];
@$optA = $_POST['optA'];
@$optB = $_POST['optB'];
@$optC = $_POST['optC'];
@$optD = $_POST['optD'];

if($question != '' and $answer != '' and $optA != '' and $optB != '' and $optC != '' and $optD != ''){
	mysqli_query($db, "INSERT INTO questions (question, answer, explanation, optA, optB, optC, optD) VALUES ('$question', '$answer', '$explanation', '$optA', '$optB', '$optC', '$optD')");
}

echo "<form method= 'post' action = 'questions.php'>";
		echo "Question <br> <textarea type= 'text' name = 'question' value='' cols=40 rows=3></textarea><br><br>";
		echo "Option A : <input type= 'text' name = 'optA' value=''><br><br>";
		echo "Option B : <input type= 'text' name = 'optB' value=''><br><br>";
		echo "Option C : <input type= 'text' name = 'optC' value=''><br><br>";
		echo "Option D : <input type= 'text' name = 'optD' value=''><br><br>";
		echo "Answer : <input type= 'text' name = 'answer' value=''><br><br>";
		echo "Explanation <br> <textarea type= 'text' name = 'explanation' value='' cols=40 rows=5></textarea><br><br>";
		echo "<input type='submit' value='Add'>";

echo "</form>";

$queryQuestions = mysqli_query($db, "SELECT * FROM questions ORDER BY id DESC");

$eId = '';
$tId = '';
while($question = mysqli_fetch_array($queryQuestions)){
	echo "<a href='http://localhost/Qhub/update_question.php?qId=$question[id]'>Edit</a> :-: ";
	echo "<a href='http://localhost/Qhub/delete_question.php?qId=$question[id]'>Delete</a> :-: ";
	echo "<a href='http://localhost/Qhub/update_question.php?qId=$question[id]'>$question[id] - $question[question]</a><br>";
}

?>