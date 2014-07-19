<tr class="report-table-row">
    <td>
        <i class="${expense.getExpenseType().getCssClass()}" style="width:20px"></i>
        ${expense}
    </td>
    <td class="unseen text-left">
        <g:formatDate date="${expense.getBeginDate()}" type="date" style="SHORT"/>
    </td>
    <td class="text-right">
        <g:formatNumber number="${expense.amountAssignedTo(registeredUser.getUserId())}" type="currency" currencyCode="EUR" />
    </td>
    <td class="text-right">
        <g:formatNumber number="${expense.getValue()}" type="currency" currencyCode="EUR" />
    </td>
</tr>
