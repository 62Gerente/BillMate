<g:if test="${actionsType.isEmpty()}">
    <g:render template="/actionType/selectGroup/empty"/>
</g:if>
<g:else>
    <g:render template="/actionType/selectGroup/index" model="[actionsType: actionsType]"/>
</g:else>
