<div class="tiles white col-md-12 p-t-30 p-b-30 p-r-40 p-l-40 m-b-20">
    <div class="row">
        <div class="text-center">
            <i class="${circle.getCssClass()} fa-5x"></i>
            <h3>
                <a href="#" class="edit-property edit-property-sm" id="edit_name" data-emptytext="<g:message code="com.billmate.expense.title.xeditable.clickToDefine" default="Click to define circle's name" />" data-name="name" data-type="text" data-pk="${circle.getId()}" data-url="${createLink(controller: 'circle', action: 'updateProperty', id: circle.getId())}">
                    ${circle}
                </a>
            </h3>
            <p class="text-left editable-ta-fullwidth">
                <a href="#" class="edit-property edit-property-sm display-block" id="edit_description" data-emptytext="<g:message code="com.billmate.expense.description.xeditable.clickToDefine" default="Click to define circle's description" />" data-showbuttons="bottom" data-name="description" data-type="textarea" data-pk="${circle.getId()}" data-url="${createLink(controller: 'circle', action: 'updateProperty', id: circle.getId())}">
                    ${circle.getDescription()}
                </a>
            </p>
        </div>
    </div>
    <div class="row p-t-5">
        <g:if test="${registeredUser.hasMovementsInCircle(circle.getId())}">
            <button class="btn btn-block btn-white" type="submit" disabled><g:message code="com.billmate.circle.regularexpense.outhouse" default="Leave the circle" /></button>
        </g:if>
        <g:else>
            <g:form data-confirm-cancel="${message(code: 'com.billmate.btn.cancel', default: 'Cancel')}" data-confirm-ok="${message(code: 'com.billmate.circle.regularexpense.outhouse', default: 'Leave the house')}" data-confirm-title="${message(code: 'com.billmate.circle.regularexpense.confirmout', default: 'Confirm exit of home')}" data-confirm-message="${message(code: 'com.billmate.circle.regularexpense.confirmoutsure', default: 'Are you sure you want to go out of the house')}" id="out-house" class="p-t-5 m-b-10" url="[action:'deleteUser',controller:'circle']" method="DELETE">
                <input type="hidden" value="${registeredUser.getUserId()}"/>
                <input type="hidden" value="${circle.getId()}"/>
                <input type="hidden" value="${createLink(controller: 'dashboard', action: 'user')}"/>
                <button class="btn btn-block btn-white" type="submit"><g:message code="com.billmate.circle.regularexpense.outhouse" default="Leave the circle" /></button>
            </g:form>
        </g:else>
        <g:if test="${circle.hasMovementsInCircle()}">
            <button class="btn btn-block btn-default" type="button" disabled><g:message code="com.billmate.circle.regularexpense.closehouse" default="Close the circle" /></button>
        </g:if>
        <g:else>
            <g:form data-confirm-cancel="${message(code: 'com.billmate.btn.cancel', default: 'Cancel')}" data-confirm-ok="${message(code: 'com.billmate.circle.regularexpense.closehouse', default: 'Close the house')}" data-confirm-title="${message(code: 'com.billmate.circle.regularexpense.confirmclose', default: 'Confirm close of the circle')}" data-confirm-message="${message(code: 'com.billmate.circle.regularexpense.confirmclosesure', default: 'Are you sure you want to close of the circle')}" id="close-house" class="p-t-5 m-b-10" url="[action:'close',controller:'circle']" method="DELETE">
                <input type="hidden" value="${registeredUser.getUserId()}"/>
                <input type="hidden" value="${circle.getId()}"/>
                <input type="hidden" value="${createLink(controller: 'dashboard', action: 'user')}"/>
                <button class="btn btn-block btn-default" type="button"><g:message code="com.billmate.circle.regularexpense.closehouse" default="Close the circle" /></button>
            </g:form>
        </g:else>
    </div>
</div>
<div class="clearfix"></div>
