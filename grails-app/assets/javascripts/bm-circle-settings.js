var userLang = navigator.language || navigator.userLanguage;
if(userLang.indexOf("pt") > -1){
    userLang = 'pt-PT'
}
else{
    userLang = 'en-US'
}

$.extend( true, $.fn.dataTable.defaults, {
    "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'p i>>",
    "sPaginationType": "bootstrap",
    "oLanguage": {
        "sLengthMenu": "_MENU_"
    }
} );


/* Default class modification */
$.extend( $.fn.dataTableExt.oStdClasses, {
    "sWrapper": "dataTables_wrapper form-inline"
} );


/* API method to get paging information */
$.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
{
    return {
        "iStart":         oSettings._iDisplayStart,
        "iEnd":           oSettings.fnDisplayEnd(),
        "iLength":        oSettings._iDisplayLength,
        "iTotal":         oSettings.fnRecordsTotal(),
        "iFilteredTotal": oSettings.fnRecordsDisplay(),
        "iPage":          oSettings._iDisplayLength === -1 ?
            0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
        "iTotalPages":    oSettings._iDisplayLength === -1 ?
            0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
    };
};


/* Bootstrap style pagination control */
$.extend( $.fn.dataTableExt.oPagination, {
    "bootstrap": {
        "fnInit": function( oSettings, nPaging, fnDraw ) {
            var oLang = oSettings.oLanguage.oPaginate;
            var fnClickHandler = function ( e ) {
                e.preventDefault();
                if ( oSettings.oApi._fnPageChange(oSettings, e.data.action) ) {
                    fnDraw( oSettings );
                }
            };

            $(nPaging).addClass('pagination').append(
                    '<ul>'+
                    '<li class="prev disabled"><a href="#"><i class="fa fa-chevron-left"></i></a></li>'+
                    '<li class="next disabled"><a href="#"><i class="fa fa-chevron-right"></i></a></li>'+
                    '</ul>'
            );
            var els = $('a', nPaging);
            $(els[0]).bind( 'click.DT', { action: "previous" }, fnClickHandler );
            $(els[1]).bind( 'click.DT', { action: "next" }, fnClickHandler );
        },

        "fnUpdate": function ( oSettings, fnDraw ) {
            var iListLength = 5;
            var oPaging = oSettings.oInstance.fnPagingInfo();
            var an = oSettings.aanFeatures.p;
            var i, ien, j, sClass, iStart, iEnd, iHalf=Math.floor(iListLength/2);

            if ( oPaging.iTotalPages < iListLength) {
                iStart = 1;
                iEnd = oPaging.iTotalPages;
            }
            else if ( oPaging.iPage <= iHalf ) {
                iStart = 1;
                iEnd = iListLength;
            } else if ( oPaging.iPage >= (oPaging.iTotalPages-iHalf) ) {
                iStart = oPaging.iTotalPages - iListLength + 1;
                iEnd = oPaging.iTotalPages;
            } else {
                iStart = oPaging.iPage - iHalf + 1;
                iEnd = iStart + iListLength - 1;
            }

            for ( i=0, ien=an.length ; i<ien ; i++ ) {
                // Remove the middle elements
                $('li:gt(0)', an[i]).filter(':not(:last)').remove();

                // Add the new list items and their event handlers
                for ( j=iStart ; j<=iEnd ; j++ ) {
                    sClass = (j==oPaging.iPage+1) ? 'class="active"' : '';
                    $('<li '+sClass+'><a href="#">'+j+'</a></li>')
                        .insertBefore( $('li:last', an[i])[0] )
                        .bind('click', function (e) {
                            e.preventDefault();
                            oSettings._iDisplayStart = (parseInt($('a', this).text(),10)-1) * oPaging.iLength;
                            fnDraw( oSettings );
                        } );
                }

                // Add / remove disabled classes from the static elements
                if ( oPaging.iPage === 0 ) {
                    $('li:first', an[i]).addClass('disabled');
                } else {
                    $('li:first', an[i]).removeClass('disabled');
                }

                if ( oPaging.iPage === oPaging.iTotalPages-1 || oPaging.iTotalPages === 0 ) {
                    $('li:last', an[i]).addClass('disabled');
                } else {
                    $('li:last', an[i]).removeClass('disabled');
                }
            }
        }
    }
} );


