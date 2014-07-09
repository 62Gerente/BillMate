<div class="div-table-widget-payment">
    <g:if test="${payments.isEmpty()}">
        <g:render template="/payment/table/history/empty"/>
    </g:if>
    <g:else>
        <table class="table table-home-box">
            <thead>
            <tr>
                <th class="text-left text-primary uppercase"><g:message code="com.billmate.payment.date" default="Payment date" /></th>
                <th class="text-left unseen text-primary uppercase"><g:message code="com.billmate.payment.isValidated" default="Validated" /></th>
                <th class="text-left unseen text-primary uppercase"><g:message code="com.billmate.payment.validationDate" default="Validation date" /></th>
                <th class="text-right text-primary uppercase"><g:message code="com.billmate.expense.value" default="Value" /></th>
            </tr>
            </thead>
            <tbody>
                <g:render template="/payment/table/history/index" model="[expense: expense, payments: payments]"/>
            </tbody>
        </g:else>
    </table>
</div>
<div class="clearfix"></div>
