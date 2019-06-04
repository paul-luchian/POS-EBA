$(document).ready(function() {

	function validateEmail(email) {
		var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		return re.test(String(email).toLowerCase());
	}

	window.getCookie = function(name) {
		var cookie = $.cookie(name)//match[2];
		if(cookie == null || cookie == undefined)
		{
			cookie = "Bearier";
		}

		else
		{
			cookie = "Bearier" + cookie;
		}
		return cookie;

	}
	var cookie = window.getCookie("access_token");


	jQuery.support.cors = true;

	$.ajax({
		type : 'POST',
		url : "http://localhost:8080/POS-EBA-RSDB/server1/tokens/check",
		crossDomain : true,
		contentType : "application/json; charset=utf-8",
		dataType : "text",
		headers: {
			"Authorization": cookie
		},

		success : function(data) {

			console.log("response " + JSON.stringify(data));


			data = data.split(':');
			result = data[1].replace('}', '');

			if (result == "false"){

				$("button#register").on("click", function(e) {
					e.preventDefault();
					var fname = $('input[name="firstname"]').val();
					var lname = $('input[name="lastname"]').val();
					var em = $('input[name="register-email"]').val();
					var pswd = $('input[name="register-password"]').val();
					var user = $('input[name="register-username"]').val();
					var confirm_psw = $('input[name="confirm_password"]').val();

					if(validateEmail(em)){
						if (pswd !== confirm_psw) {
							$("#password-error").css("display", "block");
						} else {
							var json = {
									"firstname": fname,
									"lastname": lname,
									"userName": user,
									"password": pswd,
									"email": em
							};
							var _json = JSON.stringify(json);
							console.log(_json);

							jQuery.support.cors = true;
							$.ajax({
								type: 'POST',
								url: "http://localhost:8080/POS-EBA-RS/services/register",
								crossDomain: true,
								data: _json,
								contentType: "application/json; charset=utf-8",
								dataType: "text",
								headers: {
									"Authorization": cookie
								},

								success: function(data) {
									console.log("response post" + data);
									if (data == "ok") {
										alert("Registration with succes! Please login to continue!");
										if (window.location.href.indexOf('reload')==-1) {
											window.location.reload(true);
										}
										
									} else {
										//						/*error= username used*/
										//						$("#username-error").css("display", "block");
										//						$("#email-error").css("display", "block");
										alert("Something went wrong! Plase try again later!")
									}
								},
								error: function(error) {
									console.log(error);
									alert("System error!");
									//location.reload();

								}
							});
						}
						
						
					}
					else
					{
						$("#email-error").css("display", "block");
					}

				});


			} //end result false
			else
			{
				//alert("You are already logged in." );
				window.location.replace("http://localhost:8080/POS-EBA-PF/content/profile.jsp");
			}

		},

		error : function(data) {
			alert("Error on check tokens!");
		}
	});


});