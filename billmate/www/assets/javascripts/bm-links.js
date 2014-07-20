$(document).ready(function(){
    $('a[href="#"]').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        return false;
    });

    $(".close").on('click', function() {
        $(this).closest(".alert").stop().hide();
    });

    Number.prototype.format = function(n, x) {
        var re = '(\\d)(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\.' : '$') + ')';
        return this.toFixed(Math.max(0, ~~n)).replace(new RegExp(re, 'g'), '$1,');
    };

    $(".upcoming-regular-expense .regular-expense-advanced-options").click(function(){
        $("#btn-advanced-options-regularExpense").trigger("click");
    });
});
