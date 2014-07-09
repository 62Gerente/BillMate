$(document).ready(function() {

    bm_token = window.localStorage.getItem("bm_token");
    if (bm_token) {
        window.location.replace("sucesso.html")
    };

    $("#login-form :submit").on('click', function(event) {
        event.preventDefault();
        var form = $(this).closest("#login-form");
        var link = host + "/api/v1/login";
        var data = {
            email: $("#email").val(),
            password: $("#password").val()
        }

        form.validate({
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

            invalidHandler: function(event, validator) {},

            errorPlacement: function(error, element) {
                var parent = $(element).parent('.input');
                parent.removeClass('success-control').addClass('error-control');
            },

            highlight: function(element) {
                var parent = $(element).parent();
                parent.removeClass('success-control').addClass('error-control');
            },

            unhighlight: function(element) {
                var parent = $(element).parent();
                parent.removeClass('error-control').addClass('success-control');
            },

            success: function(label, element) {
                var icon = $(element).parent('.input').children('i');
                var parent = $(element).parent('.input');
                parent.removeClass('error-control').addClass('success-control');
            }
        });

        if (form.valid()) {
            $.ajax({
                url: link,
                type: "POST",
                dataType: 'json',
                data: data,
                beforeSend: function() {
                    $("body").block({
                        message: null
                    });
                },
                complete: function() {
                    $("body").unblock();
                },
                success: function(data) {
                    if (data.error === false) {
                        $("#password").parent().removeClass('success-control').addClass('error-control');
                        $("#password").val("");
                    } else {
                        window.localStorage.clear();
                        window.localStorage.setItem("bm_token", data.token);
                        window.location.replace("sucesso.html");
                    }
                },
                error: function(data) {
                    context.children("div").first().show();
                    context.children("div").first().removeClass();
                    context.children("div").first().addClass(data.class);
                    context.find("div div").text(data.code);
                }
            });
        }
    });



    $("#register-form :submit").on('click', function(event) {
        event.preventDefault();
        var form = $(this).closest("#register-form");
        var link = host + "/api/v1/register";
        var data = {
            name: $("#name").val(),
            email: $("#email").val(),
            password: $("#password").val(),
            c_password: $("#c_password").val()
        }

        form.validate({
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

            invalidHandler: function(event, validator) {},

            errorPlacement: function(error, element) {
                var parent = $(element).parent('.input');
                parent.removeClass('success-control').addClass('error-control');
            },

            highlight: function(element) {
                var parent = $(element).parent();
                parent.removeClass('success-control').addClass('error-control');
            },

            unhighlight: function(element) {
                var parent = $(element).parent();
                parent.removeClass('error-control').addClass('success-control');
            },

            success: function(label, element) {
                var icon = $(element).parent('.input').children('i');
                var parent = $(element).parent('.input');
                parent.removeClass('error-control').addClass('success-control');
            }
        });

        if (form.valid()) {
            $.ajax({
                url: link,
                type: "POST",
                dataType: 'json',
                data: data,
                beforeSend: function() {
                    $("body").block({
                        message: null
                    });
                },
                complete: function() {
                    $("body").unblock();
                },
                success: function(data) {
                    console.log(data);
                    if (data.error === false) {
                        $("#password").parent().removeClass('success-control').addClass('error-control');
                        $("#password").val("");
                        $("#c_password").parent().removeClass('success-control').addClass('error-control');
                        $("#c_password").val("");
                    } else {
                        window.localStorage.clear();
                        window.localStorage.setItem("bm_token", data.token);
                        window.location.replace("sucesso.html");
                    }
                },
                error: function(data) {

                }
            });
        }
    });


});
