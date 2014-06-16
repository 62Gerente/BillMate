<div class=" tiles white col-md-12 p-t-20 p-b-30 m-b-20">
    <div class="tiles white">
        <div class="row">
            <div class="col-md-3 col-sm-3">
                <div class="user-profile-pic">
                    <img width="69" height="69" src="${user.getPhotoOrDefault()}" alt="">
                </div>
                <div class="user-mini-description">
                    <h3 class="text-danger semi-bold">
                        <g:formatNumber number="${user.getUser().totalDebt()}" type="currency" currencyCode="EUR" />
                    </h3>
                    <h5><g:message code="com.billmate.expense.debts" default="Debts" /></h5>
                    <h3 class="text-success semi-bold">
                        <g:formatNumber number="${user.totalAsset()}" type="currency" currencyCode="EUR" />
                    </h3>
                    <h5><g:message code="com.billmate.expense.assets" default="Assets" /></h5>
                </div>
            </div>
            <div class="col-md-8 user-description-box col-sm-8">
                <h3 class="semi-bold no-margin">${user.getName()}</h3>
                <br>
                <p><i class="fa fa-envelope"></i>${user.getEmail()}</p>
                <p><i class="fa fa-phone"></i><g:if test="${user.getPhoneNumber()}">${user.getPhoneNumber()}</g:if><g:else><small>Click to define your phone number</small></g:else></p>
                <p><i class="fa fa-key"></i><small>Click to change password</small></p>
            </div>
        </div>
    </div>
</div>