<tr>
    <td><i style="width: 15px" class="${expense.getExpenseType().getCssClass()}"></i> ${expense}</td>
    <td class="unseen">${expense.getCircle()}</td>
    <td class="unseen">
        <g:if test="${expense.getPaymentDeadline()}">
            <g:formatDate date="${expense.getPaymentDeadline()}" type="datetime" style="SMALL"/>
        </g:if>
        <g:else>
            <g:message code="com.billmate.expense.date.notDefined" default="Not defined" />
        </g:else>
    </td>
    <td class="text-right"><g:formatNumber number="${expense.amountInDebtOf(user.getId())}" type="currency" currencyCode="EUR" /></td>
</tr>
