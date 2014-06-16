<div class=" tiles white col-md-12 p-t-20 p-b-30 m-b-20">
    <div class="tiles white">
        <div class="row">
            <div class="col-md-3 col-sm-3">
                <div class="user-profile-pic">
                    <a href="#" class="profile-photo-img" id="profile-photo-link" class="profile-photo-link">
                        <img id="profile-photo-img" class="profile-photo-img" width="69" height="69" src="${user.getPhotoOrDefault()}" alt="">
                    </a>
                    <div id="profile-photo-error" class="profile-photo-error text-danger" style="display: none"></div>
                    <form enctype="multipart/form-data" action="${createLink(controller: "fileUploader", action: "process", id: user.getId())}" id="updatePhotoForm">
                            <input type="hidden" name="upload" value="avatar">
                            <input type="hidden" name="errorAction" value="errorUploadPhoto">
                            <input type="hidden" name="errorController" value="registeredUser">
                            <input type="hidden" name="successAction" value="successUploadPhoto">
                            <input type="hidden" name="successController" value="registeredUser">
                            <input type="file" name="file" id="edit_photo">
                        </form>
                    </form>
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
                <h3 class="semi-bold no-margin">
                    <a href="#" id="edit_name" data-emptytext="Click to define your name" data-name="name" data-type="text" data-pk="${user.getUser().getId()}" data-url="${createLink(controller: 'user', action: 'updateProperty', id: user.getUser().getId())}">
                        ${user.getName()}
                    </a>
                </h3>
                <br>
                <p>
                    <i class="fa fa-envelope"></i>
                    <a href="#" id="edit_email" data-emptytext="Click to define your email" data-name="email" data-type="text" data-pk="${user.getUser().getId()}" data-url="${createLink(controller: 'user', action: 'updateProperty', id: user.getUser().getId())}">
                        ${user.getEmail()}
                    </a>
                </p>
                <p>
                    <i class="fa fa-phone"></i>
                    <a href="#" id="edit_phone_number" data-emptytext="Click to define your phone number" data-name="phoneNumber" data-type="text" data-pk="${user.getId()}" data-url="${createLink(controller: 'registeredUser', action: 'updateProperty', id: user.getId())}">
                        ${user.getPhoneNumber()}
                    </a>
                </p>
                %{--<p>--}%
                    %{--<i class="fa fa-camera-retro"></i>--}%
                    %{--<a href="#" id="edit_photo" class="bm-editable">--}%
                        %{--<small>Click to change your photo</small>--}%
                    %{--</a>--}%
                %{--</p>--}%
                <p>
                    <i class="fa fa-key"></i>
                    <a href="#" id="edit_password" data-emptytext="Click to change your password" data-name="password" data-type="password" data-pk="${user.getId()}" data-url="${createLink(controller: 'registeredUser', action: 'updateProperty', id: user.getId())}"></a>
                </p>
            </div>
        </div>
    </div>
</div>