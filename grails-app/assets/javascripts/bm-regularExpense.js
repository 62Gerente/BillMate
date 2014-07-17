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
    $('#edit_reception_deadline').editable();
    $('#edit_payment_deadline').editable();
    $('#edit_debit_date').editable();
    $('#edit_reception_begin_date').editable();
    $('#edit_value').editable({
        display: function(value) {
            if(value){
                $(this).text(parseFloat(value).toFixed(2) + ' €');
            }else{
                $(this).empty();
            }
        }
    });
    $('.edit_debt_value').editable({
        display: function(value) {
            if(value){
                $(this).text(parseFloat(value).toFixed(2) + ' €');
            }else{
                $(this).empty();
            }
        }
    })
});


$('#unscheduleExpense').confirmModal({
    confirmTitle     : 'Please confirm',
    confirmMessage   : 'Are you sure you want to unschedule this expense?',
    confirmOk        : 'Unschedule',
    confirmCancel    : 'Cancel',
    confirmDirection : 'rtl',
    confirmStyle     : 'danger',
    confirmCallback  : unschedule
});

function unschedule(){
    $('#unscheduleExpense').submit()
}
