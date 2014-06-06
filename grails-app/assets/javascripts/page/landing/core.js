$(document).ready(function () {
    /*** Retina Image Loader ***/
    if ($.fn.unveil) {
        $("img").unveil();
    }

    /**** Carousel for Testominals ****/
    if ($.fn.owlCarousel) {
        $("#testomonials").owlCarousel({
            singleItem: true
        });
    }

    /**** Mobile Side Menu ****/
    if ($.fn.waypoint) {
        var $head = $('#ha-header');
        $('.ha-waypoint').each(function (i) {
            var $el = $(this),
                animClassDown = $el.data('animateDown'),
                animClassUp = $el.data('animateUp');

            $el.waypoint(function (direction) {
                if (direction === 'down' && animClassDown) {
                    $head.attr('class', 'ha-header ' + animClassDown);
                } else if (direction === 'up' && animClassUp) {
                    $head.attr('class', 'ha-header ' + animClassUp);
                }
            }, {
                offset: '100%'
            });
        });
    }

    /**** Revolution Slider ****/
    if ($.fn.revolution) {
        tourSlider = $('#tourSlider').revolution({
            delay: 9000,
            startwidth: 1170,
            startheight: 550,
            hideThumbs: 10,
            fullWidth: "on",
            forceFullWidth: "on"
        });
    }

    /**** Subscribe Form Ajax ****/
    $("#subscribe-form").submit(function (event) {
        event.preventDefault();
        form = $(this)
        submit = form.find("button[type=submit]");
        alert = form.find(".alert");
        submit.prop("disabled", true);
        alert.animate({opacity: 0},'fast', function(){
            alert.removeClass("alert-danger").removeClass("alert-success");
            $.ajax({
                url: form.attr('action'),
                data: form.serialize(),
                type: "POST",
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    if (data.error === true)
                        alert.addClass("alert-danger");
                    else {
                        alert.addClass("alert-success");
                        form.find("input[name=email]").val("");
                    }
                    alert.empty().text(data.message);
                },
                error: function () {
                    alert.addClass("alert-danger");
                },
                complete: function(){
                    alert.removeClass("hidden").animate({opacity: 1}, 'fast', function(){
                        submit.prop("disabled", false);
                    });
                }
            });
        });
    });
});
