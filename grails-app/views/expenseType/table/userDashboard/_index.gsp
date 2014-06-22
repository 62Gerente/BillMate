<g:each var="expense" in="${unresolvedExpenses}">
    <g:render template="/expenseType/table/userDashboard/show" model="[user: user, expense: expense]"/>
</g:each>
