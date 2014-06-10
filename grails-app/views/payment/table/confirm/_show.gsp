<tr>
    <td><i style="width: 15px" class="${payment.expense.getExpenseType().getCssClass()}"></i> ${payment.expense}</td>
    <td class="unseen">${payment.expense.getCircle()}</td>
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
                <input type="checkbox" id="id1" class="todo-list">
                <label class="pull-right" for="id1" style="margin-right: 20px" checked></label>
            </div>
        </div>
    </td>
</tr>
