$(document).ready(function() {
    $('#login-form').validate({
        errorElement: 'span',
        errorClass: 'error',
        focusInvalid: false,
        ignore: "",
        rules: {
            email: {
                required: true,
                email: true
            },
            password: {
                minlength: 5,
                required: true
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
        },

        submitHandler: function (form) {
            form.submit();
        }
    });

    $('#register-form').validate({
        errorElement: 'span',
        errorClass: 'error',
        focusInvalid: false,
        ignore: "",
        rules: {
            name: {
                required: true,
                minlength: 3
            },
            email: {
                required: true,
                email: true
            },
            password: {
                minlength: 5,
                required: true
            },
            c_password: {
                minlength: 5,
                required: true,
                equalTo: "#password"
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
        },

        submitHandler: function (form) {
            form.submit();
        }
    });
});
