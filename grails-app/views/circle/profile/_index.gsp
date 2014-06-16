<g:each var="circle" in="${circles}">
    <g:render template="/circle/profile/show" model="[circle: circle]"/>
</g:each>
