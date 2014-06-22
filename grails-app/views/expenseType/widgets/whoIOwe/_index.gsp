<g:each var="registeredUser" in="${whoIOwe}">
    <g:render template="/expenseType/widgets/whoIOwe/show" model="[user: user, registeredUser: registeredUser]"/>
</g:each>
