<%@ page import="com.billmate.SystemNotification" %>
<div class="side-widget-content" id="friends-list">
    <div class="user-details-wrapper">
        <div class="user-profile">
            <img src="${notification.getActor().getPhotoOrDefault()}">
            <input type="hidden" class="ref" value="${notification.getId()}">
        </div>
        <div class="user-details">
            <div class="user-name">
                ${((SystemNotification) notification).getActor()}
            </div>
            <div class="user-more">
                <g:message code="${notification.getActionType().getType()}" args="${[
                        notification.getActor(),
                        notification.getUser(),
                        notification.getCircle()
                ]}"/>
            </div>
        </div>
        <div class="user-details-status-wrapper">
            <div class="clearfix"></div>
        </div>
        <div class="user-details-count-wrapper">
            <g:if test="${notification.getIsRead()}">
                <div class="status-icon green"></div>
            </g:if>
            <g:else>
                <div class="status-icon red"></div>
            </g:else>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
