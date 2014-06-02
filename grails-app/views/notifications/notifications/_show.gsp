<div class="side-widget-content" id="friends-list">
    <div class="user-details-wrapper">
        <div class="user-profile">
            <img src="${action.getActor().getPhotoOrDefault()}">
        </div>
        <div class="user-details">
            <div class="user-name">
                ${action.getActor().getUser().getName()}
            </div>
            <div class="user-more">
                <g:message code="${action.getActionType().getType()}" args="${[
                        action.getActor().getUser().getName(),
                        action.getUser().getName(),
                        action.getCircle().getName()
                ]}"/>
            </div>
        </div>
        <div class="user-details-status-wrapper">
            <div class="clearfix"></div>
        </div>
        <div class="user-details-count-wrapper">
            <g:if test="${action.isRead(action.getSystemNotifications())}">
                    <div class="status-icon green"></div>
            </g:if>
            <g:else>
                <div class="status-icon red"></div>
            </g:else>
        </div>
        <div class="clearfix"></div>
    </div>
</div>