$(function() {
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
				var new_href = 'http://localhost:8080/POS-EBA-PF/admin';
				if(window.location.href != new_href)
				{
					window.location.href=new_href;
				}

			}
			else
			{
				window.location.href='http://localhost:8080/POS-EBA-PF/index';
			}


		},

		error : function(data) {
			console.log("Error=" + data);
		}
	});
	$(document).on('click', "#delete", function() {
		var button = document.getElementById("delete");
		var table = document.getElementById('users-table');
		var row_id = $(this).data("attribute");

		var header = table.rows[0].cells;
		console.log(row_id);
		var info = table.rows[row_id].cells;

		var obj = new Object();
		for (var i = 0; i < info.length - 1; i++) {
			obj[header[i].innerHTML.toLowerCase()] = info[i].innerHTML;
		}
		var jsonString = JSON.stringify(obj);
		console.log("id="+ obj.id);
		jQuery.support.cors = true;
		$.ajax({
			type: 'DELETE',
			url: "http://localhost:8080/POS-EBA-RS/services/admin/"+obj.id,
			crossDomain: true,
			contentType: "application/json; charset=utf-8",
			dataType: "text",
			headers: {
				"Authorization": cookie
			},
			success: function(data) {
				console.log("response post" + data);
				if (data == "ok") {
					alert("Deleted user with succes");
					window.location.reload(true);
				} else {
					alert("Something went wrong!")
				}
			},
			error: function(error) {
				console.log(error);
				alert("System error!");
				//location.reload();

			}
		});

	});

	$(document).on('click', ".formBtn", function() {
		var obj = new Object();
		obj.id = document.forms['user-form']['id'].value
		obj.firstname = document.forms['user-form']['firstname'].value;
		obj.lastname = document.forms['user-form']['lastname'].value;
		obj.username = document.forms['user-form']['username'].value;
		obj.email = document.forms['user-form']['email'].value ;
		obj.role = document.forms['user-form']['role'].value
		var _json = JSON.stringify(obj);
		console.log(_json);

		jQuery.support.cors = true;
		$.ajax({
			type: 'POST',
			url: "http://localhost:8080/POS-EBA-RS/services/admin",
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

					alert("Updated user with succes");
					window.location.reload(true);

				} else {
					alert("Something went wrong!")
				}
			},
			error: function(error) {
				console.log(error);
				alert("System error!");
				//location.reload();

			}
		});
	});

	$(document).on('click', "#edit", function() {
		var button = document.getElementById("edit");
		var table = document.getElementById('users-table');
		var row_id = $(this).data("attribute");

		var header = table.rows[0].cells;
		console.log(row_id);
		var info = table.rows[row_id].cells;

		var obj = new Object();
		for (var i = 0; i < info.length - 1; i++) {
			obj[header[i].innerHTML.toLowerCase()] = info[i].innerHTML;
		}
		var jsonString = JSON.stringify(obj);
		openWindow(jsonString);
		$('#editForm').fadeToggle();
	});

	$(document).mouseup(function(e) {
		var container = $("#editForm");

		if (!container.is(e.target) // if the target of the click isn't the
				// container...
				&& container.has(e.target).length === 0) // ... nor a
			// descendant of the
			// container
		{
			container.fadeOut();
		}
	});

});

function openWindow(user) {
	var obj = JSON.parse(user);
//	$('#editForm').fadeToggle();
	document.forms['user-form']['id'].value = obj.id;
	document.forms['user-form']['firstname'].value = obj.firstname;
	document.forms['user-form']['lastname'].value = obj.lastname;
	document.forms['user-form']['username'].value = obj.username;
	document.forms['user-form']['email'].value = obj.email;
	document.forms['user-form']['role'].value = obj.role;
	$(document).mouseup(function(e) {
		var container = $("#editForm");

		if (!container.is(e.target) // if the target of the click isn't the
				// container...
				&& container.has(e.target).length === 0) // ... nor a
			// descendant of the
			// container
		{
			container.fadeOut();
		}
	});
}

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
	$
	.ajax({
		type : 'GET',
		url : "http://localhost:8080/POS-EBA-RS/services/admin",
		crossDomain : true,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		headers: {
			"Authorization": cookie
		},
		success : function(data) {
			var users = JSON.stringify(data);
			var jsonData = JSON.parse(users);
			var table = document
			.getElementById('users-table');
			table.innerHTML = '<tr> <th>ID</th><th>Firstname</th><th>Lastname</th><th>Username</th><th>Email</th><th>Role</th><th>Action</th></tr>';
			for (var i = 0; i < jsonData.length; i++) {
				var row = table.insertRow(i + 1);
				var id = row.insertCell(0);
				var firstname = row.insertCell(1);
				var lastname = row.insertCell(2);
				var username = row.insertCell(3);
				var email = row.insertCell(4);
				var role = row.insertCell(5);
				var action = row.insertCell(6);
				id.innerHTML = jsonData[i].id;
				firstname.innerHTML = jsonData[i].firstname;
				lastname.innerHTML = jsonData[i].lastname;
				username.innerHTML = jsonData[i].username;
				email.innerHTML = jsonData[i].email;
				role.innerHTML = jsonData[i].role;
				var row_id = i + 1;
				action.innerHTML = '<button id="edit" data-attribute = '
					+ row_id
					+ '>Edit</button><button id="delete" data-attribute = '
					+ row_id + '>Delete</button>';
			}
		},

		error : function(data) {
			alert("System error!");
		}
	});

})
