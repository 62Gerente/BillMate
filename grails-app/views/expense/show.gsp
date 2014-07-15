<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="com.billmate.expense.show.page.title" default="BillMate - ${0}" args="[expense]" /></title>

    <asset:stylesheet href="../plugins/bootstrap-editable/bootstrap-editable.css"/>
</head>
<body>
    <div class="col-md-12">
        <g:render template="/shared/messages"/>
    </div>
    <div class="col-md-8 col-xs-12 col-sm-12 col-lg-8">
        <g:render template="/shared/active" model="[active: active]"/>
        <g:render template="/shared/alert" model="[cssClass: 'col8-alert', display: 'none']"/>
        <g:render template="/expense/widgets/paymentHistory" model="[registeredUser: user, expense: expense, users: expense.getAssignedUsers()]"/>
        <div class="row">
            <g:render template="/expense/edit/invoice" model="[registeredUser: user, expense: expense]"/>
            <g:render template="/expense/edit/receipt" model="[registeredUser: user, expense: expense]"/>
        </div>
    </div>
    <div class="col-md-4 col-xs-12 col-sm-12 col-lg-4">
        <g:render template="/expense/edit/description" model="[registeredUser: user, expense: expense]"/>
        <g:render template="/action/widgets/latest" model="[latestEvents: expense.latestEvents()]"/>
    </div>
<asset:javascript src="bm-dashboard.js"/>
<asset:javascript src="bm-ajax-invoice.js"/>
<asset:javascript src="../plugins/moment/moment.min.js"/>
<asset:javascript src="../plugins/bootstrap-editable/bootstrap-editable.min.js"/>
<asset:javascript src="../plugins/confirm-bootstrap/confirm-bootstrap.js"/>
<asset:javascript src="bm-expense.js"/>
</body>
</html>
