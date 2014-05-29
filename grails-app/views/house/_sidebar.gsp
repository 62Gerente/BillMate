<div class="side-bar-widgets">
    <p class="menu-title"><g:message code="com.billmate.sidebar.house" default="House"/>
        <span class="pull-right">
            <a href="#" class="create-folder"> <i class="fa fa-plus"></i>
            </a>
        </span>
    </p>
    <g:if test="${houses.isEmpty()}">
        <g:render template="/house/sidebar/empty"/>
    </g:if>
    <g:else>
        <g:render template="/house/sidebar/index" model="[houses: houses]"/>
    </g:else>
</div>