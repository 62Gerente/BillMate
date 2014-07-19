<div id="report" class="grid simple" style="margin-bottom: 20px">
    <div class="grid-body no-border invoice-body"> <br>
        <div class="pull-left">
            <span style="margin-left: -15px">
                <img height="40px" src="${assetPath(src: 'logo-full-black.svg')}" class="logo" alt="<g:message code="com.billmate.application.name" default="BillMate"/>" onerror="this.src=${assetPath(src: 'logo-full-black.png')}">
            </span>
            <address>
                <% def address = "<strong>BillMate, Inc.</strong><br>Universidade do Minho<br>Departamento de Informática<br>Campus de Gualtar" %>
                <g:message code="com.billmate.address" default="${address}"/>
            </address>
        </div>
        <div class="pull-right p-t-15">
            <h3><g:message code="com.billmate.expense.report" default="Expense report"/></h3>
            <small class="text-right pull-right" style="margin-top: -10px">
                <g:message code="com.billmate.expense.report.generatedAt.short" default="Generated at"/>
                <g:formatDate date="${new Date()}" type="date" style="LONG"/>
            </small>
        </div>
        <div class="clearfix"></div>
        <g:render template="/expense/report/table" model="[registeredUser: registeredUser, expenses: expenses, total: total, myTotal: myTotal]" />
        <div class="p-t-20 p-b-10">
            <h5 class="text-right text-black"><g:message code="com.billmate.expense.report.generatedAt.long" default="Report generated at"/></h5>
            <h5 class="text-right semi-bold text-black"><g:message code="com.billmate.url.short" default="billmate.com"/></h5>
        </div>
    </div>
</div>
<div class="row m-b-20 no-print" >
    <div class="col-md-4 p-b-5"><button id="print-report" class="btn btn-block btn-info" type="button"><i class="fa fa-print"></i>&nbsp;&nbsp;<g:message code="com.billmate.expense.report.print" default="Print report"/></button></div>
    <div class="col-md-4 p-b-5"><button disabled class="btn btn-block btn-success" type="button"><i class="fa fa-file-pdf-o"></i>&nbsp;&nbsp;<g:message code="com.billmate.expense.report.download.pdf" default="Download as PDF"/></button></div>
    <div class="col-md-4 p-b-5"><button disabled class="btn btn-block btn-primary" type="button"><i class="fa fa-file-excel-o"></i>&nbsp;&nbsp;<g:message code="com.billmate.expense.report.download.csv" default="Download as CSV"/></button></div>
</div>
