<div class="p-l-20 p-r-20 p-t-5 p-b-5 b-b b-grey expenses-widget-more-details">
    <div class="user-info-wrapper home-margin-list">
        <img class="profile-wrapper user-profile-pic-2x white-border home-img-list" src="${user.getPhotoOrDefault()}"/>
        <div class="col-md-8 user-info user-info-photo user-info-name-price no-padding">
            <div class="username inline p-t-15 widget-payments-name-user">${user}
                <i class="fa fa-angle-down widget-dashboard-arrow-down"></i>
            </div>
        </div>
        <div class="col-md-2 pull-right no-padding m-t-10 widget-payments-price m-t-13">
            <span class="label label-default label-grey-background">
                <h6 class="inline p-t-2">
                    <g:if test="${expense.haveUnvalidatedPayments(user.getId())}">
                        <span class="text-warning">
                            <g:formatNumber number="${expense.amountPaidBy(user.getId())}" type="currency" currencyCode="EUR" />
                        </span>
                    </g:if>
                    <g:elseif test="${expense.isResolvedBy(user.getId())}">
                        <span class="text-success">
                            <g:formatNumber number="${expense.amountPaidBy(user.getId())}" type="currency" currencyCode="EUR" />
                        </span>
                    </g:elseif>
                    <g:else>
                        <span class="text-danger">
                            <g:formatNumber number="${expense.amountPaidBy(user.getId())}" type="currency" currencyCode="EUR" />
                        </span>
                    </g:else>
                    /
                    <span class="bold">
                        <g:formatNumber number="${expense.amountAssignedTo(user.getId())}" type="currency" currencyCode="EUR" />
                    </span>
                </h6>
            </span>
        </div>
    </div>
    <g:render template="/payment/table/history" model="[user: user, expense: expense, payments: expense.paymentsOf(user.getId())]"/>
</div>

