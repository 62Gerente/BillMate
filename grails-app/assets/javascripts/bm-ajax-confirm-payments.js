$(".confirm-payments-form :submit").on('click', function (event) {
    event.preventDefault();
    var form = $(this).closest(".confirm-payments-form");
    var submit = form.find("button[type=submit]");

    var alert = $(".col8-alert");
    submit.prop("disabled", true);
    $.ajax({
        url: form.attr('action'),
        data: form.serialize(),
        type: "POST",
        dataType: 'json',
        beforeSend: function(){
            form.block({ message: null });
        },
        success: function (data) {
            if (data.error === true)
                alert.removeClass("alert-success").addClass("alert-danger");
            else {
                alert.removeClass("alert-danger").addClass("alert-success");
                form.remove();
            }
            alert.get(0).lastChild.nodeValue = data.message;
        },
        error: function () {
            alert.removeClass("alert-success").addClass("alert-danger");
            alert.get(0).lastChild.nodeValue = "Oops! Something went wrong.";
        },
        complete: function(){
            form.unblock();
            submit.prop("disabled", false);
            alert.stop().fadeIn().delay(5000).fadeOut();
        }
    });
});

$(".submit-cancel-payments-form").on('click', function (event) {
    event.preventDefault();
    var form = $(this).closest(".confirm-payments-form");

    var link = $(this); 
    var url = link.attr('href');
    var alert = $(".col8-alert");
    link.prop("disabled", true);
    $.ajax({
        url: url,
        data: form.serialize(),
        type: "POST",
        dataType: 'json',
        beforeSend: function(){
            form.block({ message: null });
        },
        success: function (data) {
            if (data.error === true)
                alert.removeClass("alert-success").addClass("alert-danger");
            else {
                alert.removeClass("alert-danger").addClass("alert-success");
                form.remove();
            }
            alert.get(0).lastChild.nodeValue = data.message;
        },
        error: function () {
            alert.removeClass("alert-success").addClass("alert-danger");
            alert.get(0).lastChild.nodeValue = "Oops! Something went wrong.";
        },
        complete: function(){
            form.unblock();
            link.prop("disabled", false);
            alert.stop().fadeIn().delay(5000).fadeOut();
        }
    });
});
