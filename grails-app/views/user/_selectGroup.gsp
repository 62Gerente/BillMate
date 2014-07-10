<g:if test="${users.isEmpty()}">
    <g:render template="/user/selectGroup/empty"/>
</g:if>
<g:else>
    <g:render template="/user/selectGroup/index" model="[users: users]"/>
</g:else>
