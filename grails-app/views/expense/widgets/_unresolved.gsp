<div class="row tiles-container tiles white hidden-xlg m-b-20 expenses-widget-more-details unselectable">
    <div class="col-md-12 b-grey b-r no-padding widget-table-adjust-right-margin">
        <div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
            <h4 class="text-black bold inline uppercase">
                <g:message code="com.billmate.expense.widget.unresolved.title" default="Unresolved expenses" />
            </h4>
            <div class="pull-right home-button-primary-confirm">
                <span class="label label-important">
                    <h5 class="text-white inline p-t-2">
                        <g:formatNumber number="${circle.totalDebtOfUnresolvedExpenses()}" type="currency" currencyCode="EUR" />
                        /
                        <span class="bold">
                            <g:formatNumber number="${circle.totalValueOfUnresolvedExpenses()}" type="currency" currencyCode="EUR" />
                        </span>
                    </h5>
                </span>
            </div>
        </div>
        <g:if test="${expenses.isEmpty()}">
            <g:render template="/expense/widgets/unresolved/empty"/>
        </g:if>
        <g:else>
            <g:render template="/expense/widgets/unresolved/index" model="[registeredUser: registeredUser, expenses: expenses]"/>
        </g:else>
    </div>
</div>
