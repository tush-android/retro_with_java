
<?php
header('Content-Type: application/json');
include 'connection.php';

$response=array();
$sql="select * from emp_info";
$result=mysqli_query($con,$sql);
if($result){
	while($row=mysqli_fetch_assoc($result)){
		$response[] = $row;
	}
}
echo json_encode($response);
?>