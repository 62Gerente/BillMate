<div class="grid simple">
    <div class="grid-title no-border"></div>
    <div class="grid-body no-border">
        <div class="row">
            <div class="col-md-12">
                <h3 class="semi-bold text-dark-grey">Notificações</h3>
                <p>Obtém as atualizações mais relevantes dos teus círculos e tuas despesas partilhadas.</p>
                <h4 class="text-dark-grey p-t-10">Notificações por <span class="semi-bold">email</span></h4>
                <p>Serão enviados emails personalizados para o teu endereço de email.</p>
                <div class="radio">
                    <input id="email_enable" type="radio" checked="checked">
                    <label for="email_enable">Ativar</label>
                    <input id="email_disable" type="radio">
                    <label for="email_disable">Desativar</label>
                </div>
                <br>
                <div class="row-fluid">
                    <div class="checkbox check-default">
                        <input id="email_add_circle" type="checkbox" value="1">
                        <label for="email_add_circle">Adicionado a um círculo.</label>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="checkbox check-primary">
                        <input id="email_new_expense" type="checkbox" value="1">
                        <label for="email_new_expense">Nova despesa partilhada contigo.</label>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="checkbox check-primary">
                        <input id="email_new_regular_expense" type="checkbox" value="1">
                        <label for="email_new_regular_expense">Nova despesa agendada.</label>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="checkbox check-info">
                        <input id="email_new_payment" type="checkbox" value="1">
                        <label for="email_new_payment">Novo pagamento numa despesa em que és responsável.</label>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="checkbox check-info">
                        <input id="email_upcoming_limit_date" type="checkbox" value="1">
                        <label for="email_upcoming_limit_date">Aproximação de uma data limite de pagamento.</label>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="checkbox check-warning">
                        <input id="email_upcoming_expense" type="checkbox" value="1" checked="checked">
                        <label for="email_upcoming_expense">Aproximação da data de receção de uma despesa agendada.</label>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="checkbox check-warning">
                        <input id="email_week_report" type="checkbox" value="1" checked="checked">
                        <label for="email_week_report">Relatórios de despesas semanais.</label>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="checkbox check-warning">
                        <input id="email_month_report" type="checkbox" value="1" checked="checked">
                        <label for="email_month_report">Relatórios de despesas mensais.</label>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="checkbox check-warning">
                        <input id="email_year_report" type="checkbox" value="1" checked="checked">
                        <label for="email_year_report">Relatórios de despesas anuais.</label>
                    </div>
                </div>
                <h4 class="text-dark-grey p-t-10">Notificações por <span class="semi-bold">SMS</span></h4>
                <p>Serão enviadas mensagens de texto para o número de telemóvel especificado no seu perfil.</p>
                <div class="radio">
                    <input id="sms_enable" type="radio" checked="checked">
                    <label for="sms_enable">Ativar</label>
                    <input id="sms_disable" type="radio">
                    <label for="sms_disable">Desativar</label>
                </div>
                <br>
                <div class="row-fluid">
                    <div class="checkbox check-primary">
                        <input id="sms_new_expense" type="checkbox" value="1">
                        <label for="sms_new_expense">Nova despesa partilhada contigo.</label>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="checkbox check-info">
                        <input id="sms_new_payment" type="checkbox" value="1">
                        <label for="sms_new_payment">Novo pagamento numa despesa em que és responsável.</label>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="checkbox check-info">
                        <input id="sms_upcoming_limit_date" type="checkbox" value="1">
                        <label for="sms_upcoming_limit_date">Aproximação de uma data limite de pagamento.</label>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="checkbox check-warning">
                        <input id="sms_upcoming_expense" type="checkbox" value="1" checked="checked">
                        <label for="sms_upcoming_expense">Aproximação da data de receção de uma despesa agendada.</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>