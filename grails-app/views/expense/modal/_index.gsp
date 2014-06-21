<div class="modal fade" id="expenseCreateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <br>
                <i class="fa fa-money fa-7x"></i>
                <h4 id="myModalLabel" class="semi-bold">Criar uma nova despesa</h4>
                <p class="no-margin">Preenche os campos com a informação da nova despesa</p>
                <br>
            </div>
            <div class="modal-body">
                <div class="row form-row">
                    <div class="col-md-12">
                        <input type="text" class="form-control" placeholder="Nome da despesa"/>
                    </div>
                </div>
                <div class="row form-row m-b-10">
                    <div class="col-md-12">
                        <input type="hidden" class="custom-multiselect-house-debt" placeholder="Tipo da despesa" style="width:100%" tabindex="-1" class="select2-offscreen"/>
                    </div>
                </div>
                <div class="row form-row">
                    <div class="col-md-12">
                        <select class="image-picker limit_callback show-html select-list-users" multiple="multiple">
                            <option data-img-src="https://avatars3.githubusercontent.com/u/1471378?s=460" value="1">Cute Kitten 1</option>
                            <option data-img-src="https://avatars3.githubusercontent.com/u/1723678?s=460" value="2">Cute Kitten 2</option>
                            <option data-img-src="https://avatars0.githubusercontent.com/u/1722352?s=140" value="3">Cute Kitten 3</option>
                            <option data-img-src="https://avatars3.githubusercontent.com/u/1471378?s=460" value="4">Cute Kitten 1</option>
                            <option data-img-src="https://avatars3.githubusercontent.com/u/1723678?s=460" value="5">Cute Kitten 2</option>
                            <option data-img-src="https://avatars0.githubusercontent.com/u/1722352?s=140" value="6">Cute Kitten 3</option>
                        </select>
                    </div>
                </div>
                <div class="row form-row">
                    <div class="col-md-12">
                        <textarea class="form-control" rows="5" placeholder="Descricao da despesa"></textarea>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                <button type="button" class="btn btn-primary">
                    <g:message code="com.billmate.sidebar.addDebt" default="Add Debt"/>
                </button>
            </div>
        </div>
    </div>
</div>
