$(document).ready(function(){
    $('a[href="#"]').on('click', function(e) {
        e.preventDefault();
    });

    $(".close").on('click', function() {
        $(this).closest(".alert").stop().hide();
    });

    Number.prototype.format = function(n, x) {
        var re = '(\\d)(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\.' : '$') + ')';
        return this.toFixed(Math.max(0, ~~n)).replace(new RegExp(re, 'g'), '$1,');
    };
});
