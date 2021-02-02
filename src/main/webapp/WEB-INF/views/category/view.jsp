<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/resources/css/category/style.css">

<nav class="navbar navbar-dark bg-dark navbar-expand-sm mb-3 shadow flex-wrap align-items-baseline" id="categoryNav">
    <div class="navbar-header w-sm-100">
        <span class="navbar-brand m-0 cafe24dangdanghae">Category</span>
        <c:if test="${sessionScope.admin}">
            <div class="dropdown-custom dropdown d-inline-block">
                <button type="button" class="btn btn-dark" data-toggle="dropdown" style="width: 50px">
                    <img class="w-100" alt="setting" src="/resources/images/icon_setting.png" />
                </button>
                <div class="dropdown-menu bg-dark p-0 text-center">
                    <div class="dropdown dropright dropright-custom">
                        <a class="dropdown-item text-white text-dark-hover mb-1" data-toggle="dropdown" href="#">추가</a>
                        <div class="dropdown-menu bg-dark p-1">
                            <a class="dropdown-item text-white text-dark-hover mb-1 category_modal_btn" href="#" data-toggle="modal" data-target="#categoryAddModal">카테고리</a>
                            <a class="dropdown-item text-white text-dark-hover mb-1 category_modal_btn" href="#" data-toggle="modal" data-target="#subCategoryAddModal">서브 카테고리</a>
                        </div>
                    </div>
                    <div class="dropdown dropright dropright-custom">
                        <a class="dropdown-item text-white text-dark-hover mb-1" data-toggle="dropdown" href="#">수정</a>
                        <div class="dropdown-menu bg-dark p-1">
                            <a class="dropdown-item text-white text-dark-hover mb-1 category_modal_btn" href="#" data-toggle="modal" data-target="#categoryModifyModal">카테고리</a>
                            <a class="dropdown-item text-white text-dark-hover mb-1 category_modal_btn" href="#" data-toggle="modal" data-target="#subCategoryModifyModal">서브 카테고리</a>
                        </div>
                    </div>
                    <div class="dropdown dropright dropright-custom">
                        <a class="dropdown-item text-white text-dark-hover mb-1" data-toggle="dropdown" href="#">삭제</a>
                        <div class="dropdown-menu bg-dark p-1">
                            <a class="dropdown-item text-white text-dark-hover mb-1 category_modal_btn" href="#" data-toggle="modal" data-target="#categoryRemoveModal">카테고리</a>
                            <a class="dropdown-item text-white text-dark-hover mb-1 category_modal_btn" href="#" data-toggle="modal" data-target="#subCategoryRemoveModal">서브 카테고리</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <button type="button" class="navbar-toggler float-right" data-toggle="collapse" data-target="#categoryList">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
    <div class="ml-2 collapse navbar-collapse" id="categoryList">
        <div id="categoryAccordion">
            <ul class="navbar-nav d-flex flex-wrap">
            <c:forEach items="${requestScope.categoryList}" var="n">
                <li class="nav-item" style="min-width: 120px;">
                    <button type="button" class="w-100 btn btn-dark text-white dropdown-toggle" data-toggle="collapse" data-target="#main-${n.categoryId}">${n.categoryName}</button>
                    <div class="collapse bg-dark text-center" id="main-${n.categoryId}" data-parent="#categoryAccordion">
                        <c:forEach items="${requestScope.subCategoryList}" var="m">
                            <c:if test="${m.parent == n.categoryId}">
                                <a class="dropdown-item text-white text-dark-hover mb-1" href="/${m.categoryEn}">${m.categoryName}</a>
                            </c:if>
                        </c:forEach>
                    </div>
                </li>
            </c:forEach>
            </ul>
        </div>
    </div>
</nav>