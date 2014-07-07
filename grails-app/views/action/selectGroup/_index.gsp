<optgroup label='<g:message code="com.billmate.actions" default="Actions"/>'>
    <g:each var="actionType" in="${actionsType}">
        <g:render template="/action/selectGroup/show" model="[actionType: actionType]"/>
    </g:each>
</optgroup>
