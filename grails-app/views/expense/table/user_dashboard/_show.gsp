<tr>
    <td><i style="width: 15px" class="${expense.getExpenseType().getCssClass()}"></i> ${expense.getExpenseType().getName()}</td>
    <td class="unseen">${expense.getCircle()}</td>
    <td class="unseen">${expense.getPaymentDeadline()}
        <g:if test="${expense.getPaymentDeadline()}">
            ${expense.getPaymentDeadline()}
        </g:if>
        <g:else>
            <g:message code="com.billmate.expense.date.not_defined" default="Not defined" />
        </g:else>
    </td>
    <td class="text-right"><g:formatNumber number="${expense.debtOf(user.getId())}" type="currency" currencyCode="EUR" /></td>
</tr>