<div class="p-l-20 p-r-20 p-t-5 p-b-5 b-b b-grey expenses-widget-more-details">
    <div class="user-info-wrapper home-margin-list">
        <img class="profile-wrapper user-profile-pic-2x white-border home-img-list" src="${registeredUser.getPhotoOrDefault()}"/>
        <div class="col-md-8 user-info user-info-photo user-info-name-price no-padding">
            <div class="username inline p-t-15 widget-payments-name-user">${registeredUser.getName()}
                <i class="fa fa-angle-down widget-dashboard-arrow-down"></i>
            </div>
        </div>
        <div class="col-md-2 pull-right no-padding m-t-10 price-widget-dashboard widget-payments-price m-t-13">
            <span class="label label-default label-grey-background">
                <h6 class="bold inline p-t-2">
                    <g:formatNumber number="${user.unresolvedExpensesWhichResponsibleIs(registeredUser.getId()).sum{ it.amountInDebtOf(user.getId()) }}" type="currency" currencyCode="EUR" />
                </h6>
            </span>
        </div>
        <div class="div-btn-widget-payments pull-right">
            <div class="col-md-4 pull-right no-padding home-button-divida-primary-confirm">
                <button type="button" class="inline btn btn-primary btn-small btn-cons pull-right"><g:message code="com.billmate.payment.new" default="Pay it now" /></button>
            </div>
        </div>
    </div>
    <g:render template="/expense/table/whoOwe" model="[user: user, unresolvedExpenses: user.unresolvedExpensesWhichResponsibleIs(registeredUser.getId())]"/>
</div>
