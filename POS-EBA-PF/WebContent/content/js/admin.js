$(document)
		.ready(
				function() {

					jQuery.support.cors = true;
					$
							.ajax({
								type : 'GET',
								url : "http://localhost:8080/POS-EBA-RS/services/admin",
								crossDomain : true,
								contentType : "application/json; charset=utf-8",
								dataType : "json",

								success : function(data) {
									var users = JSON.stringify(data);
									var jsonData = JSON.parse(users);
									var table = document
											.getElementById('users-table');
									table.innerHTML = '<tr> <th>ID</th><th>First Name</th><th>Last Name</th><th>Username</th><th>Email</th><th>Role</th><th>Action</th></tr>';
									for (var i = 0; i < jsonData.length; i++) {
										var row = table.insertRow(i + 1);
										var id = row.insertCell(0);
										var firstname = row.insertCell(1);
										var lastname = row.insertCell(2);
										var username = row.insertCell(3);
										var email = row.insertCell(4);
										var role = row.insertCell(5);
										id.innerHTML = jsonData[i].id;
										firstname.innerHTML = jsonData[i].firstname;
										lastname.innerHTML = jsonData[i].lastname;
										username.innerHTML = jsonData[i].username;
										email.innerHTML = jsonData[i].email;
										role.innerHTML = jsonData[i].role;
									}
								},

								error : function(data) {
									alert("System error!");
								}
							});
				})
