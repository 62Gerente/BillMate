<optgroup label='<g:message code="com.billmate.collectives" default="Groups"/>'>
    <g:each var="collective" in="${collectives}">
        <g:render template="/collective/selectGroup/show" model="[collective: collective]"/>
    </g:each>
</optgroup>
