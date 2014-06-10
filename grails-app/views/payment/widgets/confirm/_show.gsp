<div class="p-l-20 p-r-20 p-t-5 p-b-5 b-b b-grey expenses-widget-more-details">
    <div class="user-info-wrapper home-margin-list">
        <img class="profile-wrapper user-profile-pic-2x white-border home-img-list" src="${user.getPhotoOrDefault()}"/>
        <div class="col-md-7 user-info user-info-photo user-info-name-price no-padding">
            <div class="username inline p-t-15 widget-payments-name-user">${user.getName()}
                <i class="fa fa-angle-down widget-dashboard-arrow-down"></i>
            </div>
        </div>
        <div class="col-md-2 pull-right no-padding m-t-10 price-widget-dashboard widget-payments-price m-t-13">
            <span class="label label-default label-grey-background">
                <h6 class="bold inline p-t-2">
                    <g:formatNumber number="${registeredUser.unconfirmedPaymentsOnResponsibleExpensesOf(user.getId()).sum{ it.getValue() }}" type="currency" currencyCode="EUR" />
                </h6>
            </span>
        </div>
        <div class="div-btn-widget-payments col-md-3 pull-right" style="margin-right: 3px;">
            <div class="pull-right no-padding home-button-divida-primary-confirm">
                <div class="btn-group pull-right" style="display: inline">
                    <button class="btn btn-small btn-primary"><g:message code="com.billmate.payment.confirm" default="Confirm" /></button>
                    <button type="button" class="btn btn-small btn-primary dropdown-toggle" data-toggle="dropdown"> <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <li><a href="#"><g:message code="com.billmate.payment.cancel" default="Cancel" /></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <g:render template="/payment/table/confirm" model="[user: user, unconfirmedPayments: registeredUser.unconfirmedPaymentsOnResponsibleExpensesOf(user.getId())]"/>
</div>
