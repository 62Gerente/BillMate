$(".upcoming-expense-form :submit").on('click', function (event) {
    event.preventDefault();
    var form = $(this).closest(".upcoming-expense-form");

    form.validate({
        errorElement: 'span',
        errorClass: 'error',
        focusInvalid: false,
        ignore: "",
        rules: {
            value: {
                required: true,
                blank: false,
                number: true
            }
        },

        invalidHandler: function (event, validator) {
        },

        errorPlacement: function (error, element) {
            var parent = $(element).parent('.input');
            parent.removeClass('success-control').addClass('error-control');
        },

        highlight: function (element) {
            var parent = $(element).parent();
            parent.removeClass('success-control').addClass('error-control');
        },

        unhighlight: function (element) {
            var parent = $(element).parent();
            parent.removeClass('error-control').addClass('success-control');
        },

        success: function (label, element) {
            var icon = $(element).parent('.input').children('i');
            var parent = $(element).parent('.input');
            parent.removeClass('error-control').addClass('success-control');
        }
    });

    if(form.valid()){
        var submit = form.find("button[type=submit]");
        var alert = $(".col4-alert");
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
                    form.closest(".upcoming-regular-expense").remove();
                }
                alert.get(0).lastChild.nodeValue = data.message;
                window.location.reload();
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

    var alert = $(".col4-alert");
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
