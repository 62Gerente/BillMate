<div class="container">
    <div class="row login-container animated fadeInUp">
        <div class="col-md-6 col-md-offset-3 p-b-20 text-center">
            <a href="${createLink(action: 'index', controller: 'base')}"><img class="logo" src="${assetPath(src: 'logo-full.svg')}" class="logo" alt="<g:message code="com.billmate.application.name" default="BillMate"/>" onerror="this.src=${assetPath(src: 'logo-full.png')}"></a>
        </div>
        <div class="col-md-6 col-md-offset-3 no-padding">
            <g:render template="/shared/messages" />
        </div>
        <div class="col-md-6 col-md-offset-3 tiles white no-padding">
            <div class="p-t-30 p-l-40 p-b-20 xs-p-t-10 xs-p-l-10 xs-p-b-10">
                <h2 class="normal">
                    <g:message code="com.billmate.authentication.signin.signin.to" default="Sign In to" />
                    <g:message code="com.billmate.application.name" default="BillMate" />
                </h2>
                <p>
                    <g:message code="com.billmate.authentication.signin.signin.intro" default="Fill fields with your account's info and get inside" />
                    <br/>
                </p>
            </div>
            <div class="tiles grey p-t-20 p-b-20 text-black">
                <g:form url="[action: 'doLogin', controller: 'authentication']" id="login-form" class="animated fadeIn form-icon-validate">
                    <div class="row form-row m-l-20 m-r-20 xs-m-l-10 xs-m-r-10">
                        <div class="col-md-6 col-sm-6">
                            <div class="input right">
                                <input name="email" id="email" type="text" class="form-control" placeholder="<g:message code="com.billmate.user.email" default="Email "/>">
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-6">
                            <div class="input right">
                                <input name="password" id="password" type="password" class="form-control" placeholder="<g:message code="com.billmate.registered.user.password" default="Password "/>">
                            </div>
                        </div>
                    </div>
                    <div class="row m-l-20 m-r-20 xs-m-l-10 xs-m-r-10">
                        <div class="control-group  col-md-12">
                            <div class="forgot-password pull-left">
                                <a href="login_v2.html#" class="text-grey">
                                    <g:message code="com.billmate.authentication.signin.forgot.password" default="Forgot your password?" />
                                </a>
                            </div>
                            <div class="pull-right">
                                <input type="submit" name="login-submit" class="btn btn-primary btn-cons" value="<g:message code="com.billmate.authentication.signin.button" default="Sign in "/>"/>
                            </div>
                        </div>
                    </div>
                </g:form>
            </div>
            <div class="col-md-12 white text-right">
                <div class="p-t-30 p-r-20 p-b-20 xs-p-t-10 xs-p-r-20 xs-p-b-10">
                    <g:message code="com.billmate.authentication.signin.not.member" default="Not a member?" />
                    <a href="">
                        <g:message code="com.billmate.authentication.signin.create.account" default="Create an account" />
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
