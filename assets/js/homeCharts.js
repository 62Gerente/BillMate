$(document).ready(function() {		

	//Bar Chart  - Jquert flot
	
    var d1_1 = [
        [1325376000000, 120],
        [1328054400000, 70],
        [1330560000000, 100],
        [1333238400000, 60]
    ];
 
    var d1_2 = [
        [1325376000000, 80],
        [1328054400000, 60],
        [1330560000000, 30],
        [1333238400000, 35]
    ];
 
    var d1_3 = [
        [1325376000000, 80],
        [1328054400000, 40],
        [1330560000000, 30],
        [1333238400000, 20]
    ];
 
    var d1_4 = [
        [1325376000000, 15],
        [1328054400000, 10],
        [1330560000000, 15],
        [1333238400000, 20]
    ];
 
    var data1 = [
        {
            label: "Eletricidade",
            data: d1_1,
            bars: {
                show: true,
                barWidth: 12*24*60*60*300,
                fill: true,
                lineWidth:0,
                order: 1,
                fillColor:  "rgba(243, 89, 88, 0.7)"
            },
            color: "rgba(243, 89, 88, 0.7)"
        },
        {
            label: "Gás",
            data: d1_2,
            bars: {
                show: true,
                barWidth: 12*24*60*60*300,
                fill: true,
                lineWidth: 0,
                order: 2,
                fillColor:  "rgba(251, 176, 94, 0.7)"
            },
            color: "rgba(251, 176, 94, 0.7)"
        },
        {
            label: "Renda",
            data: d1_3,
            bars: {
                show: true,
                barWidth: 12*24*60*60*300,
                fill: true,
                lineWidth: 0,
                order: 3,
                fillColor:  "rgba(10, 166, 153, 0.7)"
            },
            color: "rgba(10, 166, 153, 0.7)"
        },
        {
            label: "Outros",
            data: d1_4,
            bars: {
                    show: true,
                barWidth: 12*24*60*60*300,
                fill: true,
                lineWidth: 0,
                order: 4,
                fillColor:  "rgba(0, 144, 217, 0.7)"
            },
            color: "rgba(0, 144, 217, 0.7)"
        },

    ];
 
    $.plot($("#placeholder-bar-chart"), data1, {
        xaxis: {
            min: (new Date(2011, 11, 15)).getTime(),
            max: (new Date(2012, 04, 01)).getTime(),
            mode: "time",
            timeformat: "%b",
            tickSize: [1, "month"],
            monthNames: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            tickLength: 0, // hide gridlines
            axisLabel: 'Month',
            axisLabelUseCanvas: true,
            axisLabelFontSizePixels: 12,
            axisLabelFontFamily: 'Verdana, Arial, Helvetica, Tahoma, sans-serif',
            axisLabelPadding: 5,
        },
        yaxis: {
            axisLabel: 'Value',
            axisLabelUseCanvas: true,
            axisLabelFontSizePixels: 12,
            axisLabelFontFamily: 'Verdana, Arial, Helvetica, Tahoma, sans-serif',
            axisLabelPadding: 5
        },
        grid: {
            hoverable: true,
            clickable: false,
            borderWidth: 1,
			borderColor:'#f0f0f0',
			labelMargin:8,
        },
        series: {
            shadowSize: 1
        }
    });
	
	
});