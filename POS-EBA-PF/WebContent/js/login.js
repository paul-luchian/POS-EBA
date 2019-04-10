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
		console.log("login_json=" + json);
		jQuery.support.cors = true;
		$.ajax({
			type : 'POST',
			url : "http://localhost:8080/POS-EBA-RS/services/validation/login",
			crossDomain : true,
			data : json,
			contentType : "application/json; charset=utf-8",
			dataType : "json",

			success : function(data) {
				// TODO
				// cookie is in data {rCode,cookie:{}}
				// get the cookie and set it
				// after that redirect to profile
				console.log("response post" + JSON.stringify(data));
				/*
				 * window.location
				 * .replace("http://localhost:8080/POS-EBA-PF/profile.jsp");
				 */
			},
			error : function(data) {
				console.log("error" + JSON.stringify(data));
			}
		});
		// location.assign("localhost:8080/POS-EBA-RS/services/validator/profile");
	})

})
