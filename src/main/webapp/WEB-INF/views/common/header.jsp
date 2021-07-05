<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="/resources/javascript/header/ui-action-min.js"></script>

<!-- Modal -->
<div id="headerModalGroup" class="head-font">
	<div class="modal fade" id="loginModal">
	    <div class="modal-dialog">
	        <div class="modal-content p-5">
	            <div class="modal-header">
	                <h1 class="modal-title">Login</h1>
	                <button type="button" class="close" data-dismiss="modal">&times;</button>
	            </div>
	            <div class="modal-body">
	                <form>
	                	<input type="password" class="d-none"> <!-- fakeInput -->
	                    <div class="form-group">
	                        <input type="email" class="form-control" id="loginId" placeholder="아이디">
	                    </div>
	                    <div class="form-group">
	                        <input type="password" class="form-control" id="loginPw" placeholder="비밀번호">
	                        <div class="form-msg text-danger d-none" id="loginFailMsg">아이디 또는 비밀번호가 일치하지 않습니다.</div>
	                    </div>
	                    <button type="button" class="btn btn-block btn-default" onclick="login()">로그인</button>
	                </form>
	                <div class="text-center mt-3">
	                    <a href="#" class="mx-2 text-dark" data-toggle="modal" data-target="#findModal" data-dismiss="modal">계정 찾기</a>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>

	<div class="modal fade" id="findModal">
	    <div class="modal-dialog">
	        <div class="modal-content p-5">
	            <div class="modal-header">
	                <h1 class="modal-title">계정 찾기</h1>
	                <button type="button" class="close" data-dismiss="modal">&times;</button>
	            </div>
	            <div class="modal-body">
	            	<div class="mb-3">계정에 등록된 이메일 주소를 입력해주세요.</div>
	                <form>
	                    <div class="form-group">
	                        <input type="text" class="form-control" id="findEmail" placeholder="이메일">
	                        <div class="form-msg d-none" id="findFormMsg"></div>
	                    </div>
	                    <button type="button" class="btn btn-block btn-default" onclick="find()">찾기</button>
	                </form>
	                <div class="text-center mt-3">
	                    <a href="#" class="mx-2 text-dark" data-toggle="modal" data-target="#loginModal" data-dismiss="modal">로그인</a>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</div>


<nav class="navbar bg-dark navbar-dark navbar-expand-sm font-17 head-font">
    <div class="navbar-header w-sm-100 mb-2">
        <div class="row px-3">
            <a href="/" class="navbar-brand">Life &amp; Coding</a>
            <button type="button" class="navbar-toggler ml-auto" data-toggle="collapse" data-target="#userNav">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </div>
    <div class="collapse navbar-collapse mb-2" id="userNav">
    	<div class="input-group float-right" style="width: 150px;height: 30px;">
			<input type="text" id="search" class="form-control h-100" placeholder="제목 검색"/>
            <div class="input-group-append h-100">
               <button type="button" class="btn btn-default p-0" onclick="search()" style="width: 30px">
              		<img alt="search" src="/resources/images/icon_search.png" style="width: 19px; height: 15px;" />
            	</button>
        	</div>
        </div>
        <ul class="navbar-nav ml-auto">
       	<c:choose>
    		<c:when test="${!sessionScope.admin}">
            <li class="nav-item"><a href="#" role="button" class="nav-link" data-toggle="modal" data-target="#loginModal">로그인</a></li>
            </c:when>
            <c:otherwise>
            <li class="nav-item"><a class="nav-link" href="/post/create">글쓰기</a></li>
			<li class="nav-item"><a class="nav-link" href="/admin/logout.do">로그아웃</a></li>
			<li class="nav-item"><a class="nav-link" href="/admin/info">마이페이지</a></li>
            </c:otherwise>
        </c:choose>
        </ul>
    </div>
</nav>

