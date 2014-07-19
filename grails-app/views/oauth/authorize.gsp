<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="com.billmate.session.create.page.title" default="BillMate - Sign In" /></title>
</head>
<body>
<div class="col-md-6 col-md-offset-3 tiles white no-padding">
    <div class="p-t-30 p-l-40 p-b-20 xs-p-t-10 xs-p-l-30 xs-p-b-10">
        <h2 class="normal">
            <g:message code="com.billmate.session.create.title" default="Sign In to BillMate" />
        </h2>
        <p>
            <g:message code="com.billmate.session.create.intro" default="Fill fields with your account's info and get inside" />
            <br/>
        </p>
    </div>
    <div class="tiles grey p-t-20 p-b-20 text-black">
        <g:form url="[action: 'save', controller: 'oauth']" id="login-form">
            <div class="row form-row m-l-20 m-r-20 xs-m-l-10 xs-m-r-10">
                <div class="col-md-6 col-sm-6">
                    <div class="form-group">
                        <div class="controls">
                            <input name="email" id="email" type="text" class="form-control" placeholder="<g:message code="com.billmate.user.email" default="Email address"/>">
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-sm-6">
                    <div class="form-group">
                        <div class="controls">
                            <input name="password" id="password" type="password" class="form-control" placeholder="<g:message code="com.billmate.registered.user.password" default="Password "/>">
                        </div>
                        <div class="controls">
                            <input name="redirect" type="hidden" value="${redirect_path}"/>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-12">
                    <div class="forgot-password pull-left">
                        <a href="#" class="text-grey">
                            <g:message code="com.billmate.session.create.forgotPassword" default="Forgot your password?" />
                        </a>
                    </div>
                    <div class="pull-right">
                        <input type="submit" name="login-submit" class="btn btn-primary btn-cons" value="<g:message code="com.billmate.session.create.button" default="Sign in "/>"/>
                    </div>
                </div>
            </div>
        </g:form>
    </div>
    <div class="col-md-12 white text-right">
        <div class="p-t-30 p-r-20 p-b-20 xs-p-t-10 xs-p-r-20 xs-p-b-10">
            <g:message code="com.billmate.session.create.notMember" default="Not a member?" />
            <a href="${createLink(controller: 'register', action: 'create')}">
                <g:message code="com.billmate.session.create.register" default="Create an account" />
            </a>
        </div>
    </div>
</div>
</body>
</html>
