$(document).ready(function() {
	$("button#login").on("click", function(e) {
		e.preventDefault();
		var email = $('input[name="email"]').val();
		var password = $('input[name="password"]').val();
		var arg = {
			"email" : email,
			"password" : password
		}
		var json = JSON.stringify(arg);
	
		jQuery.support.cors = true;
		$.ajax({
			type : 'POST',
			url : "http://localhost:8080/POS-EBA-RS/services/login",
			crossDomain : true,
			data : json,
			contentType : "application/json; charset=utf-8",
			dataType : "json",

			success : function(data) {
					if(data == "ok")
					{
						$.get("http://localhost:8080/POS-EBA-RS/services/profile");
					}
					else
						{
							$("#login-error").css("display", "block");
						}
				},
		
			error:function(data)
			{
				alert("System error!");	
			}
		});
		
	})

})
