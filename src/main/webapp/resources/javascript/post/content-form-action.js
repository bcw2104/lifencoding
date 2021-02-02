
var selectValid;
var titleValid;
var contentValid;

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
				$("#thumbnailInfo").text(target.files && target.files.length ? target.files[0].name : target.value.replace(/^C:\\fakepath\\/i, ''));
			}else{
				alert("파일 크기가 3MB를 초과합니다.");
				$("#thumbnailInfo").text("파일 선택");
			}
		}else{
			alert("프로필 사진의 확장자는 svg,bmp,png,jpg,jpeg만 가능합니다.");
			$("#thumbnailInfo").text("파일 선택");
		}
	}else{
		$("#thumbnailInfo").text("파일 선택");
	}
}

$(document).ready(function() {
	$("#thumbnail").change(function() {
		uploadFile(this);
	});

	if($("#page").val() == "create"){
		selectValid = 0;
		titleValid = 0;
		contentValid = 0;
	}
	else{
		selectValid = 1;
		titleValid = 1;
		contentValid = 1;
	}


	$("#postCreateForm").submit(function() {

		var value = CKEDITOR.instances['editor'].getData();
    	if(value != ""){
    		CKEDITOR.instances['editor'].getData();
			contentValid = 1;
		}
		else{
			contentValid = 0;
		}

		if(selectValid == 1 && titleValid == 1 && contentValid == 1 ){
			return true;
		}
		else{
			if(contentValid != 1)
				alert("본문을 작성해주세요.");
			else
				alert("카테고리 및 제목을 확인해주세요.");
			return false;
		}
	});

	$(".category_select").blur(function(){
		if($(this).val() != 0){
			$(this).next().addClass("d-none")
			selectValid = 1;
		}
		else{
			$(this).next().removeClass("d-none").removeClass("text-success").addClass("text-danger").text("선택해주세요.");
			selectValid = 0;
		}
	});

	$(".post_title").blur(function(){
		if($(this).val() != ""){
			$(this).next().addClass("d-none")
			titleValid = 1;
		}
		else{
			$(this).next().removeClass("d-none").removeClass("text-success").addClass("text-danger").text("제목을 작성해주세요.");
			titleValid = 0;
		}
	});

});