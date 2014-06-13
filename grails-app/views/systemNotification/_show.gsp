<div>
    <g:link controller="systemNotification" action="markAsRead" id="${notification.getId()}"></g:link>
    <div class="side-widget-content" id="friends-list">
        <div class="user-details-wrapper">
            <div class="user-profile">
                <img src="${notification.getActor().getPhotoOrDefault()}">
            </div>
            <div class="user-details">
                <div class="user-name">
                    ${notification.getActor()}
                </div>
                <div class="user-more">
                    <g:message code="com.billmate.action.${notification.getActionType().getType()}.long" args="${[
                            notification.getActor(),
                            notification.getCircle(),
                            notification.getUser(),
                            notification.getExpense(),
                            notification.getRegularExpense()
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
</div>
