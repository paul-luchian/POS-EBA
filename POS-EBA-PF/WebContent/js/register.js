$(document).ready(function() {
	
	function validateEmail(email) {
	    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    return re.test(String(email).toLowerCase());
	}
	
    $("button#register").on("click", function(e) {
            e.preventDefault();
            var fname = $('input[name="firstname"]').val();
            var lname = $('input[name="lastname"]').val();
            var em = $('input[name="register-email"]').val();
            var pswd = $('input[name="register-password"]').val();
            var user = $('input[name="register-username"]').val();
            var confirm_psw = $('input[name="confirm_password"]').val();
            
            if(validateEmail(em)){
            	if (pswd !== confirm_psw) {
                    $("#password-error").css("display", "block");
                } else {
                    var json = {
                        "firstname": fname,
                        "lastname": lname,
                        "userName": user,
                        "password": pswd,
                        "email": em
                    };
                    var _json = JSON.stringify(json);
                    console.log(_json);

                    jQuery.support.cors = true;
                    $.ajax({
                        type: 'POST',
                        url: "http://localhost:8080/POS-EBA-RS/services/register",
                        crossDomain: true,
                        data: _json,
                        contentType: "application/json; charset=utf-8",
                        dataType: "text",

                        success: function(data) {
                            console.log("response post" + data);
                            if (data == "ok") {
                                alert("Inregistrarea a fost efectuata cu succes! Pentru a continua este negesara intrarea in cont.");
                            } else {
                                //						/*error= username used*/
                                //						$("#username-error").css("display", "block");
                                //						$("#email-error").css("display", "block");
                                alert("Operatiunea a fost intrerupta! Va rugam incercati mai tarziu!")
                            }
                        },
                        error: function(error) {
                            console.log(error);
                            alert("System error!");
                            //location.reload();

                        }
                    });
                }
            }
            else
            	{
            	$("#email-error").css("display", "block");
            	}
            
    });
});