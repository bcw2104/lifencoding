
function search(){
	window.location.href="/?search="+$("#search").val();
}

function login() {
	var send = {
		"id" : $("#loginId").val(),
		"pw" : $("#loginPw").val()
	};

	$.ajax({
		url : "/admin/login.do",
		type : "post",
		data : JSON.stringify(send),
		contentType : "application/json",
		dataType : "text",
		success : function(data) {
			if (data == "true") {
				$("#loginModal").modal("hide");
				window.location.href = "/";
			} else {
				$("#loginFailMsg").removeClass("d-none");
			}
		}
	});
}

function find() {
	$.ajax({
		url : "/admin/find.do",
		type : "post",
		data: {email:$("#findEmail").val()},
		success : function(data) {
			if (data == "true") {
				$("#findFormMsg").removeClass("d-none").removeClass("text-danger").addClass("text-success").text("이메일로 아이디 및 임시 비밀번호를 전송하였습니다.");
			} else {
				$("#findFormMsg").removeClass("d-none").removeClass("text-success").addClass("text-danger").text("이메일이 일치하지 않습니다.");
			}
		}
	});
}