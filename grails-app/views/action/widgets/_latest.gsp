<div class="tiles white m-b-20 p-b-10">
    <div class="tiles-body no-padding">
        <div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
            <h4 class="text-black bold inline uppercase">
                <g:message code="com.billmate.action.widget.latest.title" default="Latest events"/>
            </h4>
        </div>
        <br>
        <g:if test="${latestEvents.isEmpty()}">
            <g:render template="/action/widgets/latest/empty"/>
        </g:if>
        <g:else>
            <g:render template="/action/widgets/latest/index" model="[latestEvents: latestEvents]"/>
        </g:else>
    </div>
</div>
