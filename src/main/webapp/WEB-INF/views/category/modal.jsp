<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="/resources/javascript/category/category-modal-action.js"></script>

<div id="categoryModalGroup">
		<!-- modal -->
		<div class="modal fade body-font" id="categoryAddModal">
	        <div class="modal-dialog">
	            <div class="modal-content p-3">
	                <div class="modal-header">
	                    <h1 class="modal-title">Category 생성</h1>
	                    <button type="button" class="close" data-dismiss="modal">&times;</button>
	                </div>
	                <div class="modal-body">
	                    <form action="/category/add.do" method="post" id="categoryAddForm">
	                    	<input type="hidden" name="type" value="main" />
	                        <div class="form-group">
	                            <input type="text" class="form-control category_name" name="categoryName"  placeholder="카테고리 명">
	                            <div class="form-msg d-none"></div>
	                        </div>
	                        <input type="submit" class="btn btn-block btn-default" value="카테고리 생성">
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>

	    <div class="modal fade body-font" id="subCategoryAddModal">
	        <div class="modal-dialog">
	            <div class="modal-content p-3">
	                <div class="modal-header">
	                    <h1 class="modal-title">Sub Category 생성</h1>
	                    <button type="button" class="close" data-dismiss="modal">&times;</button>
	                </div>
	                <div class="modal-body">
	                    <form action="/category/add.do" method="post" id="subCategoryAddForm">
	                        <input type="hidden" name="type" value="sub" />
	                        <div class="form-group">
	                        	<select class="custom-select category_select" name="parent">
									<option value="0">카테고리</option>
									<c:forEach items="${requestScope.categoryList}" var="n">
									<option value="${n.categoryId}">${n.categoryName}</option>
									</c:forEach>
								</select>
								<div class="form-msg d-none"></div>
	                        </div>
	                        <div class="form-group">
	                            <input type="text" class="form-control subcategory_name" name="categoryName" placeholder="서브 카테고리 명">
	                            <div class="form-msg d-none"></div>
	                        </div>
	                        <div class="form-group">
	                        	<input type="text" class="form-control subcategory_en" name="categoryEn"  placeholder="서브 카테고리 영문명(주소 표시용)">
	                        	<div class="form-msg d-none"></div>
	                        </div>
	                        <input type="submit" class="btn btn-block btn-default" value="서브 카테고리 생성">
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>

	    <div class="modal fade body-font" id="categoryModifyModal">
	        <div class="modal-dialog">
	            <div class="modal-content p-3">
	                <div class="modal-header">
	                    <h1 class="modal-title">Category 수정</h1>
	                    <button type="button" class="close" data-dismiss="modal">&times;</button>
	                </div>
	                <div class="modal-body">
	                    <form action="/category/modify.do" method="post" id="categoryModifyForm">
	                    	<input type="hidden" name="type" value="main" />
	                    	<div class="form-group">
	                        	<select class="custom-select category_select" name="categoryId">
									<option value="0">변경할 카테고리</option>
									<c:forEach items="${requestScope.categoryList}" var="n">
									<option value="${n.categoryId}">${n.categoryName}</option>
									</c:forEach>
								</select>
								<div class="form-msg d-none"></div>
	                        </div>
	                        <div class="form-group">
	                            <input type="text" class="form-control category_name" name="categoryName" placeholder="카테고리 명">
	                            <div class="form-msg d-none"></div>
	                        </div>
	                        <input type="submit" class="btn btn-block btn-default" value="카테고리 수정">
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>

	    <div class="modal fade body-font" id="subCategoryModifyModal">
	        <div class="modal-dialog">
	            <div class="modal-content p-3">
	                <div class="modal-header">
	                    <h1 class="modal-title">Sub Category 수정</h1>
	                    <button type="button" class="close" data-dismiss="modal">&times;</button>
	                </div>
	                <div class="modal-body">
	                    <form action="/category/modify.do" method="post" id="subCategoryModifyForm">
	                        <input type="hidden" name="type" value="sub" />
	                        <div class="form-group">
	                        	<select class="custom-select category_select" name="categoryId">
									<option value="0">카테고리</option>
									<c:forEach items="${requestScope.categoryList}" var="n">
									<optgroup label="${n.categoryName}">
										<c:forEach items="${requestScope.subCategoryList}" var="m">
											<c:if test="${m.parent == n.categoryId}">
											<option value="${m.categoryId}">${m.categoryName}</option>
											</c:if>
										</c:forEach>
									</optgroup>
									</c:forEach>
								</select>
								<div class="form-msg d-none"></div>
	                        </div>
	                        <div class="form-group">
	                            <input type="text" class="form-control subcategory_name" name="categoryName" placeholder="서브 카테고리 명">
	                            <div class="form-msg d-none"></div>
	                        </div>
	                        <div class="form-group">
	                        	<input type="text" class="form-control subcategory_en" name="categoryEn" placeholder="서브 카테고리 영문명(주소 표시용)">
	                        	<div class="form-msg d-none"></div>
	                        </div>
	                        <input type="submit" class="btn btn-block btn-default" value="서브 카테고리 수정">
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>

	    <div class="modal fade body-font" id="categoryRemoveModal">
	        <div class="modal-dialog">
	            <div class="modal-content p-3">
	                <div class="modal-header">
	                    <h1 class="modal-title">Category 삭제</h1>
	                    <button type="button" class="close" data-dismiss="modal">&times;</button>
	                </div>
	                <div class="modal-body">
	                    <form action="/category/delete.do" method="post" id="categoryRemoveForm">
	                    	<input type="hidden" name="type" value="main" />
	                    	<div class="form-group">
		                        <select class="custom-select category_select" name="categoryId">
									<option value="0">카테고리</option>
									<c:forEach items="${requestScope.categoryList}" var="n">
									<option value="${n.categoryId}">${n.categoryName}</option>
									</c:forEach>
								</select>
								<div class="form-msg d-none"></div>
							</div>
	                        <input type="submit" class="btn btn-block btn-default" value="카테고리 삭제">
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>

	    <div class="modal fade body-font" id="subCategoryRemoveModal">
	        <div class="modal-dialog">
	            <div class="modal-content p-3">
	                <div class="modal-header">
	                    <h1 class="modal-title">Sub Category 삭제</h1>
	                    <button type="button" class="close" data-dismiss="modal">&times;</button>
	                </div>
	                <div class="modal-body">
	                    <form action="/category/delete.do" method="post" id="subCategoryRemoveForm">
	                        <input type="hidden" name="type" value="sub" />
	                        <div class="form-group">
	                        	<select class="custom-select category_select" name="categoryId">
									<option value="0">카테고리</option>
									<c:forEach items="${requestScope.categoryList}" var="n">
									<optgroup label="${n.categoryName}">
										<c:forEach items="${requestScope.subCategoryList}" var="m">
											<c:if test="${m.parent == n.categoryId}">
											<option value="${m.categoryId}">${m.categoryName}</option>
											</c:if>
										</c:forEach>
									</optgroup>
									</c:forEach>
								</select>
								<div class="form-msg d-none"></div>
	                        </div>
	                        <input type="submit" class="btn btn-block btn-default" value="서브 카테고리 삭제">
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>