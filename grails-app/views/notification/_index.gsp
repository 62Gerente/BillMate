<div id="sidr" class="chat-window-wrapper">
    <div id="main-chat-wrapper">
        <div class="fadeIn" id="chat-users">
            <div class="alert alert-error error-notification">
                <button class="close" data-dismiss="alert"></button>
                <g:message code="com.billmate.notification.error_mark_as_read" default="Could not mark notification as read"/>
            </div>
            <div class="side-widget">
                <div class="header-seperation">
                    <div class="side-widget-title text-white bold inline p-t-20">
                        <g:message code="com.billmate.notification.title" default="Notifications"/>
                    </div>
                    <div class="inline">
                        <g:link controller="notification" action="readAll"></g:link>
                        <i class="text-grey fa">
                            <span class="inline mark-read-notification">
                                <g:message code="com.billmate.notification.mark_as_read" default="Mark as read"/>
                            </span>
                        </i>
                    </div>
                </div>
                <g:each var="notification" in="${notifications}">
                    <g:render template="/notification/show" model="[notification: notification]"/>
                </g:each>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
