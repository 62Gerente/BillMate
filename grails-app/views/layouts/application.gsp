<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <meta charset="utf-8" />
    <title><g:layoutTitle default="BillMate"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta content="" name="description" />

    <asset:stylesheet href="bm-application.css"/>
    <g:layoutHead/>
    <asset:javascript src="bm-application.js"/>
</head>
<body>
    <g:render template="/shared/header" model="[user: user]"/>
    <g:render template="/systemNotification/index" model="[registeredUser: user, notifications: user.getSystemNotifications()]"/>

    <div class="page-container row-fluid">
      <g:render template="/shared/sidebar" model="[user: user]"/>

      <div class="page-content">
        <div class="clearfix"></div>
        <div class="content">
          <g:layoutBody/>
        </div>
      </div>
    </div>
</body>
</html>