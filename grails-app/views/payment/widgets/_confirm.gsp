<g:if test="${!whoHaveUnconfirmedPayments.isEmpty()}">
    <div class="row tiles-container tiles white hidden-xlg m-b-20 expenses-widget-more-details">
        <div class="row tiles-container tiles white hidden-xlg">
            <div class="col-md-12 b-grey b-r no-padding widget-table-adjust-right-margin">
                <div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
                    <h4 class="text-black bold inline uppercase">
                        <g:message code="com.billmate.payment.widget.confirm.title" default="Payments waiting confirmation" />
                    </h4>
                    <div class="pull-right" id="home-button-primary-confirm">
                        <h4>
                            <button type="button" class="inline btn btn-info btn-small">
                                <g:message code="com.billmate.payment.confirmAll" default="Confirm All" />
                            </button>
                        </h4>
                    </div>
                </div>
                <g:render template="/payment/widgets/confirm/index" model="[registeredUser: registeredUser, whoHaveUnconfirmedPayments: whoHaveUnconfirmedPayments]"/>
                <div class="p-l-20 p-r-20">
                    <div id="home-button-divida-secondary-confirm" class="m-b-10">
                        <button type="button" class="btn btn-default btn-small" style="width: 100%">
                            <g:message code="com.billmate.payment.confirmSelected" default="Confirm" />
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</g:if>