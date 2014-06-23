var totalValue = 0;
var numberOfElements = 0;
var parcialValue = [];
var listElements = [];
var listSelectables = [];
var id_circle = 13;
var id_user = 3;
var unknownUser = "/BillMate/assets/default-user.png";

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
        return "<i class='" + state.cssClass + "'></i>&nbsp;" + $("<div>").html(state.name).text();
    }

    function formatCircles(state) {
        return "<i class='" + state.description + "'></i>&nbsp;" + $("<div>").html(state.name).text();
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
                return {
                    results: data.data
                };
            }
        }
    });

    $(".custom-multiselect-expense-circle").select2({
        minimumInputLength: 1,
        formatResult: formatCircles,
        formatSelection: formatCircles,
        ajax: {
            url: "/BillMate/circle/getCirclesIfContainsStringPassedByURL",
            dataType: 'json',
            data: function(term, page) {
                return {
                    q: term,
                    id: $(this).siblings("input").val()
                };
            },
            results: function(data, page) {
                return {
                    results: data.data
                };
            }
        }
    });

    function calculateValues(){
        numberOfElements = 0;
        listElements.forEach(function(entry){ if(entry.selectable == true)
            numberOfElements++; });
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
                $(".thumbnails.image_picker_selector").find("li:nth(" + position + ") .thumbnail").trigger("click")
            position++;
        });
    }

    function startImagePicker(){
        $("select").imagepicker({
            show_label: true,
            clicked: enableClickOnImagePicker
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
    }

    function listFriendsOfCircleAjaxRequestInList(data){
        if(listElements.length == 0)
            listElements = data.data;
        startProcedure()
    }

    function fillList(){
        var url = "/BillMate/circle/getFriendsOfaCircleExceptId?id_circle=13&id_user=3";
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
            $(".thumbnails.image_picker_selector").find("li:nth(" + position + ") .thumbnail p").text(entry.value.toFixed(2) + " €");
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

    /*$("li .thumbnail").click(function(){
        var element = $(this).find("li");
        if(element.length){
            alert(element.index())

        $(this).hide();
    });}*/

});
