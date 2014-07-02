<div class="cbp_tmicon primary">
    <div class="user-profile">
        <img src="assets/img/profiles/d.jpg" data-src="assets/img/profiles/d.jpg"
             data-src-retina="assets/img/profiles/d2x.jpg" width="48" height="48" alt="">
    </div>
</div>

<div class="cbp_tmlabel">
    <div class="p-t-5 p-l-30 p-r-20 p-b-10 xs-p-r-10 xs-p-l-10 xs-p-t-5">
        <div class="muted m-t-15">
            <i class="fa ${action.getCircle().getCssClass()}"></i> ${action.getCircle()} - <prettytime:display date="${action.getActionDate()}" />
        </div>
        <h4 class="dark-text">
            <g:message code="com.billmate.history.addRegularExpenseCircle" args="[null, null, action.getCircle(), null, action.getRegularExpense()]"/>
        </h4>
    </div>
</div>
