<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="com.billmate.userDashboard.page.title" default="BillMate - User Dashboard" /></title>
</head>
<body>
    <div class="col-md-8 col-xs-12 col-sm-12 col-lg-8">
        <g:render template="/payment/widgets/confirm" model="[unconfirmedPayments: dashboard.unconfirmedPayments()]" />
        <g:render template="/expense/widgets/whoIOwe" model="[user: dashboard.getUser(), totalDebt: dashboard.totalDebt(), whoIOwe: dashboard.whoIOwe()]"/>
        <g:render template="/expense/widgets/whoOweMe" model="[registeredUser: dashboard.getRegisteredUser(), totalAsset: dashboard.totalAsset(), whoOweMe: dashboard.whoOweMe()]"/>
    </div>
    <div class="col-md-4 col-xs-12 col-sm-12 col-lg-4">
        <g:render template="/regularExpense/widgets/upcoming" model="[]"/>
    </div>
<asset:javascript src="bm-flot-chart.js"/>
<asset:javascript src="bm-dashboard.js"/>
</body>
</html>
