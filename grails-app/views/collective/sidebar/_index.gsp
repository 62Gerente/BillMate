<ul class="folders">
    <g:each var="collective" in="${collectives}">
        <g:render template="/collective/sidebar/show" model="[collective: collective]"/>
    </g:each>
</ul>