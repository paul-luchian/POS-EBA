$(document).ready(function() {
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
		console.log("submit clicked");
		jQuery.support.cors = true;
		$.ajax({
			type : 'GET',
			url : "http://localhost:8080/POS-EBA-RS/services/signin",
			crossDomain : true,
			contentType : "text/html; charset=utf-8",
			dataType : "text",

			success : function(data) {
			},

			error : function(data) {
				console.log("Data=" + data);
			}
		});
	})
});