<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <meta charset="utf-8" />
    <title><g:layoutTitle default="BillMate"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta content="" name="description" />
    <meta content="" name="author" />

    <asset:stylesheet href="application.css"/>
    <asset:stylesheet href="session.css"/>
    <g:layoutHead/>

</head>
<body class="no-top lazy">
    <div class="container">
        <div class="row login-container animated fadeInUp">
            <div class="col-md-6 col-md-offset-3 p-b-20 text-center">
                <a href="${createLink(uri: '/')}"><img class="logo" src="${assetPath(src: 'logo-full.svg')}" class="logo" alt="<g:message code="com.billmate.application.name" default="BillMate"/>" onerror="this.src=${assetPath(src: 'logo-full.png')}"></a>
            </div>
            <div class="col-md-6 col-md-offset-3 no-padding">
                <g:render template="/shared/messages" />
            </div>

            <g:layoutBody/>

        </div>
    </div>
    <asset:javascript src="application.js"/>
    <asset:javascript src="session.js"/>
</body>
</html>
