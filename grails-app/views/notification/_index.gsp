<div id="sidr" class="chat-window-wrapper">
    <div id="main-chat-wrapper">
        <div class="chat-window-wrapper fadeIn" id="chat-users">
            <div class="side-widget">
                <div class="header-seperation">
                    <div class="side-widget-title text-white bold inline p-t-20">
                        <g:message code="com.billmate.notification.title" default="Notifications"/>
                    </div>
                    <ul class="nav pull-right notifcation-center">
                        <li class="dropdown" id="portrait-chat-toggler">
                            <a href="#" class="chat-menu-toggle">
                                <div class="iconset top-chat-white "></div>
                            </a>
                        </li>
                    </ul>
                </div>
                <g:each var="notification" in="${notifications}">
                    <g:render template="/notification/show" model="[notification: notification]"/>
                </g:each>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>