<!DOCTYPE html>
<html>
<head>
<title>Forgot Password</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
	</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
.semere {
	display: block;
	margin-left: auto;
	margin-right: auto;
}
</style>



</head>
<body>
	<header style="background-color: #03a9f4; height: 30px;">
	  Forgot password!
	   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 </header>
	<div  class="container">
	     <hr style="border-top: 5px solid rgba(103,58,183,1);"/>
		<img id="studentImage"  src="img/student.png" class="semere"  style="height: 200px;">
		
		<form name="authform"  action="fPassword" method="post">
		<div  class="semere"   style="width: 50%">
		   <hr/>
		   <span  id="emessage"  style="color:blue;font-size: 16px;font-weight: bold;"></span>
		 			<br/>
		 			 
		            <label for="usernameEmail">Enter Username or Email</label>
		            <input type="text" name="usernameEmail" class="form-control" id="usernameEmail">
		              <br/>
		             <br/>
		<button type="submit" class="btn btn-primary">Get Password</button>
		<hr/>
		<span  id="message"  style="color:red;font-size: 16px;font-weight: bold;">
		 -----> ${passmsg} <----- 
		 </span>
		</div>
		 
		 
		</form>
	</div>

</body>
</html>