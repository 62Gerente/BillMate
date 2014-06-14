<div class="row m-b-20">
  <div class="col-md-12">
      <g:render template="/shared/alert" model="[cssClass: 'upcoming-regular-expense-alert', display: 'none']"/>
  </div>
  <g:render template="/regularExpense/widgets/upcoming/index" model="[regularExpenses: regularExpenses]"/>
</div>
