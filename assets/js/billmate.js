$(document).ready(function() {		
	$(".home-next-facts-close").click(function(){
		$("#panel-next-notifs").hide();
	});

	$("#settings-userss").click(function(e){
		$("#user-options").trigger("click");
	});

	$("#teste-user").click(function(){
		$("#user-options").trigger("click");
	});
});