<div class="col-md-12">

    <div class="grid simple no-border upcoming-regular-expense">
        <div class="grid-title no-border descriptive clickable">
            <h4 class="semi-bold no-margin"><i class="${regularExpense.getExpenseType().getCssClass()} p-r-5"></i>${regularExpense}</h4>
            <p class="">
                <span class="muted"><g:message code="com.billmate.regularExpense.alreadyReceived" default="Have you already received the {0} bill?" args="${[regularExpense]}" /></span>
            </p>
            <div class="actions upcoming-regular-expense-expand"> <a class="view" href="javascript:;"><i class="fa fa-angle-down"></i></a>
            </div>

        </div>
        <div class="grid-body no-border ">
            <div class="post p-b-5">
                <div class="info-wrapper">
                    <div class="info">
                      <p class="small">
                          <i class="${regularExpense.getCircle().getCssClass()}"></i>
                          ${regularExpense.getCircle()}
                      </p>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
              <g:form url="[action: 'saveExpense', controller: 'regularExpense', id: regularExpense.getId()]" class="upcoming-expense-form">
                <input type="hidden" value="<g:createLink controller = "regularExpense" action="show" params="[id:regularExpense.getId()]"/>" />
                <input type="hidden" value="<g:createLink controller = "circle" action="show" params="[id:regularExpense.getCircle().getId()]"/>" />
                <input type="hidden" value="${regularExpense.getId()}" />
                <div class="form-actions">
                    <div class="input-group transparent">
                        <span class="input-group-addon ">
                            <i class="fa fa-euro"></i>
                        </span>
                        <input class="form-control" type="number" step="any" name="value" value="${regularExpense.getValue()}" placeholder="<g:message code="com.billmate.regularExpense.value" default="Value" />">
                    </div>
                </div>
                <div class="white text-right">
                    <div class="p-t-45 p-b-10">
                        <div class="btn-group">
                            <button class="btn btn-small btn-info btn-demo-space"><g:message code="com.billmate.expense.create" default="Add expense" /></button>
                            <button type="button" class="btn btn-small btn-info dropdown-toggle btn-demo-space" data-toggle="dropdown"> <span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li><a href="#" data-toggle="modal" data-target="#regularExpenseCreateModal" class="regular-expense-advanced-options"><g:message code="com.billmate.regularExpense.advancedOptions" default="Advanced options" /></a></li>
                                <li class="divider"></li>
                                <li>
                                    <g:link url="[action: 'postpone', controller: 'regularExpense', id: regularExpense.getId()]" class="postpone-upcoming-expense">
                                        <g:message code="com.billmate.regularExpense.cancel" default="Cancel expense" />
                                    </g:link>
                                </li>
                                <li><a href="#" data-toggle="modal" data-target="#regularExpenseEditModal"><g:message code="com.billmate.regularExpense.settings" default="Settings" /></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
              </g:form>
        </div>
    </div>
</div>
