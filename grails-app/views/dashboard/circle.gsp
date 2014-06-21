<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="com.billmate.circleDashboard.page.title" default="BillMate - {0}" args="${[dashboard.getCircle()]}" /></title>

    <asset:stylesheet src="bm-circle-dashboard.css"/>
</head>
<body>
<div class="col-md-12">
    <g:render template="/shared/messages"/>
</div>
<div class="col-md-8 col-xs-12 col-sm-12 col-lg-8">
    <g:render template="/expense/widgets/unresolved" model="[registeredUser: user, circle: dashboard.getCircle(), expenses: dashboard.unresolvedExpenses()]"/>
    <g:render template="/expense/charts/monthlySpending" model="[entity: dashboard.getCircle(), expenseTypes: dashboard.expenseTypesWithMoreSpendingInLastMonths()]"/>
</div>
<div class="col-md-4 col-xs-12 col-sm-12 col-lg-4">
    <g:render template="/circle/dashboard/description" model="[registeredUser: user, circle: dashboard.getCircle()]"/>
    <g:render template="/regularExpense/widgets/upcoming" model="[regularExpenses: dashboard.regularExpensesInReceptionTime(), user: user]"/>
    <g:render template="/action/widgets/latest" model="[latestEvents: dashboard.latestEvents()]"/>
</div>
<asset:javascript src="bm-dashboard.js"/>
</body>
</html>
