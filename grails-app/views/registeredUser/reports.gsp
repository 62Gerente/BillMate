<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="com.billmate.registeredUser.reports.page.title" default="BillMate - Reports"/></title>

    <asset:stylesheet href="bm-reports.css"/>
</head>
<body>
    <g:render template="/shared/active" model="[active: active]"/>
    <div class="col-md-8 col-lg-8">
        <g:render template="/expense/report" model="[registeredUser: user, expenses: userReports.expensesOfMonth(), total: userReports.totalValueOfExpensesOfMonth(), myTotal: userReports.myQuotaOfTotalValueOfMonth()]"></g:render>
    </div>
    <div class="col-md-4 col-lg-4">
        <g:render template="/expense/report/filters" model="[registeredUser: user, expenseTypes: userReports.expenseTypes(), houses: userReports.houses(), collectives: userReports.collectives()]" />
        <g:render template="/expense/report/invoices" model="[expenses: userReports.expensesOfMonth()]"/>
    </div>
<asset:javascript src="../plugins/jquery-print/jquery.printElement.js"/>
<asset:javascript src="bm-reports.js"/>
</body>
</html>
