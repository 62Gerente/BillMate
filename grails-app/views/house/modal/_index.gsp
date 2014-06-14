<div class="modal fade" id="houseCreateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <br>
                <i class="fa fa-home fa-7x"></i>
                <h4 id="myModalLabel" class="semi-bold"><g:message code="com.billmate.house.modal.create" default="Create a new home"/></h4>
                <p class="no-margin"><g:message code="com.billmate.house.modal.info" default="Populates the fields with the information of the new house"/></p>
                <br>
            </div>
            <g:form name="houseForm" url="[action:'createHouse',controller:'Circle']">
                <div class="modal-body">
                    <div class="row form-row">
                        <div class="col-md-12">
                            <g:textField name="houseName" placeholder="${message(code: 'com.billmate.house.modal.placeholder_name_house')}" class="form-control"/>
                        </div>
                    </div>
                    <div class="row form-row">
                        <div class="col-md-12">
                            <g:textField name="expenseType" placeholder="${message(code: 'com.billmate.house.modal.placeholder_expense_type')}" class="custom-multiselect-house-debt select2-offscreen" style="width:100%" tabindex="-1"/>
                        </div>
                    </div>
                    <div class="row form-row p-t-10">
                        <div class="col-md-12">
                            <g:textField name="friendsHome" placeholder="${message(code: 'com.billmate.house.modal.placeholder_house_friends')}" class="custom-multiselect-house-user select2-offscreen" style="width:100%" tabindex="-1"/>
                        </div>
                    </div>
                    <div class="row form-row p-t-10">
                        <div class="col-md-12">
                            <g:textArea name="houseDescription" rows="5" placeholder="${message(code: 'com.billmate.house.modal.placeholder_house_description')}" class="form-control"/>
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