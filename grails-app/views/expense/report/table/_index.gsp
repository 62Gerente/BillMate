<g:each var="expense" in="${expenses}">
    <g:render template="/expense/report/table/show" model="[registeredUser: registeredUser, expense: expense]"/>
</g:each>
