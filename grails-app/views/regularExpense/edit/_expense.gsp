<div class="tiles white m-t-15">
    <div class="tiles-body no-padding">
        <div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
            <h4 class="text-black bold inline uppercase"><g:message code="com.billmate.regularexpense.expense.table" default="Expenses" /></h4>
            <div class="pull-right m-r-10" id="expense-circles-btnNew">
                <h4>
                    <i class="fa fa-plus"></i>
                </h4>
            </div>
        </div>
        <div class="notification-messages no-padding m-t-20">
            <div class="grid simple">
                <div class="grid-body no-border">
                    <g:hiddenField name="identifier-regularexpense-expense" value="${regularExpense.getId()}"/>
                    <g:hiddenField name="identifier-user-expense" value="${registeredUser.getId()}"/>
                    <table class="table table-hover table-condensed" id="circle-expense-datatable">
                        <thead>
                        <tr>
                            <th><g:message code="com.billmate.regularexpense.expense.name" default="Name" /></th>
                            <th data-hide="phone,tablet"><g:message code="com.billmate.regularexpense.expense.responsible" default="Responsible" /></th>
                            <th><g:message code="com.billmate.regularexpense.expense.value" default="Value" /></th>
                            <th><g:message code="com.billmate.regularexpense.expense.begindate" default="Expense date" /></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
