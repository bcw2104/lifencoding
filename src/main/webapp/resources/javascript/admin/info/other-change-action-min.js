var currentNickname;
var nicknameValid = 0;

function changeNickname(){
	var nickname = $("#adminNickname").val();

	if(currentNickname != nickname){
		if(nicknameValid == 1){
			var send = {
				"id" : $("#adminId").val(),
				"nickname" : nickname
			};

			$.ajax({
				url:"/admin/modify.do",
				type : "post",
				data : JSON.stringify(send),
				contentType : "application/json",
				success : function() {
					alert("닉네임이 변경되었습니다.");
					location.reload();
			    }
			});
		}
		else{
			alert("닉네임을 확인해주세요.");
		}
	}
}

function changeComment(){
	var comment = $("#adminComment").val();

	var send = {
		"id" : $("#adminId").val(),
		"comment" : comment
	};

	$.ajax({
		url:"/admin/modify.do",
		type : "post",
		data : JSON.stringify(send),
		contentType : "application/json",
		success : function() {
			alert("코멘트가 변경되었습니다.");
			location.reload();
	    }
	});

}

$(document).ready(function() {
	currentNickname = $("#adminNickname").val();

	$("#adminNickname").blur(function(event) {
		var regExp = /^[a-zA-Z가-힣0-9]{2,10}$/;
		var nickname = $("#adminNickname").val();

		if(regExp.test(nickname)){
			if(nickname != currentNickname){
				$("#nicknameMsg").removeClass("d-none").removeClass("text-danger").addClass("text-success").text("사용 가능한 닉네임입니다.");
			}
			nicknameValid = 1;
		}
		else{
			$("#nicknameMsg").removeClass("d-none").removeClass("text-success").addClass("text-danger").text("10자 내 영문, 숫자, 한글만 사용 가능합니다.");
			nickNameValid=0;
		}
	});
});