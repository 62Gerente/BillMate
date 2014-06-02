<table class="table table-home-box">
    <thead>
    <tr>
        <th class="text-left text-primary uppercase"><g:message code="com.billmate.expense_type" default="Expense type" /></th>
        <th class="text-left unseen text-primary uppercase"><g:message code="com.billmate.circle" default="Circle" /></th>
        <th class="unseen text-primary uppercase"><g:message code="com.billmate.expense.payment_deadline" default="Payment deadline" /></th>
        <th class="text-right text-primary uppercase"><g:message code="com.billmate.expense.value" default="Value" /></th>
    </tr>
    </thead>
    <tbody>
    <g:if test="${unresolvedExpenses.isEmpty()}">
        <g:render template="/expense/table/user_dashboard/empty"/>
    </g:if>
    <g:else>
        <g:render template="/expense/table/user_dashboard/index" model="[user: user, unresolvedExpenses: unresolvedExpenses]"/>

    </g:else>
    </tbody>
</table>

<div id="home-button-divida-secondary-confirm" class="m-b-10">
    <button type="button" class="btn btn-default btn-small" style="width: 100%">
        <g:message code="com.billmate.payment.new" default="Pay it now" />
    </button>
</div>