<?php

require_once __DIR__ . '/db_connect.php';
$db = db_connect();

if($_SERVER['REQUEST_METHOD'] == 'GET'){
	$tId = $_GET['tId'];
}
else{
	$tId = $_POST['tId'];
}
@$name = $_POST['name'];

if(isset($name) and $name != ''){
	mysqli_query($db, "update topics set name = '$name' where id = '$tId'");
	header("Location:topics.php");
}
$query = mysqli_query($db, "select *from topics where id = '$tId'");
echo "<form method= 'post' action = 'update_topic.php'>";
		echo "<input type='hidden' name='tId' value='$tId'>";
		while($topic = mysqli_fetch_array($query)){
			echo "Topic : <input type= 'text' name = 'name' value='$topic[name]'><br><br>";
		}
		echo "<input type='submit' value='Save'>";
echo "</form>";
echo "<form method= 'post' action = 'delete_topic.php'>";
		echo "<input type='hidden' name='tId' value='$tId'>";
		echo "<input type='submit' value='Delete'>";
echo "</form>";
?>