$(document).ready(function(){
    $(".side-widget-content").click(function(){
        var $notification = $(this);
        var id = $(this).children(".user-details-wrapper").children(".user-profile").children("input").val()
        $.ajax({
            type: "GET",
            url: '../notification/makeRead',
            data: { id: id },
            dataType: 'json'
        }).done(function(response){
            if(response.notification){
                $notification.children(".user-details-wrapper").children(".user-details-count-wrapper").children(".status-icon").removeClass("red");
                $notification.children(".user-details-wrapper").children(".user-details-count-wrapper").children(".status-icon").addClass("green");
                var valueOfCountNotifications = parseInt($(".chat-message-count").text());
                var valueDec = valueOfCountNotifications-1;
                if(valueDec > 0) {
                    $(".chat-message-count").text(valueDec);
                }
                else{
                    $(".chat-message-count").remove();
                }
            }
        });
    });

    $(".header-seperation").click(function(){
        $.ajax({
            type: "GET",
            url: '../notification/makeAllRead',
            dataType: 'json'
        }).done(function(response){
            if(response.notification){
                $(".status-icon").removeClass("red");
                $(".status-icon").addClass("green");
                $(".chat-message-count").remove();
            }
        });
    });

    var refreshId = setInterval( function()
    {
        $.ajax({
            type: "GET",
            url: '../notification/getAllNotifications?numberActualNotifications=',
            dataType: 'json'
        }).done(function(response){
            if(response.notification){
                $(".status-icon").removeClass("red");
                $(".status-icon").addClass("green");
                $(".chat-message-count").remove();
            }
        });
    }, 10000);
});