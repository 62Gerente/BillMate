<optgroup label='<g:message code="com.billmate.actions" default="Actions"/>'>
    <g:each var="actionType" in="${actionsType}">
        <g:render template="/actionType/selectGroup/show" model="[actionType: actionType]"/>
    </g:each>
</optgroup>
