
function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}



$(document).ready(function(){
	console.log(  "call from profile");


	var cookie = readCookie ('here name');
	if (cookie == null){

		console.log( "\n Redirect login. No cookie found with name ");
		window.location.replace("http://localhost:8080/POS-EBA-PF/login.jsp");
	}
	else
	{

		var _json = JSON.stringify(cookie);
		// call cookie check service			
		$.ajax({
			type:'GET', 
			url:"http://localhost:8080/POS-EBA-RS/services/validation/cookie",
			//data:_json,
			//contentType:"application/json; charset=utf-8",
			dataType:"json",
			xhrFields: {
			      withCredentials: true
			   },

			success:function(data)
			{
				console.log("\n Response ok validate info.");

				$.ajax({
					type:'GET', 
					url:"http://localhost:8080/POS-EBA-RS/services/profiles/info",
					xhrFields: {
					      withCredentials: true
					   },
					   dataType:"json",
					   
					success:function(data)
					{
						console.log( "\n response get info "+ data);
						//split from data 
						
						
						var pieces = data.split (';');
						
						// data ={firstname=x; lastname=y;email=z;}
						
						
				
						var elements = args[i].split(equal);
							
						var fname = pieces[0].split ('=');
						var lname = pieces[1].split ('=');
						var user_email = pieces[2].split ('=');

						$("#firstname").val(fname[1]); 
						$("#lastname").val(lname[1]); 
						$("#email").val(user_email[1]); 


					},
					error:function(data)
					{
						console.log("\n  Error at get profile info "+JSON.stringify(data));
					}
				}); // end get info


			}, 

			error:function(data)
			{
				console.log("\n Error at validate cookie :"+JSON.stringify(data));
			}


		});

	}// end else
});// end doc.ready

