<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isErrorPage = "true" %>
<%@page errorPage="500" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />

<!-- Bootstrap4 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="/resources/css/common.css">
<link rel="stylesheet" href="/resources/css/font.css">

<title>Life &amp; Coding</title>
</head>

<body style="background-color: rgb(250, 250, 250)">
    <header>
    	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    </header>
    <div class="container">
    	<div class="my-5 py-5 nanumbarungothic">
    		<h1>Error!!</h1>
			<span class="mr-2 font-18">페이지가 응답하지않습니다.</span>
			<a href="/"><button type="button" class="btn btn-default" style="width:80px">홈으로</button></a>
		</div>
    </div>
    <footer>
    	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    </footer>
</body>
</html>
