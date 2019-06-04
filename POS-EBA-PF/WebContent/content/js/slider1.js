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
				$("#signin").html('View Profile');
				$("#signin").attr("href", "http://localhost:8080/POS-EBA-PF/profile");
			}
			else
			{
				$("#signin").html('Login/Register');
				$("#signin").attr("href", "http://localhost:8080/POS-EBA-PF/login");
			}


		},

		error : function(data) {
			console.log("Error=" + data);
		}
	});
	var slideIndex1 = 1;
	var slides = document.getElementsByClassName("mySlides");
	var dots = document.getElementsByClassName("dot1");

	showSlides1(slideIndex1);

	function plusSlides1(n) {
		showSlides1(slideIndex1 += n);
	}

	function currentSlide1(n) {
		slideIndex1 = n;
		showSlides1(slideIndex1);
	}

	function showSlides1(n) {
		var i;
		if (slides.length > 0 && dots.length > 0) {
			if (n > slides.length) {
				slideIndex1 = 1
			}
			if (n < 1) {
				slideIndex1 = slides.length
			}
			for (i = 0; i < slides.length; i++) {
				slides[i].style.display = "none";
			}
			for (i = 0; i < dots.length; i++) {
				dots[i].className = dots[i].className.replace(" active", "");
			}
			slides[slideIndex1 - 1].style.display = "block";
			dots[slideIndex1 - 1].className += " active";
		}
		//setTimeout(showSlides1, 10000);
	}

	$(".prev").on("click", function() {
		plusSlides1(-1);
	});
	$(".next").on("click", function() {
		plusSlides1(1);
	});
	$("#dot_1").on("click", function() {
		currentSlide1(1);
	});
	$("#dot_2").on("click", function() {
		currentSlide1(2);
	});

	$("#dot_3").on("click", function() {
		currentSlide1(3);
	});
	$("#signin").on("click", function() {
		window.location.replace($(this).attr('href'));
	})
});