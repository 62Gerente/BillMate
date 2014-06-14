<div class="m-l-20 notification-messages">
    <div class="user-profile">
        <img src="${action.getActor().getPhotoOrDefault()}" alt="" width="35" height="35">
    </div>
    <div class="message-wrapper">
        <div class="heading">${action.getActor()}</div>
        <div class="description">
            <g:message code="com.billmate.action.${action.getActionType().getType()}.short" args="${[
                    action.getActor(),
                    action.getUser(),
                    action.getCircle(),
                    action.getExpense(),
                    action.getRegularExpense(),
                    action.getPayment()
            ]}"/>
        </div>
        <div class="date"><g:formatDate date="${action.getActionDate()}" type="datetime" style="SMALL"/></div>
    </div>
</div>
