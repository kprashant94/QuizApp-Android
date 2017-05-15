<?php

$response = array();

require_once __DIR__ . '/db_connect.php';

$db = db_connect();

$queryExams = mysqli_query($db, "SELECT *FROM exams") or die(mysqli_connect_error());
$queryTopics = mysqli_query($db, "SELECT *FROM topics") or die(mysqli_connect_error());
$queryQuestions = mysqli_query($db, "SELECT *FROM questions") or die(mysqli_connect_error());
$queryExamsTopics = mysqli_query($db, "SELECT *FROM exams_topics") or die(mysqli_connect_error());
$queryTopicsQuestions = mysqli_query($db, "SELECT *FROM topics_questions") or die(mysqli_connect_error());
$queryExamsTopicsQuestions = mysqli_query($db, "SELECT *FROM exams_topics_questions") or die(mysqli_connect_error());

if (mysqli_num_rows($queryExams) > 0) {
	
    $response["exams"] = array();
	$response["topics"] = array();
	$response["questions"] = array();
	$response["exams_topics"] = array();
	$response["topics_questions"] = array();
	$response["exams_topics_questions"] = array();
    
    while ($row = mysqli_fetch_array($queryExams)) {
        $exam = array();
        $exam["id"] = $row["id"];
        $exam["name"] = $row["name"];
	
        array_push($response["exams"], $exam);
    }
	
	while ($row = mysqli_fetch_array($queryTopics)) {
        $topic = array();
        $topic["id"] = $row["id"];
        $topic["name"] = $row["name"];
	
        array_push($response["topics"], $topic);
    }
	
	while ($row = mysqli_fetch_array($queryQuestions)) {
        $question = array();
        $question["id"] = $row["id"];
        $question["question"] = $row["question"];
		$question["answer"] = $row["answer"];
		$question["explanation"] = $row["explanation"];
		$question["optA"] = $row["optA"];
		$question["optB"] = $row["optB"];
		$question["optC"] = $row["optC"];
		$question["optD"] = $row["optD"];
	
        array_push($response["questions"], $question);
    }
	
	while ($row = mysqli_fetch_array($queryExamsTopics)) {
        $exam_topic = array();
        $exam_topic["id"] = $row["id"];
		$exam_topic["eId"] = $row["eId"];
		$exam_topic["tId"] = $row["tId"];
	
        array_push($response["exams_topics"], $exam_topic);
    }
	
	while ($row = mysqli_fetch_array($queryTopicsQuestions)) {
        $topic_question = array();
        $topic_question["id"] = $row["id"];
		$topic_question["tId"] = $row["tId"];
		$topic_question["qId"] = $row["qId"];
	
        array_push($response["topics_questions"], $topic_question);
    }
	
	while ($row = mysqli_fetch_array($queryExamsTopicsQuestions)) {
        $exam_topic_question = array();
        $exam_topic_question["id"] = $row["id"];
		$exam_topic_question["eId"] = $row["eId"];
		$exam_topic_question["tId"] = $row["tId"];
		$exam_topic_question["qId"] = $row["qId"];
	
        array_push($response["exams_topics_questions"], $exam_topic_question);
    }
	
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No products found";

    // echo no users JSON
    echo json_encode($response);
}
?>
