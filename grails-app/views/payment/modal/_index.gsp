<div class="modal fade" id="detailedConfirmPayments" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                <h4 id="myModalLabel" class="semi-bold"><g:message code="com.billmate.payment.confirmAll.mTitle" default="Pay Expense"/></h4>
                <p class="no-margin"><g:message code="com.billmate.payment.info" default="Fill in fields with information of the expense"/></p>
                <br>
            </div>
            <div class="modal-body">
                <div class="simple-options-form-detailedConfirmPayments">
                    <div class="row form-row m-b-10">
                        <div class="col-md-12">
                            <div class="input-group transparent">
                                <span class="input-group-addon">
                                    <i class="fa fa-euro"></i>
                                </span>
                                <input id="valueExpense" class="form-control value-detailedConfirmPayments" type="number" step="any" name="detailedConfirmPayments" value="" placeholder="<g:message code='com.billmate.payment.value' default='Value'/>"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="notification-messages no-padding m-t-20">
                    <div class="grid simple">
                        <div class="grid-body no-border">
                            <table class="table table-hover table-condensed" id="users-detailedConfirmPayments">
                                <thead>
                                <tr>
                                    <th><g:message code="com.billmate.payment.mName" default="Name" /></th>
                                    <th><g:message code="com.billmate.payment.mCircle" default="Circle" /></th>
                                    <th><g:message code="com.billmate.payment.mTotal" default="Total" /></th>
                                    <th></th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="pull-right">
                    <input type="hidden" value="<g:createLink controller="payment" action="confirmAll" />"/>
                    <button type="button" class="btn btn-default" data-dismiss="modal"><g:message code="com.billmate.btn.cancel" default="Cancel"/></button>
                    <button type="button" class="btn btn-primary btn-submit-new-detailedConfirmPayments">
                        <g:message code="com.billmate.payment.confirmPayment" default="Add Debt"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
