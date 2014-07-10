<body>
    <div class="row-fluid">
        <div class="span12">
            <div class="grid simple ">
                <div class="grid-title">
                    <h4>
                        <span class="semi-bold"><g:message code="com.billmate.circle.expense.title" default="My expenses" /></span>
                    </h4>
                </div>

                <div class="grid-body ">
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
                                <th><g:message code="com.billmate.circle.expense.paid" default="Paid" /></th>
                            </tr>
                        </thead>
                    </table>

                </div>
            </div>
        </div>
    </div>
</body>
