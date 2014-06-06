$(document).ready(function(){
    $(".side-widget-content").click(function(){
        var $notification = $(this);
        var id = $(this).children(".user-details-wrapper").children(".user-profile").children("input").val()
        $.ajax({
            type: "PUT",
            url: '../notification/read/'+id,
            beforeSend: function(){
                $('.chat-window-wrapper').block({ message: null });
            },
            complete: function(){
                $('.chat-window-wrapper').unblock();
            },
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

    $(".mark-read-notification").click(function(){
        $.ajax({
            type: "PUT",
            url: '../notification/read_all',
            beforeSend: function(){
                $('.chat-window-wrapper').block({ message: null });
            },
            complete: function(){
                $('.chat-window-wrapper').unblock();
            },
            dataType: 'json'
        }).done(function(response){
            if(response.notification){
                $(".status-icon").removeClass("red");
                $(".status-icon").addClass("green");
                $(".chat-message-count").remove();
            }
        });
    });

    /*var refreshId = setInterval( function()
    {
        $.ajax({
            type: "PUT",
            url: '../notification/getAllNotifications?numberActualNotifications=',
            dataType: 'json'
        }).done(function(response){
            if(response.notification){
                $(".status-icon").removeClass("red");
                $(".status-icon").addClass("green");
                $(".chat-message-count").remove();
            }
        });
    }, 10000);*/
});