
<?php
	include 'connection.php';
	$id=$_POST['id'];
	$sql="delete from emp_info where id='$id'";
	if($con->query($sql) === TRUE){
		echo json_encode(["success" => true,"message" => "User Deleted Successfully"]);
	}
	else{
		echo json_encode(["success" => false, "message" => "Error: ". $con->error]);
	}
?>