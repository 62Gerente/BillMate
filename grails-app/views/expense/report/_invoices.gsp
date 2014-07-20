<div class="grid simple m-b-20 no-print">
    <div class="grid-body no-border">
        <h3 class="m-t-20"><g:message code="com.billmate.expense.report.invoices" default="Invoices and Receipts"/></h3>
        <p class="p-b-10">
            <g:message code="com.billmate.expense.report.invoices.explanation" default="Consult and download all of your expenses's invoices or receipts in one document."/>
        </p>
        <table id="invoices-table" class="table">
            <thead>
            <tr>
                <th class="text-left"><g:message code="com.billmate.expense" default="Expense"/></th>
                <th style="width:30px" class="text-right"><g:message code="com.billmate.expense.invoice" default="Invoice"/></th>
                <th style="width:30px" class="text-right"><g:message code="com.billmate.expense.receipt" default="Receipt"/></th>
            </tr>
            </thead>
            <tbody>
                <g:if test="${expenses.isEmpty()}">
                    <g:render template="/expense/report/documents/empty" model="[expenses: expenses]" />
                </g:if>
                <g:else>
                    <g:render template="/expense/report/documents/index" model="[expenses: expenses]" />
                </g:else>
            </tbody>
        </table>
        <button disabled class="btn btn-block btn-info" type="button"><i class="fa fa-file-pdf-o"></i>&nbsp;&nbsp;<g:message code="com.billmate.expense.report.invoices.download_all" default="Download all invoices"/></button>
        <button disabled class="btn btn-block btn-success" type="button"><i class="fa fa-file-pdf-o"></i>&nbsp;&nbsp;<g:message code="com.billmate.expense.report.receipts.download_all" default="Download all receipts"/></button>
        <div class="clearfix"></div>
    </div>
</div>
