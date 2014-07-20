if (typeof Number.prototype.format === 'undefined') {
    Number.prototype.format = function (precision) {
        if (!isFinite(this)) {
            return this.toString();
        }
        var a = this.toFixed(precision).split('.'), head = Number(this < 0);
        head += (a[0].length - head) % 3 || 3;
        a[0] = a[0].slice(0, head) + a[0].slice(head).replace(/\d{3}/g, ',$&');
        return a.join('.');
    };
}

$( document ).ready(function() {
    $("#print-report").on("click", function(){
        window.print()
    });

    $(".filter-select").on("change", function(){
        var form = $("#filters-form");
        var reportTotal = $("#report-table-total");
        var reportMyTotal = $("#report-table-my-total");
        var reportRows = $(".report-table-row");
        var invoicesRows = $(".invoices-table-row");
        var reportTableBody =  $("#report-table").children("tbody");
        var invoicesTableBody =  $("#invoices-table").children("tbody");

        $.ajax({
            url: form.attr('action'),
            data: form.serializeArray(),
            type: "POST",
            dataType: 'json',
            success: function (data) {
                var total = 0;
                var myTotal = 0;

                reportRows.remove();
                invoicesRows.remove();

                if(data.error){
                    reportTableBody.prepend('<tr class="report-table-row"><td colspan="4">' + data.error + '</td></tr>');
                    invoicesTableBody.prepend('<tr class="invoices-table-row"><td colspan="3">' + data.error + '</td></tr>');
                }else{
                    var expenses = data.expenses;

                    for (var index = 0; index < expenses.length; ++index) {
                        var expense = expenses[index];
                        var beginDate = expense.beginDate
                        var rowTotal = parseFloat(expense.total);
                        var rowQuota = parseFloat(expense.myQuota);

                        var newReportRow = '<tr class="report-table-row"><td><i class="' + expense.cssClass + '" style="width:20px"></i>' + expense.title + '</td><td class="unseen text-left">' + beginDate + '</td><td class="text-right">' + rowQuota.format(2) + '€</td><td class="text-right">' + rowTotal.format(2) + '€</td></tr>';
                        var newInvoicesRow = '<tr class="invoices-table-row"><td>' + expense.title + '</td><td class="text-center">';
                        if(expense.invoice){
                            newInvoicesRow += '<a href=/fileUploader/show/"' + expense.invoice + '"><i class="fa fa-file-pdf-o"></i></a>';
                        }else{
                            newInvoicesRow += '<i class="fa fa-file-pdf-o" style="opacity:0.3;margin-left:20px"></i>';
                        }
                        newInvoicesRow += '</td><td class="text-center">';
                        if(expense.receipt){
                            newInvoicesRow += '<a href=/fileUploader/show/"' + expense.receipt + '"><i class="fa fa-file-pdf-o"></i></a>';
                        }else{
                            newInvoicesRow += '<i class="fa fa-file-pdf-o" style="opacity:0.3;margin-left:20px"></i>';
                        }
                        newInvoicesRow += '</td></tr>';

                        reportTableBody.prepend(newReportRow);
                        invoicesTableBody.prepend(newInvoicesRow);

                        total += rowTotal;
                        myTotal += rowQuota;
                    }
                }

                reportTotal.text(total.format(2) + " €")
                reportMyTotal.text(myTotal.format(2) + " €")
            },
            error: function () {
            }
        });
    });
});
