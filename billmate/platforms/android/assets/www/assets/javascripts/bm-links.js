$(document).ready(function(){
    $('a[href="#"]').on('click', function(e) {
        e.preventDefault();
    });

    $(".close").on('click', function() {
        $(this).closest(".alert").stop().hide();
    });
});