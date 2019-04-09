$(document).ready(function(){
	$("button#login").on("click", function()
	{
		var email=$('input[name="email"]').val();
		var password = $('input[name="password"]').var();
		var arg = new Object();
		arg.name=email;
		arg.value=password;
		console.log(arg);
		//location.assign("localhost:8080/POS-EBA-RS/services/validator/profile");
	})

})

