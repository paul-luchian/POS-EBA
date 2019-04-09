$(document).ready(function(){
	var cookies = document.cookie;

	var separator = ';';
	var equal = '=';
	var args = cookies.split(separator);

	var i =0;
	var jsonArray = new Array();
	for(i = 0; i < args.length; i++)
	{
		var elements = args[i].split(equal);
		var arg = new Object();
		arg.name=elements[0];
		arg.value=elements[1];

		if(arg.name === "JSESSIONID")
		{
			jsonArray.push(arg);
		}

	}
	var json = JSON.stringify(jsonArray[0]);

	$.ajax({
		type:"POST",
		dataType:"text",
		url:"checkcookie.jsp",
		data:{"cookie":json},
		success:function(data)
		{
			if(data !== null)
			{
				//Am nevoie de id-ul utilizatorului din baza de date criptat
				//cookie-valid -> redirect to /services/profile/{id} ca sa rezulte o ruta: /services/profile/ddhdhsalkeuio47y4nj
				$.ajax({
					type:"GET",
					dataType:"text",
					url:"profile.jsp",
					data:{"user_id":data},
					success:function(data)
					{
						location.assign("profile.jsp");
					}
				});
			}
			else
			{
				location.assign("login.jsp");
			}
		}
	});
});