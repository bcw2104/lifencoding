
var oldPwValid = 0;
var newPwValid = 0;
var newPwCheck = 0;

function changePw(){
	if(oldPwValid == 1 && newPwValid == 1 && newPwCheck == 1){
		var send = {
			"id" : $("#adminId").val(),
			"pw" : $("#newPw").val()
		};

		$.ajax({
			url : "/admin/modify.do",
			type : "post",
			data : JSON.stringify(send),
			contentType : "application/json",
			success : function() {
				alert("비밀번호가 변경되었습니다.");
				location.reload();
		    }

		});
	}else{
		alert("다시 한번 확인해주세요.");
	}
}

$(document).ready(function() {
	function checkOldPassword() {
		var send = {
			"id" : $("#adminId").val(),
			"pw" : $("#oldPw").val()
		};

		$.ajax({
			url : "/admin/check.do",
			type : "post",
			data : JSON.stringify(send),
			contentType : "application/json",
			dataType : "text",
			success : function(data) {
				if (data == "true") {
					$("#oldPwMsg").removeClass("d-none").removeClass("text-danger").addClass("text-success").text("확인");
					oldPwValid = 1;
				} else {
					$("#oldPwMsg").removeClass("d-none").removeClass("text-success").addClass("text-danger").text("현재 비밀번호가 일치하지 않습니다");
					oldPwValid = 0;
				}
			}
		});
	}

	function checkNewPassword(){
		var pw = $("#newPw").val();
		var pwCk = $("#newPwCheck").val();

		if(pwCk != ""){
			if(pw == pwCk){
				$("#newPwCheckMsg").removeClass("d-none").removeClass("text-danger").addClass("text-success").text("일치");
				newPwCheck = 1;
			}
			else{
				$("#newPwCheckMsg").removeClass("d-none").removeClass("text-success").addClass("text-danger").text("새 비밀번호가 일치하지 않습니다");
				newPwCheck=0;
			}
		}else{
			newPwCheck=0;
		}
	}

	$("#oldPw").blur(function(event) {
		checkOldPassword();
	});

	$("#newPw").blur(function(event) {
		var regExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*-+])(?=.*[0-9]).{8,16}$/;
		var pw = $(this).val();

		if(pw != ""){
			if(regExp.test(pw)){
				$("#newPwMsg").removeClass("d-none").removeClass("text-danger").addClass("text-success").text("사용 가능한 비밀번호입니다.");
				newPwValid = 1;
			}
			else{
				$("#newPwMsg").removeClass("d-none").removeClass("text-success").addClass("text-danger").text("8~16자의 영문, 숫자, 특수문자를 사용하세요.");
				newPwValid=0;
			}
		}
		else{
			newPwValid=0;
		}
		checkNewPassword();
	});

	$("#newPwCheck").blur(function(event) {
		checkNewPassword();
	});
});