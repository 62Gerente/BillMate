<div class="div-table-widget-payment">
    <table class="table table-home-box">
        <thead>
        <tr>
            <th class="text-left text-primary uppercase"><g:message code="com.billmate.expense" default="Expense" /></th>
            <th class="text-left unseen text-primary uppercase"><g:message code="com.billmate.circle" default="Circle" /></th>
            <th class="text-left unseen text-primary uppercase"><g:message code="com.billmate.payment.date" default="Payment date" /></th>
            <th class="text-right text-primary uppercase"><g:message code="com.billmate.expense.value" default="Value" /></th>
            <th class="text-right text-primary uppercase"></th>
        </tr>
        </thead>
        <tbody>
        <g:if test="${unconfirmedPayments.isEmpty()}">
            <g:render template="/payment/table/confirm/empty"/>
        </g:if>
        <g:else>
            <g:render template="/payment/table/confirm/index" model="[user: user, unconfirmedPayments: unconfirmedPayments]"/>
        </g:else>
        <g:if test="${unconfirmedPayments.size()>1}">
            <tr>
                <td class="text-right text-grey" colspan="4">
                    <div class="inline b-t b-grey p-t-5">
                        <g:formatNumber number="${unconfirmedPayments.sum{ it.getValue() }}" type="currency" currencyCode="EUR" />
                    </div>
                </td>
            </tr>
        </g:if>
        </tbody>
    </table>
    <div class="m-b-10 home-button-divida-secondary-confirm">
        <div class="btn-group text-center" style="display: inline;width: 100%;">
            <div>
                <button type="submit" class="btn btn-small btn-primary" style="width: 85%"><g:message code="com.billmate.payment.confirm" default="Confirm" /></button>
                <button type="button" class="btn btn-small btn-primary dropdown-toggle" data-toggle="dropdown"> <span class="caret no-margin"></span></button>
                <ul class="dropdown-menu">
                    <li><a class="submit-cancel-payments-form" href="${createLink(controller: 'payment', action: 'cancel')}"><g:message code="com.billmate.payment.cancel" default="Cancel" /></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="clearfix"></div>
