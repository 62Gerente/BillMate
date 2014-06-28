$.fn.editable.defaults.mode = 'inline';
$.fn.editable.defaults.ajaxOptions = {type: "POST"};
$.fn.editable.defaults.success = function(response, newValue) {
    if(response.error) return response.message;
};

$(document).ready(function() {
    $('#edit_description').editable();
    $('#edit_title').editable();
    $('#edit_begin_date').editable();
    $('#edit_end_date').editable();
    $('#edit_date').editable();
    $('#edit_payment_deadline').editable();
    $('#edit_reception_deadline').editable();
    $('#edit_payment_date').editable();
    $('#edit_reception_date').editable();
});
