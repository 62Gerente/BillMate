<ul class="folders">
    <g:each var="house" in="${houses}">
        <g:render template="/house/sidebar/show" model="[house: house]"/>
    </g:each>
</ul>