<?php

require_once __DIR__ . '/db_connect.php';
$db = db_connect();

if($_SERVER['REQUEST_METHOD'] == 'GET'){
	$qId = $_GET['qId'];
}
else{
	$qId = $_POST['qId'];
}
@$question = $_POST['question'];
@$answer = $_POST['answer'];
@$explanation = $_POST['explanation'];
@$optA = $_POST['optA'];
@$optB = $_POST['optB'];
@$optC = $_POST['optC'];
@$optD = $_POST['optD'];

if(isset($question) and isset($answer) and isset($optA) and isset($optB) and isset($optC) and isset($optD)){
	mysqli_query($db, "update questions set question = '$question', answer = '$answer', explanation = '$explanation', optA = '$optA', optB = '$optB', optC = '$optC', optD = '$optD' where id = '$qId'");
	header("Location:questions.php");
}
$query = mysqli_query($db, "select *from questions where id = '$qId'");
echo "<form method= 'post' action = 'update_question.php'>";
		echo "<input type='hidden' name='qId' value='$qId'>";
		while($question = mysqli_fetch_array($query)){
			echo "Question <br> <textarea type= 'text' name = 'question' cols=40 rows=3>$question[question]</textarea><br><br>";
			echo "Option A : <input type= 'text' name = 'optA' value='$question[optA]'><br><br>";
			echo "Option B : <input type= 'text' name = 'optB' value='$question[optB]'><br><br>";
			echo "Option C : <input type= 'text' name = 'optC' value='$question[optC]'><br><br>";
			echo "Option D : <input type= 'text' name = 'optD' value='$question[optD]'><br><br>";
			echo "Answer : <input type= 'text' name = 'answer' value='$question[answer]'><br><br>";
			echo "Explanation <br> <textarea type= 'text' name = 'explanation' cols=40 rows=5>$question[explanation]</textarea><br><br>";
		}
		echo "<input type='submit' value='Save'>";
echo "</form>";
echo "<form method= 'post' action = 'delete_question.php'>";
		echo "<input type='hidden' name='qId' value='$qId'>";
		echo "<input type='submit' value='Delete'>";
echo "</form>";
?>