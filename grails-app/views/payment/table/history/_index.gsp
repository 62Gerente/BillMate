<g:each var="payment" in="${payments}">
    <g:render template="/payment/table/history/show" model="[expense: expense, payment: payment]"/>
</g:each>
