<div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
    <div class="user-info-wrapper home-margin-list">
        <img class="profile-wrapper user-profile-pic-2x white-border home-img-list" src="${registeredUser.getPhotoOrDefault()}"/>
        <div class="col-md-8 user-info user-info-photo user-info-name-price no-padding">
            <div class="username inline p-t-15" style="width:100%; overflow:hidden; text-overflow:ellipsis; white-space:nowrap">${registeredUser.getName()}</div>
        </div>
        <div class="col-md-4 pull-right no-padding m-t-15" id="home-button-divida-primary-confirm" style="width:125px">
            <button type="button" class="inline btn btn-default btn-small btn-cons pull-right"><g:message code="com.billmate.payment.new" default="Pay it now" /></button>
        </div>
    </div>
    <g:render template="/expense/table/user_dashboard" model="[user: user, unresolvedExpenses: user.unresolvedExpensesWhoResponsibleIs(registeredUser.getId())]"/>
</div>
