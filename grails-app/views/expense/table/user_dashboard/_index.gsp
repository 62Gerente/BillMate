<g:each var="expense" in="${unresolvedExpenses}">
    <g:render template="/expense/table/user_dashboard/show" model="[user: user, expense: expense]"/>
</g:each>
