$(document).ready(function () {
    $(".history-select").select2();
    $(".history-select").on("change", function (e) {
        getFilteredHistory($("#history-filter-form"))
    });
    $("#history-load-more").on("click", function (e) {
        loadMoreFilteredHistory($("#history-filter-form"))
    });
});
