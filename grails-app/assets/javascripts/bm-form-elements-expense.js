var totalValue = 0;
var numberOfElements = 0;
var parcialValue = [];
var listElements = [];
var listSelectables = [];
var id_circle = 13;
var id_user = 3;
var idExpenseType = 0;
var unknownUser = "/BillMate/assets/default-user.png";
var hasCircles = false;
var hasExpenseTypes = false;
var hasUsers = false;

$(document).ready(function() {

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


    function formatExpenseTypes(state) {
        idExpenseType = state.id;
        return "<i class='" + state.cssClass + "'></i>&nbsp;" + $("<div>").html(state.name).text();
    }

    function formatResultCircles(state) {
        idExpenseType = state.id;
        return "<i class='" + state.icon + "'></i>&nbsp;" + $("<div>").html(state.name).text();
    }

    function formatSelectionCircles(state) {
        id_circle = state.id;
        listElements = [];
        fillList();
        return "<i class='" + state.icon + "'></i>&nbsp;" + $("<div>").html(state.name).text();
    }

    $(".custom-multiselect-expense-debt").select2({
        minimumInputLength: 1,
        formatResult: formatExpenseTypes,
        formatSelection: formatExpenseTypes,
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
                    id: $(this).parents(".simple-options-form-debt").children("input").val()
                };
            },
            results: function(data, page) {
                hasCircles = true;
                return {
                    results: data.data
                };
            }
        }
    });


    function calculateValues(){
        numberOfElements = 0;
        listElements.forEach(function(entry){ if(entry.selectable == true) numberOfElements++; });
        totalValue = $(".input-group.transparent").find("input").val();
        listElements.forEach(function(entry){
            entry.value = 0;
            if(entry.selectable == true)
                entry.value = totalValue / numberOfElements;
        });
    }

    function addNewElementstoSelect(){
        var position = 0;
        $(".select-list-users").empty();
        listElements.forEach(function(entry){
            $(".select-list-users").append("<option data-img-src='" + entry.photo + "' value='" + entry.id + "'>" + entry.value.toFixed(2) +" €</option>")
        });
    }

    function enableSelectables(){
        var position = 0;
        listElements.forEach(function(entry){
            if(entry.selectable == true)
                $(".thumbnails.image_picker_selector").find("li:nth(" + position + ") .thumbnail").trigger("click");
            position++;
        });
    }

    function startImagePicker(){
        $("select").imagepicker({
            show_label: true,
            clicked: enableClickOnImagePicker
        });
        addValuesToPhotos();
    }

    function addValuesToPhotos(){
        var position = 0;
        listElements.forEach(function(entry){
            $(".thumbnails.image_picker_selector li:nth(" + position + ") .thumbnail").append("<div class='name-inside-photo-modal-debt'>" + entry.value.toFixed(2) + " €</div><span class='tick-green'><i class='fa fa-circle fa-2x text-info'></i></span>");
            position++;
        });
    }

    function updateNamesInBoxes(){
        var position = 0;
        listElements.forEach(function(entry){
            var element = $(".thumbnails.image_picker_selector li:nth(" + position + ") .thumbnail");
            element.find("p").replaceWith(function(){
                return $("<h6/>", {html: entry.name});
            });
            element.find("h6").addClass("text-center semi-bold");
            element.find("h6").detach().appendTo(element.parent());
            position++;
        });
    }

    function enableClickOnImagePicker(){
        $(".thumbnails.image_picker_selector").find("li").click(function(){
            var index = $(this).index();
            var click = $(".thumbnails.image_picker_selector").find("li:nth(" + index + ") .thumbnail");

            if(click.hasClass("selected")){
                listElements[index].selectable = true;
            }else{
                listElements[index].selectable = false;
            }
            propagateChanges();
        });
    }

    function startProcedure(){
        calculateValues();
        addNewElementstoSelect();
        startImagePicker();
        enableSelectables();
        updateNamesInBoxes();
    }

    function listFriendsOfCircleAjaxRequestInList(data){
        if(listElements.length == 0)
            listElements = data.data;
        startProcedure()
    }

    function fillList(){
        var url = "/BillMate/circle/getFriendsOfaCircleExceptId?id_circle=" + id_circle +"&id_user=" + id_user;
        $.get(url, function (data) {
            listFriendsOfCircleAjaxRequestInList(data);
        } );
        $(".select-list-users").data('picker');
    }

    $(".select-list-users").imagepicker({
        initialized: fillList
    });

    function updateValues(){
        var position = 0;
        listElements.forEach(function(entry){
            $(".thumbnails.image_picker_selector").find("li:nth(" + position + ") .thumbnail .name-inside-photo-modal-debt").text(entry.value.toFixed(2) + " €");
            position++;
        });
    }

    function propagateChanges(){
        calculateValues();
        updateValues();
    }

    $(".input-group.transparent .value-debt").keyup(function(){
        propagateChanges();
    });

    $(".btn-options-form-debt").click(function(){
        $(this).parents(".modal-footer").siblings(".modal-body").find(".simple-options-form-debt").slideToggle();
        $(this).parents(".modal-footer").siblings(".modal-body").find(".advanced-options-form-debt").slideToggle();
    });

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

        var listIDsUsers = [];
        var listValuesUsers = [];
        listElements.forEach(function(entry){
            listIDsUsers.push(entry.id);
            listValuesUsers.push(entry.value);
        });

        var formData = {name: name, idCircle: id_circle, idExpenseType: idExpenseType, value: value, description: description, idUser: id_user, listOfFriends: listIDsUsers, listValuesUsers: listValuesUsers};

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

    function doAlertInput(selector, selectorEvent, classe){
        selector.addClass(classe);
        selectorEvent.change(function(){
            selector.removeClass(classe);
        });
    }

    function doAlertSelect(selector, classe, selectorStart){
        selector.addClass(classe);
        $(selectorStart).on("select2-selecting",function(){
            selector.removeClass(classe);
        });
    }

    $("ul.thumbnails.image_picker_selector li .thumbnail").click(function(){

    });
});
