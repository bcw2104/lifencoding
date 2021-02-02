<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="/resources/javascript/admin/info/pw-modal-action.js"></script>
<script src="/resources/javascript/admin/info/email-modal-action.js"></script>
<script src="/resources/javascript/admin/info/other-change-action.js"></script>
<script src="/resources/javascript/admin/info/img-change-action.js"></script>

<!-- modal -->
<div class="modal fade nanumbarungothic" id="pwModal">
    <div class="modal-dialog">
        <div class="modal-content p-5">
            <div class="modal-header">
                <h1 class="modal-title">비밀번호 변경</h1>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <input type="password" class="form-control" id="oldPw" placeholder="현재 비밀번호">
                        <div class="form-msg d-none" id="oldPwMsg"></div>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="newPw" placeholder="새 비밀번호">
                        <div class="form-msg d-none" id="newPwMsg"></div>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="newPwCheck" placeholder="비밀번호 확인">
                        <div class="form-msg d-none" id="newPwCheckMsg"></div>
                    </div>
                    <button type="button" class="btn btn-block btn-default" onclick="changePw()">변경하기</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade nanumbarungothic" id="emailModal">
    <div class="modal-dialog">
        <div class="modal-content p-5">
            <div class="modal-header">
                <h1 class="modal-title">이메일 변경</h1>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <div class="input-group">
                            <input type="text" class="form-control" id="newEmail" placeholder="이메일">
                            <div class="input-group-append">
                                <button type="button" class="btn btn-block btn-default" onclick="sendEmail()">인증코드</button>
                            </div>
                            <div class="form-msg d-none" id="newEmailMsg"></div>
                        </div>
                    </div>
                    <div class="form-group d-none" id="codeInputGroup">
                        <input type="text" class="form-control" id="codeInput" placeholder="인증코드">
                        <div class="form-msg d-none" id="codeMsg"></div>
                    </div>
                    <button type="button" class="btn btn-block btn-default" onclick="changeEmail()">변경하기</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="container my-5">
	<div class="shadow">
	    <h4 class="mb-5 bg-dark text-white p-3 cafe24dangdanghae">마이페이지</h4>
	    <div class="row p-sm-5 p-3">
	        <div class="col-lg-5 col-xl-4 mb-4 d-flex flex-column justify-content-center">
	            <img id="profileImg" alt="profile" style="width: 150px; height: 150px" class="rounded-circle mb-4">
	            <br />
	            <form id="imgForm">
	                <div class="form-group">
	                    <div class="position-relative" style="width:200px;">
	                        <input type="file" class="custom-file-input" id="newimg" name="newImg" style="cursor:pointer;" />
	                        <label for="newimg" class="custom-file-label" id="newimgInfo">파일 선택</label>
	                    </div>
	                </div>
	            </form>
	        </div>
	        <div class="col-lg-7 col-xl-4 mb-4">
	            <form class="w-md-100 w-75">
	                <div class="form-group">
	                    <label for="adminId">아이디</label>
	                    <input type="text" id="adminId" class="form-control" disabled="disabled" value="${requestScope.admin.adminId}">
	                </div>
	                <div class="form-group">
	                    <label for="nickname">닉네임</label>
	                    <div class="input-group">
	                        <input type="text" id="adminNickname" class="form-control" value="${requestScope.admin.adminNickname}">
	                        <div class="input-group-append">
	                            <button type="button" class="btn btn-default" onclick="changeNickname()">변경</button>
	                        </div>
	                    </div>
	                    <div class="form-msg d-none" id="nicknameMsg"></div>
	                </div>
	                <div class="form-group">비밀번호<a href="#" class="float-right font-14" data-toggle="modal" data-target="#pwModal">변경하기</a>
	                </div>
	                <div class="form-group">
	                    <label for="email">이메일</label>
	                    <a href="#" class="float-right font-14" data-toggle="modal" data-target="#emailModal">변경하기</a>
	                    <input type="text" class="form-control" disabled="disabled" value="${requestScope.admin.adminEmail}">
	                </div>
	            </form>
	        </div>
	        <div class="col-xl-4 mb-5">
	        	<form>
		            <div class="form-group">
		                <label for="comment">코멘트</label>
		                <div class="input-group">
			                <textarea id="adminComment" rows="4" class="form-control" style="resize:none">${requestScope.admin.adminComment}</textarea>
			                <div class="input-group-append">
				            	<button type="button" class="btn btn-default" onclick="changeComment()">변경</button>
				            </div>
		                </div>
		            </div>
	            </form>
	        </div>
	    </div>
	</div>
</div>