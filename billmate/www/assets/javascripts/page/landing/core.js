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
});
