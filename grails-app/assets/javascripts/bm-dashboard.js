//= require bm-flot-chart.js
//= require support_ticket.js
//= require bm-widget-dashboard.js

$('document').ready(function(){
   $('.clickable').on('click', function(){
      var $icon = $(this).find('.fa-angle-down, .fa-angle-up');
       $icon.toggleClass('fa-angle-down');
       $icon.toggleClass('fa-angle-up');
   });
});
