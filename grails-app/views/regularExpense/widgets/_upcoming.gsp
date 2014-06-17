<g:if test="${!regularExpenses.isEmpty()}">
  <div class="row m-b-20">
    <g:render template="/regularExpense/widgets/upcoming/index" model="[regularExpenses: regularExpenses]"/>
  </div>
</g:if>