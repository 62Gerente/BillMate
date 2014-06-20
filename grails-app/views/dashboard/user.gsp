<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="com.billmate.userDashboard.page.title" default="BillMate - User Dashboard" /></title>
</head>
<body>
    <div class="col-md-8 col-xs-12 col-sm-12 col-lg-8">
        <g:render template="/shared/alert" model="[cssClass: 'col8-alert', display: 'none']"/>
        <g:render template="/payment/widgets/confirm" model="[registeredUser: dashboard.getRegisteredUser(), whoHaveUnconfirmedPayments: dashboard.whoHaveUnconfirmedPayments()]" />
        <g:render template="/expense/widgets/whoIOwe" model="[user: dashboard.getUser(), totalDebt: dashboard.totalDebt(), whoIOwe: dashboard.whoIOwe()]"/>
        <g:render template="/expense/widgets/whoOweMe" model="[registeredUser: dashboard.getRegisteredUser(), totalAsset: dashboard.totalAsset(), whoOweMe: dashboard.whoOweMe()]"/>
        <g:render template="/expense/charts/monthlySpending" model="[entity: dashboard.getUser(), expenseTypes: dashboard.expenseTypesWithMoreSpendingInLastMonths()]"/>
    </div>
    <div class="col-md-4 col-xs-12 col-sm-12 col-lg-4">
        <div class="row m-b-20">
            <g:render template="/expense/widgets/balance/index" model="[balance: dashboard.getTotalBalance()]"/>
            <g:render template="/expense/widgets/debt/index" model="[balance: dashboard.getTotalBalanceExpensesWhoIAmNotResponsible()]"/>
        </div>
        <g:render template="/shared/alert" model="[cssClass: 'col4-alert', display: 'none']"/>
        <g:render template="/regularExpense/widgets/upcoming" model="[regularExpenses: dashboard.regularExpensesInReceptionTime()]"/>
        <g:render template="/action/widgets/latest" model="[latestEvents: dashboard.latestEvents()]"/>
    </div>
<asset:javascript src="bm-dashboard.js"/>
</body>
</html>
