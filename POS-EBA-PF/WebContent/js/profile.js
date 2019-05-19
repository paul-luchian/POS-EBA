//
//function readCookie(name) {
//	var nameEQ = name + "=";
//	var ca = document.cookie.split(';');
//	for(var i=0;i < ca.length;i++) {
//		var c = ca[i];
//		while (c.charAt(0)==' ') c = c.substring(1,c.length);
//		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
//	}
//	return null;
//}
//
//
//
//$(document).ready(function(){
//	console.log(  "call from profile");
//
//
//	var cookie = readCookie ('access_token');
//	if (cookie == null){
//
//		console.log( "\n Redirect login. No cookie found with name ");
//		//window.location.replace("http://localhost:8080/POS-EBA-PF/login.jsp");
//	}
//	else
//	{
//
//
//		$.ajax({
//			type:'GET', 
//			url:"http://localhost:8080/POS-EBA-RS/services/profiles",
//			xhrFields: {
//				withCredentials: true
//			},
//			dataType:"json",
//
//			success:function(data)
//			{
//				console.log( "\n response get info "+ data);
//				
//				
//				//split from data 
//
//
//				var pieces = data.split (';');
//
//				// data ={firstname=x; lastname=y;email=z;}
//
//
//
//				var elements = args[i].split(equal);
//
//				var fname = pieces[0].split ('=');
//				var lname = pieces[1].split ('=');
//				var user_email = pieces[2].split ('=');
//
//				$("#firstname").val(fname[1]); 
//				$("#lastname").val(lname[1]); 
//				$("#email").val(user_email[1]); 
//
//
//			},
//			error:function(data)
//			{
//				console.log("\n  Error at get profile info "+JSON.stringify(data));
//			}
//		}); // end get info
//
//
//	}// end else
//});// end doc.ready
//
