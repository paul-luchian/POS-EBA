$(function() {
	$(document).on('click', "#changePassword", function(){

		console.log(document.getElementById('password').value );
		console.log(document.getElementById('confirm_password').value  );
		if(document.getElementById('password').value != document.getElementById('confirm_password').value ){
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
			obj.password = document.getElementById('old-password').value ;
			obj.newpassword = document.getElementById('confirm_password').value;
			
			var _json = JSON.stringify(obj);
			console.log(_json);
			
			
			$.ajax({
				type : 'POST',
				url : "http://localhost:8080/POS-EBA-RS/services/profile",
				crossDomain : true,
				data: _json,
				contentType : "application/json; charset=utf-8",
				dataType : "text",
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
					console.log("data="+data);
					alert(" Error on request for change pasword!");
				}



			});
		}
	});
});		


$(function() {
	$(document).on('click', "#changeProfile", function(){
		
		var first =document.getElementById('firstname').value ;
		var last = document.getElementById('lastname').value ;


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
		obj.firstname = first;
		obj.lastname = last;
	
		
		var _json = JSON.stringify(obj);
		console.log(_json);
		
		
		$.ajax({
			type : 'PATCH',
			url : "http://localhost:8080/POS-EBA-RS/services/profile",
			crossDomain : true,
			data: _json,
			contentType : "application/json; charset=utf-8",
			dataType : "text",
			headers: {
				"Authorization": cookie
			},
			success : function(data) {
				
				console.log("Response update user profile: " + data);
				if (data == "ok") {
					
					alert(" Profile updated successfully! ");
					window.location.reload(true);
					
				} else {
					alert(" Something went wrong! Profile detailes were not updated! ")
				}
			},
			error : function(data) {
				alert(" Error on request for change profile!");
			}



		});
	
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
					document.getElementById('firstname').value  = obj.firstname;
					document.getElementById('lastname').value = obj.lastname;
					document.getElementById('username').value = obj.username;
					document.getElementById('email').value = obj.email;
					
					
				},

				error : function(data) {
					alert("System error!");
				}
			});
		});





