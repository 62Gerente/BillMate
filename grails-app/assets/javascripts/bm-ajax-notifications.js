$(document).ready(function(){
    $(".error-notification").hide();

    $(".side-widget-content").not(':has(.status-icon.green)').click(function(){
        if($(this).find(".status-icon.red").length == 0) return;
        var $notification = $(this).find(".status-icon");
        var link = $(this).siblings("a").attr('href');
        $.ajax({
            type: "PUT",
            url: link,
            beforeSend: function(){
                $('.chat-window-wrapper').block({ message: null });
            },
            complete: function(){
                $('.chat-window-wrapper').unblock();
            },
            success: function(response){
                if(response.notification){
                    $notification.removeClass("red");
                    $notification.addClass("green");
                    var valueOfCountNotifications = parseInt($(".chat-message-count").text());
                    var valueDec = valueOfCountNotifications-1;
                    if(valueDec > 0) {
                        $(".chat-message-count").text(valueDec);
                    }
                    else{
                        $(".chat-message-count").remove();
                    }
                }
            },
            error: function () {
                $(".error-notification").show();
            },
            dataType: 'json'
        });
    });

    $(".mark-read-notification").click(function(){
        var link = $(this).parent("i").siblings("a").attr('href');
        $.ajax({
            type: "PUT",
            url: link,
            beforeSend: function(){
                $('.chat-window-wrapper').block({ message: null });
            },
            complete: function(){
                $('.chat-window-wrapper').unblock();
            },
            dataType: 'json',
            success: function (response) {
                if(response.notification){
                    $(".status-icon").removeClass("red");
                    $(".status-icon").addClass("green");
                    $(".chat-message-count").remove();
                }
            },
            error: function () {
                $(".error-notification").show();
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
