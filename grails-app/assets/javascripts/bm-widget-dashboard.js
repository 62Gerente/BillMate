$(document).ready(function(){
    $(".table.table-home-box").hide();
    $(".home-button-divida-primary-confirm").hide();



    $(".expenses-widget-more-details").click(function(){
        $(this).children(".table-home-box").toggle();
        $(this).children(".home-margin-list").children(".price-widget-dashboard").toggle();
        var elem = $(this).children(".home-margin-list").siblings(".table-home-box");
        if(elem.css("display") == "none"){
            $(this).children(".home-margin-list").children(".home-button-divida-primary-confirm").hide();
            $(this).children(".home-margin-list").siblings(".home-button-divida-secondary-confirm").hide();
        }
        else{
            if($(document).width() > 480)
                $(this).children(".home-margin-list").children(".home-button-divida-primary-confirm").show();
            else
                $(this).children(".home-margin-list").children(".home-button-divida-secondary-confirm").show();
        }
    });

});