$( "#close-fast-notif" ).click(function() {
  $( "#dashboard-recent" ).hide( "slow" );
});

$(".notifs-right .lab").click(function(){

	var $act = $(this).parents("div[class^=comment-recent]").hide("slow");
	var $bool = $(this).parents("div[class^=comment-recent]").hasClass("first");
	var $count = $(this).parents("div[class^=comment-recent]").siblings().filter(function() {
			return $(this).css('display') != 'none';
		}).length;
	var $text = $(this).parents("div[class^=comment-text]").siblings("div[class^=comment-by]").children(".next-notifs-title").text();
	
	if($bool){
		$(this).parents("div[class^=comment-recent]").siblings().filter(function() {
			return $(this).css('display') != 'none';
		}).first().addClass("first");
		$(this).parents("div[class^=comment-recent]").siblings().filter(function() {
			return $(this).css('display') == 'none';
		}).removeClass("comment comment-recent");
		$(this).parents("div[class^=comment-recent]").removeClass("comment comment-recent");
	}

	$.growl.notice({ title: $text, message: "A despesa já foi recebida" });

	if($count == 0)
	{
		$(this).parents("div[class^=panel]").hide("slow");
	}

});

$("#closeMenu").click(function(){
	$("#main-menu-toggle").trigger("click");
});

$("#nameMenu").click(function(){
	if($(this).hasClass("text-wrap-break")){
		$(this).removeClass("text-wrap-break");
	}else{
		$(this).addClass("text-wrap-break");
	}
});