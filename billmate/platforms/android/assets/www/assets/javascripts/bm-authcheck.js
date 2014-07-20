var checkAuth = function() {
    bm_token = window.localStorage.getItem("bm_token");
    if (!bm_token) {
        window.location.replace("login.html")
    } else {
        window.location.replace("dashboard.html")
    };
}
