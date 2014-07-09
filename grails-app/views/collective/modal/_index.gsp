<script type='text/javascript'>
    var dataDebtCollective = [];
    var setsDebtCollective = [];
    var dataUserCollective=[];
    var setsUserCollective = [];
    var objJSONDataCollective = '${expenseTypes.encodeAsJSON()}';
    var parsedJSONDataCollective = eval(objJSONDataCollective);
    for(var i=0; i< parsedJSONDataCollective.length; i++){
        var value = parsedJSONDataCollective[i];
        dataDebtCollective[i] = {id: value.id, text: value.name, faicon: value.cssClass};
    }

    var objJSONDataCollective = '${users.encodeAsJSON()}';
    var parsedJSONDataCollective = eval(objJSONDataCollective);
    for(var i=0; i< parsedJSONDataCollective.length; i++){
        var value = parsedJSONDataCollective[i];
        dataUserCollective[i] = {id: value.id, text: value.name + "###" + value.email, faicon: value.cssClass};
    }

    var unknownUserCollective = '${path}';

</script>

<div class="modal fade" id="collectiveCreateModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="" style="display: none">
            <button class="close"></button>
            <div><g:message code=""/></div>
        </div>
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <br>
                <i class="fa fa-users fa-7x"></i>
                <h4 id="myModalLabel" class="semi-bold"><g:message code="com.billmate.collective.modal.create" default="Create new collective"/></h4>
                <p class="no-margin"><g:message code="com.billmate.collective.modal.info" default="Populates the fields with the information of the new collective"/></p>
                <br>
            </div>
            <g:form class="collectiveForm" controller="collective" action="save">
                <div class="modal-body">
                    <div class="row form-row">
                        <div class="col-md-12">
                            <div class="controls">
                                <g:textField name="collectiveName" placeholder="${message(code: 'com.billmate.collective.modal.placeholderNameCollective')}" class="form-control collectiveName"/>
                            </div>
                        </div>
                    </div>
                    <div class="row form-row">
                        <div class="col-md-12">
                            <g:textField name="expenseType" placeholder="${message(code: 'com.billmate.collective.modal.placeholderExpenseType')}" class="custom-multiselect-collective-debt select2-offscreen expenseType"/>
                        </div>
                    </div>
                    <div class="row form-row p-t-10">
                        <div class="col-md-12">
                            <g:textField name="friendsCollective" placeholder="${message(code: 'com.billmate.collective.modal.placeholderCollectiveFriends')}" class="custom-multiselect-collective-user select2-offscreen friendsCollective"/>
                        </div>
                    </div>
                    <div class="row form-row p-t-10">
                        <div class="col-md-12">
                            <g:textArea name="collectiveDescription" rows="5" placeholder="${message(code: 'com.billmate.collective.modal.placeholderCollectiveDescription')}" class="form-control collectiveDescription"/>
                        </div>
                    </div>
                    <div class="row form-row p-t-10">
                        <div class="col-md-12 m-l-5 m-b-10">
                            <a href="#" class="expand-more-add-friends"><span><g:message code="com.billmate.user.addNonRegisteredUser" default="Add non registered users? Click here"/></span></a>
                        </div>
                        <div class="add-more-friends-collective">
                            <div class="col-md-10">
                                <input type="text" placeholder="Email"/>
                            </div>
                            <div class="col-md-2 divAddNewReferredUserCollective">
                                <button type="button" class="btn btn-default addNewReferredUserCollective">
                                    <g:message code="com.billmate.btn.add" default="Add"/>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><g:message code="com.billmate.btn.cancel" default="Cancel"/></button>
                    <g:submitButton name="createGroup" value="${message(code: 'com.billmate.collective.modal.create')}" class="btn btn-primary"/>
                    <g:hiddenField name="identifier" value="${user}"/>
                </div>
            </g:form>
        </div>
    </div>
</div>
