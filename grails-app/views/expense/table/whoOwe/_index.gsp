<g:each var="expense" in="${unresolvedExpenses}">
    <g:render template="/expense/table/whoOwe/show" model="[user: user, expense: expense]"/>
</g:each>
