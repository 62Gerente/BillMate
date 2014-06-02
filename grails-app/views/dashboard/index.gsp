<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="com.billmate.user_dashboard.page.title" default="BillMate - User Dashboard" /></title>
</head>
<body>
    <div class="col-md-8 col-xs-12 col-sm-12 col-lg-8">
      <g:render template="/expense/widgets/who_i_owe" model="[user: dashboard.getUser(), totalDebt: dashboard.totalDebt(), whoIOwe: dashboard.whoIOwe()]"/>
      <g:render template="/expense/widgets/who_owe_me" model="[registeredUser: dashboard.getRegisteredUser(), totalAsset: dashboard.totalAsset(), whoOweMe: dashboard.whoOweMe()]"/>
    </div>
<asset:javascript src="bm-flot-chart.js"/>
<asset:javascript src="bm-dashboard.js"/>
</body>
</html>
