<g:each var="user" in="${users}">
    <g:render template="/user/table/show" model="[user: user]"/>
</g:each>
