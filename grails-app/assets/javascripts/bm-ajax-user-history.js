function getFilteredHistory(form) {
    var list = $("#history-timeline");
    var btnLoadMore = $("#history-load-more");
    var pageInput = $("#history-filter-form input[name=page]")

    pageInput.val(1);

    $.ajax({
        url: form.attr('action'),
        data: form.serialize(),
        type: "GET",
        dataType: 'json',
        success: function (data) {
            if (data.error === true) {
                list.empty();
                showError(list, data.message);
                return;
            }

            var sizeInput = $("#history-filter-form input[name=size]")

            list.empty();

            var i = 0;
            for (action in data.actions) {
                listAction(list, data.actions[action])
                i++;
            }

            if (i < sizeInput.val())
                btnLoadMore.hide();
            else
                btnLoadMore.show();

        },
        error: function () {
            showError(list, "Oops, an error occurred.");
            list.empty();
        }
    });
}


function loadMoreFilteredHistory(form) {
    var list = $("#history-timeline");
    var btnLoadMore = $("#history-load-more");
    var pageInput = $("#history-filter-form input[name=page]");

    pageInput.val(parseInt(pageInput.val()) + 1);
    $.ajax({
        url: form.attr('action'),
        data: form.serialize(),
        type: "GET",
        dataType: 'json',
        success: function (data) {
            if (data.error === true) {
                btnLoadMore.hide();
                return;
            }

            var sizeInput = $("#history-filter-form input[name=size]")

            var i = 0;
            for (action in data.actions) {
                listAction(list, data.actions[action])
                i++;
            }

            if (i < sizeInput.val())
                btnLoadMore.hide();
            else
                btnLoadMore.show();

        },
        error: function () {
            pageInput.val(parseInt(pageInput.val()) - 1);
            showError(list, "Oops, an error occurred.");
            list.empty();
        }
    });
}
