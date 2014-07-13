<%@ page import="com.billmate.CircleType" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <meta charset="utf-8" />
    <title><g:layoutTitle default="BillMate"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta content="" name="description" />

    <asset:stylesheet href="bm-application.css"/>
    <asset:javascript src="bm-application.js"/>
    <g:layoutHead/>
</head>
<body>
    <g:render template="/shared/header" model="[user: user]"/>
    <g:render template="/systemNotification/index" model="[registeredUser: user, notifications: user.getSystemNotifications()]"/>

    <div class="page-container row-fluid">
    <g:render template="/house/modal/index" model="[users: user.getFriendsOfAllCircles(), expenseTypes: user.getExpenseTypeByHouse(), path: user.getPathToDefaultPhoto(), user: user.getUser().getId()]"/>
    <g:render template="/collective/modal/index" model="[users: user.getFriendsOfAllCircles(), expenseTypes: user.getExpenseTypeByCollective(), path: user.getPathToDefaultPhoto(), user: user.getUser().getId()]"/>
    <g:render template="/expense/modal/index" model="[registeredUser: user.getId(), user: user.getUserId()]"/>
    <g:render template="/regularExpense/modal/index" model="[registeredUser: user.getId(), user: user.getUserId()]"/>
    <g:render template="/regularExpense/modal/create" model="[registeredUser: user.getId(), user: user.getUserId()]"/>
    <g:render template="/payment/modal/index" model="[registeredUser: user.getId(), user: user.getUserId()]"/>
    <g:render template="/payment/modal/create" model="[registeredUser: user.getId(), user: user.getUserId()]"/>
    <g:render template="/shared/sidebar" model="[user: user]"/>
    <div class="page-content">
        <div class="clearfix"></div>
        <div class="content">
          <g:render template="/shared/breadcrumb" model="[breadcrumb: breadcrumb]"/>
          <g:layoutBody/>
        </div>
      </div>
    </div>
</body>
</html>
