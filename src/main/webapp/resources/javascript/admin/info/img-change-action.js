
function checkFileSize(fileSize){
	var maxSize = 3 * 1024 * 1024;

	if(fileSize > maxSize){
		return false;
	}
	else{
		return true;
	}
}

function uploadFile(target){
	var fileSize = target.files[0].size;
	var fileExtension = target.files[0].name.substr(target.files[0].name.lastIndexOf(".")+1);

	if(target.files[0] != undefined){
		if(fileExtension == "svg" || fileExtension == "bmp" || fileExtension == "png" || fileExtension=="jpg" || fileExtension=="jpeg"){
			if(checkFileSize(fileSize)){
				var fReader = new FileReader();
				fReader.readAsDataURL(target.files[0]);
				fReader.onloadend = function(event){

		    	var form = $("#imgForm")[0];
				var data = new FormData(form);

				$.ajax({
					url: "/admin/changeImg.do",
		            type: "post",
		            enctype: "multipart/form-data",
		            data: data,
		            processData: false,
		            contentType: false,
		            cache: false,
		            timeout: 600000,
		            success : function(data){
		            	alert("프로필 사진 변경이 완료되었습니다.")
		            	location.reload();
		            }
		        });
				$("#newimgInfo").text(target.files && target.files.length ? target.files[0].name : target.value.replace(/^C:\\fakepath\\/i, ''));
			}
			}else{
				alert("파일 크기가 3MB를 초과합니다.");
				$("#newimgInfo").text("파일 선택");
			}
		}else{
			alert("프로필 사진의 확장자는 svg,bmp,png,jpg,jpeg만 가능합니다.");
			$("#newimgInfo").text("파일 선택");
		}
	}else{
		$("#newimgInfo").text("파일 선택");
	}
}

$(document).ready(function() {

	$("#newimg").change(function() {
		uploadFile(this);
	});
});