<g:each var="expenseType" in="${expenseTypes}">
    <g:render template="/expenseType/index/show" model="[registeredUser: registeredUser, expenseType: expenseType]"/>
</g:each>
