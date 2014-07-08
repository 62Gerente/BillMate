<div class="tiles white m-t-15">
    <div class="tiles-body no-padding">
        <div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
            <h4 class="text-black bold inline uppercase"><g:message code="com.billmate.circle.expense.table" default="Monthly expenses" /></h4>
        </div>
        <div class="notification-messages no-padding m-t-20">
            <div class="grid simple">
                <div class="grid-body no-border">
                    <g:hiddenField name="identifier-circle-expense" value="${circle.getId()}"/>
                    <g:hiddenField name="identifier-user-expense" value="${registeredUser.getId()}"/>
                    <table class="table table-hover table-condensed" id="circle-datatable">
                        <thead>
                        <tr>
                            <th><g:message code="com.billmate.circle.expense.name" default="Name" /></th>
                            <th data-hide="phone,tablet"><g:message code="com.billmate.circle.expense.responsible" default="Responsible" /></th>
                            <th><g:message code="com.billmate.circle.expense.payvalue" default="Paid value" /></th>
                            <th><g:message code="com.billmate.circle.expense.total" default="Total" /></th>
                            <th data-hide="phone,tablet"><g:message code="com.billmate.circle.expense.invoice" default="Invoice" /></th>
                            <th data-hide="phone,tablet"><g:message code="com.billmate.circle.expense.receipt" default="Receipt" /></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
