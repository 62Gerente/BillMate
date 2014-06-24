<tr class="single-payment">
    <td class="unseen">
        <g:if test="${payment.getDate()}">
            <g:formatDate date="${payment.getDate()}" type="datetime" style="SMALL"/>
        </g:if>
        <g:else>
            <g:message code="com.billmate.payment.date.notDefined" default="Not defined" />
        </g:else>
    </td>
    <td>
        <i class="fa fa-times grey" style="color: #aaa"></i>
        Não
    </td>
    <td>
        <g:if test="${payment.getDate()}">
            <g:formatDate date="${payment.getDate()}" type="datetime" style="SMALL"/>
        </g:if>
        <g:else>
            <g:message code="com.billmate.payment.date.notDefined" default="Not defined" />
        </g:else>
    </td>
    <td class="text-right"><g:formatNumber number="${payment.getValue()}" type="currency" currencyCode="EUR" /></td>
</tr>
