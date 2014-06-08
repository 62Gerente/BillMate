$(document).ready(function() {
    $('.upcoming-expense-form').validate({
        errorElement: 'span',
        errorClass: 'error',
        focusInvalid: false,
        ignore: "",
        rules: {
            value: {
                required: true,
                blank: false,
                number: true
            }
        },

        invalidHandler: function (event, validator) {
        },

        errorPlacement: function (error, element) {
            var parent = $(element).parent('.input');
            parent.removeClass('success-control').addClass('error-control');
        },

        highlight: function (element) {
            var parent = $(element).parent();
            parent.removeClass('success-control').addClass('error-control');
        },

        unhighlight: function (element) {
            var parent = $(element).parent();
            parent.removeClass('error-control').addClass('success-control');
        },

        success: function (label, element) {
            var icon = $(element).parent('.input').children('i');
            var parent = $(element).parent('.input');
            parent.removeClass('error-control').addClass('success-control');
        }
    });
});
