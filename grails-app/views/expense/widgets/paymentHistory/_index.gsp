<g:each var="user" in="${users}">
    <g:render template="/expense/widgets/paymentHistory/show" model="[expense: expense, user: user]"/>
</g:each>
