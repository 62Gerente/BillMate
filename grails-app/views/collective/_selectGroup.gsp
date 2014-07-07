<g:if test="${collectives.isEmpty()}">
    <g:render template="/collective/selectGroup/empty"/>
</g:if>
<g:else>
    <g:render template="/collective/selectGroup/index" model="[collective: collectives]"/>
</g:else>
