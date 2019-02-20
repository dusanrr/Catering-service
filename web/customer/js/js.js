/* particlesJS('dom-id', params);
/* @dom-id : set the html tag id [string, optional, default value : particles-js]
/* @params: set the params [object, optional, default values : check particles.js] */

$(document).ready(function(){
  
  $("header .menu #start").click(function(){
     $('a').removeClass("activated");
    $('#start').addClass("activated");
    
  });
  
   $(".menu li:nth-child(1) a").click(function(){
     		     		
     		
				 $('a').removeClass("activated");
         $(".menu li:nth-child(1) a").addClass("activated");
         
   });
   $(".menu li:nth-child(2) a").click(function(){
     		     		
     		
				 $('a').removeClass("activated");
         $(".menu li:nth-child(2) a").addClass("activated");
         
   });
   $(".menu li:nth-child(3) a").click(function(){
     		     		
     		
				 $('a').removeClass("activated");
         $(".menu li:nth-child(3) a").addClass("activated");
         
   });
   $(".menu li:nth-child(4) a").click(function(){
     		     		
     		
				 $('a').removeClass("activated");
         $(".menu li:nth-child(4) a").addClass("activated");
         
   });
   $(".menu li:nth-child(5) a").click(function(){
     		     		
     		
				 $('a').removeClass("activated");
         $(".menu li:nth-child(5) a").addClass("activated");
         
   });
  $(".menu li:nth-child(6) a").click(function(){
     		     		
     		
				 $('a').removeClass("activated");
         $(".menu li:nth-child(6) a").addClass("activated");
         
   });
   
  
  var screen = $(window).width();
  if(screen > 600){
    /* config dom id (optional) + config particles params */
particlesJS('particles-js', {
  particles: {
    color: '#fff',
    color_random: false,
    shape: 'circle', // "circle", "edge" or "triangle"
    opacity: {
      opacity: 1,
      anim: {
        enable: true,
        speed: 1.5,
        opacity_min: 0,
        sync: false
      }
    },
    size: 4,
    size_random: true,
    nb: 150,
    line_linked: {
      enable_auto: true,
      distance: 100,
      color: '#fff',
      opacity: 1,
      width: 1,
      condensed_mode: {
        enable: false,
        rotateX: 600,
        rotateY: 600
      }
    },
    anim: {
      enable: true,
      speed: 1.5
    }
  },
  interactivity: {
    enable: true,
    mouse: {
      distance: 300
    },
    detect_on: 'canvas', // "canvas" or "window"
    mode: 'grab', // "grab" of false
    line_linked: {
      opacity: .5
    },
    events: {
      onclick: {
        enable: true,
        mode: 'push', // "push" or "remove"
        nb: 4
      },
      onresize: {
        enable: true,
        mode: 'out', // "out" or "bounce"
        density_auto: false,
        density_area: 800 // nb_particles = particles.nb * (canvas width *  canvas height / 1000) / density_area
      }
    }
  },
  /* Retina Display Support */
  retina_detect: true
});
  }
});
//DATUM

//POP UP LOGIN
$('.openlpopup').click(function (e) {
         e.preventDefault();
         $('.lpopup').addClass('opened');
    });
$('.closelpopup').click(function (e) {
         e.preventDefault();
         $('.lpopup').removeClass('opened');
    });
//POP UP REGISTER
$('.openrpopup').click(function (e) {
         e.preventDefault();
         $('.rpopup').addClass('opened');
    });
$('.closerpopup').click(function (e) {
         e.preventDefault();
         $('.rpopup').removeClass('opened');
    });    
 //POP UP CONTACT
$('.opencpopup').click(function (e) {
         e.preventDefault();
         $('.cpopup').addClass('opened');
    });
$('.closecpopup').click(function (e) {
         e.preventDefault();
         $('.cpopup').removeClass('opened');
    });    
  //BUY POP UP
$('.openbuypopup').click(function (e) {
         e.preventDefault();
         $('.buypopup').addClass('opened');
    });
$('.closebuypopup').click(function (e) {
         e.preventDefault();
         $('.buypopup').removeClass('opened');
    });    
     //REKLAMAPOP UP
$('.openrecpopup').click(function (e) {
         e.preventDefault();
         $('.recpopup').addClass('opened');
    });
$('.closerecpopup').click(function (e) {
         e.preventDefault();
         $('.recpopup').removeClass('opened');
    });    