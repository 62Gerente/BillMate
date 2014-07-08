<div class="row tiles-container tiles white hidden-xlg m-b-20">
    <div class="col-md-12 b-grey b-r no-padding">
        <div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
            <h4 class="text-black bold inline uppercase">Tipos de despesas</h4>
            <div class="pull-right m-r-10" id="expense-type-circles-btnNew">
                <h4>
                    <i class="fa fa-plus"></i>
                </h4>
            </div>
        </div>
        <div class="p-l-20 p-r-20">
            <table class="table table-home-box table-home-box-fixed" id="circle-expense-type">
                <tbody>
                <g:if test="${expenseTypes.isEmpty()}">
                    <g:render template="/expenseType/index/empty"/>
                </g:if>
                <g:else>
                    <g:render template="/expenseType/index/index" model="[registeredUser: registeredUser, expenseTypes: expenseTypes]"/>
                </g:else>
                <tr>
                    <td colspan="3">
                        <div class="input-append success date m-t-20 m-b-20 div-new-expensetype-circle">
                            <input type="hidden" value="${circle.getId()}"/>
                            <input type="text" placeholder="<g:message code="com.billmate.circle.regularexpense.add" default="Add Expense Type" />" class="input-small new-expensetype-circle" style="width: 98%">
                            <span class="add-on submit-new-expensetype-circle" style="height: 35px">
                                <i class="fa fa-plus"></i>
                            </span>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
