<div class="tiles white col-md-12 p-t-30 p-b-30 p-r-40 p-l-40 m-b-20">
    <div class="row">
        <div class="text-center">
            <i class="${regularExpense.getExpenseType().getCssClass()} fa-5x"></i>
            <h3>
                <a href="#" class="edit-property edit-property-sm" id="edit_title" data-emptytext="<g:message code="com.billmate.regularExpense.title.xeditable.clickToDefine" default="Click to define scheduled expense's title" />" data-name="title" data-type="text" data-pk="${regularExpense.getId()}" data-url="${createLink(controller: 'regularExpense', action: 'updateProperty', id: regularExpense.getId())}">
                    ${regularExpense}
                </a>
            </h3>
            <h2 class="bold">
                <a href="#" class="edit-property edit-property-sm" id="edit_value" data-emptytext="<g:message code="com.billmate.regularExpense.value.xeditable.clickToDefine" default="Click to define scheduled expense's value" />" data-name="value" data-type="number" data-step="any" data-min="0.01" data-pk="${regularExpense.getId()}" data-url="${createLink(controller: 'regularExpense', action: 'updateProperty', id: regularExpense.getId())}">
                    ${regularExpense.getValue()}
                </a>
            </h2>
            <p class="text-left p-t-10 editable-ta-fullwidth">
                <a href="#" class="edit-property edit-property-sm display-block" id="edit_description" data-emptytext="<g:message code="com.billmate.regularExpense.description.xeditable.clickToDefine" default="Click to define scheduled expense's description" />" data-showbuttons="bottom" data-name="description" data-type="textarea" data-pk="${regularExpense.getId()}" data-url="${createLink(controller: 'regularExpense', action: 'updateProperty', id: regularExpense.getId())}">
                    ${regularExpense.getDescription()}
                </a>
            </p>
        </div>
    </div>
    <div class="row p-t-10 p-b-10">
        <div class="col-md-6">
            <h6 class="semi-bold text-left no-margin label-property-sm">
                <g:message code="com.billmate.regularExpense.responsible" default="Responsible" />
            </h6>
        </div>
        <div class="col-md-6">
            <h6 class="no-margin p-t-5 p-b-5">
                <a href="#" class="edit-property edit-property-sm" id="edit-responsible" data-name="responsible" data-mode="popup" data-type="select" data-pk="${regularExpense.getId()}" data-title="<g:message code="com.billmate.regularExpense.responsible.select" default="Select responsible" />" data-url="${createLink(controller: 'regularExpense', action: 'updateProperty', id: regularExpense.getId())}">
                    ${regularExpense.getResponsible()}
                </a>
            </h6>
        </div>
        <div class="col-md-6">
            <h6 class="semi-bold text-left no-margin label-property-sm">
                <g:message code="com.billmate.regularExpense.receptionBeginDate" default="Reception date" />
            </h6>
        </div>
        <div class="col-md-6">
            <h6 class="no-margin p-t-5 p-b-5">
                <a href="#" class="edit-property edit-property-sm" id="edit_reception_begin_date" data-mode="popup" data-showbuttons="bottom" data-placement="left" data-emptytext="<g:message code="com.billmate.xeditable.clickToDefine" default="Click to define" />" data-name="receptionBeginDate" data-type="combodate" data-template="D MMM YYYY  HH:mm" data-format="DD-MM-YYYY HH:mm" data-viewformat="DD-MM-YYYY HH:mm" data-pk="${regularExpense.getId()}" data-url="${createLink(controller: 'regularExpense', action: 'updateProperty', id: regularExpense.getId())}">
                    <g:formatDate date="${regularExpense.getReceptionBeginDate()}" type="datetime" style="SMALL"/>
                </a>
            </h6>
        </div>
        <div class="col-md-6">
            <h6 class="semi-bold text-left no-margin label-property-sm">
                <g:message code="com.billmate.regularExpense.beginDate" default="Begin date" />
            </h6>
        </div>
        <div class="col-md-6">
            <h6 class="no-margin p-t-5 p-b-5">
                <a href="#" class="edit-property edit-property-sm" id="edit_begin_date" data-mode="popup" data-showbuttons="bottom" data-placement="left" data-emptytext="<g:message code="com.billmate.xeditable.clickToDefine" default="Click to define" />" data-name="beginDate" data-type="combodate" data-template="D MMM YYYY  HH:mm" data-format="DD-MM-YYYY HH:mm" data-viewformat="DD-MM-YYYY HH:mm" data-pk="${regularExpense.getId()}" data-url="${createLink(controller: 'regularExpense', action: 'updateProperty', id: regularExpense.getId())}">
                    <g:formatDate date="${regularExpense.getBeginDate()}" type="datetime" style="SMALL"/>
                </a>
            </h6>
        </div>
        <div class="col-md-6">
            <h6 class="semi-bold text-left no-margin label-property-sm">
                <g:message code="com.billmate.regularExpense.endDate" default="End date" />
            </h6>
        </div>
        <div class="col-md-6">
            <h6 class="no-margin p-t-5 p-b-5">
                <a href="#" class="edit-property edit-property-sm" id="edit_end_date" data-mode="popup" data-showbuttons="bottom" data-placement="left" data-emptytext="<g:message code="com.billmate.xeditable.clickToDefine" default="Click to define" />" data-name="endDate" data-type="combodate" data-template="D MMM YYYY  HH:mm" data-format="DD-MM-YYYY HH:mm" data-viewformat="DD-MM-YYYY HH:mm" data-pk="${regularExpense.getId()}" data-url="${createLink(controller: 'regularExpense', action: 'updateProperty', id: regularExpense.getId())}">
                    <g:formatDate date="${regularExpense.getEndDate()}" type="datetime" style="SMALL"/>
                </a>
            </h6>
        </div>
        <div class="col-md-6">
            <h6 class="semi-bold text-left no-margin label-property-sm">
                <g:message code="com.billmate.regularExpense.paymentDeadline" default="Payment deadline" />
            </h6>
        </div>
        <div class="col-md-6">
            <h6 class="no-margin p-t-5 p-b-5">
                <a href="#" class="edit-property edit-property-sm" id="edit_payment_deadline" data-mode="popup" data-showbuttons="bottom" data-placement="left" data-emptytext="<g:message code="com.billmate.xeditable.clickToDefine" default="Click to define" />" data-name="paymentDeadline" data-type="combodate" data-template="D MMM YYYY  HH:mm" data-format="DD-MM-YYYY HH:mm" data-viewformat="DD-MM-YYYY HH:mm" data-pk="${regularExpense.getId()}" data-url="${createLink(controller: 'regularExpense', action: 'updateProperty', id: regularExpense.getId())}">
                    <g:formatDate date="${regularExpense.getPaymentDeadline()}" type="datetime" style="SMALL"/>
                </a>
            </h6>
        </div>
        <div class="col-md-6">
            <h6 class="semi-bold text-left no-margin label-property-sm" style="width: 105%;">
                <g:message code="com.billmate.regularExpense.receptionDeadline" default="Reception deadline" />
            </h6>
        </div>
        <div class="col-md-6">
            <h6 class="no-margin p-t-5 p-b-5">
                <a href="#" class="edit-property edit-property-sm" id="edit_reception_deadline" data-mode="popup" data-showbuttons="bottom" data-placement="left" data-emptytext="<g:message code="com.billmate.xeditable.clickToDefine" default="Click to define" />" data-name="receptionDeadline" data-type="combodate" data-template="D MMM YYYY  HH:mm" data-format="DD-MM-YYYY HH:mm" data-viewformat="DD-MM-YYYY HH:mm" data-pk="${regularExpense.getId()}" data-url="${createLink(controller: 'regularExpense', action: 'updateProperty', id: regularExpense.getId())}">
                    <g:formatDate date="${regularExpense.getReceptionDeadline()}" type="datetime" style="SMALL"/>
                </a>
            </h6>
        </div>
    </div>
    <div class="row p-t-10">
        <button class="btn btn-block btn-primary" type="button">
            <g:message code="com.billmate.expense.new" default="Create expense" />
        </button>
        <button class="btn btn-block btn-white btn-danger-hover" type="button">
            <g:message code="com.billmate.regularExpense.unchedule" default="Unschedule expense" />
        </button>
    </div>
</div>
<div class="clearfix"></div>

<script>
    $(function(){
        $('#edit-responsible').editable({
            value: ${regularExpense.getResponsibleId()},
            source: [
                <g:each in="${regularExpense.getAssignedRegisteredUsers()}" var="user">
                {value: ${user.getId()}, text: '${user}'},
                </g:each>
            ]
        });
    });
</script>
