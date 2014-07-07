<div class="cbp_tmicon ${action.getActionType().getCssClass()}">
    <i class="${action.getActionType().getIcon()} fa-1-2x m-t-10"></i>
</div>

<div class="cbp_tmlabel">
    <div class="p-t-5 p-l-30 p-r-20 p-b-10 xs-p-r-10 xs-p-l-10 xs-p-t-5">
        <div class="muted m-t-15">
            <i class="fa ${action.getCircle().getCssClass()}"></i> ${action.getCircle()} - <prettytime:display date="${action.getActionDate()}" />
        </div>
        <h4 class="dark-text">
            <g:message code="com.billmate.history.addPaymentExpense" args="[null, action.getExpense().getResponsible(), null, action.getExpense(), null, action.getPayment()]"/>
        </h4>
    </div>

    <div class="clearfix"></div>

    <div class="tiles grey p-t-10 p-b-10 p-l-20 p-r-20">
        <span class="muted dark-text"><g:message code="com.billmate.expense.action.total"/>:</span> <span class="label label-success">${action.getPayment()}</span>
    </div>
</div>
