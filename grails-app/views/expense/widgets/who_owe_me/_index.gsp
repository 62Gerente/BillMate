<g:each var="user" in="${whoOweMe}">
    <g:render template="/expense/widgets/who_owe_me/show" model="[registeredUser: registeredUser, user: user]"/>
</g:each>
