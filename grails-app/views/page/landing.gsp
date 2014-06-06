<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <title>
        <g:message code="com.billmate.application.name" default="BillMate" />
    </title>
    <asset:stylesheet href="page/landing/bm-landing.css" />
</head>

<body>
<div class="main-wrapper">
<header id="ha-header" class="ha-header ha-header-hide ">
    <div class="ha-header-perspective">
        <div class="ha-header-front navbar navbar-default">
            <div class="compressed">
                <div class="navbar-header">
                    <button data-target=".navbar-collapse" data-toggle="collapse" class="navbar-toggle" type="button">
                        <span class="sr-only"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="#" class="navbar-brand compressed">
                        <img src="${assetPath(src: 'page/landing/logo-full-black.svg')}" alt="" data-src="${assetPath(src: 'page/landing/logo-full-black.svg')}" onerror="this.src=${assetPath(src: 'page/landing/logo-full-black.png')}" width="150" />
                    </a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="${createLink(controller: 'session', action: 'create')}">
                                <g:message code="com.billmate.session.create.button" default="Sign In" />
                            </a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'register', action: 'create')}">
                                <g:message code="com.billmate.session.create.register" default="Create an account" />
                            </a>
                        </li>
                    </ul>
                </div>
                <!--/.nav-collapse -->
            </div>

        </div>
    </div>
</header>
<div class="section full-view ha-waypoint" data-animate-down="ha-header-hide" data-animate-up="ha-header-hide">
    <div role="navigation" class="navbar navbar-transparent navbar-top">
        <div class="container">
            <div class="compressed">
                <div class="navbar-header">
                    <button data-target=".navbar-collapse" data-toggle="collapse" class="navbar-toggle" type="button">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="#" class="navbar-brand">
                        <img src="${assetPath(src: 'page/landing/logo-full-white.svg')}" alt="" data-src="${assetPath(src: 'page/landing/logo-full-white.svg')}" onerror="this.src=${assetPath(src: 'page/landing/logo-full-white.png')}" width="150" />
                    </a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="${createLink(controller: 'session', action: 'create')}">
                                <g:message code="com.billmate.session.create.button" default="Sign In" />
                            </a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'register', action: 'create')}">
                                <g:message code="com.billmate.session.create.register" default="Create an account" />
                            </a>
                        </li>
                    </ul>
                </div>
                <!--/.nav-collapse -->
            </div>
        </div>
    </div>
    <!--BEGIN SLIDER -->
    <div class="tp-banner-container">
        <div class="tp-banner" id="tourSlider">
            <ul>
                <!-- SLIDE  -->
                <li data-transition="fade" data-slotamount="5" data-masterspeed="700">
                    <!-- MAIN IMAGE -->
                    <img src="${assetPath(src: 'page/landing/bg/home_bg.png')}" alt="slidebg1" data-bgfit="cover" data-bgposition="center center" data-bgrepeat="no-repeat">
                    <!-- LAYERS -->
                    <div class="tp-caption mediumlarge_light_white_center sft tp-resizeme slider" data-x="300" data-hoffset="0" data-y="130" data-speed="600" data-start="500" data-easing="Power4.easeOut" data-endspeed="600" data-endeasing="Power0.easeIn" style="z-index: 3">
                        <img src="${assetPath(src: 'page/landing/imac.png')}" alt="">
                    </div>
                    <div class="tp-caption sfb slider" data-x="140" data-hoffset="0" data-y="210" data-speed="800" data-start="1300" data-easing="Power4.easeOut" data-endspeed="300" data-endeasing="Power1.easeIn" data-captionhidden="off" style="z-index: 6">
                        <img src="${assetPath(src: 'page/landing/ipad.png')}" alt="">
                    </div>
                    <div class="tp-caption sfr slider" data-x="615" data-hoffset="0" data-y="310" data-speed="800" data-start="1600" data-easing="Power4.easeOut" data-endspeed="300" data-endeasing="Power1.easeIn" data-captionhidden="off" style="z-index: 6">
                        <img src="${assetPath(src: 'page/landing/iphone.png')}" alt="">
                    </div>
                </li>
            </ul>
            <div class="tp-bannertimer"></div>
        </div>
    </div>
