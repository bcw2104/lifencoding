
var selectValid;
var categoryNameValid;
var categoryEnValid;


$(document).ready(function() {
	$(".category_modal_btn").click(function() {
		selectValid = 0;
		categoryNameValid = 0;
		categoryEnValid = 0;

		$("#categoryModalGroup").find(".form-msg").addClass("d-none");
		$("#categoryModalGroup").find(".form-control").val("");
	});

	$("#categoryAddForm").submit(function() {
		if(categoryNameValid == 1){
			return true;
		}
		else{
			alert("다시 한번 확인해주세요.");
			return false;
		}
	});

	$("#subCategoryAddForm, #subCategoryModifyForm").submit(function() {
		if(selectValid == 1 && categoryNameValid == 1 && categoryEnValid == 1){
			return true;
		}
		else{
			alert("다시 한번 확인해주세요.");
			return false;
		}
	});

	$("#categoryModifyForm").submit(function() {
		if(selectValid == 1 && categoryNameValid == 1){
			return true;
		}
		else{
			alert("다시 한번 확인해주세요.");
			return false;
		}
	});

	$("#subCategoryRemoveForm,#categoryRemoveForm").submit(function() {
		if(selectValid == 1){
			return confirm("카테고리 삭제 시 해당 카테고리의 모든 게시글이 삭제됩니다. 삭제하시겠습니까?");
		}
		else{
			alert("다시 한번 확인해주세요.");
			return false;
		}
	});

	$(".category_name").blur(function(){
		var formMsg = $(this).next();
		var data = $(this).val();
		if(data != ""){
			$.ajax({
				url:"/category/check.do",
				type:"get",
				data: "type=main&categoryName="+data,
				success:function(receive){
					if(receive == "true"){
						formMsg.removeClass("d-none").removeClass("text-danger").addClass("text-success").text("사용 가능");
						categoryNameValid = 1;
					}
					else{
						formMsg.removeClass("d-none").removeClass("text-success").addClass("text-danger").text("사용 불가");
						categoryNameValid = 0;
					}
				}
			});
		}
		else{
			categoryNameValid = 0;
		}
	});

	$(".category_select").blur(function(){
		if($(this).val() != 0){
			$(this).next().addClass("d-none");
			selectValid = 1;
		}
		else{
			$(this).next().removeClass("d-none").removeClass("text-success").addClass("text-danger").text("선택해주세요.");
			selectValid = 0;
		}
	});

	$(".subcategory_name").blur(function(){
		var formMsg = $(this).next();
		var data = $(this).val();
		if(data != ""){
			$.ajax({
				url:"/category/check.do",
				type:"get",
				data: "type=sub&categoryName="+data,
				success:function(receive){
					if(receive == "true"){
						formMsg.removeClass("d-none").removeClass("text-danger").addClass("text-success").text("사용 가능");
						categoryNameValid = 1;
					}
					else{
						formMsg.removeClass("d-none").removeClass("text-success").addClass("text-danger").text("사용 불가");
						categoryNameValid = 0;
					}
				}
			});
		}
		else{
			categoryNameValid = 0;
		}
	});

	$(".subcategory_en").blur(function(){
		var formMsg = $(this).next();
		var data = $(this).val();

		if(data != ""){
			$.ajax({
				url:"/category/check.do",
				type:"get",
				data: "type=sub&categoryEn="+data,
				success:function(receive){
					if(receive == "true"){
						formMsg.removeClass("d-none").removeClass("text-danger").addClass("text-success").text("사용 가능");
						categoryEnValid = 1;
					}
					else{
						formMsg.removeClass("d-none").removeClass("text-success").addClass("text-danger").text("사용 불가");
						categoryEnValid = 0;
					}
				}
			});
		}
		else{
			categoryEnValid = 0;
		}
	});


});