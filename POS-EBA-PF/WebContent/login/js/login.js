$(document).ready(function() {

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
	var xhr = $.ajax({
		type : 'POST',
		url : "http://localhost:8080/POS-EBA-RSDB/server1/tokens/check",
		crossDomain : true,
		contentType : "application/json; charset=utf-8",
		dataType : "text",
		headers: {
			"Authorization": cookie
		},
		success : function(data, response) {

			data = data.split(':');
			result  = data[1].replace('}', '');
			if(result == 'true')
			{
				var new_href = 'http://localhost:8080/POS-EBA-PF/profile';
				if(window.location.href != new_href)
				{
					window.location.href=new_href;
				}

			}
		},

		error : function(data) {
			console.log("Error=" + data);
		}
	});

	
	function setCookie(cname, cvalue) {
		var d = new Date();
		console.log("date="+d);
		d.setTime(d.getTime() + (30*60*1000));
		var expires = "expires="+ d.toUTCString();
		document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
	}


	$("button#login").on("click", function(e) {
		e.preventDefault();
		var email = $('input[name="email"]').val();
		var password = $('input[name="password"]').val();

		var json = {
				"userName" : email,
				"password" : password
		};

		var _json = JSON.stringify(json);

		console.log("json for register="+_json);



		jQuery.support.cors = true;

//		window.getCookie = function(name) {
//			var match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
//			if (match) return match[2];
//		}
//		var cookie = window.getCookie("access_token")
//		if(cookie == null || cookie == undefined)
//		{
//			cookie = "Bearier";
//		}
//		else
//		{
//			cookie = "Bearier" + cookie;
//		}

		var xhr = $.ajax({
			type : 'POST',
			url : "http://localhost:8080/POS-EBA-RS/services/login",
			crossDomain : true,
			data : _json,
			contentType : "application/json; charset=utf-8",
			dataType : "text",
			headers: {
				"Authorization": cookie
			},
			success: function(data, response) {

				console.log("response="+xhr.getResponseHeader('Authorization'));
				console.log("status="+xhr.status);
				var header
				status=xhr.status;
//				var json = JSON.parse(data);
//				var status = json.status;
//				var headers = JSON.stringify(json.headers['Authorization']);
					
				if(status ==200){
					console.log(status);
					console.log("json "+ json);
					var headers = xhr.getResponseHeader('Authorization');

					console.log("headers="+headers);

					var array = headers.split(" ");
					console.log(array);
					var token = array[1];
					console.log("token="+token);
//					token = token.slice(0,-2);				

					setCookie("access_token",token);
					//document.cookie='access_token='+token ;

					window.location.replace("http://localhost:8080/POS-EBA-PF/profile");
				}
				else
				{
					alert("Username or password invalid");
				}
			},

			error:function(data)
			{
				console.log(data);
				console.log("\n Eroare "+  JSON.stringify(data));	
				console.log("\ntoken nesetat");
			}
		});



	});


});
