<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <g:render template="/shared/active" model="[active: active]"/>
    <title><g:message code="com.billmate.circleDashboard.page.title" default="BillMate - ${0} settings" args="[circle]" /></title>

    <asset:stylesheet href="../plugins/bootstrap-editable/bootstrap-editable.css"/>
    <asset:stylesheet src="bm-circle-dashboard.css"/>
</head>
<body>
    <div class="col-md-12">
        <g:render template="/shared/messages"/>
        <g:render template="/shared/alert" model="[cssClass: 'col8-alert', display: 'none']"/>
    </div>
    <div class="col-md-8 col-xs-12 col-sm-12 col-lg-8">
        <g:render template="/user/widgets/circleUsers" model="[registeredUser: user, users: circle.getUsers(), circle: circle]"/>
        <g:render template="/circle/edit/regularexpense" model="[registeredUser: user, users: circle.getUsers(), circle: circle]"/>
    </div>
    <div class="col-md-4 col-xs-12 col-sm-12 col-lg-4">
        <g:render template="/circle/edit/information" model="[registeredUser: user, circle: circle]"/>
        <g:render template="/expenseType/index" model="[registeredUser: user, expenseTypes: circle.getExpenseTypes(), circle: circle]"/>
    </div>

    <asset:javascript src="bm-dashboard.js"/>
    <asset:javascript src="../plugins/moment/moment.min.js"/>
    <asset:javascript src="../plugins/bootstrap-editable/bootstrap-editable.min.js"/>
    <asset:javascript src="../plugins/confirm-bootstrap/confirm-bootstrap.js"/>
    <asset:javascript src="bm-circle-settings.js"/>
</body>
</html>
