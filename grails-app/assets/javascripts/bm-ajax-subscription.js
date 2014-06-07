$("#subscribe-form").submit(function (event) {
    event.preventDefault();
    form = $(this)
    submit = form.find("button[type=submit]");
    alert = form.find(".alert");
    submit.prop("disabled", true);
    alert.animate({opacity: 0},'fast', function(){
        alert.removeClass("alert-danger").removeClass("alert-success");
        $.ajax({
            url: form.attr('action'),
            data: form.serialize(),
            type: "POST",
            dataType: 'json',
            success: function (data) {
                console.log(data);
                if (data.error === true)
                    alert.addClass("alert-danger");
                else {
                    alert.addClass("alert-success");
                    form.find("input[name=email]").val("");
                }
                alert.empty().text(data.message);
            },
            error: function () {
                alert.addClass("alert-danger");
            },
            complete: function(){
                alert.removeClass("hidden").animate({opacity: 1}, 'fast', function(){
                    submit.prop("disabled", false);
                });
            }
        });
    });
});
