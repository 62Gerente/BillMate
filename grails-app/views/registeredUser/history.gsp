<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="com.billmate.user.history.page.title" default="BillMate - History"/></title>
    <asset:stylesheet href="bm-history.css"/>
</head>

<body>
<g:render template="/shared/active" model="[active: active]"/>
<form id="history-filter-form" action="${createLink(action: 'history', params: [id: history.getRegisteredUser().getId()])}" method="POST">
    <input type="hidden" name="alt" value="json">
    <input type="hidden" name="page" value="1">
    <input type="hidden" name="size" value="20">
    <div class="row">
        <div class="col-md-8 col-sm-offset-2 col-sm-offset-4">
            <g:render template="/shared/alert" model="[cssClass: 'hidden']"/>
        </div>
        <div class="col-md-8 col-sm-offset-2 col-sm-offset-4">
            <div class="col-sm-2"></div>
            <div class="col-sm-5">
                <select name="circle" class="history-select pull-right" style="width: 100%">
                    <option selected><g:message code="com.billmate.circles.all" default="All circles"></g:message></option>
                    <g:render template="/house/selectGroup" model="[houses: history.getHouses()]"/>
                    <g:render template="/collective/selectGroup" model="[collectives: history.getCollectives()]"/>
                </select>
            </div>

            <div class="col-sm-5">
                <select name="type" class="history-select pull-right" style="width: 100%">
                    <option selected><g:message code="com.billmate.action.all" default="All events"></g:message></option>
                    <g:render template="/actionType/selectGroup" model="[actionsType: history.getActionTypes()]"/>
                </select>
            </div>
        </div>
    </div>

    <div class="col-md-10 col-vlg-7">
        <g:render template="/registeredUser/history/index" model="[registeredUser: history.getRegisteredUser(), actions: history.getRealizedActions()]"/>
    </div>

    <div class="row">
        <div class="col-md-12 p-b-30">
            <g:if test="${20 <= history.getRealizedActions().size()}">
                <button id="history-load-more" type="button" class="btn btn-primary btn-cons center-block m-t-20" style="margin-left: auto; margin-right: auto;">
                    <g:message code="com.billmate.history.search.loadMore" default="Load more"></g:message>...
                </button>
            </g:if>
        </div>
    </div>
</form>

<asset:javascript src="bm-history.js"/>
</body>
</html>
