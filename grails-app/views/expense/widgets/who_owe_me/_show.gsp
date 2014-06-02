<div class="p-l-20 p-r-20 p-t-10 b-b b-grey">
    <div class="user-info-wrapper home-margin-list">
        <img class="profile-wrapper user-profile-pic-2x white-border home-img-list" src="${user.getPhotoOrDefault()}"/>
        <div class="col-md-8 user-info user-info-photo user-info-name-price no-padding">
            <div class="username inline p-t-15" style="width:100%; overflow:hidden; text-overflow:ellipsis; white-space:nowrap">${user.getName()}</div>
        </div>
    </div>
    <g:render template="/expense/table/user_dashboard" model="[user: user, unresolvedExpenses: registeredUser.unresolvedResponsibleExpensesBy(user.getId())]"/>
</div>
