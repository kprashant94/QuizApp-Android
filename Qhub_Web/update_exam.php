<?php

require_once __DIR__ . '/db_connect.php';
$db = db_connect();

if($_SERVER['REQUEST_METHOD'] == 'GET'){
	$eId = $_GET['eId'];
}
else{
	$eId = $_POST['eId'];
}
@$name = $_POST['name'];

if(isset($name) and $name != ''){
	mysqli_query($db, "update exams set name = '$name' where id = '$eId'");
	header("Location:exams.php");
}
$query = mysqli_query($db, "select *from exams where id = '$eId'");
echo "<form method= 'post' action = 'update_exam.php'>";
		echo "<input type='hidden' name='eId' value='$eId'>";
		while($exam = mysqli_fetch_array($query)){
			echo "Exam : <input type= 'text' name = 'name' value='$exam[name]'><br><br>";
		}
		echo "<input type='submit' value='Save'>";
echo "</form>";
echo "<form method= 'post' action = 'delete_exam.php'>";
		echo "<input type='hidden' name='eId' value='$eId'>";
		echo "<input type='submit' value='Delete'>";
echo "</form>";
?>