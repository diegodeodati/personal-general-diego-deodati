// manage slider effect
var slideCurrent = 1;
var totalSlides = 0;

var handlerNext = function() {
	
	slideCurrent++;
	
//	$(".slidePortal").hide();
//	$("#slide"+slideCurrent).show();
//	$("#slide"+slideCurrent).scrollTo(500, {easing:'linear'});
	
	$(".slidePortal").addClass("slidePortalHidden down");
	$("#slide"+slideCurrent).removeClass("slidePortalHidden down");
	$("#slide"+slideCurrent).addClass("up");
	
	enableDisablenav(slideCurrent, totalSlides);
};

var handlerPrev = function() {
	
	slideCurrent--;
	
//	$(".slidePortal").hide();
//	$("#slide"+slideCurrent).show();
//	$("#slide"+slideCurrent).scrollTo(500, {easing:'linear'});
	
	$(".slidePortal").addClass("slidePortalHidden down");
	$("#slide"+slideCurrent).removeClass("slidePortalHidden down");
	$("#slide"+slideCurrent).addClass("up");
	
	enableDisablenav(slideCurrent, totalSlides);
};
 
$(document).ready(function () {
	// Gets the number of elements with class yourClass
	totalSlides = $('.slidePortal').length;

	slideCurrent = 1;
	
//	$(".slidePortal").hide();
//	$("#slide"+slideCurrent).show();
	
	$(".slidePortal").addClass("slidePortalHidden down");
	$("#slide"+slideCurrent).removeClass("slidePortalHidden down");
	$("#slide"+slideCurrent).addClass("up");
	
	enableDisablenav(slideCurrent, totalSlides);
	
});

function enableDisablenav(slideCurrent, totalSlides) {

	if(slideCurrent == 1) {
		
		// disable slidePrevBtn
		$(".halfMoon-left a li").addClass('disabledButton');
		// unbind
		$("#slidePrevBtn").unbind();
		
		if(slideCurrent >= totalSlides) {
			$(".halfMoon-right a li").addClass('disabledButton');
			// unbind
			$("#slideNextBtn").unbind();
		} else {
			$(".halfMoon-right a li").removeClass('disabledButton');
			// unbind
			$("#slideNextBtn").unbind();
			$("#slideNextBtn").bind('click', handlerNext);
		}
		
	} else {
	
		// last page
		if(slideCurrent >= totalSlides){
			//alert('last page');
			$(".halfMoon-right a li").addClass('disabledButton');
			// unbind
			$("#slideNextBtn").unbind();
			
			$(".halfMoon-left a li").removeClass('disabledButton');
			// bind
			$("#slidePrevBtn").unbind();
			$("#slidePrevBtn").bind('click', handlerPrev);	
		} else {
			// middle page
			$(".halfMoon-left a li").removeClass('disabledButton');
			// bind
			$("#slidePrevBtn").unbind();
			$("#slidePrevBtn").bind('click', handlerPrev);	
			
			$(".halfMoon-right a li").removeClass('disabledButton');
			// bind
			$("#slideNextBtn").unbind();
			$("#slideNextBtn").bind('click', handlerNext);
		}
	
	}
	
}
 