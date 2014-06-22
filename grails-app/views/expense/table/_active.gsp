<div class="div-table-widget-payment">
    <table class="table table-home-box">
        <thead>
        <tr>
            <th class="text-left text-primary uppercase"><g:message code="com.billmate.user" default="User"/></th>
            <th class="unseen text-primary uppercase"><g:message code="com.billmate.expense.paymentDeadline" default="Payment deadline" /></th>
            <th class="unseen text-right text-primary uppercase"><g:message code="com.billmate.expense.total" default="Total" /></th>
            <th class="text-right text-primary uppercase"><g:message code="com.billmate.expense.debt" default="Debt" /></th>
            <th class="text-right text-primary uppercase td-settings"></th>
        </tr>
        </thead>
        <tbody>
        <g:if test="${users.isEmpty()}">
            <g:render template="/expense/table/active/empty"/>
        </g:if>
        <g:else>
            <g:render template="/expense/table/active/index" model="[]"/>
        </g:else>
        <g:if test="${users.size()>1}">
            <tr>
                <td class="text-right text-grey" colspan="4">
                    <div class="inline b-t b-grey p-t-5">
                        <g:formatNumber number="${15.27}" type="currency" currencyCode="EUR" />
                    </div>
                </td>
            </tr>
        </g:if>
        </tbody>
    </table>
    <div class="m-b-10 home-button-divida-secondary-confirm">
        <button type="button" class="btn btn-default btn-small width-btn-payment">
            <g:message code="com.billmate.payment.new" default="Pay it now" />
        </button>
    </div>
</div>
<div class="clearfix"></div>
