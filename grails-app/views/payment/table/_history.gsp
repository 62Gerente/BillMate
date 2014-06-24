<div class="div-table-widget-payment">
    <table class="table table-home-box">
        <thead>
        <tr>
            <th class="text-left unseen text-primary uppercase"><g:message code="com.billmate.payment.date" default="Payment date" /></th>
            <th class="text-left text-primary uppercase">Confirmado</th>
            <th class="text-left text-primary uppercase">Data de verificação</th>
            <th class="text-right text-primary uppercase"><g:message code="com.billmate.expense.value" default="Value" /></th>
        </tr>
        </thead>
        <tbody>
        <g:if test="${payments.isEmpty()}">
            <g:render template="/payment/table/history/empty"/>
        </g:if>
        <g:else>
            <g:render template="/payment/table/history/index" model="[expense: expense, payments: payments]"/>
        </g:else>
        </tbody>
    </table>
</div>
<div class="clearfix"></div>
