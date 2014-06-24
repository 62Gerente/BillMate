<div class="row tiles-container tiles white hidden-xlg m-b-20 expenses-widget-more-details unselectable">
    <div class="row tiles-container tiles white hidden-xlg">
        <div class="col-md-12 b-grey b-r no-padding widget-table-adjust-right-margin">
            <div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
                <h4 class="text-black bold inline uppercase">
                    Atribuição de despesas
                </h4>
            </div>
            <g:if test="${users.isEmpty()}">
                <g:render template="/expense/widgets/paymentHistory/empty"/>
            </g:if>
            <g:else>
                <g:render template="/expense/widgets/paymentHistory/index" model="[expense: expense, users: users]"/>
            </g:else>
        </div>
    </div>
</div>
