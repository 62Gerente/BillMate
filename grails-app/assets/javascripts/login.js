$(document).ready(function() {
	$(".lazy").lazyload({
      effect : "fadeIn"
   });

    $('#login-form').validate({
        errorElement: 'span',
        errorClass: 'error',
        focusInvalid: false,
        ignore: "",
        rules: {
            email: {
                minlength: 2,
                required: true,
                email: true
            },
            password: {
                minlength: 5,
                required: true
            }
        },

        invalidHandler: function (event, validator) {
            //display error alert on form submit
        },

        errorPlacement: function (error, element) { // render error placement for each input type
            var icon = $(element).parent('.input').children('i');
            var parent = $(element).parent('.input');
            parent.removeClass('success-control').addClass('error-control');
        },

        highlight: function (element) { // hightlight error inputs
            var parent = $(element).parent();
            parent.removeClass('success-control').addClass('error-control');
        },

        unhighlight: function (element) { // revert the change done by hightlight

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