</div>
<div class="section white text-center p-t-40 p-b-40" id="banner-footer">
    <h2 class="normal m-b-10">
        <g:message code="com.billmate.landing.application.slogan" default="Simplify management of shared costs" args="${ ['semi-bold'] }" />
    </h2>
    <p>
        <g:message code="com.billmate.landing.application.slogan.secondary" default="Follow shared expenses of your circles of friends and see the bills simplified" />
    </p>
    <a href="${createLink(controller: 'register', action: 'create')}"><button type="button" class="btn btn-primary btn-cons m-t-10"><g:message code="com.billmate.session.create.register" default="Create an account" /></button></a>
    <button type="button" class="btn btn-secondary btn-cons m-t-10"><g:message code="com.billmate.landing.seeMore" default="See more" /></button>
</div>
<div id="cbp-so-scroller" class="cbp-so-scroller section white ha-waypoint" data-animate-down="ha-header-color" data-animate-up="ha-header-hide">

    <section class="cbp-so-section">
        <figure class="cbp-so-side cbp-so-side-left">
            <img src="${assetPath(src: 'page/landing/houses.png')}" alt="img01">
        </figure>
        <article class="cbp-so-side cbp-so-side-right text-center middle">
            <div class="row">
                <div class="col-md-12 p-t-80 col-vlg-8 col-vlg-offset-2">
                    <h1>
                        <i class="fa fa-building-o fa-2x"></i>
                    </h1>
                    <h1 class="m-b-20">
                        <g:message code="com.billmate.landing.features.yourHouse" default="Your home" args="${ ['semi-bold'] }" />
                    </h1>
                    <p>
                        <g:message code="com.billmate.landing.features.yourHouse.description" default="Sharing house with friends? Add information of the costs and we'll help you to maintain timely payments, notifying your friends. See your responsibilities in an organized manner." />
                    </p>
                    <div class="m-t-60">
                        <div class="col-xs-4 col-md-2 col-xs-offset-2 col-md-offset-1">
                            <i class="fa fa-flash fa-2x"></i>
                            <p class="p-t-10"><g:message code="com.billmate.landing.features.yourHouse.expenses.electricity" default="Electricity" /></p>
                        </div>
                        <div class="col-xs-4 col-md-2">
                            <i class="fa fa-fire fa-2x"></i>
                            <p class="p-t-10"><g:message code="com.billmate.landing.features.yourHouse.expenses.gas" default="Gas" /></p>
                        </div>
                        <div class="col-xs-4 col-md-2">
                            <i class="fa fa-tint fa-2x"></i>
                            <p class="p-t-10"><g:message code="com.billmate.landing.features.yourHouse.expenses.water" default="Water" /></p>
                        </div>
                        <div class="col-xs-4 col-md-2">
                            <i class="fa fa-globe fa-2x"></i>
                            <p class="p-t-10"><g:message code="com.billmate.landing.features.yourHouse.expenses.internet" default="Internet" /></p>
                        </div>
                        <div class="col-xs-4 col-md-2">
                            <i class="fa fa-home fa-2x"></i>
                            <p class="p-t-10"><g:message code="com.billmate.landing.features.yourHouse.expenses.rent" default="Rent" /></p>
                        </div>
                    </div>
                </div>
            </div>
        </article>
    </section>

    <section class="cbp-so-section">
        <article class="cbp-so-side cbp-so-side-left text-center middle">
            <div class="row">
                <div class="col-md-12 p-t-40 col-vlg-8 col-vlg-offset-2">
                    <h1>
                        <i class="fa fa-users fa-2x"></i>
                    </h1>
                    <h1 class="m-b-20">
                        <g:message code="com.billmate.landing.features.yourCircles" default="Your circles" args="${ ['semi-bold'] }" />
                    </h1>
                    <p>
                        <g:message code="com.billmate.landing.features.yourCircles.description" default="Create your circles of friends for various events. Create specific costs for each circle, learn how to organize yourselves and have fun for a long time." />
                    </p>
                    <div class="m-t-60 ">
                        <div class="col-xs-4 col-md-2 col-xs-offset-2 col-md-offset-1">
                            <i class="fa fa-taxi fa-2x"></i>
                            <p class="p-t-10"><g:message code="com.billmate.landing.features.yourCircles.expenses.carpooling" default="Carpooling"/></p>
                        </div>
                        <div class="col-xs-4 col-md-2">
                            <i class="fa fa-plane fa-2x"></i>
                            <p class="p-t-10"><g:message code="com.billmate.landing.features.yourCircles.expenses.traveling" default="Traveling"/></p>
                        </div>
                        <div class="col-xs-4 col-md-2">
                            <i class="fa fa-sun-o fa-2x"></i>
                            <p class="p-t-10"><g:message code="com.billmate.landing.features.yourCircles.expenses.holidays" default="Holidays"/></p>
                        </div>
                        <div class="col-xs-4 col-md-2">
                            <i class="fa fa-ticket fa-2x"></i>
                            <p class="p-t-10"><g:message code="com.billmate.landing.features.yourCircles.expenses.events" default="Events"/></p>
                        </div>
                        <div class="col-xs-4 col-md-2">
                            <i class="fa fa-glass fa-2x"></i>
                            <p class="p-t-10"><g:message code="com.billmate.landing.features.yourCircles.expenses.dinners" default="Dinners"/></p>
                        </div>
                    </div>
                </div>
            </div>
        </article>
        <figure class="cbp-so-side cbp-so-side-right">
            <img src="${assetPath(src: 'page/landing/circles.png')}" alt="img01">
        </figure>
    </section>

    <section class="cbp-so-section">
        <figure class="cbp-so-side cbp-so-side-left">
            <img src="${assetPath(src: 'page/landing/panel.png')}" alt="img01">
        </figure>
        <article class="cbp-so-side cbp-so-side-right text-center middle">
            <div class="row">
                <div class="col-md-12 p-t-80 col-vlg-8 col-vlg-offset-2">
                    <h1>
                        <i class="fa fa-magic fa-2x"></i>
                    </h1>
                    <h1 class="m-b-20">
                        <g:message code="com.billmate.landing.features.yourDashboard" default="Your dashboard" args="${ ['semi-bold'] }" />
                    </h1>
                    <p>
                        <g:message code="com.billmate.landing.features.yourDashboard.description" default="Control your spending by consulting reports and statistics of your expenses through the simple and intuitive dashboard that we have prepared for you." />
                    </p>
                    <div class="m-t-60 ">
                        <div class="col-xs-5 col-md-3 col-lg-2 col-md-offset-0 col-xs-offset-1 col-lg-offset-2">
                            <i class="fa fa-bar-chart-o fa-2x"></i>
                            <p class="p-t-10"><g:message code="com.billmate.landing.features.yourDashboard.expenses.statistics" default="Statistics" />
                            </p>
                        </div>
                        <div class="col-xs-5 col-md-3 col-lg-2">
                            <i class="fa fa-history fa-2x"></i>
                            <p class="p-t-10"><g:message code="com.billmate.landing.features.yourDashboard.expenses.history" default="History" /></p>
                        </div>
                        <div class="col-xs-5 col-md-3 col-lg-2 col-md-offset-0 col-xs-offset-1 col-lg-offset-0">
                            <i class="fa fa-file-text-o fa-2x"></i>
                            <p class="p-t-10"><g:message code="com.billmate.landing.features.yourDashboard.expenses.reports" default="Reports" /></p>
                        </div>
                        <div class="col-xs-5 col-md-3 col-lg-2">
                            <i class="fa fa-bell-o fa-2x"></i>
                            <p class="p-t-10"><g:message code="com.billmate.landing.features.yourDashboard.expenses.notifications" default="Notifications" /></p>
                        </div>
                    </div>
                </div>
            </div>
        </article>
    </section>

    <section class="cbp-so-section">
        <article class="cbp-so-side cbp-so-side-left text-center middle">
            <div class="row">
                <div class="col-md-12 p-t-80 col-vlg-8 col-vlg-offset-2">
                    <h1>
                        <i class="fa fa-magic fa-2x"></i>
                    </h1>
                    <h1 class="m-b-20">
                        <g:message code="com.billmate.landing.features.moreFeatures" default="More features..." args="${ ['semi-bold'] }" />
                    </h1>
                    <div class="m-t-40 ">
                        <div class="row p-t-20">
                            <div class="col-xs-2 col-xs-offset-1 col-md-2 col-md-offset-2 col-lg-1 p-t-10">
                                <i class="fa fa-check fa-2x"></i>
                            </div>
                            <div class="col-xs-9 col-md-8 text-left">
                                <h4><g:message code="com.billmate.landing.features.moreFeatures.customCircleExpenses" default="Create custom expense types for each circle" /></h4>
                            </div>
                        </diV>
                        <div class="row p-t-20">
                            <div class="col-xs-2 col-xs-offset-1 col-md-2 col-md-offset-2 col-lg-1 p-t-10">
                                <i class="fa fa-check fa-2x primary"></i>
                            </div>
                            <div class="col-xs-9 col-md-8 text-left">
                                <h4><g:message code="com.billmate.landing.features.moreFeatures.regularExpensesSchedule" default="Schedule your regular expenses" /></h4>
                            </div>
                        </div>
                        <div class="row p-t-20">
                            <div class="col-xs-2 col-xs-offset-1 col-md-2 col-md-offset-2 col-lg-1 p-t-10">
                                <i class="fa fa-check fa-2x"></i>
                            </div>
                            <div class="col-xs-9 col-md-8 text-left">
                                <h4><g:message code="com.billmate.landing.features.moreFeatures.expensesByFriend" default="See expenses organized by friends" /></h4>
                            </div>
                        </div>
                        <div class="row p-t-20">
                            <div class="col-xs-2 col-xs-offset-1 col-md-2 col-md-offset-2 col-lg-1 p-t-10">
                                <i class="fa fa-check fa-2x primary"></i>
                            </div>
                            <div class="col-xs-9 col-md-6 text-left">
                                <h4><g:message code="com.billmate.landing.features.moreFeatures.expenditureTriangulation" default="Have fun with expense triangulation" /></h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </article>
        <figure class="cbp-so-side cbp-so-side-right">
            <img src="${assetPath(src: 'page/landing/features.png')}" alt="img01">
        </figure>
    </section>
