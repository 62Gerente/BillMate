$(".upcoming-expense-form :submit").on('click', function (event) {
    event.preventDefault();
    var form = $(this).closest(".upcoming-expense-form");

    if(form.valid()){
        var submit = form.find("button[type=submit]");
        var alert = $(".upcoming-regular-expense-alert");
        submit.prop("disabled", true);
        $.ajax({
            url: form.attr('action'),
            data: form.serialize(),
            type: "POST",
            dataType: 'json',
            beforeSend: function(){
                form.closest(".upcoming-regular-expense").block({ message: null });
            },
            success: function (data) {
                if (data.error === true)
                    alert.removeClass("alert-success").addClass("alert-danger");
                else {
                    alert.removeClass("alert-danger").addClass("alert-success");
                    form.closest(".col-md-12").remove();
                }
                alert.get(0).lastChild.nodeValue = data.message;
            },
            error: function () {
                alert.removeClass("alert-success").addClass("alert-danger");
                alert.get(0).lastChild.nodeValue = "Oops! Something went wrong.";
            },
            complete: function(){
                form.closest(".upcoming-regular-expense").unblock();
                submit.prop("disabled", false);
                alert.stop().fadeIn().delay(5000).fadeOut();
            }
        });
    }
});

$(".postpone-upcoming-expense").on('click', function (event) {
    event.preventDefault();
    var link = $(this);

    var alert = $(".upcoming-regular-expense-alert");
    link.prop("disabled", true);
    $.ajax({
        url: link.attr('href'),
        type: "POST",
        dataType: 'json',
        beforeSend: function(){
            link.closest("li").block({ message: null });
        },
        success: function (data) {
            if (data.error === true)
                alert.removeClass("alert-success").addClass("alert-danger");
            else {
                alert.removeClass("alert-danger").addClass("alert-success");
                link.closest(".col-md-12").remove();
            }
            alert.get(0).lastChild.nodeValue = data.message;
        },
        error: function () {
            alert.removeClass("alert-success").addClass("alert-danger");
            alert.get(0).lastChild.nodeValue = "Oops! Something went wrong.";
        },
        complete: function(){
            link.closest("li").unblock();
            link.prop("disabled", false);
            alert.stop().fadeIn().delay(5000).fadeOut();
        }
    });
});