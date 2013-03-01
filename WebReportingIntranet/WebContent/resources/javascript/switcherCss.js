$(document).ready(function() { 
	$(".layoutUnit").removeClass("ui-widget-content ui-corner-all");
	$(".layoutUnit").children("div").removeClass("ui-widget-content ui-corner-all");
	$(".layoutUnit-HeaderTop").children("div").removeClass("ui-widget-content ui-corner-all");
	$("#locationsDataTableId > div").removeClass("ui-widget-header");
	// $(PrimeFaces.escapeClientId('tabView:locationsDataTableId') + " > div").removeClass("ui-widget-header");
	// $("#locationsDataTableId > div > div").removeClass("ui-datatable-scrollable-header-box");
	// $("#locationsDataTableId > div > div").addClass("dataTableFixed");
	$("#locationsDataTableId > div > div > table > tfoot > tr > td").removeClass("ui-state-default");
	$("#locationsDataTableId > div > div > table > tfoot > tr > td").addClass("dataTableTfoot");
	// $(PrimeFaces.escapeClientId('tabView:locationsDataTableId') + " > div > div > table > tfoot > tr > td").removeClass("ui-state-default");
	// $(PrimeFaces.escapeClientId('tabView:locationsDataTableId') + " > div > div > table > tfoot > tr > td").addClass("dataTableTfoot");
	
	// $("#summaryPie").children("canvas").find(".jqplot-grid-canvas").css({'display': 'none'});
	// $("#linear").children("canvas").find(".jqplot-grid-canvas").css({'opacity': '0.3'});
	
});

function switchThemeColor(themeColor) {

//	alert(themeColor);
	
	$("#hiddenThemeColor").val(themeColor);
	$("#hiddenThemeColor").click();
	
	window.location.reload(true);
	//$(link).attr({rel:"stylesheet"}).reload(true);
	
	return false;
	
}