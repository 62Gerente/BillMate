<div class="row tiles-container tiles white hidden-xlg m-b-20">
    <div class="col-md-12 b-grey b-r no-padding">
        <div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
            <h4 class="text-black bold inline uppercase"><g:message code="com.billmate.debt.widget.simple.title" default="Debts" /></h4>

            <div class="pull-right home-button-primary-confirm">
                <h4>
                    <button type="button" class="btn btn-primary btn-small">
                        Add debt
                    </button>
                </h4>
            </div>
        </div>
        <div class="p-l-20 p-r-20">
            <table class="table table-home-box table-home-box-fixed">
                <tbody>
                <g:if test="${debts.isEmpty()}">
                    <g:render template="/debt/widgets/default/empty"/>
                </g:if>
                <g:else>
                    <g:render template="/debt/widgets/default/index" model="registeredUser: registeredUser, debts: debts"/>
                </g:else>
                </tbody>
            </table>
        </div>
    </div>
</div>
