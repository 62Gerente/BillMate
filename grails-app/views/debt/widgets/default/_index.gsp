<g:each var="debt" in="${debts}">
    <g:render template="/debt/widgets/default/show" model="[registeredUser: registeredUser, debt: debt]"/>
</g:each>
