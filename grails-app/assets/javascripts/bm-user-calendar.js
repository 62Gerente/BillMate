$(document).ready(function() {
    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        defaultDate: '2014-07-05',
        editable: true,
        events: "/BillMate/user/teste/4"
    });
    $('.fc-header').hide();

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
        $('#calendar').fullCalendar('refetchEvents');
    }
});