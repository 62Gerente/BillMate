$(document).ready(function(){
    $(".table.table-home-box").show();
    $(".div-btn-widget-payments").hide();
    $(".div-table-widget-payment").hide();

    $(".expenses-widget-more-details").click(function(){
        $(this).children(".div-table-widget-payment").toggle();

        $(this).children(".home-margin-list").children(".price-widget-dashboard").toggle();
        $(this).children(".home-margin-list").children(".div-btn-widget-payments").toggle();
        $(this).children(".home-margin-list").children(".div-btn-widget-payments").toggleClass('inline');

        $(this).children(".home-margin-list").children("div").children("div").children("i").toggleClass("fa-angle-down");
        $(this).children(".home-margin-list").children("div").children("div").children("i").toggleClass("fa-angle-up");
    });
});