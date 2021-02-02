
var currentEmail;
var newEmailValid = 0;
var newEmailConfirm = 0;

function changeEmail(){
	var email = $("#newEmail").val();
	if(currentEmail == email && newEmailConfirm == 1){
		var send = {
			"id" : $("#adminId").val(),
			"email" : currentEmail
		};

		$.ajax({
			url : "/admin/modify.do",
			type : "post",
			data : JSON.stringify(send),
			contentType : "application/json",
			success : function() {
				alert("이메일이 변경되었습니다.");
				location.reload();
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
		});
	}
	else{
		alert("이메일 인증을 완료해주세요.");
	}
}

function sendEmail(){
	newEmailConfirm = 0;
	$("#newEmailMsg").removeClass("d-none").removeClass("text-danger").addClass("text-success").text("인증코드를 전송중입니다.");
	$("#codeInputGroup").removeClass("d-none");
	if(newEmailValid == 1){
		$.ajax({
			url : "/admin/code.do",
			type : "get",
			data : "email="+currentEmail,
			success: function(){
				$("#newEmailMsg").removeClass("d-none").removeClass("text-danger").addClass("text-success").text("전송된 인증코드를 입력하세요.");
			},
		});
	}
	else{
		alert("다시 한번 확인해주세요.");
	}
}

$(document).ready(function() {
	$("#newEmail").blur(function() {
		var regExp = /^[0-9a-z]([-_.]?[0-9a-z])*@[0-9a-z]([-_.]?[0-9a-z])*.[a-z]{2,3}$/i;
		var newEmail = $("#newEmail").val();

		if(newEmail != ""){
			if(regExp.test(newEmail)){
				newEmailValid = 1;
				currentEmail = $("#newEmail").val();
			}
			else{
				$("#newEmailMsg").removeClass("d-none").removeClass("text-success").addClass("text-danger").text("올바르지 않은 이메일 주소입니다.");
				newEmailValid=0;
			}
		}
		else{
			newEmailValid=0;
		}
	});

	$("#codeInput").blur(function() {
		if(currentEmail == $("#newEmail").val()){
			$.ajax({
				url : "/admin/confirm.do",
				type : "get",
				data : "code="+$("#codeInput").val(),
				dataType:"text",
				success : function(receive) {
					if(receive == "true"){
					newEmailConfirm = 1;
						$("#newEmailMsg").addClass("d-none");
						$("#codeMsg").removeClass("d-none").removeClass("text-danger").addClass("text-success").text("인증 완료");
					}
					else{
						$("#newEmailMsg").addClass("d-none");
						$("#codeMsg").removeClass("d-none").removeClass("text-success").addClass("text-danger").text("올바르지 않은 인증코드입니다.");
						newEmailConfirm=0;
					}
				}
			});
		}
		else{
			$("#codeMsg").removeClass("d-none").removeClass("text-success").addClass("text-danger").text("올바르지 않은 인증코드입니다.");
			newEmailConfirm=0;
		}
	});

});