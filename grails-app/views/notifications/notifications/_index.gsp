<%@ page import="com.billmate.SystemNotification;" %>
<div id="main-chat-wrapper">
    <div class="chat-window-wrapper fadeIn" id="chat-users">
        <div class="side-widget">
            <div class="header-seperation">
                <div class="side-widget-title text-white bold inline p-t-20">NOTIFICAÇÕES</div>
                <ul class="nav pull-right notifcation-center">
                    <li class="dropdown" id="portrait-chat-toggler">
                        <a href="#" class="chat-menu-toggle">
                            <div class="iconset top-chat-white "></div>
                        </a>
                    </li>
                </ul>
            </div>
            <g:each in="${actions}">
                <g:render template="/notifications/notifications/show" model="[action: it]"/>
            </g:each>
        </div>
    </div>
    <div class="chat-input-wrapper" style="display:none">
        <textarea id="chat-message-input" rows="1" placeholder="Type your message"></textarea>
    </div>
    <div class="clearfix"></div>
</div>