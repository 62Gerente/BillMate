$(".confirm-payments-form :submit").on('click', function (event) {
    event.preventDefault();
    var form = $(this).closest(".confirm-payments-form");
    var widget = form.closest(".confirm-payments-widget");
    var submit = form.find("button[type=submit]");
    var action = form.attr('action');
    var alert = $(".col8-alert");
    var trs = form.find('input[value]:checkbox:checked').closest('.single-payment');
    
    submitAndAlert(trs, form, submit, action, form.serialize(), alert);
});

$(".submit-cancel-payments-form").on('click', function (event) {
    event.preventDefault();
    var link = $(this); 
    var form = link.closest(".confirm-payments-form");
    var widget = form.closest(".confirm-payments-widget");
    var url = link.attr('href');
    var alert = $(".col8-alert");
    
    submitAndAlert(form, form, link, url, form.serialize(), alert);
});

$(".submit-confirm-all-payments, .submit-cancel-all-payments").on('click', function (event) {
    event.preventDefault();
    var link = $(this); 
    var widget = link.closest(".confirm-payments-widget");
    var url = link.attr('href');
    var alert = $(".col8-alert");
    var inputs = widget.find('input[value]');
    
    var ids = inputs.map(function() {
        return this.value;
    }).get();

    var data = new Object();
    data['payment[]'] = ids;
    
    submitAndAlert(widget, widget, link, url, data, alert);
});

$(".submit-confirm-selected-payments, .submit-cancel-selected-payments").on('click', function (event) {
    event.preventDefault();
    var link = $(this); 
    var widget = link.closest(".confirm-payments-widget");
    var url = link.attr('href');
    var alert = $(".col8-alert");
    var inputs = widget.find('input[value]:checked');
    var trs = inputs.closest('.single-payment');
    var forms = trs.closest(".confirm-payments-form");

    var ids = inputs.map(function() {
        return this.value;
    }).get();

    var data = new Object();
    data['payment[]'] = ids;
    
    submitAndAlert(trs, widget, link, url, data, alert);
});

function submitAndAlert(_remove, _block, _link, _url, _data, _alert, _rmWidget, _rmForms){
    _link.prop("disabled", true);
    $.ajax({
        url: _url,
        data: _data,
        type: "POST",
        dataType: 'json',
        beforeSend: function(){
            _block.block({ message: null });
        },
        success: function (data) {
            if (data.error === true)
                _alert.removeClass("alert-success").addClass("alert-danger");
            else {
                _alert.removeClass("alert-danger").addClass("alert-success");
                _remove.remove();

                $(".confirm-payments-form").not(":has('.single-payment')").remove();
                $(".confirm-payments-widget").not(":has('.confirm-payments-form')").remove();
            }
            _alert.get(0).lastChild.nodeValue = data.message;
        },
        error: function () {
            _alert.removeClass("alert-success").addClass("alert-danger");
            _alert.get(0).lastChild.nodeValue = "Oops! Something went wrong.";
        },
        complete: function(){
            _block.unblock();
            _link.prop("disabled", false);
            _alert.stop().fadeIn().delay(5000).fadeOut();
        }
    });
};
