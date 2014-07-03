$(document).ready(function(){
    $(".table.table-home-box").show();
    $(".div-btn-widget-payments").hide();
    $(".div-table-widget-payment").hide();

    $(".expenses-widget-more-details").click(function(e){
        var target  = $(e.target);
        if( target.is('a') || target.parent().andSelf().is('button') || target.parent().andSelf().hasClass('checkbox') || target.parent().andSelf().is('a')) {
            return true;
        }

        var $homeMarginList = $(this).children(".home-margin-list");
        $(this).children(".div-table-widget-payment").toggle();

        $homeMarginList.children(".price-widget-dashboard").toggle();
        $homeMarginList.children(".div-btn-widget-payments").toggle().toggleClass('inline');

        $homeMarginList.find("i.fa-angle-down, i.fa-angle-up").toggleClass("fa-angle-down").toggleClass("fa-angle-up");
    });
});
