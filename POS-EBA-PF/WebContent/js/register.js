$(document).ready(function()
{
	$("button#register").on("click", function(e)
			{
				e.preventDefault();
				var fname = $('input[name="firstname"]').val();
				var lname = $('input[name="lastname"]').val();
				var em = $('input[name="register-email"]').val();
				var pswd = $('input[name="register-password"]').val();
				var user = $('input[name="register-username"]').val();

				var json = {"firstName":fname, "lastName":lname, "username":user,"mail":em, "password":pswd};
				var _json = JSON.stringify(json);
				console.log("json="+JSON.stringify(json));
				
				jQuery.support.cors = true;
				$.ajax({
					type:'POST', 
					url:"http://localhost:8080/POS-EBA-RS/services/validation/register",
					crossDomain: true,
					data:_json,
					contentType:"application/json; charset=utf-8",
					dataType:"json",
					
					success:function(data)
					{
							console.log( "response post"+ data);
						
							window.location.replace("http://localhost:8080/POS-EBA-PF/login.jsp");
	
					},
					error:function(data)
					{
						console.log("error"+JSON.stringify(data));
					}
				});
				console.log( "after ajax");
			});

});