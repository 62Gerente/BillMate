$(document).ready(function(){
    var dataDebt=[{id: 15, text: 'Eletricidade', faicon: 'fa fa-flash'},{id: 17, text: 'Compras', faicon: 'fa fa-shopping-cart'},{id: 19, text: 'Jantaradas', faicon: 'fa fa-cutlery'}];
    var setsDebt = ["15","17","19"];
    var dataUser=[{id: 8, text: 'Francisco Neves', faicon: 'https://avatars0.githubusercontent.com/u/1722352?s=140'},
                  {id: 4, text: 'André Santos', faicon: 'https://avatars2.githubusercontent.com/u/1471378?s=140'},
                  {id: 6, text: 'Pedro Leite', faicon: 'https://avatars2.githubusercontent.com/u/1723678?s=140'},
                  {id: 10, text: 'Ricardo Branco', faicon: 'https://avatars1.githubusercontent.com/u/1476127?s=460'}];
    var setsUser = ["4","6","8","10"];
    var unknownUser = "http://localhost:8080/BillMate/assets/default-user.png";
    var unknownDebt = "fa fa-tag";

    function formatIcon(state) {
        var icon
        if(state && state.faicon) icon = state.faicon
        else icon = unknownDebt
        return "<i class='" + icon + "'></i>&nbsp;" + state.text;
    }

    function formatImage(state) {
        var image
        if(state && state.faicon) image = state.faicon
        else image = unknownUser
        return "<div class='inline'><img src='" + image + "' style='width:20px; height:17px;'/></div>&nbsp;" + state.text;
    }

    /*$(".custom-multiselect").select2({
        //data:{ results: data},
        tags:[],
        //formatSelection: format,
        tokenSeparators: [",", " "]});*/

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

});