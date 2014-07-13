var userLang = navigator.language || navigator.userLanguage;
if(userLang.indexOf("pt") > -1){
    userLang = 'pt-PT'
}
else{
    userLang = 'en-US'
}

/* Set the defaults for DataTables initialisation */
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


/* Table initialisation */
$(document).ready(function() {
    var idUserToSubmit;
    var alreadyKeyUp = false;
    var listIdsExpense = [];
    var listJunk = [];
    var listCkecked = [];
    var totalList = [];
    var listValues = [];
    var flag = false;
    var responsiveHelper = undefined;
    var breakpointDefinition = {
        tablet: 1024,
        phone : 480
    };

    function updateAfterFill(){
        $("#valueExpense.value-detailedConfirmPayments").val(sumDebtValue().toFixed(2));

        $("#users-detailedConfirmPayments_wrapper div.col-md-6").first().addClass("col-xs-6");
        $("#users-detailedConfirmPayments_wrapper div.col-md-6").last().addClass("col-xs-6 col-md-6");

        $('#users-detailedConfirmPayments_wrapper .dataTables_filter input').addClass("input-medium"); // modify table search input
        $('#users-detailedConfirmPayments_wrapper .dataTables_length select').addClass("select2-wrapper span12"); // modify table per page dropdown

        $("#search-datatable-placeholder").siblings().attr("placeholder",$("#search-datatable-placeholder").val());

        $(".select2-wrapper").select2({minimumResultsForSearch: -1});

        if(!flag){
            $("#ToolTables_users-detailedConfirmPayments_0").hide();
            flag = true;
        }

        $("#valueExpense.value-detailedConfirmPayments").trigger("keyup");
        alreadyKeyUp = false;
    }

    $(".div-btn-widget-payments button.whoOweMe").click(function(){
        var button = $(this).closest(".div-btn-widget-payments");
        var idUser = button.find("input:nth(0)").val();
        var idRegisteredUser = button.find("input:nth(1)").val();
        var link = button.find("input:nth(2)").val();
        var tableElement = $('#users-detailedConfirmPayments');
        idUserToSubmit = idUser;
        if(tableElement.dataTable() != null)
            tableElement.dataTable().fnDestroy();
        var url = link + "?idUser=" + idUser + "&idRegisteredUser=" + idRegisteredUser;
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
                        return "<i class='fa fa-check text-success'></i>";
                    },
                    'bSortable': false,
                    'aTargets': [ 3 ]
                },
                {
                    "mData": null ,
                    "mRender" : function ( data, type, full ) {
                        var text = 'text-danger';
                        if(full[2] == full[3]){
                            text = 'text-success';
                        }
                        return "<span class=" + text + ">" + full[2].toFixed(2) + " € </span> / " + full[3].toFixed(2) + " €";
                    },
                    'aTargets': [ 2 ]
                },
                {
                    "mData": null ,
                    "mRender" : function ( data, type, full ) {
                        return "<i class='" + full[4] + " p-r-10'></i>" + full[0]
                    },
                    'aTargets': [ 0 ]
                },
                {
                    "mData": null ,
                    "mRender" : function ( data, type, full ) {
                        return "<i class='" + full[5] + " p-r-10'></i>" + full[1]
                    },
                    'aTargets': [ 1 ]
                }
            ],
            "aaSorting": [[ 0, "asc" ]],
            "oLanguage": {
                "sUrl": "../assets/jquery-datatable/i18n/" + userLang + ".json"
            },
            bAutoWidth: false,
            "bProcessing": true,
            "bPaginate": false,
            "bInfo": false,
            "bFilter": false,
            "iDisplayLength" : -1,
            "sAjaxSource": url,
            "sAjaxDataProp": "data",
            fnPreDrawCallback: function () {
                if (!responsiveHelper) {
                    responsiveHelper = new ResponsiveDatatablesHelper(tableElement, breakpointDefinition);
                }
                $('#users-detailedConfirmPayments').find("tr.odd,tr.even").unbind("click");
            },
            fnRowCallback  : function( nRow, aData, iDisplayIndex ) {
                responsiveHelper.createExpandIcon(nRow);
                listCkecked[iDisplayIndex] = true;
                listValues[iDisplayIndex] = aData[3] - aData[2];
                listJunk[iDisplayIndex] = 0;
                totalList[iDisplayIndex] = aData[3];
                listIdsExpense[iDisplayIndex] = aData[6];
                addEventClick(nRow, aData, iDisplayIndex);
            },
            fnDrawCallback : function (oSettings) {
                responsiveHelper.respond();
                updateAfterFill();
                addEventChangeInput();
            }
        });
    });

    function addEventChangeInput(){
        $("#valueExpense.value-detailedConfirmPayments").keyup(function(){
            alreadyKeyUp = true;
            var value = parseFloat($(this).val());
            var acc = 0;
            var position = 0;
            listValues.forEach(function(){
                listValues[position] += listJunk[position];
                listJunk[position] = 0;
                position++;
            });
            position = 0;
            listValues.forEach(function(entry){
                acc += entry;
                if(acc <= value){
                    listCkecked[position] = true;
                }
                else{
                    if((acc-entry) <= value){
                        var partial = value - (acc - entry);
                        var oldValue = parseFloat(listValues[position]);
                        listValues[position] = partial;
                        listJunk[position] = oldValue - partial;
                        listCkecked[position] = true;
                    }
                    else{
                        listCkecked[position] = false;
                        listJunk[position] = listValues[position];
                        listValues[position] = 0;
                    }
                }
                updateRows(listCkecked[position], position, $(this), $("#users-detailedConfirmPayments tbody tr:nth(" + position + ")"));
                position++;
            });
            position = 0;
        });
    }

    function addEventClick(nRow, aData, iDisplayIndex){
        $(nRow).click(function(e){
            e.preventDefault();

            if($("#valueExpense.value-detailedConfirmPayments").val() == 0){
                var pos = 0, sum = 0;
                totalList.forEach(function(entry){
                    listJunk[pos] = entry;
                    listValues[pos++] = entry;
                    sum += entry;
                });
                $("#valueExpense.value-detailedConfirmPayments").val(sum.toFixed(2));
                alreadyKeyUp = false;
            }
            if(listCkecked[iDisplayIndex] == true){
                listCkecked[iDisplayIndex] = false;
                listJunk[iDisplayIndex] = listValues[iDisplayIndex];
                listValues[iDisplayIndex] = 0;
            }
            else if(listCkecked[iDisplayIndex] == false){
                if(alreadyKeyUp && sumDebtValue() >= $("#valueExpense.value-detailedConfirmPayments").val()) return;
                listCkecked[iDisplayIndex] = true;
                listValues[iDisplayIndex] = listJunk[iDisplayIndex];
                listJunk[iDisplayIndex] = 0;
            }

            $(this).find("td:nth(3) i").toggleClass("text-success");
            $(this).find("td:nth(3) i").toggleClass("fa-check");
            $(this).find("td:nth(3) i").toggleClass("fa-times");


            var checkedValue = listCkecked[iDisplayIndex];

            var context = $("#valueExpense.value-detailedConfirmPayments");

            updateRows(checkedValue, iDisplayIndex, context, $(this));
        });
    }

    function updateRows(checkedValue, index, context, thiz){
        if(checkedValue == true){
            var classCss = "text-success";
            var act = thiz.find("td:nth(2) span");
            var partial = listValues[index];
            act.removeClass(); act.empty();
            act.text(partial.toFixed(2) + " €");
            if(listJunk[index] > 0){
                classCss = "text-danger";
            }
            act.addClass(classCss);
            context.val("").val(sumDebtValue().toFixed(2));
        }else{
            var partial = 0;
            var act = thiz.find("td:nth(2) span");
            act.removeClass(); act.empty();
            act.text(partial.toFixed(2) + " €");
            act.addClass("text-danger");
            context.val("").val(sumDebtValue().toFixed(2));
        }
        updateChecks(index);
    }

    function updateChecks(index){
        var ctx = $("#users-detailedConfirmPayments tbody tr:nth(" + index + ")");
        if(listValues[index] > 0 && listValues[index] <= totalList[index]){
            ctx.find("td:nth(3) i").removeClass().addClass("fa fa-check text-success");
        }else{
            ctx.find("td:nth(3) i").removeClass().addClass("fa fa-times");
        }
    }

    function regexVal(string){
        var re = /\d*[,.]\d*/;
        return re.test(string);
    }

    function sumDebtValue(){
        var value = 0;
        var position = 0;
        listCkecked.forEach(function(entry){
            if(entry == true)
                value += listValues[position];
            position++;
        });
        return value;
    }

    $(".btn-submit-new-detailedConfirmPayments").click(function(){
        var link = $(this).siblings("input").val();
        var context = $(this).closest('.modal-dialog');
        $.ajax({
            url: link,
            type: "POST",
            dataType: 'json',
            data: {
                'idsExpense': JSON.stringify(listIdsExpense),
                'values': JSON.stringify(listValues),
                'idUser': idUserToSubmit,
                'flag': false
            },
            beforeSend: function () {
                $("body").block({ message: null });
            },
            complete: function () {
                $("body").unblock();
            },
            success: function (data) {
                context.children("div").first().show();
                context.children("div").first().removeClass();
                if(data.error == true){
                    context.children("div").first().addClass("alert alert-error form-modal-house-error");
                }else{
                    context.children("div").first().addClass("alert alert-success form-modal-house-success");
                }
                context.children("div").first().find("div").text(data.message);
                if (data.error === false) {
                    context.delay(2000);
                    window.location.reload();
                }
            },
            error: function (data) {
                context.children("div").first().show();
                context.children("div").first().removeClass();
                context.children("div").first().addClass(data.class);
                context.find("div div").text(data.code);
            }
        });
    });
});
