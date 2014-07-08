<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <asset:stylesheet src="../plugins/fullcalendar/jquery-ui.min.css"/>
        <asset:stylesheet src="../plugins/fullcalendar/fullcalendar.css"/>
        <asset:stylesheet src="../plugins/fullcalendar/fullcalendar.print.css" media='print'/>
    </head>

    <body>
        <div class="row-fluid">
            <div class="row">
                <div class="row tiles-container red no-padding">
                    <div class="col-md-12 tiles white no-padding">
                        <div class="tiles-body">
                            <div class="full-calender-header">
                                <div class="pull-left">
                                    <div class="btn-group ">
                                        <button class="btn btn-success" id="calender-prev"><i class="fa fa-angle-left"></i>
                                        </button>
                                        <button class="btn btn-success" id="calender-next"><i class="fa fa-angle-right"></i>
                                        </button>
                                    </div>
                                </div>

                                <div class="pull-right">
                                    <div data-toggle="buttons-radio" class="btn-group">
                                        <button class="btn btn-primary active" type="button" id="change-view-month"><g:message code="com.billmate.expense.calendar.month" default="Month" /></button>
                                        <button class="btn btn-primary " type="button" id="change-view-week"><g:message code="com.billmate.expense.calendar.week" default="Week" /></button>
                                        <button class="btn btn-primary" type="button" id="change-view-day"><g:message code="com.billmate.expense.calendar.day" default="Day" /></button>
                                    </div>
                                </div>
                                <input type="hidden" id="link-user-events" value="<g:createLink controller = "user" action="events" id="${user.getId()}"/>" />
                                <div class="clearfix"></div>
                            </div>
                            <div id='calendar'></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>