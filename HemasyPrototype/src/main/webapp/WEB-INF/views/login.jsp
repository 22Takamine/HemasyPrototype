<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../../css/common.css">
<link rel="stylesheet" href="../../css/style.css">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Hemasy</title>
<meta name="description" content="">
<link rel="manifest" href="img/favicon/manifest.json">
<meta name="apple-mobile-web-app-title" content="Hemasy">
<meta name="application-name" content="Hemasy">
<meta name="theme-color" content="#fff">

<link
	href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link media="all" type="text/css" rel="stylesheet" href="css/common.css">
<link media="all" type="text/css" rel="stylesheet" href="css/style.css">
<title>トップ画面</title>
</head>
<body>
	<form:form action="result" modelAttribute="index" method="post">
		<div class="pc-wrap">
			<div class="content form">
				<h1 class="mb-30 f-56 logoF">Hemasy</h1>
				<c:if test="${not empty msg}">
					<p>${fn:escapeXml(msg)}</p>
				</c:if>

				<p class="mb-5">
					<label><fmt:message key="form.lbl.mail" /></label>
				</p>
				<p class="mb-20">
					<form:input path="mail" type="email" placeholder="メールアドレス"
						class="width-100-percent" />
					<form:errors path="mail" cssStyle="color: red" />
				</p>

				<p class="mb-5">
					<label><fmt:message key="form.lbl.password" /></label>
				</p>
				<p class="mb-20">
					<form:input path="password" type="password"
						placeholder="パスワード (8文字以上12文字以内)" class="width-100-percent" />
					<form:errors path="password" cssStyle="color: red" />
				</p>

				<form:button name="login" type="submit"
					class="btn btn_orange width-100-percent" value=""
					onclick="location.href='index.html'">
					<fmt:message key="form.lbl.login" />
				</form:button>

				<!-- getになって送信できない -->
				<p class="mt-20 center">
					<a href="/register">新規登録がまだお済みでない方は<br>こちらから新規登録をお願いいたします。
					</a>
				</p>

				<p class="center mt-40">
					<a href="" class="text-link">利用規約</a>
				</p>

				<p class="center mt-20 mb-40">
					<a href="" class="text-link">プライバシーポリシー</a>
				</p>
			</div>
		</div>

	</form:form>
	<script type="text/javascript" src="js/vendor/footerFixed.js"></script>
	<script src="js/commons.js"></script>
</body>
</html>