</div>
<div class="section white" id="testomonials-wrapper">
    <div class="container">
        <div class="p-t-60 p-b-50">
            <div id="testomonials" class="owl-carousel row">
                <div class="item">
                    <div class="col-md-6 col-md-offset-3">
                        <h3 class="normal text-center text-white">
                            I’m really happy that webarch is able to offer support specifically tailored to creative businesses and I’d love Nottingham to become renowned for this.
                        </h3>
                        <p class="text-center text-white">Caroline Hennigan, Programme Director, Broadway</p>
                    </div>
                </div>
                <div class="item">
                    <div class="col-md-6 col-md-offset-3">
                        <h3 class="normal text-center text-white">
                            I’m really happy that webarch is able to offer support specifically tailored to creative businesses and I’d love Nottingham to become renowned for this.
                        </h3>
                        <p class="text-center text-white">Caroline Hennigan, Programme Director, Broadway</p>
                    </div>
                </div>
                <div class="item">
                    <div class="col-md-6 col-md-offset-3">
                        <h3 class="semi-bold text-center text-white">
                            I’m really happy that webarch is able to offer support specifically tailored to creative businesses and I’d love Nottingham to become renowned for this.
                        </h3>
                        <p class="text-center text-white">Caroline Hennigan, Programme Director, Broadway</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="section black">
    <div class="container">
        <div class="p-t-60 p-b-60">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <h2 class="text-center text-white m-b-30">
                        <g:message code="com.billmate.landing.subscribe.title" default="Subscribe to get most recent news" args="${ ['semi-bold'] }" />
                    </h2>
                    <g:render template="/subscription/create"/>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="section white footer">
    <div class="container">
        <div class="p-t-30 p-b-30">
            <div class="row">
                <div class="col-xs-12 xs-m-b-20">
                    <div class="pull-left m-r-20">
                        <img src="${assetPath(src: 'page/landing/logo-black.svg')}" alt="" data-src="${assetPath(src: 'page/landing/logo-black.svg')}" onerror="this.src=${assetPath(src: 'page/landing/logo-black.png')}" width="30" />
                    </div>
                    <div class="bold" style="text-transform: uppercase"><g:message code="com.billmate.application.name" default="BillMate" /> &copy; 2014</div>
                    Universidade do Minho
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<asset:javascript src="page/landing/bm-landing.js" />
<asset:javascript src="page/landing/core.js" />

</body>

</html>
