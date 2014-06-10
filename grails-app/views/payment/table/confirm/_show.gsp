<tr>
    <td><i style="width: 15px" class="${payment.expense.getExpenseType().getCssClass()}"></i> ${payment.expense}</td>
    <td class="unseen">${payment.expense.getCircle()}</td>
    <td class="unseen">
    <g:if test="${payment.expense.getPaymentDeadline()}">
        ${payment.expense.getPaymentDeadline()}
    </g:if>
    <g:else>
        <g:message code="com.billmate.expense.date.notDefined" default="Not defined" />
    </g:else>
    </td>
    <td class="text-right"><g:formatNumber number="${payment.getValue()}" type="currency" currencyCode="EUR" /></td>
</tr>
