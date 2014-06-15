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
        return "<div class='inline'><img src='" + image + "' style='width:20px; height:17px;'/></div>&nbsp;" + state.text;
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
        createSearchChoice:function(term, data){
            if($(data).filter(function() { return this.text.localeCompare(term)===0; }).length===0){
                return{
                    id:term,
                    text:term
                };
            }
        },
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
        var context = form.siblings(".modal-header");
        $.ajax({
            url: link,
            type: "POST",
            dataType: 'json',
            data: data,
            beforeSend: function(){
                $("body").block({ message: null });
            },
            complete: function(){
                $("body").unblock();
            },
            success: function(data){
                    context.children("div").show();
                    context.children("div").removeClass();
                    context.children("div").addClass(data.class);
                    context.find("div div").text(data.code);
                    if(data.error === false) {
                        form.find(".houseName").val("");
                        form.find(".houseDescription").val("");
                        form.delay( 2000 );
                        window.location.reload();
                    }
            },
            error: function(data){
                context.children("div").show();
                context.children("div").removeClass();
                context.children("div").addClass(data.class);
                context.find("div div").text(data.code);
            }
        });
    });
});