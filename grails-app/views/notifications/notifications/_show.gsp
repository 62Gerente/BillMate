<div class="side-widget-content" id="friends-list">
    <div class="user-details-wrapper" data-chat-status="busy" data-chat-user-pic="assets/img/profiles/d.jpg" data-chat-user-pic-retina="assets/img/profiles/d2x.jpg" data-user-name="David Nester">
        <div class="user-profile">
            <img src="${user.getPhotoOrDefault()}" alt="" data-src="assets/img/profiles/h.jpg" data-src-retina="assets/img/profiles/h2x.jpg">
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
        <g:if test="${action.isRead(action.getSystemNotifications())}">
            <div class="user-details-count-wrapper">
                <div class="status-icon green"></div>
            </div>
        </g:if>
        <div class="clearfix"></div>
    </div>
</div>