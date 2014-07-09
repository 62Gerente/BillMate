<g:each var="payment" in="${unconfirmedPayments}">
    <g:render template="/payment/table/confirm/show" model="[user: user, payment: payment]"/>
</g:each>
