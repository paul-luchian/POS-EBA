<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/app.css" rel="stylesheet" type="text/css">
<link href="css/tabmenu.css" rel="stylesheet" type="text/css">
<title>Register and login</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="js/register.js"></script>
<script src="js/login.js"></script>
</head>
<body>
	<div class="container">
		<input id="tab1" type="radio" name="tabs" checked> <label
			for="tab1">Login</label> <input id="tab2" type="radio" name="tabs">
		<label for="tab2">Register</label>


		<section id="login-container">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-md-8">
						<div class="card">
							<div class="card-header"></div>

							<div class="card-body">
								<form>


									<div class="form-group row">
										<label for="email"
											class="col-md-4 col-form-label text-md-right">Email
											address</label>

										<div class="col-md-6">
											<input id="email-login" type="email" class="form-control"
												name="email" required autofocus>
										</div>
									</div>

									<div class="form-group row">
										<label for="password"
											class="col-md-4 col-form-label text-md-right">Password</label>

										<div class="col-md-6">
											<input id="password-login" type="password" class="form-control"
												name="password" required>
										</div>
									</div>

									<div class="form-group row">
										<div class="col-md-6 offset-md-4">
											<div class="form-check">
												<input class="form-check-input" type="checkbox"
													name="remember" id="remember"> <label
													class="form-check-label" for="remember"> Remember
													me </label>
											</div>
										</div>
									</div>
									<label id="login-error" style="display:none">Incorrect username or password!</label>
									<div class="form-group row mb-0">
										<div class="col-md-8 offset-md-4">
											<button id="login" type="submit" class="btn btn-primary">
												Login</button>


										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<section id="register-container">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-md-8">
						<div class="card">
							<div class="card-header"></div>

							<div class="card-body">
								<form>

									<div class="form-group row">
										<label for="name"
											class="col-md-4 col-form-label text-md-right">First
											Name</label>

										<div class="col-md-6">
											<input id="firstname" type="text" class="form-control"
												name="firstname" required autofocus>
										</div>
									</div>

									<div class="form-group row">
										<label for="lastname"
											class="col-md-4 col-form-label text-md-right">Last
											Name</label>

										<div class="col-md-6">
											<input id="lastname" type="text" class="form-control"
												name="lastname" required autofocus>
										</div>
									</div>
									
									<div class="form-group row">
										<label id="username-error" style="display:none">Username already used!</label>
										<label for="username"
											class="col-md-4 col-form-label text-md-right">Username</label>

										<div class="col-md-6">
											<input id="username" type="text" class="form-control"
												name="register-username" required autofocus>
										</div>
									</div>



									<div class="form-group row">
									<label id="email-error" style="display:none">Email not valid!</label>
										<label for="email"
											class="col-md-4 col-form-label text-md-right">Email
											address</label>

										<div class="col-md-6">
											<input id="email" type="email" class="form-control"
												name="register-email" required autofocus>
										</div>
									</div>

									<div class="form-group row">
										<label for="password"
											class="col-md-4 col-form-label text-md-right">Password</label>

										<div class="col-md-6">
											<input id="password" type="password" class="form-control"
												name="register-password" required>
										</div>
									</div>

									<div class="form-group row">
										<label for="conf_password"
											class="col-md-4 col-form-label text-md-right">Confirm
											Password</label>

										<div class="col-md-6">
											<input id="confirm_password" type="password"
												class="form-control" name="confirm_password" required>
												<label id="password-error" style="display:none">Passwords are not the same!</label>
										</div>
									</div>

									<div class="form-group row mb-0">
										<div class="col-md-8 offset-md-4">
											<button type="submit" class="btn btn-primary" id="register">
												Register</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>