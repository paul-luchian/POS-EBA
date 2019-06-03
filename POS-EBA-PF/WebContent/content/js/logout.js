$(function() {
	
	var delete_cookie = function(name) {
		$.cookie(name, null, { path: '/' });
//		console.log("cookie name to delete="+name);
//	    document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
	};
	
	$(document).on('click', "#logout", function() {

		jQuery.support.cors = true;

		window.getCookie = function(name) {
			var match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
			if (match) return match[2];
		}
		var cookie = window.getCookie("access_token")
		if(cookie == null || cookie == undefined)
		{
			cookie = "Bearier";
		}
		else
		{
			cookie = "Bearier" + cookie;
		}
		console.log("cookie="+cookie);
		$.ajax({
			type : 'POST',
			url : "http://localhost:8080/POS-EBA-RS/services/logout",
			crossDomain : true,
			contentType : "application/json; charset=utf-8",
			dataType : "text",
			headers: {
				"Authorization": cookie
			},
			success: function(data) {
				if(data=="ok")
				{
					console.log("token deleted from db");
					var name = "access_token";
					$.removeCookie(name, { path: '/' });
					window.location.replace("http://localhost:8080/POS-EBA-PF/login");
				}
				else
				{
					alert("Something went wrong! Please try again later!");
				}
			}
			,
			error:function(data)
			{
				console.log(data);
				console.log("\n Eroare "+  JSON.stringify(data));	
				console.log("\ntoken nesetat");
			}
		});
	});
});