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


/* Table initialisation */
$(document).ready(function() {
    var flag = false;
    var responsiveHelper = undefined;
    var breakpointDefinition = {
        tablet: 1200,
        phone : 480
    };

    $("#btn-create-expense-regular").click(function(){
        window.location.href=$(this).find("#link-to-expense-datatable").val();
    });

    function propagateRowUpdates(context){
        var textInfo = context.text();
        if(context.find("i").length == 0)
        {
            if(textInfo == "")
                context.empty().append("<i class='fa fa-file-pdf-o' style='opacity:0.3;margin-left:20px'></i>");
            else
                context.empty().append("<a class='download-invoice' target='_blank' href='" + $("#url-pdf").val() + "/" +  textInfo + "' style='text-decoration:none;color:inherit;margin-left:15px'><i class='fa fa-file-pdf-o text-grey utility-icon hover-icon-blg'></i></a>");
        }
    }

    function updateAfterFill(){
        var invoice = $("#circle-datatable").find("tr.odd, tr.even");
        var receipt = $("#circle-datatable").find("tr.odd, tr.even");
        var invoiceList = invoice.find("td:nth(3)");
        var receiptList = receipt.find("td:nth(4)");
        invoiceList.each(function(index){ propagateRowUpdates($(this)) });
        receiptList.each(function(index){ propagateRowUpdates($(this)) });

        $("#circle-datatable_wrapper div.col-md-6").first().addClass("col-xs-6");
        $("#circle-datatable_wrapper div.col-md-6").last().addClass("col-xs-6 col-md-6");

        $('#circle-datatable_wrapper .dataTables_filter input').addClass("input-medium "); // modify table search input
        $('#circle-datatable_wrapper .dataTables_length select').addClass("select2-wrapper span12"); // modify table per page dropdown

        $("#search-datatable-placeholder").siblings().attr("placeholder",$("#search-datatable-placeholder").val());

        $(".select2-wrapper").select2({minimumResultsForSearch: -1});

        if(!flag){
            $("#ToolTables_circle-datatable_1").click(function(){
                $("#circle-datatable").tableExport({type:'csv',escape:'false'});
            });
            $("#ToolTables_circle-datatable_2").click(function(){
                $("#circle-datatable").tableExport({type:'excel',escape:'false'});
            });
            $("#ToolTables_circle-datatable_3").click(function(){
                $("#circle-datatable").tableExport({type:'pdf',pdfFontSize:'7', escape:'false', pdfLeftMargin:10});
            });
            $("#ToolTables_circle-datatable_4").click(function(){
                $("#circle-datatable").tableExport({type:'json',escape:'false'});
            });
            flag = true;
        }
    }

    var tableElement = $('#circle-datatable');
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
                    return full[4]
                },
                aTargets: [ 3,4 ]
            },
            {
                "mData": null ,
                "mRender" : function ( data, type, full ) {
                    var text = 'text-danger';
                    var value = full[12];
                    if(full[12] >= full[3]){
                        text = 'text-success';
                        if(full[12] > full[3]){
                            value = full[3];
                        }
                    }
                    return "<span class='" + text + "'>" + value.toFixed(2) + " € </span> / " + full[3].toFixed(2) + " €";
                },
                'aTargets': [ 2 ]
            },
            {
                "mData": null ,
                "mRender" : function ( data, type, full ) {
                    return "<i class='" + full[8] + " p-r-10'></i>" + full[0]
                },
                'aTargets': [ 0 ]
            },
            {
                "mData": null ,
                "mRender" : function ( data, type, full ) {
                    return "<span class='p-r-10'><img src='" + full[9] + "'height='15' width='15'/></span>" + full[1]
                },
                'aTargets': [ 1 ]
            },
            { 'bSortable': false, 'aTargets': [ 3,4 ] }
        ],
        "aaSorting": [[ 0, "asc" ]],
        "oLanguage": {
            "sUrl": "../../assets/jquery-datatable/i18n/" + userLang + ".json"
        },
        bAutoWidth: false,
        "bProcessing": true,
        "sAjaxSource": "/BillMate/circle/expenses?idCircle=" + $("#identifier-circle-expense").val() + "&idUser=" + $("#identifier-user-expense").val(),
        "sAjaxDataProp": "data",
        fnPreDrawCallback: function () {
            if (!responsiveHelper) {
                responsiveHelper = new ResponsiveDatatablesHelper(tableElement, breakpointDefinition);
            }
        },
        fnRowCallback  : function( nRow, aData, iDisplayIndex ) {
            responsiveHelper.createExpandIcon(nRow);
            $(nRow).find("td:nth(3)").click(function(e){
                e.stopPropagation();
            });
            $(nRow).find("td:nth(4)").click(function(e){
                e.stopPropagation();
            });
            $(nRow).click(function(e){
                document.location.href = "/BillMate/expense/show/" + aData[7];
            });
        },
        fnDrawCallback : function (oSettings) {
            responsiveHelper.respond();
            updateAfterFill();
        }
    });

    $("button.show-expense").click(function(){
        window.location.href=$(this).attr("data-value");
    });
});
