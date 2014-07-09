<li>
    <g:if test="${link.href}">
        <a href="${link.href}">
            ${link.name}
        </a>
    </g:if>
    <g:else>
        ${link.name}
    </g:else>
</li>
