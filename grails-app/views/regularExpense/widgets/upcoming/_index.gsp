<g:each var="regularExpense" in="${regularExpenses}">
    <g:render template="/regularExpense/widgets/upcoming/show" model="[regularExpense]"/>
</g:each>