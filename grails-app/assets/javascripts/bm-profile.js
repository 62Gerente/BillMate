$.fn.editable.defaults.mode = 'inline';
$.fn.editable.defaults.ajaxOptions = {type: "POST"};
$.fn.editable.defaults.success = function(response, newValue) {
    if(response.error) return response.message;
};

$(document).ready(function() {
    $('#edit_name').editable();
    $('#edit_email').editable();
    $('#edit_phone_number').editable();
    $('#edit_password').editable();
});

$("#profile-photo-link").on('click', function(){
    $("#edit_photo").trigger('click');
});

$("#edit_photo").on('change', function(){
    $("#profile-photo-error").hide();

    var form = $("#updatePhotoForm");
    var formData = new FormData($('#updatePhotoForm')[0]);

    $.ajax({
        url: form.attr('action'),
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        type: "POST",
        dataType: 'json',
        beforeSend: function(){
            $("#profile-photo-link").prop("disabled", true);
        },
        success: function (data) {
            if (data.error === true)
                $("#profile-photo-error").text(data.message).show();
            else {
                $(".profile-photo-img").attr("src", data.photo_url);
            }
        },
        error: function () {
            $("#profile-photo-error").text("Oops! Something went wrong.");
        },
        complete: function(){
            $("#profile-photo-link").prop("disabled", false);
        }
    });
});