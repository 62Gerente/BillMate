<optgroup label='<g:message code="com.billmate.houses" default="Houses"/>'>
    <g:each var="house" in="${houses}">
        <g:render template="/house/selectGroup/show" model="[house: house]"/>
    </g:each>
</optgroup>