/*
 * TableTools Bootstrap compatibility
 * Required TableTools 2.1+
 */

// Set the classes that TableTools uses to something suitable for Bootstrap
$.extend( true, $.fn.DataTable.TableTools.classes, {
    "container": "DTTT ",
    "buttons": {
        "normal": "btn btn-white",
        "disabled": "disabled"
    },
    "collection": {
        "container": "DTTT_dropdown dropdown-menu",
        "buttons": {
            "normal": "",
            "disabled": "disabled"
        }
    },
    "print": {
        "info": "DTTT_print_info modal"
    },
    "select": {
        "row": "active"
    }
} );

// Have the collection use a bootstrap compatible dropdown
$.extend( true, $.fn.DataTable.TableTools.DEFAULTS.oTags, {
    "collection": {
        "container": "ul",
        "button": "li",
        "liner": "a"
    }
} );

$(".select2-wrapper").select2({minimumResultsForSearch: -1});


$.fn.editable.defaults.mode = 'inline';
$.fn.editable.defaults.ajaxOptions = {type: "POST"};
$.fn.editable.defaults.success = function(response, newValue) {
    if(response.error) return response.message;
};

var hasErrorsCircle = false;

$(document).ready(function() {

    var flag = false;
    var responsiveHelper = undefined;
    var breakpointDefinition = {
        tablet: 1200,
        phone : 480
    };

    var unknownUser = '/BillMate/assets/default-user.png';
    var id_user = 0;
    var id_expenseType = 0;


    configs();

    function configs(){
        $('#edit_name').editable();
        $('#edit_description').editable();

        $(".div-new-friend-circle").hide();
        $(".div-new-expensetype-circle").hide();

        $("#add-new-user-circle").click(function(){
            $(".div-new-friend-circle").toggle();
        });

        $("#expense-type-circles-btnNew").click(function(){
            $(".div-new-expensetype-circle").toggle();
        });

        $("#regular-expense-circles-btnNew").click(function(){
            $("#btn-advanced-options-newRegularExpense").trigger("click");
        });
    }


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

    $("#circle-expense-type").find("i.fa-times").click(function(){
        var formData = {id: $(this).closest("tr").find("input").val(), id_circle: $(".div-new-friend-circle > input:nth(0)").val()}
        var status = $(this).closest(".content").find(".col-md-12 .alert");

        ajaxRequest("/BillMate/circle/deleteExpensetype", formData, status,"");
    });

    $(".submit-new-expensetype-circle").click(function(){
        hasErrorsCircle = false;
        if(id_expenseType == 0){
            hasErrorsCircle = true;
            doAlertSelect($(this).siblings("div"),"select2-container-error",$(this).siblings("div").find("span.select2-chosen"));
        }

        var formData = {id: id_expenseType, id_circle: $(".div-new-friend-circle > input:nth(0)").val()}
        var status = $(this).closest(".content").find(".col-md-12 .alert");

        if(!hasErrorsCircle) {
            ajaxRequest("/BillMate/circle/addExpensetype", formData, status,"");
        }
    });

    $("span.submit-new-friend-circle").click(function(){
        hasErrorsCircle = false;
        if(id_user == 0) {
            hasErrorsCircle = true;
            doAlertSelect($(this).siblings("div"),"select2-container-error",$(this).siblings("div").find("span.select2-chosen"));
        }

        var formData = {id: id_user, id_circle: $(".div-new-friend-circle > input:nth(0)").val()};
        var status = $(".submit-new-friend-circle").closest(".content").find(".col-md-12 .alert");

        if(!hasErrorsCircle) {
            ajaxRequest("/BillMate/circle/adduser", formData, status,"");
        }
    });

    function doAlertSelect(selector, errorClass, selectorStart){
        selector.addClass(errorClass);
        selectorStart.on("select2-selecting",function(){
            selector.removeClass(errorClass);
        });
    }


    //Select2 jquery plugin configs
    function formatImage(state) {
        var image;
        if(state && state.faicon) image = state.faicon;
        else image = unknownUser;
        id_user = state.id;
        $(".submit-new-friend-circle").siblings("div").removeClass("select2-container-error");
        return "<div class='inline p-r-5'><img src='" + image + "' style='width:17px; height:17px;'/></div>" + state.name;
    }

    function formatExpenseTypesCircle(state) {
        id_expenseType = state.id;
        $(".submit-new-expensetype-circle").siblings("div").removeClass("select2-container-error");
        return "<i class='" + state.cssClass + "'></i>&nbsp;" + $("<div>").html(state.name).text();
    }

    $(".new-friend-circle").select2({
        minimumInputLength: 1,
        formatResult: formatImage,
        formatSelection: formatImage,
        ajax: {
            type: "POST",
            url: $("#user-list-in-circle").val() + "/" + $(".div-new-friend-circle").find("input:nth(0)").val(),
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

    $(".new-expensetype-circle").select2({
        minimumInputLength: 1,
        formatResult: formatExpenseTypesCircle,
        formatSelection: formatExpenseTypesCircle,
        ajax: {
            type: "POST",
            url: "/BillMate/circle/listExpensetypeBy/" + $(".new-expensetype-circle").siblings("input").val(),
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

    $("#user-list-in-circle").siblings("table").find("tr td .fa-sign-out.selected").click(function(){
        var id_list_user = $(this).closest("td").find("input").val();
        var formData = {id: id_list_user, id_circle: $(".div-new-friend-circle > input:nth(0)").val()};
        var status = $(this).closest(".content").find(".col-md-12 .alert");

        ajaxRequest("/BillMate/circle/deleteUser", formData, status,"");
    });


    //Confirm Modal
    $('#out-house').confirmModal({
        confirmTitle     : 'Please confirm',
        confirmMessage   : 'Are you sure you want to pay this expense?',
        confirmOk        : 'Pay',
        confirmCancel    : 'Cancel',
        confirmDirection : 'rtl',
        confirmStyle     : 'primary',
        confirmCallback  : outHouse
    });

    function outHouse(){
        var formData = {id: $('#out-house > input:nth(1)').val(), id_circle: $('#out-house > input:nth(2)').val()};
        var status = $('#out-house').closest(".content").find(".col-md-12 .alert");
        ajaxRequest($('#out-house').attr('action'), formData, status,$('#out-house > input:nth(3)').val());
    }


    $('#close-house').confirmModal({
        confirmTitle     : 'Please confirm',
        confirmMessage   : 'Are you sure you want to pay this expense?',
        confirmOk        : 'Pay',
        confirmCancel    : 'Cancel',
        confirmDirection : 'rtl',
        confirmStyle     : 'primary',
        confirmCallback  : closeHouse
    });

    function closeHouse(){
        var formData = {id: $('#close-house > input:nth(2)').val()};
        var status = $('#close-house').closest(".content").find(".col-md-12 .alert");
        ajaxRequest($('#close-house').attr('action'), formData, status, $('#close-house > input:nth(3)').val());
    }





    //Datatables
    var tableElement = $('#circle-edit-datatable');
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
        "sAjaxSource": "/BillMate/circle/regularexpense?id=" + $("#identifier-circle-expense").val(),
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

        $("#ToolTables_circle-edit-datatable_0").hide();

        $("#circle-edit-datatable_wrapper div.col-md-6").first().addClass("col-xs-6");
        $("#circle-edit-datatable_wrapper div.col-md-6").last().addClass("col-xs-6 col-md-6");

        $('#circle-edit-datatable_wrapper .dataTables_filter input').addClass("input-medium "); // modify table search input
        $('#circle-edit-datatable_wrapper .dataTables_length select').addClass("select2-wrapper span12"); // modify table per page dropdown

        $("#search-datatable-placeholder").siblings().attr("placeholder",$("#search-datatable-placeholder").val());

        $(".select2-wrapper").select2({minimumResultsForSearch: -1});
    }

});
