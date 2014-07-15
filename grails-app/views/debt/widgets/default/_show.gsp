<tr>
    <td class="home-img-list-confs">
        <div class="friend-profile-pic">
            <div class="user-profile-pic-normal">
                <img width="35" height="35" src="${debt.getUser().getPhotoOrDefault()}" alt="">
            </div>
        </div>
    </td>
    <td class="text-black">
        <p>${debt.getUser()}</p>
    </td>
    <td class="pull-right text-black">
        <a href="#">
            <g:formatNumber number="${debt.getValue()}" type="currency" currencyCode="EUR" />
        </a>
    </td>
    <td class="text-right p-b-10">
        <input type="hidden" value="${debt.getUserId()}"/>
        <a href="#">
            <i class="fa fa-sign-out selected text-grey"></i>
        </a>
    </td>
</tr>
