<optgroup label='<g:message code="com.billmate.users" default="Users"/>'>
    <g:each var="user" in="${users}">
        <g:render template="/user/selectGroup/show" model="[user: user]"/>
    </g:each>
</optgroup>
