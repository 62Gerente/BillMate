$(document).ready(function(){
    var unknownDebt = "fa fa-tag";

    function formatIcon(state) {
        var icon;
        if(state && state.faicon) icon = state.faicon;
        else icon = unknownDebt;
        return "<i class='" + icon + "'></i>&nbsp;" + $("<div>").html(state.text).text();
    }

    function formatImage(state) {
        var image;
        if(state && state.faicon) image = state.faicon;
        else image = unknownUser;
        return "<div class='inline p-r-5'><img src='" + image + "' style='width:20px; height:17px;'/></div>" + state.text.split('###')[0];
    }

    $(".custom-multiselect-house-debt").select2({
        createSearchChoice:function(term, data){
            if($(data).filter(function() { return this.text.localeCompare(term)===0; }).length===0){
                return{
                    id:term,
                    text:term
                };
            }
        },
        multiple: true,
        formatResult: formatIcon,
        formatSelection: formatIcon,
        data:dataDebt
    });
    $(".custom-multiselect-house-debt").select2("val", setsDebt);

    $(".custom-multiselect-house-user").select2({
        createSearchChoice:function(term, data){},
        formatNoMatches: function () {return ""},
        multiple: true,
        formatResult: formatImage,
        formatSelection: formatImage,
        data:dataUser
    });
    $(".custom-multiselect-house-user").select2("val", setsUser);

    $("#houseCreateModal").find(".close").click(function(){
        $(this).parent().css('display','none');
    });

    $(".houseForm :submit").on('click', function (event) {
        event.preventDefault();
        var form = $(this).closest(".houseForm");
        var link = form.attr('action');
        var data = form.serialize();
        var context = form.parents(".modal-dialog");

        form.validate({
            focusInvalid: false,
            ignore: "",
            rules: {
                houseName: {
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
                var parent = $(element).parent('.input');
                parent.removeClass('error-control').addClass('success-control');
            }
        });

        if(form.valid()) {
            $.ajax({
                url: link,
                type: "POST",
                dataType: 'json',
                data: data,
                beforeSend: function () {
                    $("body").block({ message: null });
                },
                complete: function () {
                    $("body").unblock();
                },
                success: function (data) {
                    context.children("div").first().show();
                    context.children("div").first().removeClass();
                    context.children("div").first().addClass(data.class);
                    context.children("div").first().find("div").text(data.code);
                    if (data.error === false) {
                        form.find(".houseName").empty();
                        form.find(".houseDescription").empty();
                        form.delay(2000);
                        window.location.reload();
                    }
                },
                error: function (data) {
                    context.children("div").first().show();
                    context.children("div").first().removeClass();
                    context.children("div").first().addClass(data.class);
                    context.find("div div").text(data.code);
                }
            });
        }
    });

    $(".formNewReferredUser").validate({
        ignore: '',
        messages: {
            nameReferred: {
                required: true
            },
            emailReferred: {
                required: true
            }
        }
    });

    $(".addNewReferredUser").click(function(){
        var email = $(this).closest(".add-more-friends").find("div:nth(0) :input").val();
        var name = email.split("@")[0];
        var validEmail = isValidEmailAddress(email);

        if(email == "" || !validEmail){
                $(this).parent().siblings("div:nth(0)").children("input").removeClass().addClass("error");
        }else{
            setsUser = [];
            var list = $(this).closest(".modal-body").children("div:nth(2)").find("li.select2-search-choice div:not('.inline')").map(function() { return $(this).text(); });

            var sizeList = list.length;
            var sizeDataUser = dataUser.length;

            for (i = 0; i < sizeList; i++) {
                for (j = 0; j < sizeDataUser; j++) {
                    var userAlreadyRegisted = dataUser[j].text.split("###")[0];
                    if(list[i] == userAlreadyRegisted){
                        setsUser[setsUser.length] = dataUser[j].id;
                    }
                }
            }
            dataUser[dataUser.length] = {id: name+"###"+email, text: name+"###"+email, faicon: unknownUser};
            setsUser[setsUser.length] = name+"###"+email;
            $(".custom-multiselect-house-user").select2({
                createSearchChoice:function(term, data){},
                formatNoMatches: function () {return ""},
                multiple: true,
                formatResult: formatImage,
                formatSelection: formatImage,
                data:dataUser
            });
            $(".custom-multiselect-house-user").select2("val", setsUser);
            $(this).closest(".add-more-friends").find("div:nth(0) :input").val("");
        }
    });

    $('.add-more-friends input.error').live('input',function(){
        $(this).removeClass().addClass('success');
    });

    $(".expand-more-add-friends").click(function(){
        $(this).parent().siblings(".add-more-friends").toggle();
    });

    function isValidEmailAddress(emailAddress) {
        return validateEmail(emailAddress);
    };

    function validateEmail(email)
    {
        return email.match( /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/);
    }
});
