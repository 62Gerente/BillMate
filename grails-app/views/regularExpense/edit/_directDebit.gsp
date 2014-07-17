<div class="tiles white col-md-12 p-t-30 p-b-30 p-r-40 p-l-40 m-b-20">
    <div class="row">
        <div class="text-center">
            <i class="fa fa-credit-card fa-5x"></i>
            <h3>
                Direct debit
            </h3>
            <p class="text-left p-t-10 editable-ta-fullwidth">
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Facilis cupiditate similique, eos fuga animi dolores dolorum tempora qui culpa corrupti.
            </p>
        </div>
    </div>
    <div class="row p-t-10 p-b-10">
        <div class="col-md-6">
            <h6 class="semi-bold text-left no-margin label-property-sm">
                <g:message code="com.billmate.directDebit.debitDate" default="Debit date" />
            </h6>
        </div>
        <div class="col-md-6">
            <h6 class="no-margin p-t-5 p-b-5">
                <a href="#" class="edit-property edit-property-sm" id="edit_debit_date" data-mode="popup" data-showbuttons="bottom" data-placement="left" data-emptytext="<g:message code="com.billmate.xeditable.clickToDefine" default="Click to define" />" data-name="debitDate" data-type="combodate" data-template="D MMM YYYY  HH:mm" data-format="DD-MM-YYYY HH:mm" data-viewformat="DD-MM-YYYY HH:mm" data-pk="${directDebit.getId()}" data-url="${createLink(controller: 'directDebit', action: 'updateProperty', id: directDebit.getId())}">
                    <g:formatDate date="${directDebit.getDebitDate()}" type="datetime" style="SMALL"/>
                </a>
            </h6>
        </div>
    </div>
</div>
<div class="clearfix"></div>
