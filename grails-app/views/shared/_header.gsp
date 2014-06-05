<div class="header navbar navbar-inverse ">
    <div class="navbar-inner">
        <div class="header-seperation">
            <ul class="nav pull-left notifcation-center" id="main-menu-toggle-wrapper">
                <li class="dropdown">
                    <a id="main-menu-toggle" href="#" class="">
                        <div class="iconset top-menu-toggle-white"></div>
                    </a>
                </li>
            </ul>
            <a href=<g:createLink controller="Dashboard" action="index" />>
                <img src="${assetPath(src: 'logo-full.svg')}" class="logo" alt="<g:message code="com.billmate.application.name" default="BillMate"/>" onerror="this.src=${assetPath(src: 'logo-full.png')}">
            </a>
            <ul class="nav pull-right notifcation-center">
                <li class="dropdown" id="portrait-chat-toggler">
                    <a href="#" class="chat-menu-toggle">
                        <div class="iconset top-chat-white "></div>
                    </a>
                </li>
            </ul>
        </div>
        <div class="header-quick-nav">
            <div class="pull-left">
                <ul class="nav quick-section">
                    <li class="quicklinks">
                        <a href="#" class="" id="layout-condensed-toggle">
                            <div class="iconset top-menu-toggle-dark"></div>
                        </a>
                    </li>
                </ul>
                <ul class="nav quick-section">
                    <li class="m-r-10 input-prepend inside search-form no-boarder">
                        <span class="add-on">
                            <span class="iconset top-search"></span>
                        </span>
                        <input type="text" class="no-boarder" placeholder=<g:message code="com.billmate.header.search" default="Search..."/>>
                    </li>
                </ul>
            </div>
            <div class="pull-right">
                <div class="chat-toggler">
                    <a href="#" class="dropdown-toggle" id="my-task-list" data-placement="bottom" data-content="" data-toggle="dropdown" data-original-title="">
                        <div class="user-details">
                            <div class="username">
                                ${user}
                            </div>
                        </div>
                        <div class="iconset top-down-arrow"></div>
                    </a>
                    <div id="notification-list" style="display:none">
                        <div>
                            <div class="single-notification-messages">
                                <a href="#"><i class="fa fa-user p-r-15"></i>
                                    <g:message code="com.billmate.user.profile" default="Profile"/>
                                </a>
                            </div>
                            <div class="single-notification-messages">
                                <g:form controller="session" action="delete" method="DELETE">
                                    <button type="submit">
                                        <i class="fa fa-sign-out p-r-15"></i>
                                        <g:message code="com.billmate.session.logout" default="Logout"/>
                                    </button>
                                </g:form>
                            </div>
                        </div>
                    </div>
                    <div class="profile-pic">
                        <img src="${user.getPhotoOrDefault()}" width="35" height="35">
                    </div>
                </div>
                <ul class="nav quick-section ">
                    <li class="quicklinks">
                        <span class="h-seperate"></span>
                    </li>
                    <li class="quicklinks">
                        <a id="chat-menu-toggle" href="#sidr" class="chat-menu-toggle">
                            <div class="iconset top-chat-dark ">
                                <g:if test="${user.getNumberOfUnreadNotifications()>0}">
                                    <span class="badge badge-important animated bounceIn chat-message-count" id="chat-message-count">${user.getNumberOfUnreadNotifications()}</span>
                                </g:if>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
