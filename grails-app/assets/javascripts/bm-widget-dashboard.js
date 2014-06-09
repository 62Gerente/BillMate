$(document).ready(function(){
    $(".table.table-home-box").show();
    $(".div-btn-widget-payments").hide();
    $(".div-table-widget-payment").hide();

    $(".expenses-widget-more-details").click(function(){
        var $homeMarginList = $(this).children(".home-margin-list");
        $(this).children(".div-table-widget-payment").toggle();

        $homeMarginList.children(".price-widget-dashboard").toggle();
        $homeMarginList.children(".div-btn-widget-payments").toggle().toggleClass('inline');

        $homeMarginList.find("i").toggleClass("fa-angle-down").toggleClass("fa-angle-up");
    });
});
