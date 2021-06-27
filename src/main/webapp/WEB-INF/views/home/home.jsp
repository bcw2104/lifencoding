<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="maxPage" value="${requestScope.postCount%5 > 0 ? requestScope.postCount/5+1 : requestScope.postCount/5}" ></c:set>
<c:set var="curPage" value="${param.p == null ? 1 : param.p}" />
<c:set var="now" value="<%=new java.util.Date()%>" />

<link rel="stylesheet" href="/resources/css/home/style.css">

<div class="container-fluid p-0 cafe24dangdanghae">
	<div class="banner position-relative bg-dark">
		<h3 class="banner-title position-absolute text-white">일상과 배움을 기록하는 공간</h3>
   	</div>
</div>

<div class="container my-5">
	<jsp:include page="/WEB-INF/views/category/view.jsp"></jsp:include>
	<div class="shadow p-sm-5 p-0 row m-0">
		<div class="col-lg-4 mb-3 p-0">
			<div class="row m-0">
				<div class="col-sm-4 col-lg-12 ">
					<img class="profile-img mb-3" id="profileImg" alt="profile" />
				</div>
				<div class="col-sm-1"></div>
				<div class="col-8 col-sm-7 col-lg-12">
					<h6>${requestScope.adminInfo.adminNickname }</h6>
					<div class="mb-1 font-14">${requestScope.adminInfo.adminComment}</div>
					<div class="font-14">
						<a class="text-dark" href="https://mail.naver.com/write/popup/?orderType=new&to=bcw2104@gmail.com">Contact</a>
						<span class="mx-2">|</span>
						<a class="text-dark" href="https://github.com/bcw2104">Github</a>
					</div>
				</div>
			</div>
			<hr/>
			<div>
				<div class="card-deck">
					<div class="card">
						<div class="card-body text-center">
							<div class="card-title">전체 방문자 수</div>
							<h2 class="text-primary">${requestScope.totalVisit}</h2>
						</div>
					</div>
					<div class="card">
						<div class="card-body text-center">
							<div class="card-title">오늘 방문자 수</div>
							<h2 class="text-primary">${requestScope.todayVisit}</h2>
						</div>
					</div>
				</div>
			</div>
			<hr/>
		</div>
		<div class="col-lg-1"></div>
		<div class="col-lg-7 p-2">
			<div class="content-hot">
		    	<h5 class="mb-2 bg-dark text-white p-2 cafe24dangdanghae">인기 글</h5>
				<c:forEach items="${requestScope.hotPostList}" var="n">
				<div class="post-item my-4 d-flex pl-2">
					<div class="p-0 flex-fill d-flex flex-column justify-content-between">
						<div>
							<a class="text-dark font-17" href="/${n.categoryEn}/${n.postId}">${n.postTitle}</a>
							<div class="mt-3 font-13 post-content">${n.postContent}</div>
						</div>
						<div class="mt-3 font-12">
							<span><a class="text-primary" href="/${n.categoryEn}">${n.categoryName}</a></span>
							<span class="mx-2 text-secondary">|</span>
							<span class="text-secondary">
							<fmt:formatDate value="${n.postDate}" var="postDate" pattern="yyyy-MM-dd" />
	                   		<c:choose>
	                   			<c:when test="${postDate == today}">
									<fmt:formatDate value="${n.postDate}" pattern="HH:mm"/>
	                   			</c:when>
	                   			<c:otherwise>
	                   				<fmt:formatDate value="${n.postDate}" pattern="yyyy-MM-dd"/>
	                   			</c:otherwise>
	                   		</c:choose>
	                   		</span>
						</div>
					</div>
					<c:if test="${n.thumbnail != ''}">
		        	<img class="thumbnail ml-3" src="/resources/upload/files/post/${n.postId}/thumbnail/${n.thumbnail}">
		        	</c:if>
				</div>
				<hr/>
				</c:forEach>
		    </div>
			<div class="content-recent mt-5">
				<h5 class="mb-2 bg-dark text-white p-2 cafe24dangdanghae">최신 글</h5>
				<c:forEach items="${requestScope.postList}" var="n">
				<div class="post-item my-4 d-flex pl-2">
					<div class="p-0 flex-fill d-flex flex-column justify-content-between">
						<div>
							<a class="text-dark font-17" href="/${n.categoryEn}/${n.postId}">${n.postTitle}</a>
							<div class="mt-3 font-13 post-content">${n.postContent}</div>
						</div>
						<div class="mt-3 font-12">
							<span><a class="text-primary" href="/${n.categoryEn}">${n.categoryName}</a></span>
							<span class="mx-2 text-secondary">|</span>
							<span class="text-secondary">
							<fmt:formatDate value="${n.postDate}" var="postDate" pattern="yyyy-MM-dd" />
	                   		<c:choose>
	                   			<c:when test="${postDate == today}">
									<fmt:formatDate value="${n.postDate}" pattern="HH:mm"/>
	                   			</c:when>
	                   			<c:otherwise>
	                   				<fmt:formatDate value="${n.postDate}" pattern="yyyy-MM-dd"/>
	                   			</c:otherwise>
	                   		</c:choose>
	                   		</span>
						</div>
					</div>
					<c:if test="${n.thumbnail != ''}">
		        	<img class="thumbnail ml-3" src="/resources/upload/files/post/${n.postId}/thumbnail/${n.thumbnail}">
		        	</c:if>
				</div>
				<hr/>
				</c:forEach>
				<ul class="pager row list-unstyled justify-content-center mt-3">
			        <li class="pager-item ${curPage-1 < 1 ? 'd-none' : ''}"><a class="text-dark" href="?p=${curPage-1}">이전</a></li>
			        <c:forEach begin="1" end="${maxPage}" varStatus="n">
			        	<li class="pager-item ${curPage == n.index ? 'pager-item-active' : ''}"><a class="text-dark p-2" href="?p=${n.index}">${n.index}</a></li>
			        </c:forEach>
			        <li class="pager-item ${curPage+1 > maxPage ? 'd-none' : ''}"><a class="text-dark" href="?p=${curPage+1}">다음</a></li>
			    </ul>
		    </div>
	    </div>
	</div>
</div>