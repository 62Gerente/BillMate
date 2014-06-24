<div class="tiles white col-md-12 p-t-30 p-b-30 p-r-40 p-l-40 m-b-20">
    <div class="row">
        <div class="text-center">
            <i class="${expense.getExpenseType().getCssClass()} fa-5x"></i>
            <h3>${expense}</h3>
            <h2 class="bold">15,30 €</h2>
            <p class="text-left">${expense.getDescription()}</p>
        </div>
    </div>
    <div class="row p-t-10 p-b-10">
        <g:if test="${expense.getBeginDate() && expense.getEndDate()}">
            <div class="col-md-6">
                <h6 class="semi-bold text-left no-margin">
                    <g:message code="com.billmate.expense.beginDate" default="Begin date" />
                </h6>
            </div>
            <div class="col-md-6">
                <h6 class="no-margin">
                    <g:formatDate date="${expense.getBeginDate()}" type="datetime" style="SMALL"/>
                </h6>
            </div>
            <div class="col-md-6">
                <h6 class="semi-bold text-left no-margin">
                    <g:message code="com.billmate.expense.endDate" default="End date" />
                </h6>
            </div>
            <div class="col-md-6">
                <h6 class="no-margin">
                    <g:formatDate date="${expense.getBeginDate()}" type="datetime" style="SMALL"/>
                </h6>
            </div>
        </g:if>
        <g:elseif test="expense.getBeginDate()">
            <div class="col-md-6">
                <h6 class="semi-bold text-left no-margin">
                    <g:message code="com.billmate.expense.date" default="Expense date" />
                </h6>
            </div>
            <div class="col-md-6">
                <h6 class="no-margin">
                    <g:formatDate date="${expense.getBeginDate()}" type="datetime" style="SMALL"/>
                </h6>
            </div>
        </g:elseif>
        <g:elseif test="expense.getPaymentDeadline()">
            <div class="col-md-6">
                <h6 class="semi-bold text-left no-margin">
                    <g:message code="com.billmate.expense.paymentDeadline" default="Payment deadline" />
                </h6>
            </div>
            <div class="col-md-6">
                <h6 class="no-margin">
                    <g:formatDate date="${expense.getPaymentDeadline()}" type="datetime" style="SMALL"/>
                </h6>
            </div>
        </g:elseif>
        <g:elseif test="expense.getReceptionDeadline()">
            <div class="col-md-6">
                <h6 class="semi-bold text-left no-margin">
                    <g:message code="com.billmate.expense.receptionDeadline" default="Reception deadline" />
                </h6>
            </div>
            <div class="col-md-6">
                <h6 class="no-margin">
                    <g:formatDate date="${expense.getReceptionDeadline()}" type="datetime" style="SMALL"/>
                </h6>
            </div>
        </g:elseif>
        <g:elseif test="expense.getPaymentDate()">
            <div class="col-md-6">
                <h6 class="semi-bold text-left no-margin">
                    <g:message code="com.billmate.expense.paymentDate" default="Payment date" />
                </h6>
            </div>
            <div class="col-md-6">
                <h6 class="no-margin">
                    <g:formatDate date="${expense.getPaymentDate()}" type="datetime" style="SMALL"/>
                </h6>
            </div>
        </g:elseif>
        <g:elseif test="expense.getReceptionDate()">
            <div class="col-md-6">
                <h6 class="semi-bold text-left no-margin">
                    <g:message code="com.billmate.expense.receptionDate" default="Reception date" />
                </h6>
            </div>
            <div class="col-md-6">
                <h6 class="no-margin">
                    <g:formatDate date="${expense.getReceptionDate()}" type="datetime" style="SMALL"/>
                </h6>
            </div>
        </g:elseif>
    </div>
    <div class="row p-t-5">
        <button class="btn btn-block btn-primary" type="button">
            <g:message code="com.billmate.payment.new" default="Delete expense" />
        </button>
        <button class="btn btn-block btn-white btn-danger-hover" type="button">
            <g:message code="com.billmate.expense.delete" default="Delete expense" />
        </button>
    </div>
</div>
<div class="clearfix"></div>
