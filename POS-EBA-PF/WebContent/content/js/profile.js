$(function() {
	$(document).on('click', "#changePassword", function(){

		console.log(document.forms["profile-form"]['password'].value );
		console.log(document.forms["profile-form"]['confirm_password'].value );
		if(document.forms["profile-form"]['password'].value != document.forms["profile-form"]['confirm_password'].value){
			alert("Sorry ! Password are not the same!");

		}else{



			jQuery.support.cors = true;

			window.getCookie = function(name) {
				var match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
				if (match) return match[2];
			}
			var cookie = window.getCookie("access_token");
			if(cookie == null || cookie == undefined)
			{
				cookie = "Bearier"; console.log("cookie nesetat in profile");
			}
			else
			{
				cookie = "Bearier" + cookie; console.log("cookie setat in profile");
			}

			
			var obj = new Object();
			obj.password = document.forms['profile-form']['old-password'].value;
			obj.newpassword = document.forms['profile-form']['confirm_password'].value;
			var _json = JSON.stringify(obj);
			console.log(_json);
			
			
			$.ajax({
				type : 'POST',
				url : "http://localhost:8080/POS-EBA-RS/services/profile",
				crossDomain : true,
				data: _json,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				headers: {
					"Authorization": cookie
				},
				success : function(data) {
					
					console.log("response update user password: " + data);
					if (data == "ok") {
						
						alert(" Password updated successfully! ");
						window.location.reload(true);
						
					} else {
						alert(" Something went wrong! Password was not updated! ")
					}
				},
				error : function(data) {
					alert(" Error on change pasword!");
				}



			});
		}
	});
});

$(document)
.ready(
		function() {

			jQuery.support.cors = true;

			window.getCookie = function(name) {
				var match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
				if (match) return match[2];
			}
			var cookie = window.getCookie("access_token");
			if(cookie == null || cookie == undefined)
			{
				cookie = "Bearier"; console.log("cookie nesetat in profile");
			}
			else
			{
				cookie = "Bearier" + cookie; console.log("cookie setat in profile");
			}



			$.ajax({
				type : 'GET',
				url : "http://localhost:8080/POS-EBA-RS/services/profile",
				crossDomain : true,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				headers: {
					"Authorization": cookie
				},
				success : function(data) {

					console.log("response " +JSON.stringify(data));

					var resp = JSON.stringify(data);
					var obj = JSON.parse(resp);
					document.forms['profile-form']['firstname'].value = obj.firstname;
					document.forms['profile-form']['lastname'].value = obj.lastname;
					document.forms['profile-form']['username'].value = obj.username;
					document.forms['profile-form']['email'].value = obj.email;
				},

				error : function(data) {
					alert("System error!");
				}
			});
		});





