<%@page import="com.lifencoding.util.GlobalValues"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="/resources/css/post/style-min.css">

<script src="/resources/javascript/post/ui-action-min.js"></script>

<c:set var="maxPage" value="${requestScope.postCount%10 > 0 ? requestScope.postCount/10+1 : requestScope.postCount/10}" ></c:set>
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="curPage" value="${(param.p == null or param.p <1 or param.p >maxPage) ? 1 : param.p}" />
<c:choose>
	<c:when test="${param.search != null}">
		<c:set var="search" value="search=${param.search}&" />
	</c:when>
	<c:otherwise>
		<c:set var="search" value="" />
	</c:otherwise>
</c:choose>

<fmt:formatDate value="${now}" var="today" pattern="yyyy-MM-dd" />

<div class="container mt-5">
	<jsp:include page="/WEB-INF/views/category/view.jsp"></jsp:include>

	<div class="p-sm-5 p-3 shadow">
	    <div class="p-2 font-15">
	    	<h6 class="mb-3">최신 글 (${requestScope.postCount})</h6>
	        <ul class="breadcrumb position-relative">
	        	<c:choose>
		            <c:when test="${requestScope.currentCategory != null && requestScope.currentSubCategory != null}">
		                <li class="breadcrumb-item">${currentCategory.categoryName}</li>
		                <li class="breadcrumb-item">
		                    <a class="text-dark" href="/${requestScope.currentSubCategory.categoryEn}">${requestScope.currentSubCategory.categoryName}</a>
		                </li>
		            </c:when>
		            <c:otherwise>
		            	<li class="breadcrumb-item">검색 : ${param.search}</li>
		            </c:otherwise>
	            </c:choose>
	            <li class="position-absolute" style="right: 16px;">
	                <a role="button" href="#" onclick="hidePostList()" id="postToggler" class="text-dark font-13">접어두기</a>
	            </li>
	        </ul>
	    </div>
	    <div id="postList">
	        <table class="table mt-2 font-13">
	            <thead>
	                <tr>
	                    <th class="d-block border-0 font-15">제목</th>
	                    <th class="text-right border-0 font-15" style="width:100px">게시일</th>
	                </tr>
	            </thead>
	            <tbody>
	            	<c:forEach begin="0" end="9" items="${requestScope.postList}" var="n">
	                <tr>
	                    <td class="text-left">
	                    	<a class="text-dark" href="/${n.categoryEn}/${n.postId}?p=${curPage}">${n.postTitle}</a>
	                    </td>
	                    <td class="text-right">
	                    	<span class="text-secondary">
	                    		<c:choose>
	                    			<c:when test="${n.postDate == today}">
										<fmt:formatDate value="${n.postDate}" pattern="HH:mm"/>
	                    			</c:when>
	                    			<c:otherwise>
	                    				<fmt:formatDate value="${n.postDate}" pattern="yyyy-MM-dd"/>
	                    			</c:otherwise>
	                    		</c:choose>
	                    	</span>
	                    </td>
	                </tr>
	                </c:forEach>
	            </tbody>
	        </table>
	        <ul class="pager row list-unstyled justify-content-center mt-3">
	            <li class="pager-item ${curPage-1 < 1 ? 'd-none' : ''}"><a class="text-dark" href="?${search}p=${curPage-1}">이전</a></li>
	            <c:forEach begin="1" end="${maxPage}" varStatus="n">
	            	<li class="pager-item ${curPage == n.index ? 'pager-item-active' : ''}"><a class="text-dark p-2" href="?${search}p=${n.index}">${n.index}</a></li>
	            </c:forEach>
	            <li class="pager-item ${curPage+1 > maxPage ? 'd-none' : ''}"><a class="text-dark" href="?${search}p=${curPage+1}">다음</a></li>
	        </ul>
	    </div>
	</div>
	<div class="my-5">
	    <div class="shadow p-sm-5 py-5 px-3">
	    <c:choose>
			<c:when test="${requestScope.currentPost != null}">
			<div class="content-header">
	            <div class="mb-3">
	                <a class="text-dark font-weight-bold" href="/${requestScope.currentPost.categoryEn}">${requestScope.currentPost.categoryName}</a>
	            </div>
	            <div class="content-title font-weight-bold py-3">${requestScope.currentPost.postTitle}</div>
	            <div class="author mt-3">
	                <img class="author-img border border-0 rounded-circle position-relative" src="<%=GlobalValues.profileImg %>${requestScope.adminInfo.adminImg}" alt="profile" style="top: -11px;"/>
	                <div class="d-inline-block ml-2">
		                <div style="line-height: 20px;">${requestScope.adminInfo.adminNickname}</div>
		                <div class="text-secondary font-14" style="line-height: 20px;">
							<fmt:formatDate value="${requestScope.currentPost.postDate}"  pattern="yyyy-MM-dd HH:mm" />
						</div>
					</div>
					<span class="text-secondary float-right font-14">조회 수 : ${requestScope.currentPost.hits}</span>
	            </div>
	        </div>
	        <hr />
	        <div class="content-body my-3">
	            <div class="content-post" style="overflow: auto;">${requestScope.currentPost.postContent}</div>
	            <c:if test="${sessionScope.admin}">
	            <div class="text-right font-16 mt-5">
	            	<a class="text-secondary" href="/post/${requestScope.currentPost.postId}/edit">수정</a>
	            	<span class="text-secondary mx-2">|</span>
	            	<a class="text-secondary" href="/post/${requestScope.currentPost.postId}/delete.do">삭제</a>
	            </div>
	            </c:if>
	        </div>
	        <div class="content-footer mt-2 mx-2">
	            <table class="table mt-2 font-13">
	                <tbody>
	                	<c:forEach items="${requestScope.nearPost}" var="n">
	                    <tr>
	                        <td class="text-left" style="width:80px">
	                        	<c:choose>
	                        		<c:when test="${n.postId > requestScope.currentPost.postId}">
									<span>다음 글</span>
	                        		</c:when>
	                        		<c:otherwise>
	                        		<span>이전 글</span>
	                        		</c:otherwise>
	                        	</c:choose>
	                        </td>
	                        <td class="text-left">
	                        	<a class="text-dark d-block w-100" href="/${n.categoryEn}/${n.postId}?p=${curPage}">${n.postTitle}</a>
	                        </td>
	                         <td class="text-right" style="width:100px">
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
	                        </td>
	                    </tr>
	                    </c:forEach>
	                </tbody>
	            </table>
	        </div>
			</c:when>
			<c:otherwise>
			<div class="text-center">
				<h3>게시글이 존재하지 않습니다.</h3>
			</div>
			</c:otherwise>
	    </c:choose>
	    </div>
	</div>
</div>
