<g:if test="${expenseTypes.isEmpty()}">
    <g:render template="/expenseType/selectGroup/empty"/>
</g:if>
<g:else>
    <g:render template="/expenseType/selectGroup/index" model="[expenseTypes: expenseTypes]"/>
</g:else>
