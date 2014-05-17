<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<!--[if IE 8]>         <html class="ie8"> <![endif]-->
<!--[if IE 9]>         <html class="ie9 gt-ie8"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="gt-ie8 gt-ie9 not-ie"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="BillMate"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

    <!-- Open Sans font from Google CDN -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300&subset=latin" rel="stylesheet" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Rock+Salt' rel='stylesheet' type='text/css'>

    <asset:stylesheet href="application.css"/>
    <asset:stylesheet href="authentication.css"/>
    <asset:javascript src="application.js"/>

    <!--[if lt IE 9]>
        <asset:javascript src="ie.min.js"/>
	<![endif]-->
</head>
<body class="theme-default page-signin">

    <g:render template="/shared/messages"/>

    <g:layoutBody/>

    <asset:javascript src="authentication.js"/>
</body>
</html>
