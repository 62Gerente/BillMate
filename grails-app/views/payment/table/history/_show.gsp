<tr class="single-payment">
    <td>
        <g:if test="${payment.getDate()}">
            <g:formatDate date="${payment.getDate()}" type="datetime" style="SMALL"/>
        </g:if>
        <g:else>
            <g:message code="com.billmate.payment.date.notDefined" default="Not defined" />
        </g:else>
    </td>
    <td class="unseen">
        <g:if test="${payment.getIsValidated()}">
            <i class="fa fa-check text-success"></i> <g:message code="com.billmate.boolean.yes" default="Yes" />
        </g:if>
        <g:else>
            <i class="fa fa-times text-grey"></i> <g:message code="com.billmate.boolean.no" default="No" />
        </g:else>
    </td>
    <td class="unseen">
        <g:if test="${payment.getValidationDate()}">
            <g:formatDate date="${payment.getValidationDate()}" type="datetime" style="SMALL"/>
        </g:if>
        <g:else>
            <g:message code="com.billmate.payment.validationDate.notValidated" default="Not validated" />
        </g:else>
    </td>
    <td class="text-right"><g:formatNumber number="${payment.getValue()}" type="currency" currencyCode="EUR" /></td>
</tr>
