$("#upload-invoice").on('click', function(){
    $("#upload-invoice-input").trigger('click');
});

$("#upload-invoice-input").on('change', function(){
    var error = $("#upload-invoice-error");
    var iframe = $("#invoice-iframe");
    var upload = $("#upload-invoice");
    var container = $("#invoice-container");
    var form = $("#upload-invoice-form");
    var formData = new FormData($('#upload-invoice-form')[0]);

    error.hide();

    $.ajax({
        url: form.attr('action'),
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        type: "POST",
        dataType: 'json',
        beforeSend: function(){
            upload.prop("disabled", true);
        },
        success: function (data) {
            if (data.error === true)
                error.text(data.message).show();
            else {
                iframe.attr("src", data.invoice_url);
                upload.hide();
                container.show();
            }
        },
        error: function () {
           error.text("Oops! Something went wrong.").show();
        },
        complete: function(){
            upload.prop("disabled", false);
        }
    });
});

$("#upload-receipt").on('click', function(){
    $("#upload-receipt-input").trigger('click');
});

$("#upload-receipt-input").on('change', function(){
    var error = $("#upload-receipt-error");
    var iframe = $("#receipt-iframe");
    var upload = $("#upload-receipt");
    var container = $("#receipt-container");
    var form = $("#upload-receipt-form");
    var formData = new FormData($('#upload-receipt-form')[0]);

    error.hide();

    $.ajax({
        url: form.attr('action'),
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        type: "POST",
        dataType: 'json',
        beforeSend: function(){
            upload.prop("disabled", true);
        },
        success: function (data) {
            if (data.error === true)
                error.text(data.message).show();
            else {
                iframe.attr("src", data.invoice_url);
                upload.hide();
                container.show();
            }
        },
        error: function () {
            error.text("Oops! Something went wrong.").show();
        },
        complete: function(){
            upload.prop("disabled", false);
        }
    });
});
