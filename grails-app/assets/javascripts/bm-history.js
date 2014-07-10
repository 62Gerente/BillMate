function listAction(list, action){
    var actionType = '<i class="'+ action.icon +' fa-1-2x m-t-10"></i>';

    var bottom = "";

    var bottomWrapper = "";

    var descriptionTitle = '<div class="muted m-t-15">' +
        '<i class="fa '+ action.circleCssClass +'"></i> '+ action.circle +' - '+ action.date +
        '</div>';

    switch(action.type){
        case "signUp":
            descriptionTitle = '<div class="muted m-t-15">' +
                action.date +
                '</div>';
            actionType = '<div class="user-profile">' +
                '<img src="' + action.icon + '.svg" alt="" onerror="this.src=' + action.icon + '.png" height="35" width="35" class="m-t-5">' +
                '</div>';
            bottom = "";
            break;
        case "addCollective":
            for(user in action.circleUsers){
                members += '<li>' +
                    '<div class="profile-pic">' +
                    '<img width="35" height="35" src="'+ action.circleUsers[user] +'" alt="">' +
                    '</div>' +
                    '</li>';
            }
            bottomWrapper = '<ul class="my-friends no-margin">' +
                members +
                '</ul>';
            break;
        case "addPaymentExpense":
        case "receivePaymentExpense":
            bottomWrapper = '<span class="muted dark-text">' +
                                action.expenseText +
                            ':</span> ' +
                            '<span class="label label-success">' +
                                parseFloat(action.expenseTotal).format(2) +
                            ' €</span>';
            break;
        default:
            bottom = "";

    }

    if(bottomWrapper.length > 0){
        bottom = '<div class="clearfix"></div>' +
            '<div class="tiles grey p-t-10 p-b-10 p-l-20 no-margin">' +
            bottomWrapper +
            '<div class="clearfix"></div>' +
            '</div>';
    }

    var icon =  '<div class="cbp_tmicon ' + action.cssClass + '">' +
        actionType +
        '</div>';

    var body = '<div class="cbp_tmlabel">' +
        '<div class="p-t-5 p-l-30 p-r-20 p-b-10 xs-p-r-10 xs-p-l-10 xs-p-t-5">' +
        descriptionTitle +
        '<h4 class="dark-text">' +
        action.text +
        '</h4>' +
        '</div>' +
        bottom +
        '</div>';

    list.append('<li>' + icon + body + '</li>');
}

function showError(list, error){
    list.append('<li><div style="display:table; margin: 0 auto"><h3>' + error + '</h3></div>');
}

//= require bm-ajax-user-history.js
//= require bm-form-elements-history.js
