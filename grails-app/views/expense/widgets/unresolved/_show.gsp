<div class="p-l-20 p-r-20 p-t-5 p-b-5 b-b b-grey expenses-widget-more-details">
    <div class="user-info-wrapper home-margin-list">
        <div class="profile-wrapper user-profile-pic-2x white-border home-img-list text-center bg-blue">
            <i class="${expense.getExpenseType().getCssClass()} fa-2x text-white icon-active-expense"></i>
        </div>
        <div class="col-md-8 user-info user-info-photo user-info-name-price no-padding">
            <div class="username inline p-t-15 widget-payments-name-user">${expense}
                <i class="fa fa-angle-down widget-dashboard-arrow-down"></i>
            </div>
        </div>
        <div class="col-md-3 pull-right no-padding m-t-10 price-widget-dashboard widget-payments-price m-t-13">
            <span class="label label-default label-grey-background">
                <h6 class="inline p-t-2">
                    <g:formatNumber number="${expense.totalDebt()}" type="currency" currencyCode="EUR" />
                    /
                    <span class="bold">
                        <g:formatNumber number="${expense.getValue()}" type="currency" currencyCode="EUR" />
                    </span>
                </h6>
            </span>
        </div>
        <div class="div-btn-widget-payments pull-right">
            <div class="col-md-4 pull-right no-padding home-button-divida-primary-confirm" id="home-button-divida-primary-confirm">
                <button type="button" class="inline btn btn-white btn-small btn-cons pull-right show-expense">
                    <g:message code="com.billmate.expense.show" default="Show expense" />
                </button>
            </div>
        </div>
    </div>
    <g:render template="/expense/table/unresolved" model="[registeredUser: registeredUser, expense: expense, users: expense.assignedUsersWithDebts()]"/>
</div>
