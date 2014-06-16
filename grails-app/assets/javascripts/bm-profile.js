$.fn.editable.defaults.mode = 'inline';
$.fn.editable.defaults.ajaxOptions = {type: "POST"};
$.fn.editable.defaults.success = function(response, newValue) {
    if(response.error) return response.message;
};

$(document).ready(function() {
    $('#edit_name').editable();
    $('#edit_email').editable();
    $('#edit_phone_number').editable();
    $('#edit_password').editable();
});