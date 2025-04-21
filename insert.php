<?php
	include 'connection.php';
	$name=$_POST['name'];
	$email=$_POST['email'];
	$mob=$_POST['mob'];
	$sql="insert into emp_info(ename,eemail,emob) values('$name','$email','$mob')";
	$res=mysqli_query($con,$sql);
	if($res){
		echo json_encode(["success" => true,"message" => "Employee Inserted Successfully...!"]);
	}
	else
	{
		echo json_encode(["success" => false,"message" => "Erorr: "]);
	}

?>