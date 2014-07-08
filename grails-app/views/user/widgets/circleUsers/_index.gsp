<g:each var="user" in="${users}">
    <g:render template="/user/widgets/circleUsers/show" model="[registeredUser: registeredUser, user: user, circle: circle]"/>
</g:each>
