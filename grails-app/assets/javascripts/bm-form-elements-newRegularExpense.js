$(document).ready(function() {

    var listElementsExpenses = [];
    var idCircleRegularExpense = 0;
    var idUserRegularExpense = 0;
    var idExpenseTypeExpense = 0;
    var hasCircles = false;
    var hasExpenseTypes = false;

    $(".periodicity").mask("REPETIR \APÓS 9 \ANO, 9 MÊS E 9 DIA");

    //Next 3 functions format display results
    function formatExpenseTypesNewRegularExpense(state) {
        hasExpenseTypes = true;
        idExpenseTypeExpense = state.id;
        return "<i class='" + state.cssClass + "'></i>&nbsp;" + $("<div>").html(state.name).text();
    }

    function formatResultCirclesNewRegularExpense(state) {
        hasCircles = true;
        idCircleRegularExpense = state.id;
        return "<i class='" + state.icon + "'></i>&nbsp;" + $("<div>").html(state.name).text();
    }

    function formatSelectionCirclesNewRegularExpense(state) {
        hasCircles = true;
        idCircleRegularExpense = state.id;
        fillListExpense();
        return "<i class='" + state.icon + "'></i>&nbsp;" + $("<div>").html(state.name).text();
    }

    //Enable select2 plugin to expenses debts
    $(".custom-multiselect-newRegularExpense-debt").select2({
        minimumInputLength: 1,
        formatResult: formatExpenseTypesNewRegularExpense,
        formatSelection: formatExpenseTypesNewRegularExpense,
        enable: false,
        ajax: {
            type: "POST",
            url: $("#expense-type-link-newRegularExpense").val(),
            dataType: 'json',
            data: function(term, page) {
                return {
                    q: term,
                    idCircle: idCircleRegularExpense
                };
            },
            results: function(data, page) {
                return {
                    results: data.data
                };
            }
        }
    });

    //Enable select2 plugin to expenses circles
    $(".custom-multiselect-newRegularExpense-circle").select2({
        minimumInputLength: 1,
        formatResult: formatResultCirclesNewRegularExpense,
        formatSelection: formatSelectionCirclesNewRegularExpense,
        ajax: {
            type: "POST",
            url: $("#circle-link-newRegularExpense").val(),
            dataType: 'json',
            data: function(term, page) {
                return {
                    q: term,
                    id: $(this).parents(".simple-options-form-newRegularExpense").children("input:nth(0)").val()
                };
            },
            results: function(data, page) {
                $(".custom-multiselect-expense-debt").select2("enable",true);
                return {
                    results: data.data
                };
            }
        }
    });

    //Call images plugin
    $(".select-list-users-newRegularexpense").imagepicker({
        initialized: fillListExpense
    });

    //Fill list of Users
    function fillListExpense(){
        if(idUserRegularExpense == 0) idUserRegularExpense = $(".simple-options-form-newRegularExpense").children("input:nth(1)").val();
        if(idCircleRegularExpense != 0){
            var url = $("#users-link-newRegularExpense").val();
            $.post(url, {id_circle: idCircleRegularExpense}, function (data) {
                listFriendsOfCircleAjaxRequestInListExpense(data);
            } );
            $(".select-list-users-newRegularexpense").data('picker');
        }
    }

    //When input value debt, we need calculate new values and update in html, calling propagateChangesExpense() and updateValuesExpense()
    $(".input-group.transparent #valueNewRegularExpense").keyup(function(){
        propagateChangesExpense();
    });

    function propagateChangesExpense(){
        calculateValuesExpense();
        updateValuesExpense();
    }

    function updateValuesExpense(){
        var position = 0;
        listElementsExpenses.forEach(function(entry){
            $(".photos-options-form-newRegularExpense .thumbnails.image_picker_selector").find("li:nth(" + position + ") .thumbnail .name-inside-photo-modal-debt").text(entry.value.toFixed(2) + " €");
            position++;
        });
    }

    //Put images selected on start, and propagate changes
    function enableClickOnImagePickerExpense(){
        $(".photos-options-form-newRegularExpense .thumbnails.image_picker_selector").find("li").click(function(){
            var index = $(this).index();
            var click = $(".photos-options-form-newRegularExpense .thumbnails.image_picker_selector").find("li:nth(" + index + ") .thumbnail");

            if(click.first().hasClass("selected")){
                listElementsExpenses[index].selectable = true;
            }else{
                listElementsExpenses[index].selectable = false;
            }
            propagateChangesExpense();
        });
    }

    //Simply save actual data array and start process to listUsers
    function listFriendsOfCircleAjaxRequestInListExpense(data){
        listElementsExpenses = data.data;
        startProcedureExpense()
    }

    //Core procedure to display list images
    function startProcedureExpense(){
        calculateValuesExpense();
        addNewElementstoSelectExpense();
        startImagePickerExpense();
        enableSelectablesExpense();
        updateNamesInBoxesExpense();
    }

    //Calculate actual values
    function calculateValuesExpense(){
        var position = 1;
        var parcialValue = 0;
        var totalValue = 0;
        var numberOfElements = 0;
        listElementsExpenses.forEach(function(entry){ if(entry.selectable == true) numberOfElements++; });
        totalValue = $(".input-group.transparent").find("input#valueNewRegularExpense").val();
        listElementsExpenses.forEach(function(entry){
            entry.value = 0;
            if(entry.selectable == true){
                parcialValue = totalValue / numberOfElements;
                if(position == numberOfElements)
                    entry.value = totalValue - (parcialValue * (numberOfElements-1));
                else
                    entry.value = parcialValue;
            }
            position++;
        });
    }

    //We need append new users according selected circles
    function addNewElementstoSelectExpense(){
        var position = 0;
        $(".select-list-users-newRegularexpense ul").empty();
        $(".select-list-users-newRegularexpense").empty();
        listElementsExpenses.forEach(function(entry){
            $(".select-list-users-newRegularexpense").append("<option data-img-src='" + entry.photo + "' value='" + entry.id + "'>" + entry.value.toFixed(2) +" €</option>")
        });
    }

    //Tell to plugin show labels and associate function enableClickOnImagePickerExpense() when clicked
    function startImagePickerExpense(){
        $(".select-list-users-newRegularexpense").imagepicker({
            show_label: true,
            clicked: enableClickOnImagePickerExpense
        });
        addValuesToPhotosExpense();
    }

    //Show values to each user
    function addValuesToPhotosExpense(){
        var position = 0;
        listElementsExpenses.forEach(function(entry){
            $(".photos-options-form-newRegularExpense .thumbnails.image_picker_selector li:nth(" + position + ") .thumbnail").append("<div class='name-inside-photo-modal-debt'>" + entry.value.toFixed(2) + " €</div><span class='tick-green'><i class='fa fa-circle fa-2x text-info'></i></span>");
            position++;
        });
    }

    //Trigger click to display users selected
    function enableSelectablesExpense(){
        var position = 0;
        listElementsExpenses.forEach(function(entry){
            if(entry.selectable == true)
                $(".photos-options-form-newRegularExpense .thumbnails.image_picker_selector").find("li:nth(" + position + ") .thumbnail").trigger("click");
            position++;
        });
    }

    //Plugin add paragraph, but whe need replace p by h6
    function updateNamesInBoxesExpense(){
        var position = 0;
        listElementsExpenses.forEach(function(entry){
            var element = $(".photos-options-form-newRegularExpense .thumbnails.image_picker_selector li:nth(" + position + ") .thumbnail");
            element.find("p").replaceWith(function(){
                return $("<h6/>", {html: entry.name});
            });
            element.find("h6").addClass("text-center semi-bold");
            element.find("h6").detach().appendTo(element.parent());
            position++;
        });
    }

    // When submit form
    $(".btn-submit-new-newRegularExpense").click(function(){
        var hasErrors = false;
        var status = $(this).closest(".modal-dialog").find("div:nth(0)");
        var parent = $(this).closest(".modal-content").find(".simple-options-form-newRegularExpense");

        var name = parent.find("div.row:nth(0) input.form-control").val();
        var value = parent.find("div.row:nth(2) input.value-debt").val();
        var description = parent.find("div.row:nth(3) textarea").val();

        if(name == ""){ hasErrors = true; doAlertInput(parent.find("div.row:nth(0)"),parent.find("div.row:nth(0) input"),"error-control"); }
        if(!hasCircles){ hasErrors = true; doAlertSelect(parent.find("div.row:nth(1) .select2-container"),"select2-container-error",".custom-multiselect-expense-circle"); }
        if(!hasExpenseTypes){ hasErrors = true; doAlertSelect(parent.find("div.row:nth(2) .select2-container"),"select2-container-error",".custom-multiselect-expense-debt"); }
        if(value == "") { hasErrors = true; doAlertInput(parent.find("div.row:nth(2) .input-group"),parent.find("div.row:nth(2) .input-group input"),"error-control"); }

        var dates = $(this).parents(".modal-footer").siblings(".modal-body").find(".advanced-options-form-newRegularExpense");
        var paymentDeadline = dates.find("input:nth(0)").val();
        var receptionDeadline = dates.find("input:nth(1)").val();
        var beginDate = dates.find("input:nth(2)").val();
        var endDate = dates.find("input:nth(3)").val();
        var periodicity = dates.find("input:nth(7)").val();

        var listIDsUsers = [];
        var listValuesUsers = [];
        listElementsExpenses.forEach(function(entry){
            listIDsUsers.push(entry.id);
            listValuesUsers.push(entry.value);
        });

        var formData = {name: name, idCircle: idCircleRegularExpense, idExpenseType: idExpenseTypeExpense, value: value, description: description,
            idUser: idUserRegularExpense, listOfFriends: listIDsUsers, listValuesUsers: listValuesUsers, paymentDeadline: paymentDeadline,
            receptionDeadline: receptionDeadline, beginDate: beginDate, endDate: endDate, numberSelected: getNumberOfSelected(), periodicity: periodicity };

        if(!hasErrors){
            $.ajax({
                url: $("#submit-link-newRegularExpense").val(),
                data: formData,
                type: "POST",
                dataType: 'json',
                beforeSend: function(){
                    $("body").block({ message: null });
                },
                success: function (data) {
                    status.show();
                    status.removeClass();
                    status.addClass(data.class);
                    status.find("div").text(data.code);
                    if(!data.error){
                        status.delay();
                        window.location.reload();
                    }
                },
                error: function(request,error){
                    status.show();
                    status.removeClass();
                    status.addClass("alert alert-error form-modal-house-error");
                    status.find("div").text("Oops! Something went wrong.");
                },
                complete: function(){
                    $("body").unblock();
                }
            });
        }
    });

    //Display alerts on input text
    function doAlertInput(selector, selectorEvent, errorClass){
        selector.addClass(errorClass);
        selectorEvent.change(function(){
            selector.removeClass(errorClass);
        });
    }

    //Display alerts on select options
    function doAlertSelect(selector, errorClass, selectorStart){
        selector.addClass(errorClass);
        $(selectorStart).on("select2-selecting",function(){
            selector.removeClass(errorClass);
        });
    }

    // Show/hide advanced options
    $(".btn-options-form-newRegularExpense").click(function(){
        $(this).parents(".modal-footer").siblings(".modal-body").find(".simple-options-form-newRegularExpense").slideToggle();
        $(this).parents(".modal-footer").siblings(".modal-body").find(".advanced-options-form-newRegularExpense").slideToggle();
    });

    function getNumberOfSelected(){
        var numberSelected = 0;
        listElementsExpenses.forEach(function(entry){
            if(entry.selectable == true)
                numberSelected++;
        });
        return numberSelected;
    }
});
