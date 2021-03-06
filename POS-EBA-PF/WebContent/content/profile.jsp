<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="content/css/app.css" rel="stylesheet" type="text/css">
<link href="content/css/tabmenu.css" rel="stylesheet" type="text/css">
<title>Profile</title>


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="content/js/profile.js"></script>
<script src="content/js/logout.js"></script>
<script>
	$(document).ready(function() {

		$("#btnFrontend").click(function() {
			$("#firstname").attr("readonly", false);
			$("#lastname").attr("readonly", false);
			/*$("#email").attr("readonly", false);
			$("#username").attr("readonly", false);*/
			$("#password").attr("readonly", false);

			$("#old-passwd").attr("style", "display:flex");
			$("#new-passwd").attr("style", "display:flex");
			$("#confirmation").attr("style", "display:flex");
			$("#change-passwd").attr("style", "display:flex");

			$("#btnFrontend").attr("style", "display:none");
			$("#changeProfile").attr("style", "display:block");
			$("#passwd-label").text("New password");

		});
	});
</script>

</head>
<body>
	<div class="container">
		<input id="tab3" type="radio" name="tabs" checked> <label
			for="tab3">User Profile</label>
		<button id="logout" style="float: right">Logout</button>

		<section id="profile-container">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-md-8">
						<div class="card">
							<div class="card-header"></div>

							<div class="card-body">
						

									<div class="form-group row">
										<label for="name"
											class="col-md-4 col-form-label text-md-right">First
											Name</label>

										<div class="col-md-6">
											<input id="firstname" type="text" class="form-control"
												name="firstname" required autofocus readonly>
										</div>
									</div>

									<div class="form-group row">
										<label for="lastname"
											class="col-md-4 col-form-label text-md-right">Last
											Name</label>

										<div class="col-md-6">
											<input id="lastname" type="text" class="form-control"
												name="lastname" required autofocus readonly>
										</div>
									</div>

									<div class="form-group row">
										<label for="username"
											class="col-md-4 col-form-label text-md-right">Username</label>

										<div class="col-md-6">
											<input id="username" type="text" class="form-control"
												name="username" required autofocus readonly>
										</div>

									</div>

									<div class="form-group row">
										<label for="email"
											class="col-md-4 col-form-label text-md-right">Email
											address</label>

										<div class="col-md-6">
											<input id="email" type="email" class="form-control"
												name="email" required autofocus readonly>
										</div>

									</div>

								<div class="form-group row mb-0">
									<div class="col-md-8 offset-md-4">
										<button type="submit" id="changeProfile" class="btn btn-primary"
											style="display: none;">Change profile details</button>
									</div>
								</div>

							

								<div class="form-group row mb-0">
									<div class="col-md-8 offset-md-4">
										<button id="btnFrontend" class="btn btn-primary">Edit
											profile</button>
									</div>
								</div>
							

								<div class="form-group row" id="old-passwd"
									style="display: none;">
									<label for="old-password"
										class="col-md-4 col-form-label text-md-right"> Old
										Password</label>

									<div class="col-md-6">
										<input id="old-password" type="password" class="form-control"
											name="old-password" required>
									</div>
								</div>

								<div class="form-group row" id="new-passwd"
									style="display: none;">
									<label for="password"
										class="col-md-4 col-form-label text-md-right"
										id="passwd-label"> Password</label>

									<div class="col-md-6">
										<input id="password" type="password" class="form-control"
											name="password" required readonly>
									</div>
								</div>

								<div class="form-group row" id="confirmation"
									style="display: none;">
									<label for="conf_password"
										class="col-md-4 col-form-label text-md-right ">Confirm
										Password</label>

									<div class="col-md-6">
										<input id="confirm_password" type="password"
											class="form-control" name="confirm_password" required>
									</div>
								</div>

								<div class="form-group row mb-0" id="change-passwd"
									style="display: none;">
									<div class="col-md-8 offset-md-4">
										<button type="submit" id="changePassword"
											class="btn btn-primary">Change password</button>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>