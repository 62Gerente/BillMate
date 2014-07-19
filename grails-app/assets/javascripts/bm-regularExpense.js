$.fn.editable.defaults.mode = 'inline';
$.fn.editable.defaults.ajaxOptions = {type: "POST"};
$.fn.editable.defaults.success = function(response, newValue) {
    if(response.error) return response.message;
};

$(document).ready(function() {
    var unknownUser = '/BillMate/assets/default-user.png';
    var id_user = 0;
    var hasErrorsCircle = false;

    $("#add-new-debt-regularexpense button").click(function(){
        $(".div-new-friend-regular-expense").toggleClass("hidden");
    });

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


    function ajaxRequest(url,formData,status,href){
        $.ajax({
            url: url,
            data: formData,
            type: "POST",
            dataType: 'json',
            beforeSend: function () {
                $("body").block({ message: null });
            },
            success: function (data) {
                status.show();
                status.removeClass("alert-danger");
                status.removeClass("alert-success");
                if(data.error == true)
                    status.addClass("alert-danger");
                else
                    status.addClass("alert-success");
                status.append(data.message);
                if(!data.error){
                    status.delay(1000);
                    if(href=="")
                        window.location.reload();
                    else
                        window.location.href = href;
                }
            },
            error: function (request, error) {
                status.show();
                status.removeClass("alert-danger");
                status.removeClass("alert-success");
                status.addClass("alert-danger");
                status.append("Oops! Something went wrong.");
            },
            complete: function () {
                $("body").unblock();
            }
        })
    }

    function doAlertSelect(selector, errorClass, selectorStart){
        selector.addClass(errorClass);
        selectorStart.on("select2-selecting",function(){
            selector.removeClass(errorClass);
        });
    }

    $("span.submit-new-friend-regular-expense").click(function(){
        hasErrorsCircle = false;
        if(id_user == 0) {
            hasErrorsCircle = true;
            doAlertSelect($(this).siblings("div"),"select2-container-error",$(this).siblings("div").find("span.select2-chosen"));
        }

        var formData = {id: id_user, id_regular_expense: $(".div-new-friend-regular-expense > input:nth(0)").val()};
        var status = $(".submit-new-friend-regular-expense").closest(".content").find(".col12-alert");

        if(!hasErrorsCircle) {
            ajaxRequest("/BillMate/regularExpense/adduser", formData, status,"");
        }
    });


    //Select2 jquery plugin
    function formatImage(state) {
        var image;
        if(state && state.faicon) image = state.faicon;
        else image = unknownUser;
        id_user = state.id;
        $(".submit-new-friend-regular-expense").siblings("div").removeClass("select2-container-error");
        return "<div class='inline p-r-5'><img src='" + image + "' style='width:17px; height:17px;'/></div>" + state.name;
    }

    $(".new-friend-edit-circle").select2({
        minimumInputLength: 1,
        formatResult: formatImage,
        formatSelection: formatImage,
        ajax: {
            type: "POST",
            url: $("#user-edit-list-in-circle").val() + "/" + $(".div-new-friend-regular-expense").find("input:nth(0)").val(),
            dataType: 'json',
            data: function(term, page) {
                return {
                    q: term
                };
            },
            results: function(data, page) {
                return {
                    results: data.data
                };
            }
        }
    });

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
