<?php 

$host = 'localhost';
$user = 'root';
$pwd = '';
$db = 'timetable';

$conn = mysqli_connect($host, $user, $pwd, $db);

if(!$conn){
	die("ERROR in connection: " . mysqli_connect_error());
}

$response = array();
$sql_query = "select * from tasks";
$result = mysqli_query($conn, $sql_query);

if(mysqli_num_rows($result) > 0){
	
	while($row = mysqli_fetch_assoc($result)){
		array_push($response, $row);
	}
	}
	else{
		$response['success'] = 0;
		$response['message'] = 'No data';
	}
echo json_encode($response);
mysqli_close($conn);
 ?>