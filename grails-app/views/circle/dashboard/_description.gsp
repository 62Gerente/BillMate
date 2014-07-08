<div class="tiles white col-md-12 p-t-30 p-b-30 p-r-40 p-l-40 m-b-20">
    <div class="row">
        <div class="text-center">
          <i class="${circle.getCssClass()} fa-5x"></i>
          <h3>${circle.getName()}</h3>
          <p class="text-left">${circle.getDescription()}</p>
        </div>
    </div>
    <div class="row">
        <div class="center-block">
            <g:render template="/user/members" model="[members: circle.getUsersWithout(registeredUser.getUser().getId())]"/>
        </div>
    </div>
    <div class="row p-t-10">
        <ul class="circle-utilities">
            <li>
                <a href="${createLink([controller: "Circle", action: "history", id: circle.getId()])}"><div>
                    <i class="fa fa-clock-o"></i> <g:message code="com.billmate.circleDashboard.actions.history" default="Actions history"/>
                </div></a>
            </li>
            <li>
                <a href="#"><div>
                    <i class="fa fa-bar-chart-o"></i> <g:message code="com.billmate.circleDashboard.actions.stats" default="Group stats"/>
                </div></a>
            </li>
            <li>
                <a href="#"><div>
                    <i class="fa fa-file-text-o"></i> <g:message code="com.billmate.circleDashboard.actions.reports" default="Expense reports"/>
                </div></a>
            </li>
            <li>
                <a href="${createLink([controller: "circle", action: "edit", id: circle.getId()])}"><div>
                    <i class="fa fa-cog"></i> <g:message code="com.billmate.circleDashboard.actions.settings" default="Group settings"/>
                </div></a>
            </li>
        </ul>
    </div>
    <div class="row p-t-5">
        <button class="btn btn-block btn-primary" type="button"><g:message code="com.billmate.circleDashboard.actions.expenses" default="See expenses"/></button>
    </div>
</div>
<div class="clearfix"></div>
