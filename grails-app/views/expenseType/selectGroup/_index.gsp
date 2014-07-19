<g:each var="expenseType" in="${expenseTypes}">
    <g:render template="/expenseType/selectGroup/show" model="[expenseType: expenseType]"/>
</g:each>
