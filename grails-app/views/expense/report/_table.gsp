<table class="table m-t-50">
    <thead>
    <tr>
        <th class="text-left"><g:message code="com.billmate.expense" default="Expense"/></th>
        <th style="width:180px" class="unseen text-left"><g:message code="com.billmate.expense.date" default="Expense date"/></th>
        <th style="width:90px" class="text-right"><g:message code="com.billmate.expense.value.assignedToMe" default="My quota"/></th>
        <th style="width:90px" class="text-right"><g:message code="com.billmate.expense.total" default="Total"/></th>
    </tr>
    </thead>
    <tbody>
        <g:if test="${expenses.isEmpty()}">
            <g:render template="/expense/report/table/empty" model="[]"/>
        </g:if>
        <g:else>
            <g:render template="/expense/report/table/index" model="[registeredUser: registeredUser, expenses: expenses]"/>
            <tr>
                <td class="unseen no-border"></td>
                <td class="text-right no-border">
                    <strong>
                        <g:message code="com.billmate.expense.total" default="Total"/>
                    </strong>
                </td>
                <td class="text-right">
                    <g:formatNumber number="${myTotal}" type="currency" currencyCode="EUR" />
                </td>
                <td class="text-right">
                    <g:formatNumber number="${total}" type="currency" currencyCode="EUR" />
                </td>
            </tr>
        </g:else>
    </tbody>
</table>
