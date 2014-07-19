<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="com.billmate.user.reports.page.title" default="BillMate - Reports"/></title>
</head>
<body>
    <div class="col-md-8 col-lg-8">
        <g:render template="/expense/report" model="[registeredUser: user, expenses: userReports.expensesOfMonth(), total: userReports.totalValueOfExpensesOfMonth(), myTotal: userReports.myQuotaOfTotalValueOfMonth()]"></g:render>
    </div>
    <div class="col-md-4 col-lg-4">
        <g:render template="/expense/report/filters" model="[expenseTypes: userReports.expenseTypes(), houses: userReports.houses(), collectives: userReports.collectives()]" />
        <div class="grid simple m-b-20">
            <div class="grid-body no-border">
                <h3 class="m-t-20">Invoices</h3>
                <p>Button groups can act as a radio or a switch or even a single toggle.</p>
                <br>
                <table class="table">
                    <thead>
                    <tr>
                        <th style="width:60px" class="unseen text-center">QTY</th>
                        <th class="text-left">DESCRIPTION</th>
                        <th style="width:140px" class="text-right">UNIT PRICE</th>
                        <th style="width:90px" class="text-right">TOTAL</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="unseen text-center">1</td>
                        <td>iPhone 5 32GB White &amp; Silver (GSM) Unlocked</td>
                        <td class="text-right">$749.00</td>
                        <td class="text-right">$749.00</td>
                    </tr>
                    <tr>
                        <td class="unseen text-center">2</td>
                        <td>iPad mini with Wi-Fi 32GB - White &amp; Silver</td>
                        <td class="text-right">$429.00</td>
                        <td class="text-right">$858.00</td>
                    </tr>

                    <tr>
                        <td class="text-right no-border" colspan="3"><strong>Total</strong></td>
                        <td class="text-right">$0.00</td>
                    </tr>
                    </tbody>
                </table>
                <button class="btn btn-block btn-info" type="button"><i class="fa fa-print"></i>&nbsp;&nbsp;Print document with all invoices</button>
                <button class="btn btn-block btn-success" type="button"><i class="fa fa-file-pdf-o"></i>&nbsp;&nbsp;Download document with all invoices</button>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</body>
</html>
