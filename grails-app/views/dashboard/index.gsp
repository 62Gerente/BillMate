<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>
<body>

<g:render template="/notifications/notifications" model="[actions: dashboard.getActions()]"/>

<asset:javascript src="bm-flot-chart.js"/>
<asset:javascript src="bm-dashboard.js"/>
</body>
</html>