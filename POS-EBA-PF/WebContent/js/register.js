$(document).ready(function()
{
	$("button#register").on("click", function(e)
			{
				e.preventDefault();
				var fname = $('input[name="firstname"]').val();
				var lname = $('input[name="lastname"]').val();
				var em = $('input[name="register-email"]').val();
				var pswd = $('input[name="register-password"]').val();
				
				$.ajax({
					type:'POST', 
					dataType:'text',
					url:"register.jsp",
					data:{"firstname":firstname, "lastname":lastname, "email":em},
					success:function(data)
					{						
							console.log(data);
	
					}
				});
			});

});