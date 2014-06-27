$(document).ready(function() {

    var listElements = [];
    var id_circle = 0;
    var id_user = 0;
    var idExpenseType = 0;
    var hasCircles = false;
    var hasExpenseTypes = false;

    //Show DatePicker
    function displayDatePicker() {
        $('.clockTimePaymentExpense').datepicker({
            format: "dd/mm/yyyy",
            startView: 1,
            autoclose: true,
            todayHighlight: true
        })
    }

    $('.clockTimePaymentExpense').datepicker(displayDatePicker()).on(
        'show', function() {
            var modal = $('.clockTimePaymentExpense').closest('.modal');
            var datePicker = $('body').find('.datepicker');
            if(!modal.length) {
                $(datePicker).css('z-index', 'auto');
                return;
            }
            var zIndexModal = $(modal).css('z-index');
            $(datePicker).css('z-index', zIndexModal + 1);
        });


    //Next 3 functions format display results
    function formatExpenseTypes(state) {
        idExpenseType = state.id;
        return "<i class='" + state.cssClass + "'></i>&nbsp;" + $("<div>").html(state.name).text();
    }

    function formatResultCircles(state) {
        id_circle = state.id;
        return "<i class='" + state.icon + "'></i>&nbsp;" + $("<div>").html(state.name).text();
    }

    function formatSelectionCircles(state) {
        id_circle = state.id;
        fillList();
        return "<i class='" + state.icon + "'></i>&nbsp;" + $("<div>").html(state.name).text();
    }

    //Enable select2 plugin to expenses debts
    $(".custom-multiselect-expense-debt").select2({
        minimumInputLength: 1,
        formatResult: formatExpenseTypes,
        formatSelection: formatExpenseTypes,
        enable: false,
        ajax: {
            url: "/BillMate/expenseType/getExpensesIfContainsStringPassedByURL",
            dataType: 'json',
            data: function(term, page) {
                return {
                    q: term
                };
            },
            results: function(data, page) {
                hasExpenseTypes = true;
                return {
                    results: data.data
                };
            }
        }
    });
    $(".custom-multiselect-expense-debt").select2("enable",false);

    //Enable select2 plugin to expenses circles
    $(".custom-multiselect-expense-circle").select2({
        minimumInputLength: 1,
        formatResult: formatResultCircles,
        formatSelection: formatSelectionCircles,
        ajax: {
            url: "/BillMate/circle/getCirclesIfContainsStringPassedByURL",
            dataType: 'json',
            data: function(term, page) {
                return {
                    q: term,
                    id: $(this).parents(".simple-options-form-debt").children("input:nth(0)").val()
                };
            },
            results: function(data, page) {
                hasCircles = true;
                $(".custom-multiselect-expense-debt").select2("enable",true);
                return {
                    results: data.data
                };
            }
        }
    });

    //Call images plugin
    $(".select-list-users").imagepicker({
        initialized: fillList
    });

    //Fill list of Users
    function fillList(){
        if(id_user == 0) id_user = $(".simple-options-form-debt").children("input:nth(1)").val();
        if(id_circle != 0){
            var url = "/BillMate/circle/getFriendsOfaCircle?id_circle=" + id_circle;
            $.get(url, function (data) {
                listFriendsOfCircleAjaxRequestInList(data);
            } );
            $(".select-list-users").data('picker');
        }
    }

    //When input value debt, we need calculate new values and update in html, calling propagateChanges() and updateValues()
    $(".input-group.transparent #valueExpense").keyup(function(){
        propagateChanges();
    });

    function propagateChanges(){
        calculateValues();
        updateValues();
    }

    function updateValues(){
        var position = 0;
        listElements.forEach(function(entry){
            $(".photos-options-form-debt .thumbnails.image_picker_selector").find("li:nth(" + position + ") .thumbnail .name-inside-photo-modal-debt").text(entry.value.toFixed(2) + " €");
            position++;
        });
    }

    //Put images selected on start, and propagate changes
    function enableClickOnImagePicker(){
        $(".photos-options-form-debt .thumbnails.image_picker_selector").find("li").click(function(){
            var index = $(this).index();
            var click = $(".photos-options-form-debt .thumbnails.image_picker_selector").find("li:nth(" + index + ") .thumbnail");

            if(click.first().hasClass("selected")){
                listElements[index].selectable = true;
            }else{
                listElements[index].selectable = false;
            }
            propagateChanges();
        });
    }

    //Simply save actual data array and start process to listUsers
    function listFriendsOfCircleAjaxRequestInList(data){
        listElements = data.data;
        startProcedure()
    }

    //Core procedure to display list images
    function startProcedure(){
        calculateValues();
        addNewElementstoSelect();
        startImagePicker();
        enableSelectables();
        updateNamesInBoxes();
    }

    //Calculate actual values
    function calculateValues(){
        var totalValue = 0;
        var numberOfElements = 0;
        listElements.forEach(function(entry){ if(entry.selectable == true) numberOfElements++; });
        totalValue = $(".input-group.transparent").find("input#valueExpense").val();
        listElements.forEach(function(entry){
            entry.value = 0;
            if(entry.selectable == true)
                entry.value = totalValue / numberOfElements;
        });
    }

    //We need append new users according selected circles
    function addNewElementstoSelect(){
        var position = 0;
        $(".select-list-users ul").empty();
        $(".select-list-users").empty();
        listElements.forEach(function(entry){
            $(".photos-options-form-debt .select-list-users").append("<option data-img-src='" + entry.photo + "' value='" + entry.id + "'>" + entry.value.toFixed(2) +" €</option>")
        });
    }

    //Tell to plugin show labels and associate function enableClickOnImagePicker() when clicked
    function startImagePicker(){
        $(".select-list-users").imagepicker({
            show_label: true,
            clicked: enableClickOnImagePicker
        });
        addValuesToPhotos();
    }

    //Show values to each user
    function addValuesToPhotos(){
        var position = 0;
        listElements.forEach(function(entry){
            $(".photos-options-form-debt .thumbnails.image_picker_selector li:nth(" + position + ") .thumbnail").append("<div class='name-inside-photo-modal-debt'>" + entry.value.toFixed(2) + " €</div><span class='tick-green'><i class='fa fa-circle fa-2x text-info'></i></span>");
            position++;
        });
    }

    //Trigger click to display users selected
    function enableSelectables(){
        var position = 0;
        listElements.forEach(function(entry){
            if(entry.selectable == true)
                $(".photos-options-form-debt .thumbnails.image_picker_selector").find("li:nth(" + position + ") .thumbnail").trigger("click");
            position++;
        });
    }

    //Plugin add paragraph, but whe need replace p by h6
    function updateNamesInBoxes(){
        var position = 0;
        listElements.forEach(function(entry){
            var element = $(".photos-options-form-debt .thumbnails.image_picker_selector li:nth(" + position + ") .thumbnail");
            element.find("p").replaceWith(function(){
                return $("<h6/>", {html: entry.name});
            });
            element.find("h6").addClass("text-center semi-bold");
            element.find("h6").detach().appendTo(element.parent());
            position++;
        });
    }

    // When submit form
    $(".btn-submit-new-debt").click(function(){
        var hasErrors = false;
        var status = $(this).closest(".modal-dialog").find("div:nth(0)");
        var parent = $(this).closest(".modal-content").find(".simple-options-form-debt");

        var name = parent.find("div.row:nth(0) input").val();
        var value = parent.find("div.row:nth(2) input.value-debt").val();
        var description = parent.find("div.row:nth(3) textarea").val();

        if(name == ""){ hasErrors = true; doAlertInput(parent.find("div.row:nth(0)"),parent.find("div.row:nth(0) input"),"error-control"); }
        if(!hasCircles){ hasErrors = true; doAlertSelect(parent.find("div.row:nth(1) .select2-container"),"select2-container-error",".custom-multiselect-expense-circle"); }
        if(!hasExpenseTypes){ hasErrors = true; doAlertSelect(parent.find("div.row:nth(2) .select2-container"),"select2-container-error",".custom-multiselect-expense-debt"); }
        if(value == "") { hasErrors = true; doAlertInput(parent.find("div.row:nth(2) .input-group"),parent.find("div.row:nth(2) .input-group input"),"error-control"); }

        var dates = $(this).parents(".modal-footer").siblings(".modal-body").find(".advanced-options-form-debt");
        var paymentDeadline = dates.find("input:nth(0)").val();
        var receptionDeadline = dates.find("input:nth(1)").val();
        var beginDate = dates.find("input:nth(2)").val();
        var endDate = dates.find("input:nth(3)").val();
        var paymentDate = dates.find("input:nth(4)").val();
        var receptionDate = dates.find("input:nth(5)").val();

        var listIDsUsers = [];
        var listValuesUsers = [];
        listElements.forEach(function(entry){
            listIDsUsers.push(entry.id);
            listValuesUsers.push(entry.value);
        });

        var formData = {name: name, idCircle: id_circle, idExpenseType: idExpenseType, value: value, description: description,
                        idUser: id_user, listOfFriends: listIDsUsers, listValuesUsers: listValuesUsers, paymentDeadline: paymentDeadline,
                        receptionDeadline: receptionDeadline, beginDate: beginDate, endDate: endDate, paymentDate: paymentDate,
                        receptionDate: receptionDate, numberSelected: getNumberOfSelected()};

        if(!hasErrors){
            $.ajax({
                url: "/BillMate/expense/create",
                data: formData,
                type: "POST",
                dataType: 'json',
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
    $(".btn-options-form-debt").click(function(){
        $(this).parents(".modal-footer").siblings(".modal-body").find(".simple-options-form-debt").slideToggle();
        $(this).parents(".modal-footer").siblings(".modal-body").find(".advanced-options-form-debt").slideToggle();
    });

    function getNumberOfSelected(){
        var numberSelected = 0;
        listElements.forEach(function(entry){
            if(entry.selectable == true)
                numberSelected++;
        });
        return numberSelected;
    }
});
