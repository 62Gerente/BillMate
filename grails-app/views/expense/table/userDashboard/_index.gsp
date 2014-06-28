<g:each var="expense" in="${unresolvedExpenses}">
    <g:render template="/expense/table/userDashboard/show" model="[user: user, expense: expense]"/>
</g:each>
