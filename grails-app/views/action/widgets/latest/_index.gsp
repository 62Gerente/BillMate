<g:each var="action" in="${latestEvents}">
    <g:render template="/action/widgets/latest/show" model="[action: action]"/>
</g:each>