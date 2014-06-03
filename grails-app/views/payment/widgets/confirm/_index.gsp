<table class="table table-home-box table-home-box-fixed">
    <tbody>
        <g:each var="payment" in="${unconfirmedPayments}">
            <g:render template="/payment/widgets/confirm/show" model="[payment: payment]"/>
        </g:each>
    </tbody>
</table>
