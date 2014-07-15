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
            <li class="start">
                <a href="${createLink(controller: "dashboard", action: "user")}"> <i class="fa fa-dashboard"></i>
                    <span class="title">
                        <g:message code="com.billmate.sidebar.dashboard" default="Dashboard"/>
                    </span>
                    <span class="selected"></span>
                </a>
            </li>
            <li>
                <a href="${createLink(controller: "user", action: "expenses", id: user.getId())}"> <i class="fa fa-money"></i>
                    <span class="title">
                        <g:message code="com.billmate.sidebar.expenses" default="Expenses"/>
                    </span>
                </a>
            </li>
            <li>
                <a href="${createLink(controller: "user", action: "calendar", id: user.getId())}"> <i class="fa fa-calendar"></i>
                    <span class="title">
                        <g:message code="com.billmate.sidebar.calendar" default="Calendar"/>
                    </span>
                </a>
            </li>
            <li>
                <a href="${createLink(controller: "registeredUser", action: "history", id: user.getId())}"> <i class="fa fa-clock-o"></i>
                    <span class="title">
                        <g:message code="com.billmate.sidebar.history" default="History"/>
                    </span>
                </a>
            </li>
            <li>
                <a href="${createLink(controller: "registeredUser", action: "reports", id: user.getId())}"> <i class="fa fa-file-text-o"></i>
                    <span class="title">
                        <g:message code="com.billmate.sidebar.report" default="Reports"/>
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
        </ul>
        <g:render template="/house/sidebar" model="[houses: user.getHouses()]"/>
        <g:render template="/collective/sidebar" model="[collectives: user.getCollectives()]"/>
        <div class="clearfix"></div>
    </div>
</div>
<a href="#" class="scrollup"></a>
<div class="footer-widget">
    <button type="button" id="btn-advanced-options-expense-create-modal" class="btn btn-primary btn-cons" data-toggle="modal" data-target="#expenseCreateModal">
        <g:message code="com.billmate.sidebar.addDebt" default="Confirm Payment"/>
    </button>
    <button type="button" id="btn-advanced-options-regularExpense" class="btn btn-primary btn-cons hidden" data-toggle="modal" data-target="#regularExpenseCreateModal"></button>
    <button type="button" id="btn-advanced-options-newRegularExpense" class="btn btn-primary btn-cons hidden" data-toggle="modal" data-target="#newRegularExpenseCreateModal"></button>
</div>
