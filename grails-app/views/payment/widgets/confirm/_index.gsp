<g:each var="user" in="${whoHaveUnconfirmedPayments}">
    <g:render template="/payment/widgets/confirm/show" model="[registeredUser: registeredUser, user: user]"/>
</g:each>
