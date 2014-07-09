<g:each var="registeredUser" in="${whoIOwe}">
    <g:render template="/expense/widgets/whoIOwe/show" model="[user: user, registeredUser: registeredUser]"/>
</g:each>
