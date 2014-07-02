<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="com.billmate.user.history.page.title" default="BillMate - Edit User" /></title>

    <asset:stylesheet href="bm-history.css"/>
</head>

<body>
<div class="row">
    <div class="col-md-10 col-vlg-7">
        <g:render template="/registeredUser/history/index" model="[registeredUser: registeredUser, actions: actions]"/>
    </div>
</div>
</body>
</html>
