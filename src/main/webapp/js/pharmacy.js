function publishing(itemId){

	var app = window.location;
	var url = app + "/publish";
	$.ajax({
		type: "GET",
		dataType: "text",
		url: url,
		data: "id=" + itemId,
		success: function(response){
			$("#published_item_"+itemId).html(response);
		}
	});
}

//datePicker
$(function() {
	var locale = ""+djConfig.locale;
	var dateFormatPattern = $( "#date_format_locale" ).attr("value");
	
	$( "#date_id" ).datepicker({language: locale, format: dateFormatPattern});
});