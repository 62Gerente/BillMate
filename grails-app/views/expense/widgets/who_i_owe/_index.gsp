<g:each var="registeredUser" in="${whoIOwe}">
    <g:render template="/expense/widgets/who_i_owe/show" model="[user: user, registeredUser: registeredUser]"/>
</g:each>
