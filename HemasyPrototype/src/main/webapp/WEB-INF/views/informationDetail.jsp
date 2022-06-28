<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者お問い合わせ詳細画面</title>
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<link rel="manifest" href="img/favicon/manifest.json">
<meta name="apple-mobile-web-app-title" content="Hemasy">
<meta name="application-name" content="Hemasy">
<meta name="theme-color" content="#fff">

<link media="all" type="text/css" rel="stylesheet"
	href="css/commons.css">
<link media="all" type="text/css" rel="stylesheet" href="css/style.css">
<link media="all" type="text/css" rel="stylesheet" href="css/common.css">
<link
	href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<meta name="viewport" content="width=device-width">

<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
</head>
<body>
	<header>
		<div class="header-logo">
			<a class="f-24 logoF" href="./admin">Hemasy</a>
		</div>
		<form:form action="hamburger" modelAttribute="index" method="post">
			<button type="button" class="menu-btn">
				<i class="fa fa-bars" aria-hidden="true"></i>
			</button>
			<div class="menu">
				<div class="menu__item">
					<a href="./rank">ランキング</a>
				</div>
				<div class="menu__item">
					<a href="./adminInformation">リクエスト</a>
				</div>
				<div class="menu__item">
					<a href="./logout">ログアウト</a>
				</div>
			</div>
		</form:form>
	</header>
	<div class="main">
		<h2>お問い合わせ内容</h2>

		<div class="insert">
			<p>
				<fmt:message key="form.lbl.name" />${information.user_name}</p>
			<p>
				<fmt:message key="form.lbl.mail" />${information.mail}</p>
			<p>
				<fmt:message key="form.lbl.subject" />${information.title}</p>

			<div class="form_body">
				<p>
					<fmt:message key="form.lbl.contents" />${information.contents}</p>
			</div>
		</div>

		<div class="display-flex-between">
			<form:form action="backList" modelAttribute="index" method="GET">
				<form:input path="informationId" type="hidden"
					value="${information.information_id}" />
				<form:input path="doneFlag" type="hidden"
					value="${information.done_flag}" />
				<form:button>
					<fmt:message key="form.lbl.backList" />
				</form:button>
			</form:form>
			
			<form:form action="process" modelAttribute="index" method="GET">
				<form:input path="informationId" type="hidden"
					value="${information.information_id}" />
				<form:input path="doneFlag" type="hidden"
					value="${information.done_flag}" />
				<form:button>
					<fmt:message key="form.lbl.process" />
				</form:button>
			</form:form>

		</div>
	</div>
	<script src="js/commons.js"></script>
</body>
</html>