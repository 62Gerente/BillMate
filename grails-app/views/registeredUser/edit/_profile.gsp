<div class=" tiles white col-md-12 p-t-30 p-b-30 m-b-20">
    <div class="tiles white">
        <div class="row">
            <div class="col-md-3">
                <div>
                    <div class="large-user-profile-pic center-block">
                        <a href="#" class="profile-photo-img" id="profile-photo-link" class="profile-photo-link">
                            <img id="profile-photo-img" class="profile-photo-img" width="120" height="120" src="${user.getPhotoOrDefault()}" alt="">
                        </a>
                        <form enctype="multipart/form-data" action="${createLink(controller: "fileUploader", action: "process", id: user.getId())}" id="updatePhotoForm">
                                <input type="hidden" name="upload" value="avatar">
                                <input type="hidden" name="errorAction" value="errorUploadPhoto">
                                <input type="hidden" name="errorController" value="registeredUser">
                                <input type="hidden" name="successAction" value="successUploadPhoto">
                                <input type="hidden" name="successController" value="registeredUser">
                                <input type="file" name="file" id="edit_photo">
                        </form>
                    </div>
                    <div id="profile-photo-error" class="profile-photo-error text-danger p-r-5 p-t-5" style="display: none"></div>
                </div>
            </div>
            <div class="col-md-6 p-l-20">
                <h3 class="semi-bold no-margin">
                    <a href="#" class="edit-property edit-property-lg" id="edit_name" data-emptytext="<g:message code="com.billmate.user.name.xeditable.clickToDefine" default="Click to define your name" />" data-name="name" data-type="text" data-pk="${user.getUser().getId()}" data-url="${createLink(controller: 'user', action: 'updateProperty', id: user.getUser().getId())}">
                        ${user.getName()}
                    </a>
                </h3>
                <br>
                <p>
                    <i class="fa fa-envelope"></i>
                    <a href="#" class="edit-property edit-property-sm" id="edit_email" data-emptytext="<g:message code="com.billmate.user.email.xeditable.clickToDefine" default="Click to define your email" />" data-name="email" data-type="text" data-pk="${user.getUser().getId()}" data-url="${createLink(controller: 'user', action: 'updateProperty', id: user.getUser().getId())}">
                        ${user.getEmail()}
                    </a>
                </p>
                <p>
                    <i class="fa fa-phone"></i>
                    <a href="#" class="edit-property edit-property-sm" id="edit_phone_number" data-emptytext="<g:message code="com.billmate.registeredUser.phoneNumber.xeditable.clickToDefine" default="Click to define your phone number" />" data-name="phoneNumber" data-type="text" data-pk="${user.getId()}" data-url="${createLink(controller: 'registeredUser', action: 'updateProperty', id: user.getId())}">
                        ${user.getPhoneNumber()}
                    </a>
                </p>
                <p>
                    <i class="fa fa-key"></i>
                    <a href="#" class="edit-property edit-property-sm" id="edit_password" data-emptytext="<g:message code="com.billmate.registeredUser.password.xeditable.clickToDefine" default="Click to change your password" />" data-name="password" data-type="password" data-pk="${user.getId()}" data-url="${createLink(controller: 'registeredUser', action: 'updateProperty', id: user.getId())}"></a>
                </p>
            </div>
            <div class="col-md-3">
                <div class="profile-debts-resume">
                    <h5><g:message code="com.billmate.expense.debts" default="Debts" /></h5>
                    <h3 class="text-danger semi-bold">
                        <g:formatNumber number="${user.getUser().amountInDebt()}" type="currency" currencyCode="EUR" />
                    </h3>
                    <h5><g:message code="com.billmate.expense.assets" default="Assets" /></h5>
                    <h3 class="text-success semi-bold">
                        <g:formatNumber number="${user.amountInAsset()}" type="currency" currencyCode="EUR" />
                    </h3>
                </div>
            </div>
        </div>
    </div>
</div>
