<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="com.billmate.expense.page.title" default="BillMate - Expense"/></title>
    <asset:stylesheet src="../plugins/jquery-datatable/css/jquery.dataTables.css"/>
    <asset:stylesheet src="../plugins/boostrap-checkbox/css/bootstrap-checkbox.css"/>
    <asset:stylesheet src="../plugins/datatables-responsive/css/datatables.responsive.css"/>
    <asset:stylesheet src="../stylesheets/bm-debt-page.css"/>
</head>

<body>
<div class="row-fluid">
    <div class="span12">
        <div class="grid simple ">
            <div class="grid-title">
                <h4>
                    <span class="semi-bold"><g:message code="com.billmate.expense.default.title" default="MY EXPENSES" /></span>
                </h4>
            </div>

            <div class="grid-body ">
                <g:hiddenField name="identifier-user-expense" value="${user.getId()}"/>
                <table class="table table-hover table-condensed" id="users-debt">
                    <thead>
                    <tr>
                        <th><g:message code="com.billmate.expense.default.name" default="NAME" /></th>
                        <th data-hide="phone,tablet"><g:message code="com.billmate.expense.default.responsible" default="RESPONSIBLE" /></th>
                        <th data-hide="phone,tablet"><g:message code="com.billmate.expense.default.circle" default="CIRCLE" /></th>
                        <th><g:message code="com.billmate.expense.default.payvalue" default="PAID VALUE" /></th>
                        <th><g:message code="com.billmate.expense.default.total" default="TOTAL" /></th>
                        <th data-hide="phone,tablet"><g:message code="com.billmate.expense.default.invoice" default="INVOICE" /></th>
                        <th data-hide="phone,tablet"><g:message code="com.billmate.expense.default.receipt" default="RECEIPT" /></th>
                        <th><g:message code="com.billmate.expense.default.paid" default="PAID" /></th>
                    </tr>
                    </thead>
                </table>

            </div>
        </div>
    </div>
</div>
</body>
</html>
