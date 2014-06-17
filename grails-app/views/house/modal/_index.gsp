<script type='text/javascript'>
    var dataDebt = [];
    var setsDebt = [];
    var dataUser=[];
    var setsUser = [];
    var objJSONData = '${expenseTypes.encodeAsJSON()}';
    var parsedJSONData = eval(objJSONData);
    for(var i=0; i< parsedJSONData.length; i++){
        var value = parsedJSONData[i];
        dataDebt[i] = {id: value.id, text: value.name, faicon: value.cssClass};
        //setsDebt[i] = value.id;
    }

    var objJSONData = '${users.encodeAsJSON()}';
    var parsedJSONData = eval(objJSONData);
    for(var i=0; i< parsedJSONData.length; i++){
        var value = parsedJSONData[i];
        dataUser[i] = {id: value.id, text: value.name + "###" + value.email, faicon: value.cssClass};
        //setsUser[i] = value.id;
    }

    var unknownUser = '${path}';

</script>

<div class="modal fade" id="houseCreateModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="" id="errormsm" style="display: none">
            <button class="close"></button>
            <div><g:message code=""/></div>
        </div>
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <br>
                <i class="fa fa-home fa-7x"></i>
                <h4 id="myModalLabel" class="semi-bold"><g:message code="com.billmate.house.modal.create" default="Create a new home"/></h4>
                <p class="no-margin"><g:message code="com.billmate.house.modal.info" default="Populates the fields with the information of the new house"/></p>
                <br>
            </div>
            <g:form class="houseForm" controller="circle" action="createHouse">
                <div class="modal-body">
                    <div class="row form-row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="controls">
                                    <g:textField name="houseName" placeholder="${message(code: 'com.billmate.house.modal.placeholder_name_house')}" class="form-control houseName"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-row">
                        <div class="col-md-12">
                            <g:textField name="expenseType" placeholder="${message(code: 'com.billmate.house.modal.placeholder_expense_type')}" class="custom-multiselect-house-debt select2-offscreen expenseType" style="width:100%" tabindex="-1"/>
                        </div>
                    </div>
                    <div class="row form-row p-t-10">
                        <div class="col-md-12">
                            <g:textField name="friendsHome" placeholder="${message(code: 'com.billmate.house.modal.placeholder_house_friends')}" class="custom-multiselect-house-user select2-offscreen friendsHome" style="width:100%" tabindex="-1"/>
                        </div>
                    </div>
                    <div class="row form-row p-t-10">
                        <div class="col-md-12">
                            <g:textArea name="houseDescription" rows="5" placeholder="${message(code: 'com.billmate.house.modal.placeholder_house_description')}" class="form-control houseDescription"/>
                        </div>
                    </div>
                    <div class="row form-row p-t-10">
                        <div class="col-md-12 m-l-5 m-b-10">
                            <a href="#" class="expand-more-add-friends"><span>Adicionar amigos nao registados? Clique aqui</span></a>
                        </div>
                        <div class="add-more-friends" style="display: none">
                            <div class="col-md-5">
                                <input type="text" placeholder="Nome" style="width:100%"/>
                            </div>
                            <div class="col-md-5" style="padding-left: 0px">
                                <input type="text" placeholder="Email" style="width:100%"/>
                            </div>
                            <div class="col-md-2" style="padding-left: 0px;">
                                <button type="button" class="btn btn-default addNewReferredUser" style="height:41px;">Adicionar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><g:message code="com.billmate.btn.close" default="Close"/></button>
                    <g:submitButton name="createHouse" value="${message(code: 'com.billmate.house.modal.create')}" class="btn btn-primary"/>
                </div>
            </g:form>
        </div>
    </div>
</div>