
function hidePostList() {
	$("#postList").toggleClass("d-none");
	if ($("#postToggler").text() == "펼치기")
		$("#postToggler").text("접어두기")
	else
		$("#postToggler").text("펼치기")
}

function getProfileImage(){
	$.ajax({
		url: "/admin/profileImg.do",
		type:"get",
		success: function(path) {
			$("#profileImg").attr("src",path);
		}
	});
}

$(document).ready(function() {
	getProfileImage();
});