//////////////////////////////////LISTE/////////////////////////////////////////
jQuery(document).ready(function($) {
	$("li.dropdown").hover(function() {
		var id = $(this).attr("rel");
		$(this).toggleClass("active");
		$("ul.dropdown-" + id).toggle("fade", 150);
                $(".projectFactsWrap").toggle("fade", 150);
                $(".grid").toggle("fade", 150);
                $(".sectiontitle").toggle("fade", 150);
	});
});
//////////////////////////////STATISTIKA////////////////////////////////////////
	$.fn.jQuerySimpleCounter = function( options ) {
	    var settings = $.extend({
	        start:  0,
	        end:    100,
	        easing: 'swing',
	        duration: 400,
	        complete: ''
	    }, options );

	    var thisElement = $(this);

	    $({count: settings.start}).animate({count: settings.end}, {
			duration: settings.duration,
			easing: settings.easing,
			step: function() {
				var mathCount = Math.ceil(this.count);
				thisElement.text(mathCount);
			},
			complete: settings.complete
		});
	};

var id1  = $('#number1').text();
var id2  = $('#number2').text();
var id3  = $('#number3').text();
var id4  = $('#number4').text();
$('#number1').jQuerySimpleCounter({end: id1,duration: 3000});
$('#number2').jQuerySimpleCounter({end: id2,duration: 3000});
$('#number3').jQuerySimpleCounter({end: id3,duration: 2000});
$('#number4').jQuerySimpleCounter({end: id4,duration: 2500});
////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////POPUP//////////////////////////////////////
//ADD NEW USER
$('.openaddnewuserpopup').click(function (e) {
         e.preventDefault();
         $('.addnewuserpopup').addClass('opened');
    });
$('.closeaddnewuserpopup').click(function (e) {
         e.preventDefault();
         $('.addnewuserpopup').removeClass('opened');
    });
//EDIT USER
$('.openedituserpopup').click(function (e) {
         e.preventDefault();
         $('.edituserpopup').addClass('opened');
    });
$('.closeedituserpopup').click(function (e) {
         e.preventDefault();
         $('.edituserpopup').removeClass('opened');
    });
//DELETE USER
$('.opendeleteuserpopup').click(function (e) {
         e.preventDefault();
         $('.deleteuserpopup').addClass('opened');
    });
$('.closedeleteuserpopup').click(function (e) {
         e.preventDefault();
         $('.deleteuserpopup').removeClass('opened');
    });
//ADD NEW PRODUCT
$('.openaddnewproductpopup').click(function (e) {
         e.preventDefault();
         $('.addnewproductpopup').addClass('opened');
    });
$('.closeaddnewproductpopup').click(function (e) {
         e.preventDefault();
         $('.addnewproductpopup').removeClass('opened');
    });
//EDIT PRODUCT
$('.openeditproductpopup').click(function (e) {
         e.preventDefault();
         $('.editproductpopup').addClass('opened');
    });
$('.closeeditproductpopup').click(function (e) {
         e.preventDefault();
         $('.editproductpopup').removeClass('opened');
    });
//DELETE PRODUCT
$('.opendeleteproductpopup').click(function (e) {
         e.preventDefault();
         $('.deleteproductpopup').addClass('opened');
    });
$('.closedeleteproductpopup').click(function (e) {
         e.preventDefault();
         $('.deleteproductpopup').removeClass('opened');
    });