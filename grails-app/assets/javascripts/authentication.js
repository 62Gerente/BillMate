var init = [];

// Show/Hide password reset form on click
init.push(function () {
    $('#forgot-password').click(function () {
        $('#password-reset-form').fadeIn(400);
        return false;
    });
    $('#password-reset-form .close').click(function () {
        $('#password-reset-form').fadeOut(400);
        return false;
    });
});

// Setup Sign In form validation
init.push(function () {
    $("#signin-form_id").validate({
        focusInvalid: true,
        errorPlacement: function () {}
    });

    // Validate username
    $("#email").rules("add", {
        required: true,
        email: true
    });

    // Validate password
    $("#password").rules("add", {
        required: true,
        minlength: 6
    });
});

// Setup Password Reset form validation
init.push(function () {
    $("#password-reset-form_id").validate({
        focusInvalid: true,
        errorPlacement: function () {}
    });

    // Validate email
    $("#reset_email").rules("add", {
        required: true,
        email: true
    });
});

window.PixelAdmin.start(init);
