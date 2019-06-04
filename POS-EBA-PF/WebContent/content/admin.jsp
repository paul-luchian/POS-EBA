<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="content/css/admin.css" rel="stylesheet" type="text/css">
<link href="content/css/adminform.css" rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="content/js/admin.js"></script>
<title>Welcome to admin page</title>
</head>
<body>
	<h2>Users table</h2>
	<div id="editForm">
	
	  <h1>Update user!</h1>
	  
	  <form action="#" name='user-form'>
	  	ID<input name="id" placeholder="id" type="text" readonly />
	    Firstname<input name="firstname" placeholder="Firstname" type="text" required />
	    Lastname<input name="lastname" placeholder="Lastname" type="text" required />
	    Username<input name="username" placeholder="Username" type="text" readonly />
	    Email<input name="email" placeholder="Email" type="email" readonly />
	    Role<input name="role" placeholder="Role" type="text" required />
	    <input class="formBtn" type="reset" />
	  </form>
	  <button class="formBtn">Submit</button>
	</div>
	<table id="users-table"></table>
</body>
</html>