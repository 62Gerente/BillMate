<div class="page-sidebar" id="main-menu">
    <div class="page-sidebar-wrapper" id="main-menu-wrapper">
        <div class="user-info-wrapper">
            <div class="profile-wrapper">
                <a href="${createLink(controller: "registeredUser", action: "edit", id: user.getId())}">
                    <img class="profile-photo-img" src="${user.getPhotoOrDefault()}">
                </a>
            </div>
            <div class="user-info" style="margin-top: 12px">
                <div class="greeting"><g:message code="com.billmate.user.welcome" default="Welcome"/></div>
                <a href="${createLink(controller: "registeredUser", action: "edit", id: user.getId())}"><div class="username">${user}</div></a>
            </div>
        </div>
        <p class="menu-title"></p>
        <ul id="home-sidebar-menu">
            <li class="start active ">
                <a href="#"> <i class="fa fa-dashboard"></i>
                    <span class="title">
                        <g:message code="com.billmate.sidebar.dashboard" default="Dashboard"/>
                    </span>
                    <span class="selected"></span>
                </a>
            </li>
            <li>
                <a href="#"> <i class="fa fa-money"></i>
                    <span class="title">
                        <g:message code="com.billmate.sidebar.expenses" default="Expenses"/>
                    </span>
                </a>
            </li>
            <li>
                <a href="#"> <i class="fa fa-calendar"></i>
                    <span class="title">
                        <g:message code="com.billmate.sidebar.calendar" default="Calendar"/>
                    </span>
                </a>
            </li>
            <li>
                <a href="#"> <i class="fa fa-clock-o"></i>
                    <span class="title">
                        <g:message code="com.billmate.sidebar.history" default="History"/>
                    </span>
                </a>
            </li>
            <li>
                <a href="#"> <i class="fa fa-bar-chart-o"></i>
                    <span class="title">
                        <g:message code="com.billmate.sidebar.stats" default="Statistics"/>
                    </span>
                </a>
            </li>
            <li>
                <a href="#"> <i class="fa fa-file-text-o"></i>
                    <span class="title">
                        <g:message code="com.billmate.sidebar.report" default="Reports"/>
                    </span>
                </a>
            </li>
            <li class="hidden-lg hidden-md hidden-xs" id="more-widgets">
                <a href="javascript:;"> <i class="fa fa-plus"></i>
                </a>
                <ul class="sub-menu" style="overflow: hidden; display: none;">
                    <li class="side-bar-widgets">
                        <p class="menu-title"><g:message code="com.billmate.sidebar.house" default="House"/>
                            <span class="pull-right">
                                <a href="index.html#" class="create-folder"> <i class="fa fa-plus"></i>
                                </a>
                            </span>
                        </p>
                        <ul class="folders">
                            <li>
                                <a href="blank_template.html#">
                                    <div class="status-icon green"></div>Casa Braga</a>
                            </li>
                            <li class="folder-input" style="display:none">
                                <input type="text" placeholder="Name of folder" class="no-boarder folder-name" name="">
                            </li>
                        </ul>
                    </li>
                    <li class="side-bar-widgets">
                        <p class="menu-title"><g:message code="com.billmate.sidebar.group" default="Groups"/>
                            <span class="pull-right">
                                <a href="#" class="create-folder"> <i class="fa fa-plus"></i>
                                </a>
                            </span>
                        </p>
                        <ul class="folders">
                            <li>
                                <a href="blank_template.html#">
                                    <div class="status-icon blue"></div>Futeboladas CeSIUM</a>
                            </li>
                            <li>
                                <a href="blank_template.html#">
                                    <div class="status-icon blue"></div>Amigos Secundário</a>
                            </li>
                            <li>
                                <a href="blank_template.html#">
                                    <div class="status-icon blue"></div>Jantaradas EA</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
        <g:render template="/house/sidebar" model="[houses: user.getHouses()]"/>
        <g:render template="/collective/sidebar" model="[collectives: user.getCollectives()]"/>
        <div class="clearfix"></div>
    </div>
</div>
<a href="#" class="scrollup"></a>
<div class="footer-widget">
    <button type="button" class="btn btn-primary btn-cons">
        <g:message code="com.billmate.sidebar.addDebt" default="Add Debt"/>
    </button>
</div>