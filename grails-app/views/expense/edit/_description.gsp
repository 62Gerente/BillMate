<div class="tiles white col-md-12 p-t-30 p-b-30 p-r-40 p-l-40 m-b-20">
    <div class="row">
        <div class="text-center">
            <i class="${expense.getExpenseType().getCssClass()} fa-5x"></i>
            <h3>
                <g:if test="${expense.getResponsible().getId() == registeredUser.getId()}">
                    <a href="#" class="edit-property edit-property-sm" id="edit_title" data-emptytext="<g:message code="com.billmate.expense.title.xeditable.clickToDefine" default="Click to define expense's title" />" data-name="title" data-type="text" data-pk="${expense.getId()}" data-url="${createLink(controller: 'expense', action: 'updateProperty', id: expense.getId())}">
                        ${expense}
                    </a>
                </g:if>
                <g:else>
                    ${expense}
                </g:else>
            </h3>
            <h2 class="bold">15,30 €</h2>

            <p class="text-left p-t-10 editable-ta-fullwidth">
                <g:if test="${expense.getResponsible().getId() == registeredUser.getId()}">
                    <a href="#" class="edit-property edit-property-sm display-block" id="edit_description" data-emptytext="<g:message code="com.billmate.expense.description.xeditable.clickToDefine" default="Click to define expense's description" />" data-showbuttons="bottom" data-name="description" data-type="textarea" data-pk="${expense.getId()}" data-url="${createLink(controller: 'expense', action: 'updateProperty', id: expense.getId())}">
                            ${expense.getDescription()}
                    </a>
                </g:if>
                <g:else>
                    ${expense.getDescription()}
                </g:else>
            </p>
        </div>
    </div>
    <div class="row p-t-10 p-b-10">
        <g:if test="${expense.getBeginDate() && expense.getEndDate()}">
            <div class="col-md-6">
                <h6 class="semi-bold text-left no-margin label-property-sm">
                    <g:message code="com.billmate.expense.beginDate" default="Begin date" />
                </h6>
            </div>
            <div class="col-md-6">
                <h6 class="no-margin p-t-5 p-b-5">
                    <g:if test="${expense.getResponsible().getId() == registeredUser.getId()}">
                        <a href="#" class="edit-property edit-property-sm" id="edit_begin_date" data-mode="popup" data-showbuttons="bottom" data-placement="left" data-emptytext="<g:message code="com.billmate.xeditable.clickToDefine" default="Click to define" />" data-name="beginDate" data-type="combodate" data-template="D MMM YYYY  HH:mm" data-format="DD-MM-YYYY HH:mm" data-viewformat="DD-MM-YYYY HH:mm" data-pk="${expense.getId()}" data-url="${createLink(controller: 'expense', action: 'updateProperty', id: expense.getId())}">
                            <g:formatDate date="${expense.getBeginDate()}" type="datetime" style="SMALL"/>
                        </a>
                    </g:if>
                    <g:else>
                        <g:formatDate date="${expense.getBeginDate()}" type="datetime" style="SMALL"/>
                    </g:else>
                </h6>
            </div>
            <div class="col-md-6">
                <h6 class="semi-bold text-left no-margin label-property-sm">
                    <g:message code="com.billmate.expense.endDate" default="End date" />
                </h6>
            </div>
            <div class="col-md-6">
                <h6 class="no-margin p-t-5 p-b-5">
                    <g:if test="${expense.getResponsible().getId() == registeredUser.getId()}">
                        <a href="#" class="edit-property edit-property-sm" id="edit_end_date" data-mode="popup" data-showbuttons="bottom" data-placement="left" data-emptytext="<g:message code="com.billmate.xeditable.clickToDefine" default="Click to define" />" data-name="endDate" data-type="combodate" data-template="D MMM YYYY  HH:mm" data-format="DD-MM-YYYY HH:mm" data-viewformat="DD-MM-YYYY HH:mm" data-pk="${expense.getId()}" data-url="${createLink(controller: 'expense', action: 'updateProperty', id: expense.getId())}">
                            <g:formatDate date="${expense.getEndDate()}" type="datetime" style="SMALL"/>
                        </a>
                    </g:if>
                    <g:else>
                        <g:formatDate date="${expense.getEndDate()}" type="datetime" style="SMALL"/>
                    </g:else>
                </h6>
            </div>
        </g:if>
        <g:elseif test="expense.getBeginDate()">
            <div class="col-md-6">
                <h6 class="semi-bold text-left no-margin label-property-sm">
                    <g:message code="com.billmate.expense.date" default="Expense date" />
                </h6>
            </div>
            <div class="col-md-6">
                <h6 class="no-margin p-t-5 p-b-5">
                    <g:if test="${expense.getResponsible().getId() == registeredUser.getId()}">
                        <a href="#" class="edit-property edit-property-sm" id="edit_date" data-mode="popup" data-showbuttons="bottom" data-placement="left" data-emptytext="<g:message code="com.billmate.xeditable.clickToDefine" default="Click to define" />" data-name="beginDate" data-type="combodate" data-template="D MMM YYYY  HH:mm" data-format="DD-MM-YYYY HH:mm" data-viewformat="DD-MM-YYYY HH:mm" data-pk="${expense.getId()}" data-url="${createLink(controller: 'expense', action: 'updateProperty', id: expense.getId())}">
                            <g:formatDate date="${expense.getBeginDate()}" type="datetime" style="SMALL"/>
                        </a>
                    </g:if>
                    <g:else>
                        <g:formatDate date="${expense.getBeginDate()}" type="datetime" style="SMALL"/>
                    </g:else>
                </h6>
            </div>
        </g:elseif>
        <g:elseif test="expense.getPaymentDeadline()">
            <div class="col-md-6">
                <h6 class="semi-bold text-left no-margin label-property-sm">
                    <g:message code="com.billmate.expense.paymentDeadline" default="Payment deadline" />
                </h6>
            </div>
            <div class="col-md-6">
                <h6 class="no-margin p-t-5 p-b-5">
                    <g:if test="${expense.getResponsible().getId() == registeredUser.getId()}">
                        <a href="#" class="edit-property edit-property-sm" id="edit_payment_deadline" data-mode="popup" data-showbuttons="bottom" data-placement="left" data-emptytext="<g:message code="com.billmate.xeditable.clickToDefine" default="Click to define" />" data-name="paymentDeadline" data-type="combodate" data-template="D MMM YYYY  HH:mm" data-format="DD-MM-YYYY HH:mm" data-viewformat="DD-MM-YYYY HH:mm" data-pk="${expense.getId()}" data-url="${createLink(controller: 'expense', action: 'updateProperty', id: expense.getId())}">
                            <g:formatDate date="${expense.getPaymentDeadline()}" type="datetime" style="SMALL"/>
                        </a>
                    </g:if>
                    <g:else>
                        <g:formatDate date="${expense.getPaymentDeadline()}" type="datetime" style="SMALL"/>
                    </g:else>
                </h6>
            </div>
        </g:elseif>
        <g:elseif test="expense.getReceptionDeadline()">
            <div class="col-md-6">
                <h6 class="semi-bold text-left no-margin label-property-sm">
                    <g:message code="com.billmate.expense.receptionDeadline" default="Reception deadline" />
                </h6>
            </div>
            <div class="col-md-6">
                <h6 class="no-margin p-t-5 p-b-5">
                    <g:if test="${expense.getResponsible().getId() == registeredUser.getId()}">
                        <a href="#" class="edit-property edit-property-sm" id="edit_reception_deadline" data-mode="popup" data-showbuttons="bottom" data-placement="left" data-emptytext="<g:message code="com.billmate.xeditable.clickToDefine" default="Click to define" />" data-name="receptionDeadline" data-type="combodate" data-template="D MMM YYYY  HH:mm" data-format="DD-MM-YYYY HH:mm" data-viewformat="DD-MM-YYYY HH:mm" data-pk="${expense.getId()}" data-url="${createLink(controller: 'expense', action: 'updateProperty', id: expense.getId())}">
                            <g:formatDate date="${expense.getReceptionDeadline()}" type="datetime" style="SMALL"/>
                        </a>
                    </g:if>
                    <g:else>
                        <g:formatDate date="${expense.getReceptionDeadline()}" type="datetime" style="SMALL"/>
                    </g:else>
                </h6>
            </div>
        </g:elseif>
        <g:elseif test="expense.getPaymentDate()">
            <div class="col-md-6">
                <h6 class="semi-bold text-left no-margin label-property-sm">
                    <g:message code="com.billmate.expense.paymentDate" default="Payment date" />
                </h6>
            </div>
            <div class="col-md-6">
                <h6 class="no-margin p-t-5 p-b-5">
                    <g:if test="${expense.getResponsible().getId() == registeredUser.getId()}">
                        <a href="#" class="edit-property edit-property-sm" id="edit_payment_date" data-mode="popup" data-showbuttons="bottom" data-placement="left" data-emptytext="<g:message code="com.billmate.xeditable.clickToDefine" default="Click to define" />" data-name="paymentDate" data-type="combodate" data-template="D MMM YYYY  HH:mm" data-format="DD-MM-YYYY HH:mm" data-viewformat="DD-MM-YYYY HH:mm" data-pk="${expense.getId()}" data-url="${createLink(controller: 'expense', action: 'updateProperty', id: expense.getId())}">
                            <g:formatDate date="${expense.getPaymentDate()}" type="datetime" style="SMALL"/>
                        </a>
                    </g:if>
                    <g:else>
                        <g:formatDate date="${expense.getPaymentDate()}" type="datetime" style="SMALL"/>
                    </g:else>
                </h6>
            </div>
        </g:elseif>
        <g:elseif test="expense.getReceptionDate()">
            <div class="col-md-6">
                <h6 class="semi-bold text-left no-margin label-property-sm">
                    <g:message code="com.billmate.expense.receptionDate" default="Reception date" />
                </h6>
            </div>
            <div class="col-md-6">
                <h6 class="no-margin p-t-5 p-b-5">
                    <g:if test="${expense.getResponsible().getId() == registeredUser.getId()}">
                        <a href="#" class="edit-property edit-property-sm" id="edit_reception_date" data-mode="popup" data-showbuttons="bottom" data-placement="left" data-emptytext="<g:message code="com.billmate.xeditable.clickToDefine" default="Click to define" />" data-name="receptionDate" data-type="combodate" data-template="D MMM YYYY  HH:mm" data-format="DD-MM-YYYY HH:mm" data-viewformat="DD-MM-YYYY HH:mm" data-pk="${expense.getId()}" data-url="${createLink(controller: 'expense', action: 'updateProperty', id: expense.getId())}">
                            <g:formatDate date="${expense.getReceptionDate()}" type="datetime" style="SMALL"/>
                        </a>
                    </g:if>
                    <g:else>
                        <g:formatDate date="${expense.getReceptionDate()}" type="datetime" style="SMALL"/>
                    </g:else>
                </h6>
            </div>
        </g:elseif>
    </div>
    <div class="row p-t-5">
        <g:if test="${!expense.isAssignedTo(registeredUser.getUser().getId()) && !expense.isResolvedBy(registeredUser.getUser().getId())}">
            <button class="btn btn-block btn-primary" type="button">
                <g:message code="com.billmate.payment.new" default="New payment" />
            </button>
        </g:if>
        <g:else>
            <button class="btn btn-block btn-primary" type="button" disabled>
                <g:message code="com.billmate.payment.new" default="New payment" />
            </button>
        </g:else>
        <g:if test="${false && expense.haveAcceptedPayments()}">
            <button class="btn btn-block btn-white btn-danger-hover" type="button" disabled>
                <g:message code="com.billmate.expense.delete" default="Delete expense" />
            </button>
        </g:if>
        <g:else>
            <g:form data-confirm-cancel="${message(code: 'com.billmate.btn.cancel', default: 'Cancel')}" data-confirm-ok="${message(code: 'com.billmate.btn.delete', default: 'Delete')}" data-confirm-title="${message(code: 'com.billmate.pleaseConfirm', default: 'Please confirm')}" data-confirm-message="${message(code: 'com.billmate.expense.areYouSure', default: 'Are you sure you want to delete this expense ?')}" id="deleteExpense" class="p-t-5" url="[action:'delete',controller:'expense',id:expense.getId()]" method="DELETE">
                <button type="submit" class="btn btn-block btn-white btn-danger-hover">
                    <g:message code="com.billmate.expense.delete" default="Delete expense" />
                </button>
            </g:form>
        </g:else>
    </div>
</div>
<div class="clearfix"></div>
