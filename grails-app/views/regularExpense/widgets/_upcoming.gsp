<g:if test="${!regularExpenses.isEmpty()}">
  <div class="row m-b-20 unselectable">
    <g:render template="/regularExpense/widgets/upcoming/index" model="[regularExpenses: regularExpenses]"/>
  </div>
</g:if>