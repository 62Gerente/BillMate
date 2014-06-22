<tr>
    <td>
        <div class="profile-pic-xs m-r-5">
            <img width="15" height="15" src="${user.getPhotoOrDefault()}" alt="">
        </div>
        ${user}
    </td>
    <td class="unseen">
        <g:if test="${expense.getPaymentDeadline()}">
            <g:formatDate date="${expense.getPaymentDeadline()}" type="datetime" style="SMALL"/>
        </g:if>
        <g:else>
            <g:message code="com.billmate.expense.date.notDefined" default="Not defined" />
        </g:else>
    </td>
    <td class="unseen text-right">
        <g:formatNumber number="${expense.valueAssignedTo(user.getId())}" type="currency" currencyCode="EUR" />
    </td>
    <td class="text-right">
        <g:formatNumber number="${expense.debtOf(user.getId())}" type="currency" currencyCode="EUR" />
    </td>
    <g:if test="${user.getId() == registeredUser.getUserId() || user.getReferredUser()}">
        <td class="text-right td-settings">
            <a class="dropdown-toggle text-grey" data-toggle="dropdown"><i class="fa fa-cog"></i></a>
            <ul class="dropdown-menu">
                <g:if test="${user.getId() == registeredUser.getUserId()}">
                    <li><a href="#"><g:message code="com.billmate.payment.new" default="Pay it now" /></a></li>
                </g:if>
                <g:else>
                    <li><a href="#"><g:message code="com.billmate.payment.confirm" default="Confirm payment" /></a></li>
                </g:else>
            </ul>
        </td>
    </g:if>
</tr>
