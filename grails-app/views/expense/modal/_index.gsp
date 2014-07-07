<div class="modal fade" id="expenseCreateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="" style="display: none">
            <button class="close"></button>
            <div><g:message code=""/></div>
        </div>
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <br>
                <i class="fa fa-money fa-7x"></i>
                <h4 id="myModalLabel" class="semi-bold"><g:message code="com.billmate.expense.modal.create" default="Create new expense"/></h4>
                <p class="no-margin"><g:message code="com.billmate.expense.modal.info" default="Fill in fields with information of new expense"/></p>
                <br>
            </div>
            <div class="modal-body">
                <div class="simple-options-form-debt">
                    <input type="hidden" value="${registeredUser}"/>
                    <input type="hidden" value="${user}"/>
                    <div class="row form-row">
                        <div class="col-md-12">
                            <input type="text" class="form-control" placeholder="<g:message code="com.billmate.expense.modal.placeholderNameExpense" default="Expense name"/>"/>
                        </div>
                    </div>
                    <div class="row form-row m-b-10">
                        <div class="col-md-12">
                            <input type="hidden" class="custom-multiselect-expense-circle" placeholder="<g:message code="com.billmate.expense.modal.placeholderCircle" default="Circle"/>" style="width:100%" class="select2-offscreen"/>
                        </div>
                    </div>
                    <div class="row form-row m-b-10">
                            <div class="col-md-6">
                                <input type="hidden" class="custom-multiselect-expense-debt" placeholder="<g:message code="com.billmate.expense.modal.placeholderExpenseType" default="Expense Type"/>" style="width:100%" class="select2-offscreen"/>
                                <input type="hidden" id="expense-type-link-expense" value="<g:createLink controller="circle" action="expenseType"/>" />
                                <input type="hidden" id="circle-link-expense" value="<g:createLink controller="circle" action="circles"/>" />
                                <input type="hidden" id="users-link-expense" value="<g:createLink controller="circle" action="assignedUsers"/>" />
                                <input type="hidden" id="submit-link-expense" value="<g:createLink controller="expense" action="save"/>" />
                            </div>
                            <div class="col-md-6">
                                <div class="input-group transparent">
                                    <span class="input-group-addon">
                                        <i class="fa fa-euro"></i>
                                    </span>
                                    <input id="valueExpense" class="form-control value-debt" type="number" step="any" name="value" value="" placeholder="Valor"/>
                                </div>
                            </div>
                    </div>
                    <div class="row form-row">
                        <div class="col-md-12">
                            <g:textArea name="houseDescription" rows="5" placeholder="${message(code: 'com.billmate.expense.modal.placeholderDescription')}" class="form-control houseDescription"/>
                        </div>
                    </div>
                </div>
                <div class="photos-options-form-debt">
                    <div class="row form-row">
                        <div class="col-md-12">
                            <select class="image-picker limit_callback show-html select-list-users" multiple="multiple">
                            </select>
                        </div>
                    </div>
                </div>
                <div class="advanced-options-form-debt m-t-10" style="display: none">
                    <div class="row form-row m-b-10">
                        <div class="col-md-6 col-lg-6">
                            <div class="input-append success date" style="width: 86%;">
                                <input type="text" placeholder="<g:message code="com.billmate.expense.modal.paymentDateLimit" default="Limit date to pay"/>" class="form-control input-small clockTimePaymentExpense">
                                <span class="add-on">
                                    <span class="arrow"></span><i class="fa fa-calendar"></i>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-6">
                            <div class="input-append success date" style="width: 86%;">
                                <input type="text" placeholder="<g:message code="com.billmate.expense.modal.receiptDateLimit" default="Limit date to pay you"/>" class="form-control input-small clockTimePaymentExpense">
                                <span class="add-on">
                                    <span class="arrow"></span><i class="fa fa-calendar"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="row form-row m-b-10">
                        <div class="col-md-6 col-lg-6">
                            <div class="input-append success date" style="width: 86%;">
                                <input type="text" placeholder="<g:message code="com.billmate.expense.modal.beginDate" default="Expense date"/>" class="form-control input-small clockTimePaymentExpense">
                                <span class="add-on">
                                    <span class="arrow"></span><i class="fa fa-calendar"></i>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-6">
                            <div class="input-append success date" style="width: 86%;">
                                <input type="text" placeholder="<g:message code="com.billmate.expense.modal.endDate" default="Final Date of the expense"/>" class="form-control input-small clockTimePaymentExpense">
                                <span class="add-on">
                                    <span class="arrow"></span><i class="fa fa-calendar"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="row form-row m-b-10">
                        <div class="col-md-6 col-lg-6">
                            <div class="input-append success date" style="width: 86%;">
                                <input type="text" placeholder="<g:message code="com.billmate.expense.modal.paymentDate" default="Have you paid?"/>" class="form-control input-small clockTimePaymentExpense">
                                <span class="add-on">
                                    <span class="arrow"></span><i class="fa fa-calendar"></i>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-6">
                            <div class="input-append success date" style="width: 86%;">
                                <input type="text" placeholder="<g:message code="com.billmate.expense.modal.receptionDate" default="Already paid you?"/>" class="form-control input-small clockTimePaymentExpense">
                                <span class="add-on">
                                    <span class="arrow"></span><i class="fa fa-calendar"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="pull-left">
                    <button type="button" class="btn btn-default btn-options-form-debt"><g:message code="com.billmate.expense.modal.advancedOptions" default="Advanced options"/></button>
                </div>
                <div class="pull-right">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><g:message code="com.billmate.btn.cancel" default="Cancel"/></button>
                    <button type="button" class="btn btn-primary btn-submit-new-debt">
                        <g:message code="com.billmate.sidebar.addDebt" default="Add Debt"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
