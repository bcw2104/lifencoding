
function hidePostList() {
	$("#postList").toggleClass("d-none");
	if ($("#postToggler").text() == "펼치기")
		$("#postToggler").text("접어두기")
	else
		$("#postToggler").text("펼치기")
}