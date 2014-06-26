$(".upload-invoice, .upload-invoice-icon").on('click', function(){
    $("#upload-invoice-input").trigger('click');
});

$("#upload-invoice-input, .upload-invoice-icon").on('change', function(){
    var alert = $(".col8-alert");
    var iframe = $(".invoice-iframe");
    var upload = $(".upload-invoice");
    var uploadIcon = $(".upload-invoice-icon");
    var container = $(".invoice-container");
    var form = $("#upload-invoice-form");
    var formData = new FormData($('#upload-invoice-form')[0]);
    var download = $(".download-invoice");

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
            uploadIcon.prop("disabled", true);
        },
        success: function (data) {
            if (data.error === true) {
                alert.removeClass("alert-success").addClass("alert-danger");
                alert.get(0).lastChild.nodeValue = data.message;
                alert.stop().fadeIn().delay(5000).fadeOut();
            } else {
                iframe.attr("src", data.invoice_url);
                download.attr("href", data.invoice_url).show();
                upload.hide();
                uploadIcon.show();
                container.show();
            }
        },
        error: function () {
           alert.removeClass("alert-success").addClass("alert-danger");
           alert.get(0).lastChild.nodeValue = "Oops! Something went wrong.";
           alert.stop().fadeIn().delay(5000).fadeOut();
        },
        complete: function(){
            upload.prop("disabled", false);
            uploadIcon.prop("disabled", false);
        }
    });
});

$(".upload-receipt, .upload-receipt-icon").on('click', function(){
    $("#upload-receipt-input").trigger('click');
});

$("#upload-receipt-input, .upload-receipt-icon").on('change', function(){
    var alert = $(".col8-alert");
    var iframe = $(".receipt-iframe");
    var upload = $(".upload-receipt");
    var uploadIcon = $(".upload-receipt-icon");
    var container = $(".receipt-container");
    var form = $("#upload-receipt-form");
    var formData = new FormData($('#upload-receipt-form')[0]);
    var download = $(".download-receipt");

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
            uploadIcon.prop("disabled", true);
        },
        success: function (data) {
            if (data.error === true) {
                alert.removeClass("alert-success").addClass("alert-danger");
                alert.get(0).lastChild.nodeValue = data.message;
                alert.stop().fadeIn().delay(5000).fadeOut();
            } else {
                iframe.attr("src", data.receipt_url);
                download.attr("href", data.receipt_url).show();
                upload.hide();
                uploadIcon.show();
                container.show();
            }
        },
        error: function () {
            alert.removeClass("alert-success").addClass("alert-danger");
            alert.get(0).lastChild.nodeValue = "Oops! Something went wrong.";
            alert.stop().fadeIn().delay(5000).fadeOut();
        },
        complete: function(){
            upload.prop("disabled", false);
            uploadIcon.prop("disabled", false);
        }
    });
});