<div class="row tiles-container tiles white hidden-xlg m-b-20">
    <div class="col-md-12 b-grey b-r no-padding">
        <div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
            <h4 class="text-black bold inline uppercase"><g:message code="com.billmate.regularexpense.debt.title" default="Debts" args="[regularExpense]" /></h4>

            <div class="pull-right">
                <h4>
                    <div id="add-new-debt-regularexpense">
                        <button type="button" class="btn btn-primary btn-small">
                            <g:message code="com.billmate.regularexpense.debt.addDebt" default="Add debt" />
                        </button>
                    </div>
                </h4>
            </div>
        </div>
        <div class="p-l-20 p-r-20">
            <input type="hidden" id="user-edit-list-in-circle" value="<g:createLink controller="regularExpense" action="listUsersBy"/>" />
            <table class="table table-home-box table-home-box-fixed table-edit-user-regular-expense">
                <tbody>
                    <g:if test="${debts.isEmpty()}">
                        <g:render template="/debt/widgets/default/empty"/>
                    </g:if>
                    <g:else>
                        <g:render template="/debt/widgets/default/index" model="[registeredUser: registeredUser, debts: debts]"/>
                    </g:else>
                    <tr>
                        <td colspan="5">
                            <div class="input-append success date m-t-20 m-b-20 div-new-friend-regular-expense hidden">
                                <input type="hidden" value="${regularExpense.getId()}"/>
                                <input type="text" placeholder="<g:message code="com.billmate.regularexpense.edit.user.add" default="Add member" />" class="input-small new-friend-edit-circle" style="width: 98%">
                                <span class="add-on submit-new-friend-regular-expense" style="height: 35px">
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
