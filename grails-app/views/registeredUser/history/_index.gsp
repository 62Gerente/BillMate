<ul id="history-timeline" class="cbp_tmtimeline">
    <g:each var="action" in="${actions}">
        <g:render template="/registeredUser/history/show" model="[action: action]"/>
    </g:each>
</ul>
