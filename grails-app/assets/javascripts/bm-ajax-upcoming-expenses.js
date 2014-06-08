$(".upcoming-expense-form :submit").on('click', function (event) {
    event.preventDefault();
    var form = $(this).closest(".upcoming-expense-form");

    if(form.valid()){
        var submit = form.find("button[type=submit]");
        var alert = $(".regular-expense-alert");
        submit.prop("disabled", true);
        alert.removeClass("alert-danger").removeClass("alert-success");
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
                    alert.addClass("alert-danger");
                else {
                    alert.addClass("alert-success");
                    form.closest(".col-md-12").fadeOut(500, function() { form.closest(".col-md-12").remove(); });
                }
                alert.get(0).lastChild.nodeValue = data.message;
            },
            error: function () {
                alert.addClass("alert-danger");
                alert.get(0).lastChild.nodeValue = "Oops! Something went wrong.";
            },
            complete: function(){
                form.closest(".upcoming-regular-expense").unblock();
                submit.prop("disabled", false);
                alert.fadeIn();
            }
        });
    }
});