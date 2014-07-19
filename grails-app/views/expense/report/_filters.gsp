<div class="grid simple m-b-20">
    <div class="grid-body no-border">
        <h3 class="m-t-20">
            <g:message code="com.billmate.expense.report.filters" default="Filters"/>
        </h3>
        <p class="p-b-10">
            <g:message code="com.billmate.expense.report.filters.explanation" default="Filter the expenses you want to include in report."/>
            <small>
                <g:message code="com.billmate.expense.report.filters.explanation.update" default="The report will be updated automatically."/>
            </small>
        </p>
        <select name="circle" class="history-select pull-right m-b-10" style="width: 100%">
            <option selected><g:message code="com.billmate.circles.all" default="All circles"></g:message></option>
            <g:render template="/house/selectGroup" model="[houses: houses]"/>
            <g:render template="/collective/selectGroup" model="[collectives: collectives]"/>
        </select>
        <select name="expenseType" class="history-select pull-right m-b-10" style="width: 100%">
            <option selected><g:message code="com.billmate.expenseType.all" default="All expense types"></g:message></option>
            <g:render template="/expenseType/selectGroup" model="[expenseTypes: expenseTypes]"/>
        </select>
        <select name="date" class="history-select pull-right m-b-10" style="width: 100%">
            <option><g:message code="com.billmate.date.all_time" default="All time"/></option>
            <option><g:message code="com.billmate.date.this_year" default="This year"/></option>
            <option selected><g:message code="com.billmate.date.this_month" default="This month"/></option>
            <option><g:message code="com.billmate.date.this_week" default="This week"/></option>
        </select>
        <div class="clearfix"></div>
    </div>
</div>
