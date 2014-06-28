<div class="row tiles-container tiles white hidden-xlg m-b-20 unselectable">
    <div class="row tiles-container tiles white hidden-xlg">
        <div class="col-md-12 b-grey b-r no-padding widget-table-adjust-right-margin">
            <div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
                <h4 class="text-black bold inline uppercase">
                    <g:message code="com.billmate.expense.widget.whoOweMe.title" default="Who owe me" />
                </h4>
                <div class="pull-right p-t-2">
                    <span class="label label-success">
                        <h5 class="text-white bold inline">
                            <g:formatNumber number="${totalAsset}" type="currency" currencyCode="EUR" />
                        </h5>
                    </span>
                </div>
            </div>
            <g:if test="${whoOweMe.isEmpty()}">
                <g:render template="/expense/widgets/whoOweMe/empty"/>
            </g:if>
            <g:else>
                <g:render template="/expense/widgets/whoOweMe/index" model="[registeredUser: registeredUser, whoOweMe: whoOweMe]"/>
            </g:else>
        </div>
    </div>
</div>
