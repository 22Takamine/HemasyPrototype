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
<title>管理者お問い合わせ画面</title>
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
		<h2>管理者お問い合わせ</h2>
		
		<form:form action="search" modelAttribute="index" method="get">
	      <form:input path="keyword" type="text" placeholder="キーワード検索"></form:input>
	      <form:button><fmt:message key="form.lbl.search"/></form:button>
	    </form:form>
	    <form:form action="alreadyRead" modelAttribute="index" method="get">
	      <form:button><fmt:message key="form.lbl.alreadyRead"/></form:button>
	    </form:form>
      	
      	
		<table>
			<thead>
				<tr>
					<th>No.</th>
					<th>ユーザー名</th>
					<th>メールアドレス</th>
					<th>件名</th>
					<th>受け取り日時</th>
					<th>詳細</th>
					<th>既読</th>
					<th>済み</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="info" items="${infoList}">

					<tr>
						<td>${info.information_id}</td>
						<td>${info.user_name}</td>
						<td>${info.mail}</td>
						<td>${info.title}</td>
						<td>${info.send_at}</td>
						<td><form:form action="informationDetail"
								modelAttribute="index" method="post">
								<form:button>
									<fmt:message key="form.lbl.detail" />
								</form:button>
								<form:input path="informationId" type="hidden"
									value="${info.information_id}" />
							</form:form></td>

						<td><c:if test="${info.read_flag == 1}">
								<img src="/images/check.png">
							</c:if></td>

						<td><c:if test="${info.done_flag == 1}">
								<img src="/images/check.png">
							</c:if></td>



						<%-- 				            <td>${info.read_flag}</td> --%>

						<%-- 				            <td>${info.done_flag}</td> --%>

					</tr>

				</c:forEach>

			</tbody>
		</table>




		<%--   <form:form action="process" modelAttribute="index" method="post"> --%>
		<%--     <form:button><fmt:message key="form.lbl.process"/></form:button> --%>
		<%--   </form:form> --%>

		<form:form action="admin" modelAttribute="index" method="post">
			<form:button>
				<fmt:message key="form.lbl.back" />
			</form:button>
		</form:form>
	</div>
	<script src="js/commons.js"></script>
</body>
</html>