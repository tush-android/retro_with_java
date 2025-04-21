

<?php
	header('Content-Type:application/json');
	include 'connection.php';
	$id=$_POST['id'];
	$name=$_POST['name'];
	$email=$_POST['email'];
	$mobile=$_POST['mob'];
	$sql="update emp_info set ename='$name',eemail='$email',emob='$mobile' where id='$id'";
	if($con->query($sql) === TRUE){
		echo json_encode(["success" => true,"message" => "Note Updated Successfully"]);
	}
	else{
		echo json_encode(["success" => false,"message" => "Error: ".$con->error]);
	}
?>