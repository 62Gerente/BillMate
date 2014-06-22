<g:each var="user" in="${users}">
    <g:render template="/expense/table/unresolved/show" model="[registeredUser: registeredUser, user: user, expense: expense]"/>
</g:each>
