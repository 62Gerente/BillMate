<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="com.billmate.user.edit.page.title" default="BillMate - Edit User" /></title>

    <asset:stylesheet href="bm-profile.css"/>
    <asset:stylesheet href="../plugins/ios-switch/ios7-switch.css"/>
    <asset:stylesheet href="../plugins/bootstrap-editable/bootstrap-editable.css"/>
</head>

<body>
<div class="row">
    <div class="col-md-7">
        <g:render template="/registeredUser/edit/profile" model="[user: registeredUser]"/>
        <g:render template="/circle/profile" model="[circles: circles, registeredUser: registeredUser]"/>
    </div>
    <div class="col-md-5">
        <g:render template="/registeredUser/edit/notifications" />
    </div>

<asset:javascript src="../plugins/bootstrap-editable/bootstrap-editable.min.js"/>
<asset:javascript src="bm-profile.js"/>
</div>
</body>
</html>
