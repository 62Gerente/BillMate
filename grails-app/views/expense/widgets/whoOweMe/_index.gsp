<g:each var="user" in="${whoOweMe}">
    <g:render template="/expense/widgets/whoOweMe/show" model="[registeredUser: registeredUser, user: user]"/>
</g:each>
