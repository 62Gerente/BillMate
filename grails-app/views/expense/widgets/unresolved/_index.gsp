<g:each var="expense" in="${expenses}">
    <g:render template="/expense/widgets/unresolved/show" model="[registeredUser: registeredUser, expense: expense]"/>
</g:each>
