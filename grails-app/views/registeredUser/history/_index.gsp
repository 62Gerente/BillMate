<ul class="cbp_tmtimeline">
    <g:each var="action" in="${actions}">
        <g:render template="/registeredUser/history/show" model="[action: action]"/>
    </g:each>

    <!--<li>
        <div class="cbp_tmicon primary">
            <div class="user-profile">
                <img src="assets/img/profiles/d.jpg" data-src="assets/img/profiles/d.jpg"
                     data-src-retina="assets/img/profiles/d2x.jpg" width="48" height="48" alt="">
            </div>
        </div>

        <div class="cbp_tmlabel">
            <div class="p-t-5 p-l-30 p-r-20 p-b-10 xs-p-r-10 xs-p-l-10 xs-p-t-5">
                <div class="muted m-t-15"><i class="fa fa-home"></i> Casa de Braga - Há 5 dias</div>
                <h4 class="dark-text">
                    Pagaste
                    <span class="semi-bold">€5</span> da despesa <span class="semi-bold">Eletricidade</span> ao
                    <span class="semi-bold">Pedro Leite</span>.
                </h4>
            </div>

            <div class="clearfix"></div>

            <div class="tiles grey p-t-10 p-b-10 p-l-20 p-r-20">
                <span class="muted dark-text">Valor total da despesa:</span> <span class="label label-success">€22,34</span>
            </div>
        </div>
    </li>
    <li>
        <div class="cbp_tmicon primary">
            <div class="user-profile">
                <img src="assets/img/profiles/d.jpg" data-src="assets/img/profiles/d.jpg"
                     data-src-retina="assets/img/profiles/d2x.jpg" width="48" height="48" alt="">
            </div>
        </div>

        <div class="cbp_tmlabel">
            <div class="p-t-5 p-l-30 p-r-20 p-b-10 xs-p-r-10 xs-p-l-10 xs-p-t-5">
                <div class="muted m-t-15"><i class="fa fa-home"></i> Casa de Braga - Há 5 dias</div>
                <h4 class="dark-text">
                    Recebeste
                    <span class="semi-bold">€5</span> do
                    <span class="semi-bold">Pedro Leite</span> relativamente à despesa <span
                        class="semi-bold">Eletricidade</span>.
                </h4>
            </div>

            <div class="clearfix"></div>

            <div class="tiles grey p-t-10 p-b-10 p-l-20 p-r-20">
                <span class="muted dark-text">Valor total da despesa:</span> <span class="label label-success">€15,89</span>
            </div>
        </div>
    </li>
    <li>
        <div class="cbp_tmicon primary">
            <div class="user-profile">
                <img src="assets/img/profiles/d.jpg" data-src="assets/img/profiles/d.jpg"
                     data-src-retina="assets/img/profiles/d2x.jpg" width="48" height="48" alt="">
            </div>
        </div>

        <div class="cbp_tmlabel">
            <div class="p-t-5 p-l-30 p-r-20 p-b-10 xs-p-r-10 xs-p-l-10 xs-p-t-5">
                <div class="muted m-t-15"><i class="fa fa-home"></i> Casa de Braga - Há 8 dias</div>
                <h4 class="dark-text">
                    Criaste a despesa regular
                    <span class="semi-bold">Internet</span> no valor de <span class="semi-bold">5€</span>.
                </h4>
            </div>
        </div>
    </li>
    <li>
        <div class="cbp_tmicon primary">
            <div class="user-profile">
                <img src="assets/img/profiles/d.jpg" data-src="assets/img/profiles/d.jpg"
                     data-src-retina="assets/img/profiles/d2x.jpg" width="48" height="48" alt="">
            </div>
        </div>

        <div class="cbp_tmlabel">
            <div class="p-t-5 p-l-30 p-r-20 p-b-10 xs-p-r-10 xs-p-l-10 xs-p-t-5">
                <div class="muted m-t-15"><i class="fa fa-circle-o"></i> Jogos HASLab - Há 9 dias</div>
                <h4 class="dark-text">
                    Adicionaste
                    <span class="semi-bold">Pedro Leite</span> ao grupo
                    <span class="semi-bold">Jogos HASLab</span>.
                </h4>
            </div>
        </div>
    </li>
    <li>
        <div class="cbp_tmicon primary">
            <div class="user-profile">
                <img src="assets/img/profiles/d.jpg" data-src="assets/img/profiles/d.jpg"
                     data-src-retina="assets/img/profiles/d2x.jpg" width="48" height="48" alt="">
            </div>
        </div>

        <div class="cbp_tmlabel">
            <div class="p-t-5 p-l-30 p-r-20 p-b-10 xs-p-r-10 xs-p-l-10 xs-p-t-5">
                <div class="muted m-t-15"><i class="fa fa-circle-o"></i> Jogos HASLab - Há 9 dias</div>
                <h4 class="dark-text">
                    Removeste
                    <span class="semi-bold">Pedro Leite</span> ao grupo
                    <span class="semi-bold">Jogos HASLab</span>.
                </h4>
            </div>
        </div>
    </li>
    <li>
        <div class="cbp_tmicon primary">
            <div class="user-profile">
                <img src="assets/img/profiles/d.jpg" data-src="assets/img/profiles/d.jpg"
                     data-src-retina="assets/img/profiles/d2x.jpg" width="48" height="48" alt="">
            </div>
        </div>

        <div class="cbp_tmlabel">
            <div class="p-t-5 p-l-30 p-r-20 p-b-10 xs-p-r-10 xs-p-l-10 xs-p-t-5">
                <div class="muted m-t-15"><i class="fa fa-circle-o"></i> Jogos HASLab - Há 9 dias</div>
                <h4 class="dark-text">
                    Entraste no grupo
                    <span class="semi-bold">Jogos HASLab</span>.
                </h4>
            </div>
        </div>
    </li>
    <li>
        <div class="cbp_tmicon primary">
            <div class="user-profile">
                <img src="assets/img/profiles/d.jpg" data-src="assets/img/profiles/d.jpg"
                     data-src-retina="assets/img/profiles/d2x.jpg" width="48" height="48" alt="">
            </div>
        </div>

        <div class="cbp_tmlabel">
            <div class="p-t-5 p-l-30 p-r-20 p-b-10 xs-p-r-10 xs-p-l-10 xs-p-t-5">
                <div class="muted m-t-15"><i class="fa fa-circle-o"></i> Jogos HASLab - Há 9 dias</div>
                <h4 class="dark-text">
                    Saíste do grupo
                    <span class="semi-bold">Jogos HASLab</span>.
                </h4>
            </div>
        </div>
    </li>
    <li>
        <div class="cbp_tmicon primary">
            <div class="user-profile">
                <img src="assets/img/profiles/d.jpg" data-src="assets/img/profiles/d.jpg"
                     data-src-retina="assets/img/profiles/d2x.jpg" width="48" height="48" alt="">
            </div>
        </div>

        <div class="cbp_tmlabel">
            <div class="p-t-10 p-l-30 p-r-20 p-b-20 xs-p-r-10 xs-p-l-10 xs-p-t-5">
                <div class="m-t-10 muted">Há 10 dias</div>
                <h4 class="dark-text">
                    Criaste o círculo
                    <span class="semi-bold">Futeboladas CeSIUM</span>.
                </h4>
            </div>

            <div class="clearfix"></div>

            <div class="tiles grey p-t-20 p-b-10 p-l-20">
                <ul class="my-friends no-margin">
                    <li>
                        <div class="profile-pic">
                            <img width="35" height="35" data-src-retina="assets/img/profiles/e2x.jpg"
                                 data-src="assets/img/profiles/e.jpg" src="assets/img/profiles/e.jpg" alt="">
                        </div>
                    </li>
                    <li>
                        <div class="profile-pic">
                            <img width="35" height="35" data-src-retina="assets/img/profiles/b2x.jpg"
                                 data-src="assets/img/profiles/b.jpg" src="assets/img/profiles/b.jpg" alt="">
                        </div>
                    </li>
                    <li>
                        <div class="profile-pic">
                            <img width="35" height="35" data-src-retina="assets/img/profiles/h2x.jpg"
                                 data-src="assets/img/profiles/h.jpg" src="assets/img/profiles/h.jpg" alt="">
                        </div>
                    </li>
                </ul>

                <div class="clearfix"></div>
            </div>
        </div>
    </li>
    <li>
        <div class="cbp_tmicon primary">
            <div class="user-profile">
                <img src="assets/img/profiles/d.jpg" data-src="assets/img/profiles/d.jpg"
                     data-src-retina="assets/img/profiles/d2x.jpg" width="48" height="48" alt="">
            </div>
        </div>

        <div class="cbp_tmlabel">
            <div class="p-t-5 p-l-30 p-r-20 p-b-10 xs-p-r-10 xs-p-l-10 xs-p-t-5">
                <div class="muted m-t-15"><i class="fa fa-home"></i> Casa de Braga - Há um mês</div>
                <h4 class="dark-text">
                    Adicionaste um recibo à despesa <span class="semi-bold">Água</span> do mês de <span
                        class="semi-bold">setembro</span>.
                </h4>
            </div>

            <div class="clearfix"></div>

            <div class="tiles grey p-t-10 p-b-10 p-l-20 p-r-20">
                <ul class="action-links">
                    <li><a href="#" class="dark-text"><i class="fa fa-eye"></i> Ver recibo</a></li>
                </ul>

                <div class="clearfix"></div>
            </div>
        </div>
    </li>
    <li>
        <div class="cbp_tmicon primary">
            <div class="user-profile">
                <img src="assets/img/profiles/d.jpg" data-src="assets/img/profiles/d.jpg"
                     data-src-retina="assets/img/profiles/d2x.jpg" width="48" height="48" alt="">
            </div>
        </div>

        <div class="cbp_tmlabel">
            <div class="p-t-5 p-l-30 p-r-20 p-b-10 xs-p-r-10 xs-p-l-10 xs-p-t-5">
                <div class="muted m-t-15"><i class="fa fa-home"></i> Casa de Braga - Há um mês</div>
                <h4 class="dark-text">
                    Adicionaste uma fatura à despesa <span class="semi-bold">Eletricidade</span>.
                </h4>
            </div>

            <div class="clearfix"></div>

            <div class="tiles grey p-t-10 p-b-10 p-l-20 p-r-20">
                <ul class="action-links">
                    <li><a href="#" class="dark-text"><i class="fa fa-eye"></i> Ver fatura</a></li>
                </ul>

                <div class="clearfix"></div>
            </div>
        </div>
    </li>
    <li>
        <div class="cbp_tmicon primary">
            <div class="user-profile">
                <img src="assets/img/profiles/d.jpg" data-src="assets/img/profiles/d.jpg"
                     data-src-retina="assets/img/profiles/d2x.jpg" width="48" height="48" alt="">
            </div>
        </div>

        <div class="cbp_tmlabel">
            <div class="p-t-5 p-l-30 p-r-20 p-b-10 xs-p-r-10 xs-p-l-10 xs-p-t-5">
                <div class="muted m-t-15">Há 5 meses</div>
                <h4 class="dark-text">
                    Registaste-te no
                    <span class="semi-bold">BillMate</span>.
                </h4>
            </div>
        </div>
    </li>-->
</ul>
