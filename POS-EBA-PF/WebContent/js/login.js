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
			url : "http://localhost:8080/POS-EBA-RS/services/login",
			crossDomain : true,
			data : json,
			contentType : "application/json; charset=utf-8",
			dataType : "json",

			success : function(data) {
				// TODO
				// cookie is in data {rCode,cookie:{}}
				// get the cookie and set it
				// after that redirect to profile
				var obj = JSON.parse(JSON.stringify(data));
				var c = obj.cookie;
				var separator = ';';
				var equal = '=';
				var args = c.split(separator);

				var i = 0;
				var expiration_date = 0;

				var fields = {};
				for (i = 0; i < args.length; i++) {

					var elements = args[i].split(equal);
					if (elements[0] !== "Expiration-date") {
						fields[elements[0]] = elements[1];
					} else {
						expiration_date = elements[1];
					}

				}

				var data = JSON.stringify(fields);
				var date = new Date();
				date.setTime(expiration_date);
				$.cookie("cookie1", $.param(data), {
					expires : date
				});
				if($.cookie("cookie1") !== null){
					console.log("cookie="+ JSON.stringify($.cookie("cookie1")));
					window.location.replace("http://localhost:8080/POS-EBA-PF/profile.jsp");
				}
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
