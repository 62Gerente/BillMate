var actualMonth;
$(document).ready(function() {

    var userLang = navigator.language || navigator.userLanguage;

    if(userLang.indexOf("pt") > -1)
        buildPT();
    else
        buildDefault();

    function buildPT(){
        $('#calendar').fullCalendar({
            header: {
                center: 'title'
            },
            defaultDate: '2014-07-05',
            editable: true,
            events: {
                url: '/BillMate/user/events/4',
                type: 'POST',
                data: {
                    date: actualMonth
                }
            },
            dateFormat: 'dd/mm/yy',
            dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado','Domingo'],
            dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
            dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
            monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
            monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
            nextText: 'Próximo',
            prevText: 'Anterior',
            list: "Agenda",
            allDayText: "Todo o dia"
        });
    }

    function buildDefault(){
        $('#calendar').fullCalendar({
            header: {
                center: 'title'
            },
            defaultDate: '2014-07-05',
            editable: true,
            events: {
                url: '/BillMate/user/events/4',
                type: 'POST',
                data: {
                    date: actualMonth
                }
            }
        });
    }

    $('.fc-header-right').hide();
    $('.fc-header-left').hide();

    $('#change-view-month').click(function () {
        $('#calendar').fullCalendar('changeView', 'month');
        updateCalendar();

    });
    $('#change-view-week').click(function () {
        $('#calendar').fullCalendar('changeView', 'agendaWeek');
        updateCalendar();
    });
    $('#change-view-day').click(function () {
        $('#calendar').fullCalendar('changeView', 'agendaDay');
        updateCalendar();
    });

    $('#calender-prev').click(function () {
        $('#calendar').fullCalendar('prev');
        updateCalendar();
    });
    $('#calender-next').click(function () {
        $('#calendar').fullCalendar('next');
        updateCalendar();
    });

    function updateCalendar(){
        actualMonth = $('#calendar').fullCalendar('getDate').getMonth();
        $("#calendar").fullCalendar('refetchEvents');
        $('.fc-header-right').hide();
        $('.fc-header-left').hide();
    }
});