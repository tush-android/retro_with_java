<?php
	$con=new mysqli('localhost','root','','java_retro');
	if($con->connect_error){
		die(json_encode(["status" => "error", "message" => "Connection failed: " . $conn->connect_error]));
	}
?>