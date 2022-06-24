<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
<link href="css/commons.css" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<meta name="viewport" content="width=device-width">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
</head>
<body>
	<header>
		<div class="header-logo">
			<a href="./back">Hemasy</a>
		</div>
		<form:form action="hamburger" modelAttribute="index" method="post">
			<button type="button" class="menu-btn">
				<i class="fa fa-bars" aria-hidden="true"></i>
			</button>
			<div class="menu">
				<div class="menu__item">
					<a href="./account">アカウント管理</a>
				</div>
				<div class="menu__item">
					<a href="./rank">ランキング</a>
				</div>
				<div class="menu__item">
					<a href="./list">リスト編集</a>
				</div>
				<div class="menu__item">
					<a href="./information">お問い合わせ</a>
				</div>
				<div class="menu__item">
					<a href="./logout">ログアウト</a>
				</div>
			</div>
		</form:form>
	</header>
	<main>
		<div class="main-box">
		
			<div class="button-box">
				<div class="record-button-box">
					<a href="/record" class="link button"><img src="../../images/record.png" alt="#" class="button-img"></a> <!-- ここaタグに変えたよ -->
				</div>
				<div class="statistics-button-box">
					<a href="/statistics" class="link button"><img src="../../images/statistic.png" alt="#" class="button-img"></a> <!-- ここaタグに変えたよ -->
				</div>
			</div>
			
			<div class="human-box">
				<img src="${lungImg}" alt="#" class="lung-img img">
				<img src="${livarImg}" alt="#" class="livar-img img">
				<img src="${stomachImg}" alt="#" class="stomach-img img">
			</div>
	
			<div class="bmi-human-box">
				<div class="bmi-human-box-img">
					<p>BMI(肥満度)</p>
					<img src="${bmiImg}" alt="#">
				</div>
			</div>
		</div>
		

	</main>

	<footer>
		<div>
			<p>ここはfooterです。</p>
		</div>
	</footer>
	<script src="js/commons.js"></script>
</body>
</html>