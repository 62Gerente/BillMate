<tr class="single-payment">
    <td><i style="width: 15px" class="${payment.expense.getExpenseType().getCssClass()}"></i> ${payment.expense}</td>
    <td class="unseen">${payment.getExpense().getCircle()}</td>
    <td class="unseen">
    <g:if test="${payment.getDate()}">
        <g:formatDate date="${payment.getDate()}" type="datetime" style="SMALL"/>
    </g:if>
    <g:else>
        <g:message code="com.billmate.payment.date.notDefined" default="Not defined" />
    </g:else>
    </td>
    <td class="text-right"><g:formatNumber number="${payment.getValue()}" type="currency" currencyCode="EUR" /></td>
    <td>
        <div class="row-fluid home-check-confirm">
            <div class="checkbox check-success">
                <input type="checkbox" id="payment${payment.getId()}" name="payment[]" value="${payment.getId()}" class="todo-list" checked>
                <label class="pull-right" for="payment${payment.getId()}" style="margin-right: 20px"></label>
            </div>
        </div>
    </td>
</tr>
