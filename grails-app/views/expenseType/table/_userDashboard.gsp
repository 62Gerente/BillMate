<div class="div-table-widget-payment">
    <table class="table table-home-box">
        <thead>
        <tr>
            <th class="text-left text-primary uppercase"><g:message code="com.billmate.expense" default="Expense" /></th>
            <th class="text-left unseen text-primary uppercase"><g:message code="com.billmate.circle" default="Circle" /></th>
            <th class="unseen text-primary uppercase"><g:message code="com.billmate.expense.paymentDeadline" default="Payment deadline" /></th>
            <th class="text-right text-primary uppercase"><g:message code="com.billmate.expense.value" default="Value" /></th>
        </tr>
        </thead>
        <tbody>
        <g:if test="${unresolvedExpenses.isEmpty()}">
            <g:render template="/expenseType/table/userDashboard/empty"/>
        </g:if>
        <g:else>
            <g:render template="/expenseType/table/userDashboard/index" model="[user: user, unresolvedExpenses: unresolvedExpenses]"/>
        </g:else>
        <g:if test="${unresolvedExpenses.size()>1}">
            <tr>
                <td class="text-right text-grey" colspan="4">
                    <div class="inline b-t b-grey p-t-5">
                        <g:formatNumber number="${unresolvedExpenses.sum{ it.debtOf(user.getId()) }}" type="currency" currencyCode="EUR" />
                    </div>
                </td>
            </tr>
        </g:if>
        </tbody>
    </table>
    <div class="m-b-10 home-button-divida-secondary-confirm">
        <button type="button" class="btn btn-default btn-small width-btn-payment">
            <g:message code="com.billmate.payment.new" default="Pay it now" />
        </button>
    </div>
</div>
<div class="clearfix"></div>
