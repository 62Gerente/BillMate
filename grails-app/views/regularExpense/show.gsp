<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="com.billmate.regularExpense.show.page.title" default="BillMate - ${0}" args="[expense]" /></title>

    <asset:stylesheet href="../plugins/bootstrap-editable/bootstrap-editable.css"/>
</head>
<body>
<div class="col-md-12">
    <g:render template="/shared/messages"/>
</div>
<div class="col-md-8 col-xs-12 col-sm-12 col-lg-8">
    <g:render template="/shared/alert" model="[cssClass: 'col8-alert', display: 'none']"/>
</div>
<div class="col-md-4 col-xs-12 col-sm-12 col-lg-4">
    <g:render template="/regularExpense/edit/description" model="[registeredUser: user, regularExpense: regularExpense]"/>
    <g:render template="/regularExpense/edit/directDebit" model="[directDebit: directDebit]"/>
    <g:render template="/action/widgets/latest" model="[latestEvents: regularExpense.latestEvents()]"/>
</div>
<asset:javascript src="bm-dashboard.js"/>
<asset:javascript src="bm-ajax-invoice.js"/>
<asset:javascript src="../plugins/moment/moment.min.js"/>
<asset:javascript src="../plugins/bootstrap-editable/bootstrap-editable.min.js"/>
<asset:javascript src="../plugins/confirm-bootstrap/confirm-bootstrap.js"/>
<asset:javascript src="bm-regularExpense.js"/>
</body>
</html>
