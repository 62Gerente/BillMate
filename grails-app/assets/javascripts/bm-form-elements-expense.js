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

    $(".select-list-users").imagepicker({show_label: true});
    $(".select-list-users").data('picker');

});
