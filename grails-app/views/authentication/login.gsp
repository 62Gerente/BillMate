<div class="signin-container">
    <div class="signin-info">
        <a href="index.html" class="logo">
            <img src="${assetPath(src: 'logo.png')}" class="logo" alt="">
            <span><g:message code="com.billmate.application.name" default="BillMate"/></span>
        </a>
        <ul>
            <li><i class="fa fa-group signin-icon"></i> <g:message code="com.billmate.authentication.sigin.feature.create.circle" default="Create a circle of friends."/></li>
            <li><i class="fa fa-share signin-icon"></i> <g:message code="com.billmate.authentication.sigin.feature.add.expenses" default="Add your shared expenses."/></li>
            <li><i class="fa fa-credit-card signin-icon"></i> <g:message code="com.billmate.authentication.sigin.feature.pay.debts" default="Pay your debts on time."/></li>
            <li><i class="fa fa-rocket signin-icon"></i> <g:message code="com.billmate.authentication.sigin.feature.relax.study" default="Relax, study and have fun!"/></li>
        </ul>
    </div>
    <div class="signin-form" id="signin-form">

        <form action="index.html" id="signin-form_id">
            <div class="signin-text">
                <span><g:message code="com.billmate.authentication.sigin.intro" default="Sign In to your account"/></span>
            </div>

            <div class="form-group w-icon">
                <input type="text" name="email" id="email" class="form-control input-lg" placeholder="<g:message code="com.billmate.user.email" default="Email"/>">
                <span class="fa fa-envelope signin-form-icon"></span>
            </div>

            <div class="form-group w-icon">
                <input type="password" name="password" id="password" class="form-control input-lg" placeholder="<g:message code="com.billmate.registered.user.password" default="Password"/>">
                <span class="fa fa-lock signin-form-icon"></span>
            </div>

            <div class="form-actions">
                <input type="submit" value="<g:message code="com.billmate.authentication.sigin.button" default="SIGN IN"/>" class="signin-btn bg-primary">
                <a href="#" class="forgot-password" id="forgot-password"><g:message code="com.billmate.authentication.sigin.forgot.password" default="Forgot your password?"/></a>
            </div>
        </form>

        <div class="signin-with">
            <a href="#" class="signin-with-btn" style="background:#4f6faa;background:rgba(79, 111, 170, .8);"><g:message code="com.billmate.authentication.sigin.with" default="Sign In with"/> <span><g:message code="com.billmate.authentication.sigin.with.facebook" default="Facebook"/></span></a>
        </div>

        <div class="password-reset-form" id="password-reset-form">
            <div class="header">
                <div class="signin-text">
                    <span><g:message code="com.billmate.authentication.sigin.password.reset" default="Password reset"/></span>
                    <div class="close">&times;</div>
                </div>
            </div>

            <form id="password-reset-form_id">
                <div class="form-group w-icon">
                    <input type="text" name="email" id="reset_email" class="form-control input-lg" placeholder="<g:message code="com.billmate.user.email" default="Email"/>">
                    <span class="fa fa-envelope signin-form-icon"></span>
                </div>

                <div class="form-actions">
                    <input type="submit" value="<g:message code="com.billmate.authentication.sigin.password.reset.button" default="SEND PASSWORD RESET LINK"/>" class="signin-btn bg-primary">
                </div>
            </form>
        </div>
    </div>
</div>

<div class="not-a-member">
    <g:message code="com.billmate.authentication.sigin.not.member" default="Not a member?"/> <a href="#"><g:message code="com.billmate.authentication.sigin.signup.now" default="Sign up now"/></a>
</div>
