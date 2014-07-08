<div class="row tiles-container tiles white hidden-xlg m-b-20">
    <div class="col-md-12 b-grey b-r no-padding">
        <div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
            <h4 class="text-black bold inline uppercase"><g:message code="com.billmate.user.widget.members.title" default="Members" /></h4>

            <div class="pull-right">
                <h4>
                    <div id="add-new-user-circle">
                        <button type="button" class="btn btn-primary btn-small">
                            <g:message code="com.billmate.circle.member.add" default="Add member" />
                        </button>
                    </div>
                </h4>
            </div>
        </div>
        <div class="p-l-20 p-r-20">
            <input type="hidden" id="user-list-in-circle" value="<g:createLink controller="circle" action="listUsersBy"/>" />
            <table class="table table-home-box table-home-box-fixed">
                <tbody>
                    <g:if test="${users.isEmpty()}">
                        <g:render template="/user/widgets/circleUsers/empty"/>
                    </g:if>
                    <g:else>
                        <g:render template="/user/widgets/circleUsers/index" model="[registeredUser: registeredUser, users: users, circle: circle]"/>
                    </g:else>
                    <tr>
                        <td colspan="5">
                            <div class="input-append success date m-t-20 m-b-20 div-new-friend-circle">
                                <input type="hidden" value="${circle.getId()}"/>
                                <input type="text" placeholder="<g:message code="com.billmate.circle.user.add" default="Add User" />" class="input-small new-friend-circle" style="width: 98%">
                                <span class="add-on submit-new-friend-circle" style="height: 35px">
                                    <i class="fa fa-plus"></i>
                                </span>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
