<g:if test="${actionsType.isEmpty()}">
    <g:render template="/action/selectGroup/empty"/>
</g:if>
<g:else>
    <g:render template="/action/selectGroup/index" model="[actionsType: actionsType]"/>
</g:else>
