<?php

function db_connect(){
	require_once __DIR__ . '/db_config.php';
	$username = DB_USER;
	$password = DB_PASSWORD;
	$hostname = DB_SERVER;
	$database = DB_DATABASE;
	//connection to the database
	$dbhandle = mysqli_connect($hostname, $username, $password, $database)
	or die("Unable to connect to MySQL");
	//echo "Connected to MySQL<br>";
	return $dbhandle;
}

?>