<div class="row tiles-container tiles white hidden-xlg m-b-20 expenses-widget-more-details unselectable">
    <div class="row tiles-container tiles white hidden-xlg">
        <div class="col-md-12 b-grey b-r no-padding widget-table-adjust-right-margin">
            <div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
                <h4 class="text-black bold inline uppercase">
                    <g:message code="com.billmate.expense.widget.whoIOwe.title" default="Who I owe" />
                </h4>
                <div class="pull-right">
                    <span class="label label-important">
                        <h5 class="text-white bold inline p-t-2">
                            <g:formatNumber number="${totalDebt}" type="currency" currencyCode="EUR" />
                        </h5>
                    </span>
                </div>
            </div>
            <g:if test="${whoIOwe.isEmpty()}">
                <g:render template="/expenseType/widgets/whoIOwe/empty"/>
            </g:if>
            <g:else>
                <g:render template="/expenseType/widgets/whoIOwe/index" model="[user: user, whoIOwe: whoIOwe]"/>
            </g:else>
        </div>
    </div>
</div>
