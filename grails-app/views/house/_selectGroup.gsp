<g:if test="${houses.isEmpty()}">
    <g:render template="/house/selectGroup/empty"/>
</g:if>
<g:else>
        <g:render template="/house/selectGroup/index" model="[houses: houses]"/>
</g:else>
