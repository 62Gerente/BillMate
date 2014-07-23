<tr class="invoices-table-row">
    <td style="white-space: nowrap">
        ${expense}
    </td>
    <td class="text-center">
        <g:if test="${expense.getInvoice()}">
            <a href="${createLink(controller: "fileUploader", action: "show", id: expense.getInvoice().getId())}"><i class="fa fa-file-pdf-o"></i></a>
        </g:if>
        <g:else>
            <i class="fa fa-file-pdf-o" style="opacity:0.3;margin-left:20px"></i>
        </g:else>
    </td>
    <td class="text-center">
        <g:if test="${expense.getReceipt()}">
            <a href="${createLink(controller: "fileUploader", action: "show", id: expense.getReceipt().getId())}"><i class="fa fa-file-pdf-o"></i></a>
        </g:if>
        <g:else>
            <i class="fa fa-file-pdf-o" style="opacity:0.3;margin-left:20px"></i>
        </g:else>
    </td>
</tr>
