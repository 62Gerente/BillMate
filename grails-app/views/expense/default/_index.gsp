<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
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
                <table class="table table-hover table-condensed" id="users-debt">
                    <thead>
                    <tr>
                        <th><g:message code="com.billmate.expense.default.name" default="NAME" /></th>
                        <th data-hide="phone,tablet"><g:message code="com.billmate.expense.default.responsible" default="RESPONSIBLE" /></th>
                        <th data-hide="phone,tablet"><g:message code="com.billmate.expense.default.circle" default="CIRCLE" /></th>
                        <th><g:message code="com.billmate.expense.default.payvalue" default="PAID VALUE" /></th>
                        <th><g:message code="com.billmate.expense.default.total" default="TOTAL" /></th>
                        <th data-hide="phone,tablet"><g:message code="com.billmate.expense.default.paymentdate" default="PAYMENT DATE" /></th>
                        <th><g:message code="com.billmate.expense.default.invoice" default="INVOICE" /></th>
                        <th><g:message code="com.billmate.expense.default.receipt" default="RECEIPT" /></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>