<%@ page import="groovy.time.TimeCategory" %>
<div class="tiles white m-t-15">
    <div class="tiles-body no-padding">
        <div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
            <h4 class="text-black bold inline uppercase"><g:message code="com.billmate.expense.widget.monthlyExpenses.title" default="Monthly expenses" /></h4>
        </div>
        <div class="notification-messages no-padding">
            <div class="grid simple">
                <div class="grid-body no-border">
                    <div class="tiles-title text-black"></div>
                    <br>
                    <div id="monthlySpending"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        <g:each status="i" var="expenseType" in="${expenseTypes}">
            <g:set var="date0" value="${ use(TimeCategory){new Date()-0.months}}"/>
            <g:set var="date1" value="${ use(TimeCategory){new Date()-1.months}}"/>
            <g:set var="date2" value="${ use(TimeCategory){new Date()-2.months}}"/>
            <g:set var="date3" value="${ use(TimeCategory){new Date()-3.months}}"/>
            <g:set var="date4" value="${ use(TimeCategory){new Date()-4.months}}"/>
            <g:set var="date5" value="${ use(TimeCategory){new Date()-5.months}}"/>
            var d_${i} = [
                [${date0.toTimestamp().getTime()}, ${user.monthlySpendingOfExpenseType(date0, expenseType)}],
                [${date1.toTimestamp().getTime()}, ${user.monthlySpendingOfExpenseType(date1, expenseType)}],
                [${date2.toTimestamp().getTime()}, ${user.monthlySpendingOfExpenseType(date2, expenseType)}],
                [${date3.toTimestamp().getTime()}, ${user.monthlySpendingOfExpenseType(date3, expenseType)}],
                [${date4.toTimestamp().getTime()}, ${user.monthlySpendingOfExpenseType(date4, expenseType)}],
                [${date5.toTimestamp().getTime()}, ${user.monthlySpendingOfExpenseType(date5, expenseType)}]
            ];
        </g:each>

        var data = [
            <g:each status="i" var="expenseType" in="${expenseTypes}">
                <g:set var="colors" value="['rgba(243, 89, 88, 0.7)', 'rgba(251, 176, 94, 0.7)', 'rgba(10, 166, 153, 0.7)', 'rgba(0, 144, 217, 0.7)']"/>
                {
                    label: "${expenseType}",
                    data: d_${i},
                    bars: {
                        show: true,
                        barWidth: 12*24*60*60*300,
                        fill: true,
                        lineWidth:0,
                        order: ${i+1},
                        fillColor: "${colors[i]}"
                    },
                    color: "${colors[i]}"
                },
            </g:each>
        ];

        $.plot($("#monthlySpending"), data, {
            xaxis: {
                min: ${use(TimeCategory){(new Date() - 6.months + 2.week).toTimestamp().getTime()}},
                max: ${use(TimeCategory){(new Date() + 2.week).toTimestamp().getTime()}},
                mode: "time",
                timeformat: "%b",
                tickLength: 0
            },
            grid: {
                borderColor:'#f0f0f0'
            }
        });
    });
</script>
