$(document).ready(function(){
    $(".error-notification").hide();

    $(".side-widget-content").not(':has(.status-icon.green)').click(function(){
        if(!$(this).find(".status-icon.red").length) return;
        var $notification = $(this).find(".status-icon");
        var link = $(this).siblings("a").attr('href');
        var alert =  $(".error-notification");
        alert.hide();
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
                if(response.error === false){
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
                else{
                    alert.get(0).lastChild.nodeValue = response.message;
                    alert.show();
                }
            },
            error: function () {
                alert.get(0).lastChild.nodeValue = "Oops! Something went wrong.";
                alert.show();
            },
            dataType: 'json'
        });
    });

    $(".mark-read-notification").click(function(){
        var link = $(this).parent("i").siblings("a").attr('href');
        var alert =  $(".error-notification");
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
                if(response.error === false){
                    $(".status-icon").removeClass("red");
                    $(".status-icon").addClass("green");
                    $(".chat-message-count").remove();
                }
                else{
                    alert.get(0).lastChild.nodeValue = response.message;
                    alert.show();
                }
            },
            error: function () {
                alert.get(0).lastChild.nodeValue = "Oops! Something went wrong.";
                alert.show();
            }
        });
    });
});
