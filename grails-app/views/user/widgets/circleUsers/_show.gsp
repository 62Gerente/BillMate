<tr>
    <td class="home-img-list-confs">
        <div class="friend-profile-pic">
            <div class="user-profile-pic-normal">
                <img width="35" height="35" src="${user.getPhotoOrDefault()}" alt="">
            </div>
        </div>
    </td>
    <td class="text-black">
        <p>${user}</p>
    </td>
    <td class="unseen">
        <i class="fa fa-envelope-o" style="width: 20px"></i> ${user.getEmail()}
    </td>
    <td class="unseen">
        <i class="fa fa-mobile-phone"></i>${user.getPhoneNumber()}
    </td>
    <td class="text-right">
    <g:if test="${!user.hasMovementsInCircle(circle.getId())}">
        <input type="hidden" value="${user.getId()}"/>
        <a href="#">
            <i class="fa fa-sign-out selected text-grey"></i>
        </a>
    </g:if>
    <g:else>
        <i class="fa fa-sign-out text-grey" style="opacity: 0.3"></i>
    </g:else>

    </td>
</tr>
