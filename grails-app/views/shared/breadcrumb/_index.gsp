<ul class="breadcrumb no-print">
    <li>
        <a href="<g:createLink controller="dashboard" action="user" />">
            <i class="fa fa-home"></i>
        </a>
    </li>
    <g:each var="link" in="${breadcrumb}">
        <g:render template="/shared/breadcrumb/show" model="[link: link]"/>
    </g:each>
</ul>
