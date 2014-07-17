<tr>
    <td class="home-img-list-confs unseen">
        <div class="friend-profile-pic">
            <div class="user-profile-pic-normal">
                <img width="35" height="35" src="${debt.getUser().getPhotoOrDefault()}" alt="">
            </div>
        </div>
    </td>
    <td class="text-black unseen">
        <p>${debt.getUser()}</p>
    </td>
    <td class="text-right">
        <a href="#" class="edit-property edit-property-sm edit_debt_value" data-emptytext="<g:message code="com.billmate.debt.value.xeditable.clickToDefine" default="Click to define debt value" />" data-name="value" data-type="number" data-step="any" data-min="0.01" data-pk="${debt.getId()}" data-url="${createLink(controller: 'debt', action: 'updateProperty', id: debt.getId())}">
            ${debt.getValue()}
        </a>
    </td>
</tr>
