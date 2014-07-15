<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="com.billmate.expense.page.title" default="BillMate - Expense"/></title>
</head>

<body>
<div class="row-fluid">
    <g:render template="/shared/active" model="[active: active]"/>
    <div class="span12">
        <div class="grid simple ">
            <div class="grid-title">
                <h4>
                    <span class="semi-bold uppercase"><g:message code="com.billmate.expense.default.title" default="My EXPENSES" /></span>
                </h4>
            </div>
            <input type="hidden" value="${createLink(controller: 'fileUploader', action: 'show')}" id="url-pdf"/>
            <div class="grid-body ">
                <g:hiddenField name="identifier-user-expense" value="${user.getId()}"/>
                <table class="table table-hover table-condensed" id="users-debt">
                    <thead>
                    <tr>
                        <th><g:message code="com.billmate.expense.default.name" default="Name" /></th>
                        <th data-hide="phone,tablet"><g:message code="com.billmate.expense.default.responsible" default="Responsible" /></th>
                        <th data-hide="phone,tablet"><g:message code="com.billmate.expense.default.circle" default="Circle" /></th>
                        <th><g:message code="com.billmate.expense.default.payvalue" default="Paid value" /></th>
                        <th><g:message code="com.billmate.expense.default.total" default="Total" /></th>
                        <th data-hide="phone,tablet"><g:message code="com.billmate.expense.default.invoice" default="Invoice" /></th>
                        <th data-hide="phone,tablet"><g:message code="com.billmate.expense.default.receipt" default="Receipt" /></th>
                        <th><g:message code="com.billmate.expense.default.paid" default="Paid" /></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
