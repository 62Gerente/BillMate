$(document).ready(function(){
    var unknownDebt = "fa fa-tag";

    function formatIcon(state) {
        var icon;
        if(state && state.faicon) icon = state.faicon;
        else icon = unknownDebt;
        return "<i class='" + icon + "'></i>&nbsp;" + state.text;
    }

    function formatImage(state) {
        var image;
        if(state && state.faicon) image = state.faicon;
        else image = unknownUser;
        return "<div class='inline p-r-5'><img src='" + image + "' style='width:20px; height:17px;'/></div>" + state.text.split('###')[0];
    }

    function formatImageSelection(state) {
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
        formatSelection: formatImageSelection,
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
                    context.children("div:nth(0)").show();
                    context.children("div:nth(0)").removeClass();
                    context.children("div:nth(0)").addClass(data.class);
                    context.children("div:nth(0)").find("div").text(data.code);
                    if (data.error === false) {
                        form.find(".houseName").val("");
                        form.find(".houseDescription").val("");
                        form.delay(2000);
                        window.location.reload();
                    }
                },
                error: function (data) {
                    context.children("div:nth(0)").show();
                    context.children("div:nth(0)").removeClass();
                    context.children("div:nth(0)").addClass(data.class);
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
        var name = $(this).closest(".add-more-friends").find("div:nth(0) :input").val();
        var email = $(this).closest(".add-more-friends").find("div:nth(1) :input").val();
        //$("<li class='select2-search-choice'>    <div><div class='inline p-r-5'><img src='" + unknownUser + "'style='width:20px; height:17px;'></div>" + name + "</div>    <a href='#' onclick='return false;' class='select2-search-choice-close' tabindex='-1'></a></li>").insertAfter($(".addNewReferredUser").closest(".modal-body").children("div:nth(2)").find("li.select2-search-choice").last());
        var validEmail = isValidEmailAddress(email);

        if(name == "" || email == "" || !validEmail){
            if(name == ""){
                $(this).parent().siblings("div:nth(0)").children("input").removeClass().addClass("error");
            }
            if(email == "" || !validEmail){
                $(this).parent().siblings("div:nth(1)").children("input").removeClass().addClass("error");
            }
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
                formatSelection: formatImageSelection,
                data:dataUser
            });
            $(".custom-multiselect-house-user").select2("val", setsUser);
            $(this).closest(".add-more-friends").find("div:nth(0) :input").val("");
            $(this).closest(".add-more-friends").find("div:nth(1) :input").val("");
        }
    });

    $('.add-more-friends input.error').live('input',function(){
        $(this).removeClass().addClass('success');
    });

    $(".expand-more-add-friends").click(function(){
        $(this).parent().siblings(".add-more-friends").toggle();
    });

    function isValidEmailAddress(emailAddress) {
        var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
        return pattern.test(emailAddress);
    };
});