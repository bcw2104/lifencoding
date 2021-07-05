<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Ckeditor4 -->
<script src="/resources/ckeditor/ckeditor.js"></script>
<script src="/resources/ckeditor/config.js"></script>

<script src="/resources/javascript/post/content-form-action-min.js"></script>

<div class="container my-5">
	<div class="shadow p-sm-5 p-3">
	    <h4 class="mb-4">글 수정</h4>
	    <input type="hidden" id="page" value="modify"/>
	    <form action="/post/${requestScope.currentPost.postId}/modify.do" method="post" id="postCreateForm" encType="multipart/form-data">
	        <div class="form-group w-sm-100 w-50">
	            <select class="custom-select category_select" name="categoryId">
	                <option value="0">카테고리</option>
	                <c:forEach items="${requestScope.categoryList}" var="n">
	                    <optgroup label="${n.categoryName}">
	                        <c:forEach items="${requestScope.subCategoryList}" var="m">
	                            <c:if test="${m.parent == n.categoryId}">
	                                <option value="${m.categoryId}"
	                                ${requestScope.currentPost.categoryId == m.categoryId ? 'selected="selected"' : ''}>${m.categoryName}</option>
	                            </c:if>
	                        </c:forEach>
	                    </optgroup>
	                </c:forEach>
	            </select>
	            <div class="form-msg d-none"></div>
	        </div>
	        <div class="form-group w-sm-100 w-50">
	            <input type="text" class="form-control post_title" name="title" placeholder="제목" value="${requestScope.currentPost.postTitle}"/>
	            <div class="form-msg d-none"></div>
	        </div>
	        <div class="row m-0">
		        <div class="form-group w-sm-100 w-50">
		            <div class="position-relative">
		                <input type="file" class="custom-file-input" id="thumbnail" name="thumbnail" style="cursor:pointer;" />
		                <label for="thumbnail" class="custom-file-label" id="thumbnailInfo">섬네일 선택</label>
		            </div>
		        </div>
		        <div class="form-group ml-sm-4">
	                <div class="custom-control custom-checkbox">
	                    <input type="checkbox" class="custom-control-input" id="thumbnailCk" name="thumbnailCk" value="1">
	                    <label class="custom-control-label" for="thumbnailCk">섬네일 지정 안함</label>
	                </div>
	            </div>
            </div>
	        <div class="form-group">
	            <textarea id="editor" name="content">${requestScope.currentPost.postContent}</textarea>
	        </div>
	        <div class="form-group text-right">
	            <input type="submit" class="btn btn-default" style="width: 100px" value="완료">
	        </div>
	    </form>
	</div>
</div>

