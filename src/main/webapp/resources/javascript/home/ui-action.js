
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

