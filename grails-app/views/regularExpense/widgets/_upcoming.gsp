<div class="row m-b-20">
  <div class="col-md-12">
      <g:render template="/shared/alert" model="[cssClass: 'col4-alert', display: 'none']"/>
  </div>
  <g:render template="/regularExpense/widgets/upcoming/index" model="[regularExpenses: regularExpenses]"/>
</div>
