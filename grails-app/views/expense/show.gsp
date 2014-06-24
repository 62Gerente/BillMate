<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="com.billmate.expense.show.page.title" default="BillMate - ${0}" args="[expense]" /></title>
</head>
<body>
    <div class="col-md-8 col-xs-12 col-sm-12 col-lg-8">
        <g:render template="/expense/widgets/paymentHistory" model="[registeredUser: user, expense: expense, users: expense.getAssignedUsers()]"/>
    </div>
    <div class="col-md-4 col-xs-12 col-sm-12 col-lg-4">
        <g:render template="/expense/show/description" model="[registeredUser: user, expense: expense]"/>
        <g:render template="/action/widgets/latest" model="[latestEvents: expense.latestEvents()]"/>
    </div>
<asset:javascript src="bm-dashboard.js"/>
</body>
</html>
