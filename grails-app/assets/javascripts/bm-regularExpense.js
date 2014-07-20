$.fn.editable.defaults.mode = 'inline';
$.fn.editable.defaults.ajaxOptions = {type: "POST"};
$.fn.editable.defaults.success = function(response, newValue) {
    if(response.error) return response.message;
};

$(document).ready(function() {

    var responsiveHelper = undefined;
    var breakpointDefinition = {
        tablet: 1200,
        phone : 480
    };

    var unknownUser = '/BillMate/assets/default-user.png';
    var id_user = 0;
    var hasErrorsCircle = false;

    $("#add-new-debt-regularexpense button").click(function(){
        $(".div-new-friend-regular-expense").toggleClass("hidden");
    });

    $("#expense-circles-btnNew").click(function(){
        $("#btn-advanced-options-expense-create-modal").trigger("click");
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
        },
        success: function(response, newValue) {
            window.location.reload();
        }
    });

    $(".table-edit-user-regular-expense tr td .fa-sign-out").click(function(){
        var id_list_user = $(this).closest("td").find("input").val();
        var formData = {id: id_list_user, id_regular_expense: $(".div-new-friend-regular-expense > input:nth(0)").val()};
        var status = $(this).closest(".content").find(".col-md-12 .alert");

        ajaxRequest("/BillMate/regularExpense/deleteUser", formData, status,"");
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



    //Datatables
    var tableElement = $('#circle-expense-datatable');
    tableElement.dataTable( {
        "sDom": "<'row'<'col-md-6'l T><'col-md-6'f>r>t<'row'<'col-md-12'p i>>",
        "oTableTools": {
            "aButtons": [
                {
                    "sExtends":    "collection",
                    "sButtonText": "<i class='fa fa-cloud-download'></i>",
                    "aButtons":    [ "csv", "xls", "pdf", "copy"]
                }
            ]
        },
        "sPaginationType": "bootstrap",
        "aoColumnDefs": [
            {
                "mData": null ,
                "mRender" : function ( data, type, full ) {
                    return "<i class='" + full[5] + " p-r-10'></i>" + full[0]
                },
                'aTargets': [ 0 ]
            },
            {
                "mData": null ,
                "mRender" : function ( data, type, full ) {
                    return "<span class='p-r-10'><img src='" + full[6] + "'height='15' width='15'/></span>" + full[1]
                },
                'aTargets': [ 1 ]
            },
            {
                "mData": null ,
                "mRender" : function ( data, type, full ) {
                    return full[2].toFixed(2) + " €"
                },
                'aTargets': [ 2 ]
            },
            {
                "mData": null ,
                "mRender" : function ( data, type, full ) {
                    return full[3]
                },
                'aTargets': [ 3 ]
            },
            { 'bSortable': false, 'aTargets': [  ] }
        ],
        "aaSorting": [[ 0, "asc" ]],
        "oLanguage": {
            "sUrl": "../../assets/jquery-datatable/i18n/" + userLang + ".json"
        },
        bAutoWidth: false,
        "bProcessing": true,
        "sAjaxSource": "/BillMate/regularExpense/expenses?id=" + $("#identifier-regularexpense-expense").val(),
        "sAjaxDataProp": "data",
        fnPreDrawCallback: function () {
            if (!responsiveHelper) {
                responsiveHelper = new ResponsiveDatatablesHelper(tableElement, breakpointDefinition);
            }
        },
        fnRowCallback  : function( nRow, aData, iDisplayIndex ) {
            responsiveHelper.createExpandIcon(nRow);
            $(nRow).click(function(e){
                document.location.href = "/BillMate/expense/show/" + aData[4];
            });
        },
        fnDrawCallback : function (oSettings) {
            responsiveHelper.respond();
            updateAfterLoadingTable();
        }
    });

    function updateAfterLoadingTable(){

        $("#ToolTables_circle-expense-datatable_0").hide();

        $("#circle-expense-datatable_wrapper div.col-md-6").first().addClass("col-xs-6");
        $("#circle-expense-datatable_wrapper div.col-md-6").last().addClass("col-xs-6 col-md-6");

        $('#circle-expense-datatable_wrapper .dataTables_filter input').addClass("input-medium "); // modify table search input
        $('#circle-expense-datatable_wrapper .dataTables_length select').addClass("select2-wrapper span12"); // modify table per page dropdown

        $("#search-datatable-placeholder").siblings().attr("placeholder",$("#search-datatable-placeholder").val());

        $(".select2-wrapper").select2({minimumResultsForSearch: -1});
    }

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
