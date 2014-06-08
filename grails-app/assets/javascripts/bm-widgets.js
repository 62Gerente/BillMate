$('document').ready(function(){
    $('.clickable').on('click', function(){
        var $icon = $(this).find('.fa-angle-down, .fa-angle-up');
        $icon.toggleClass('fa-angle-down').toggleClass('fa-angle-up');
    });
});
