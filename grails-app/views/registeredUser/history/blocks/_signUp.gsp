<div class="cbp_tmicon primary">
    <div class="user-profile">
        <img src="${assetPath(src: 'logo.svg')}" alt="" onerror="this.src=${assetPath(src: 'logo.png')}" height="35" width="35" class="m-t-5">
    </div>
</div>

<div class="cbp_tmlabel">
    <div class="p-t-5 p-l-30 p-r-20 p-b-10 xs-p-r-10 xs-p-l-10 xs-p-t-5">
        <div class="muted m-t-15">
            <prettytime:display date="${action.getActionDate()}" />
        </div>
        <h4 class="dark-text">
            <g:message code="com.billmate.history.signUp" default="Create an account on BillMate"/>
        </h4>
    </div>
</div>
