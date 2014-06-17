<g:if test="${!whoHaveUnconfirmedPayments.isEmpty()}">
    <div class="row tiles-container tiles white hidden-xlg m-b-20 confirm-payments-widget">
        <div class="row tiles-container tiles white hidden-xlg">
            <div class="col-md-12 b-grey b-r no-padding widget-table-adjust-right-margin">
                <div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
                    <h4 class="text-black bold inline uppercase">
                        <g:message code="com.billmate.payment.widget.confirm.title" default="Payments waiting confirmation" />
                    </h4>
                    <div class="pull-right home-button-primary-confirm">
                        <h4>
                            <div class="btn-group" style="display: inline">
                                <a href="${createLink(controller: 'payment', action: 'confirm')}" class="btn btn-small btn-info submit-confirm-all-payments"><g:message code="com.billmate.payment.confirmAll" default="Confirm all" /></a>
                                <button type="button" class="btn btn-small btn-info dropdown-toggle" data-toggle="dropdown"> <span class="caret"></span></button>
                                <ul class="dropdown-menu m-t-30">
                                    <li><a class="submit-confirm-selected-payments" href="${createLink(controller: 'payment', action: 'confirm')}"><g:message code="com.billmate.payment.confirmSelected" default="Confirm selected" /></a></li>
                                    <li class="divider"></li>
                                    <li><a class="submit-cancel-selected-payments" href="${createLink(controller: 'payment', action: 'cancel')}"><g:message code="com.billmate.payment.cancelSelected" default="Cancel selected" /></a></li>
                                    <li><a class="submit-cancel-all-payments" href="${createLink(controller: 'payment', action: 'cancel')}"><g:message code="com.billmate.payment.cancelAll" default="Cancel all" /></a></li>
                                </ul>
                            </div>
                        </h4>
                    </div>
                </div>
                <g:render template="/payment/widgets/confirm/index" model="[registeredUser: registeredUser, whoHaveUnconfirmedPayments: whoHaveUnconfirmedPayments]"/>
                <div class="m-b-10 home-button-divida-secondary-confirm">
                    <div class="btn-group text-center m-t-15" style="width: 100%">
                        <div>
                            <a href="${createLink(controller: 'payment', action: 'confirm')}" style="width: 85%" class="btn btn-small btn-info submit-confirm-all-payments"><g:message code="com.billmate.payment.confirmAll" default="Confirm all" /></a>
                            <button type="button" class="btn btn-small btn-info dropdown-toggle" data-toggle="dropdown"> <span class="caret no-margin"></span></button>
                            <ul class="dropdown-menu">
                                <li><a class="submit-confirm-selected-payments" href="${createLink(controller: 'payment', action: 'confirm')}"><g:message code="com.billmate.payment.confirmSelected" default="Confirm selected" /></a></li>
                                <li class="divider"></li>
                                <li><a class="submit-cancel-selected-payments" href="${createLink(controller: 'payment', action: 'cancel')}"><g:message code="com.billmate.payment.cancelSelected" default="Cancel selected" /></a></li>
                                <li><a class="submit-cancel-all-payments" href="${createLink(controller: 'payment', action: 'cancel')}"><g:message code="com.billmate.payment.cancelAll" default="Cancel all" /></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</g:if>
