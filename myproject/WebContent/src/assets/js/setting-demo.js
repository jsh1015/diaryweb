"use strict";

// Setting Color
$(window).resize(function() {
	$(window).width(); 
});


$('.changeMainHeaderColor').on('click', function(){
	if($(this).attr('data-color') == 'default'){
		$('.sidebar').removeAttr('data-background-color');
	} else {
		$('.sidebar').attr('data-background-color', $(this).attr('data-color'));
		selcolor = $(this).attr('data-color');
		console.log(selcolor);
	}

	$(this).parent().find('.changeMainHeaderColor').removeClass("selected");
	$(this).addClass("selected");
	layoutsColors();
});

function layoutsColors(){
	if($('.sidebar').is('[data-background-color]')) { 
		$('html').addClass('sidebar-color');
	} else {
		$('html').removeClass('sidebar-color');
	}

	if($('.sidebar').is('[data-image]')) {
		$('.sidebar').append("<div class='sidebar-background'></div>");
		$('.sidebar').css('background-image', 'url("' + $('.sidebar').attr('data-image') + '")');
	} else {
		$(this).remove('.sidebar-background');
		$('.sidebar').css('background-image', '');
	}
}

$('.changeBackgroundColor').on('click', function(){
	$('body').removeAttr('data-background-color');
	$('body').attr('data-background-color', $(this).attr('data-color'));
	$(this).parent().find('.changeBackgroundColor').removeClass("selected");
	$(this).addClass("selected");
});

var toggle_customSidebar = false,
custom_open = 0;

if(!toggle_customSidebar) {
	var toggle = $('.custom-template .custom-toggle');

	toggle.on('click', (function(){
		if (custom_open == 1){
			$('.custom-template').removeClass('open');
			toggle.removeClass('toggled');
			custom_open = 0;
		}  else {
			$('.custom-template').addClass('open');
			toggle.addClass('toggled');
			custom_open = 1;
		}
	})
	);
	toggle_customSidebar = true;
}