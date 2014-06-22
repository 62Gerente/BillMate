<div class="div-table-widget-payment">
    <table class="table table-home-box">
        <thead>
        <tr>
            <th class="text-left text-primary uppercase"><g:message code="com.billmate.user" default="User"/></th>
            <th class="unseen text-primary uppercase"><g:message code="com.billmate.expense.paymentDeadline" default="Payment deadline" /></th>
            <th class="unseen text-right text-primary uppercase"><g:message code="com.billmate.expense.total" default="Total" /></th>
            <th class="text-right text-primary uppercase"><g:message code="com.billmate.expense.debt" default="Debt" /></th>
            <th class="text-right text-primary uppercase td-settings"></th>
        </tr>
        </thead>
        <tbody>
        <g:if test="${users.isEmpty()}">
            <g:render template="/expense/table/unresolved/empty"/>
        </g:if>
        <g:else>
            <g:render template="/expense/table/unresolved/index" model="[registeredUser: registeredUser, expense: expense, users: users]"/>
        </g:else>
        <g:if test="${users.size()>1}">
            <tr>
                <td></td>
                <td class="unseen"></td>
                <td class="unseen"></td>
                <td class="text-right text-grey">
                    <div class="inline b-t b-grey p-t-5">
                        <g:formatNumber number="${expense.totalDebt()}" type="currency" currencyCode="EUR" />
                    </div>
                </td>
                <td></td>
            </tr>
        </g:if>
        </tbody>
    </table>
    <div class="m-b-10 home-button-divida-secondary-confirm">
        <button type="button" class="btn btn-white btn-small width-btn-payment">
            <g:message code="com.billmate.expense.show" default="Show expense" />
        </button>
    </div>
</div>
<div class="clearfix"></div>
