<div class="grid simple m-b-20 no-print">
    <div class="grid-body no-border">
        <h3 class="m-t-20">
            <g:message code="com.billmate.expense.report.filters" default="Filters"/>
        </h3>
        <p class="p-b-10">
            <g:message code="com.billmate.expense.report.filters.explanation" default="Filter the expenses you want to include in report."/>
            <br>
            <small>
                <g:message code="com.billmate.expense.report.filters.explanation.update" default="The report will be updated automatically."/>
            </small>
        </p>
        <g:form id="filters-form" url="[controller: 'report', action: 'expenses']" method="POST">
            <select name="circle" class="filter-select pull-right m-b-10" style="width: 100%">
                <option selected><g:message code="com.billmate.circles.all" default="All circles"></g:message></option>
                <g:render template="/house/selectGroup" model="[houses: houses]"/>
                <g:render template="/collective/selectGroup" model="[collectives: collectives]"/>
            </select>
            <select name="expenseType" class="filter-select pull-right m-b-10" style="width: 100%">
                <option selected><g:message code="com.billmate.expenseType.all" default="All expense types"></g:message></option>
                <g:render template="/expenseType/selectGroup" model="[expenseTypes: expenseTypes]"/>
            </select>
            <select name="dateInterval" class="filter-select pull-right m-b-10" style="width: 100%">
                <option value=""><g:message code="com.billmate.date.all_time" default="All time"/></option>
                <option value="year"><g:message code="com.billmate.date.this_year" default="This year"/></option>
                <option value="month" selected><g:message code="com.billmate.date.this_month" default="This month"/></option>
                <option value="week"><g:message code="com.billmate.date.this_week" default="This week"/></option>
            </select>
            <input type="hidden" name="registeredUser" value="${registeredUser.getId()}"/>
        </g:form>
        <div class="clearfix"></div>
    </div>
</div>
