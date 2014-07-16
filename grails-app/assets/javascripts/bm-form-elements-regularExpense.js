$(document).ready(function() {

    var listElementsExpenses = [];
    var idCircleRegularExpense = 0;
    var idUserRegularExpense = 0;
    var idExpenseTypeExpense = 0;
    var idRegularExpense = 0;

    //Next 3 functions format display results
    function formatExpenseTypes(state) {
        idExpenseTypeExpense = state.id;
        return "<i class='" + state.expenseTypeCssClass + "'></i>&nbsp;" + $("<div>").html(state.expenseTypeName).text();
    }

    function formatResultCircles(state) {
        idCircleRegularExpense = state.id;
        return "<i class='" + state.icon + "'></i>&nbsp;" + $("<div>").html(state.name).text();
    }

    function formatSelectionCircles(state) {
        idCircleRegularExpense = state.id;
        fillListExpense();
        return "<i class='" + state.icon + "'></i>&nbsp;" + $("<div>").html(state.name).text();
    }

    //Call images plugin
    $(".select-list-users-expense").imagepicker({
        initialized: fillListExpense
    });

    //Fill list of Users
    function fillListExpense(){
        if(idUserRegularExpense == 0) idUserRegularExpense = $(".simple-options-form-regularExpense").children("input:nth(1)").val();
        if(idCircleRegularExpense != 0){
            var url = $("#users-link-FillRegularExpense").val();
            $.post(url, {id_regular_expense: idRegularExpense}, function (data) {
                listFriendsOfCircleAjaxRequestInListExpense(data);
            } );
            $(".select-list-users-expense").data('picker');
        }
    }

    //When input value debt, we need calculate new values and update in html, calling propagateChangesExpense() and updateValuesExpense()
    $(".input-group.transparent #valueRegularExpense").keyup(function(){
        propagateChangesExpense();
    });

    function propagateChangesExpense(){
        calculateValuesExpense();
        updateValuesExpense();
    }

    function updateValuesExpense(){
        var position = 0;
        listElementsExpenses.forEach(function(entry){
            $(".photos-options-form-regularExpense .thumbnails.image_picker_selector").find("li:nth(" + position + ") .thumbnail .name-inside-photo-modal-debt").text(entry.value.toFixed(2) + " €");
            position++;
        });
    }

    //Put images selected on start, and propagate changes
    function enableClickOnImagePickerExpense(){
        $(".photos-options-form-regularExpense .thumbnails.image_picker_selector").find("li").click(function(){
            var index = $(this).index();
            var click = $(".photos-options-form-regularExpense .thumbnails.image_picker_selector").find("li:nth(" + index + ") .thumbnail");

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
        totalValue = $(".input-group.transparent").find("input#valueRegularExpense").val();
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
        $(".select-list-users-expense ul").empty();
        $(".select-list-users-expense").empty();
        listElementsExpenses.forEach(function(entry){
            $(".select-list-users-expense").append("<option data-img-src='" + entry.photo + "' value='" + entry.id + "'>" + entry.value.toFixed(2) +" €</option>")
        });
    }

    //Tell to plugin show labels and associate function enableClickOnImagePickerExpense() when clicked
    function startImagePickerExpense(){
        $(".select-list-users-expense").imagepicker({
            show_label: true,
            clicked: enableClickOnImagePickerExpense
        });
        addValuesToPhotosExpense();
    }

    //Show values to each user
    function addValuesToPhotosExpense(){
        var position = 0;
        listElementsExpenses.forEach(function(entry){
            $(".photos-options-form-regularExpense .thumbnails.image_picker_selector li:nth(" + position + ") .thumbnail").append("<div class='name-inside-photo-modal-debt'>" + entry.value.toFixed(2) + " €</div><span class='tick-green'><i class='fa fa-circle fa-2x text-info'></i></span>");
            position++;
        });
    }

    //Trigger click to display users selected
    function enableSelectablesExpense(){
        var position = 0;
        listElementsExpenses.forEach(function(entry){
            if(entry.selectable == true)
                $(".photos-options-form-regularExpense .thumbnails.image_picker_selector").find("li:nth(" + position + ") .thumbnail").trigger("click");
            position++;
        });
    }

    //Plugin add paragraph, but whe need replace p by h6
    function updateNamesInBoxesExpense(){
        var position = 0;
        listElementsExpenses.forEach(function(entry){
            var element = $(".photos-options-form-regularExpense .thumbnails.image_picker_selector li:nth(" + position + ") .thumbnail");
            element.find("p").replaceWith(function(){
                return $("<h6/>", {html: entry.name});
            });
            element.find("h6").addClass("text-center semi-bold");
            element.find("h6").detach().appendTo(element.parent());
            position++;
        });
    }

    // When submit form
    $(".btn-submit-new-regularExpense").click(function(){
        var hasErrors = false;
        var status = $(this).closest(".modal-dialog").find("div:nth(0)");
        var parent = $(this).closest(".modal-content").find(".simple-options-form-regularExpense");

        var name = parent.find("div.row:nth(0) input.form-control").val();
        var value = parent.find("div.row:nth(2) input.value-debt").val();
        var description = parent.find("div.row:nth(3) textarea").val();

        var dates = $(this).parents(".modal-footer").siblings(".modal-body").find(".advanced-options-form-regularExpense");
        var paymentDeadline = dates.find("input:nth(0)").val();
        var receptionDeadline = dates.find("input:nth(1)").val();
        var beginDate = dates.find("input:nth(2)").val();
        var endDate = dates.find("input:nth(3)").val();
        var receptionBeginDate = parent.find(".row:nth(1) > div:nth(1) input").val();

        if(value == "") { hasErrors = true; doAlertInput(parent.find("div.row:nth(2) .input-group"),parent.find("div.row:nth(2) .input-group input"),"error-control"); }
        if(name == ""){ hasErrors = true; doAlertInput(parent.find("div.row:nth(0)"),parent.find("div.row:nth(0) input"),"error-control"); }
        if(receptionBeginDate == ""){ hasErrors = true; doAlertInput(parent.find("div.row:nth(1) > div:nth(1)"),parent.find("div.row:nth(1) > div:nth(1) input"),"error-control"); }


        var listIDsUsers = [];
        var listValuesUsers = [];
        listElementsExpenses.forEach(function(entry){
            listIDsUsers.push(entry.id);
            listValuesUsers.push(entry.value);
        });

        var formData = {name: name, idCircle: idCircleRegularExpense, idExpenseType: idExpenseTypeExpense, value: value, description: description,
            idUser: idUserRegularExpense, listOfFriends: listIDsUsers, listValuesUsers: listValuesUsers, paymentDeadline: paymentDeadline,
            receptionDeadline: receptionDeadline, beginDate: beginDate, endDate: endDate,
            receptionDate: receptionBeginDate, numberSelected: getNumberOfSelected(), regularExpenseID: idRegularExpense};

        if(!hasErrors){
            $.ajax({
                url: $("#submit-link-expense").val(),
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
    $(".btn-options-form-regularExpense").click(function(){
        $(this).parents(".modal-footer").siblings(".modal-body").find(".simple-options-form-regularExpense").slideToggle();
        $(this).parents(".modal-footer").siblings(".modal-body").find(".advanced-options-form-regularExpense").slideToggle();
    });

    function getNumberOfSelected(){
        var numberSelected = 0;
        listElementsExpenses.forEach(function(entry){
            if(entry.selectable == true)
                numberSelected++;
        });
        return numberSelected;
    }

    function handleRegularExpenseType(dataDebtRegularExpense, dataDebtList){
        var context = $(".modal#regularExpenseCreateModal .modal-body .advanced-options-form-regularExpense");


        $(".custom-multiselect-regularExpense-debt").select2({
            formatResult: formatExpenseTypes,
            formatSelection: formatExpenseTypes,
            data: dataDebtRegularExpense
        });
        $(".custom-multiselect-regularExpense-debt").select2("val", dataDebtRegularExpense[0].id);
        $(".custom-multiselect-regularExpense-debt").select2("enable", false);

        if(dataDebtList.paymentDeadline) context.find(".row:nth(0) > div:nth(0) .clockTimePaymentExpense").val(dataDebtList.paymentDeadline);
        if(dataDebtList.receptionDeadline) context.find(".row:nth(0) > div:nth(1) .clockTimePaymentExpense").val(dataDebtList.receptionDeadline);
        if(dataDebtList.beginDate) context.find(".row:nth(1) > div:nth(0) .clockTimePaymentExpense").val(dataDebtList.beginDate);
        if(dataDebtList.endDate) context.find(".row:nth(1) > div:nth(1) .clockTimePaymentExpense").val(dataDebtList.endDate);
        if(dataDebtList.receptionBeginDate) $(".simple-options-form-regularExpense").find(".row:nth(1) > div:nth(1) .clockTimePaymentExpense").val(dataDebtList.receptionBeginDate);
    }

    function handleCircle(dataCircleRegularExpense){
        $(".custom-multiselect-regularExpense-circle").select2({
            formatResult: formatResultCircles,
            formatSelection: formatSelectionCircles,
            data: dataCircleRegularExpense
        });
        $(".custom-multiselect-regularExpense-circle").select2("val",dataCircleRegularExpense[0].id);
        $(".custom-multiselect-regularExpense-circle").select2("enable", false);
    }

    $(".regular-expense-advanced-options").click(function(){
        var urlExpense = $(this).parents("form.upcoming-expense-form").children("input:nth(0)").val();
        var urlCircle = $(this).parents("form.upcoming-expense-form").children("input:nth(1)").val();
        idRegularExpense = $(this).parents("form.upcoming-expense-form").children("input:nth(2)").val();
        var context = $(".modal#regularExpenseCreateModal").find(".modal-body");
        $.post(urlExpense, function (data) {
            context.find(".row:nth(0) input:nth(1)").val(data.data.name);
            context.find(".row:nth(3) textarea").val(data.data.description);
            handleRegularExpenseType([{id:data.data.id, expenseTypeCssClass: data.data.expenseTypeCssClass, expenseTypeName: data.data.expenseTypeName}], data.data);
        });
        $.post(urlCircle, function (data) {
            handleCircle([{id: data.data.circleID, icon: data.data.circleIcon, name: data.data.circleName}]);
        });
        var price = $(this).parents("form.upcoming-expense-form").find(".form-actions input").val();
        context.find(".row:nth(2) input#valueRegularExpense").val(price);
        $(".select-list-users-expense").imagepicker({
            initialized: fillListExpense
        });
    });
});
