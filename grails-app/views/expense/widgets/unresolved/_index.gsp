<g:each var="user" in="${['André', 'António']}">
    <g:render template="/expense/widgets/active/show" model="[user: user]"/>
</g:each>
