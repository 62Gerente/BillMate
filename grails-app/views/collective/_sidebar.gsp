<div class="side-bar-widgets">
    <p class="menu-title"><g:message code="com.billmate.sidebar.group" default="Groups"/>
        <span class="pull-right">
            <a href="#" class="create-folder"> <i class="fa fa-plus"></i>
            </a>
        </span>
    </p>
    <g:if test="${collectives.isEmpty()}">
        <g:render template="/collective/sidebar/empty"/>
    </g:if>
    <g:else>
        <g:render template="/collective/sidebar/index" model="[collective: collectives]"/>
    </g:else>
</div>