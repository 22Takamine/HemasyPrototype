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
<title>メニュー画面</title>
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="css/menu.css" rel="stylesheet">
<link href="css/common.css" rel="stylesheet">
<link href="css/commons.css" rel="stylesheet">
<!-- <link href="css/style.css" rel="stylesheet">-->
<meta name="viewport" content="width=device-width">
<link
	href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link media="all" type="text/css" rel="stylesheet" href="css/menu.css">
<link media="all" type="text/css" rel="stylesheet" href="css/common.css">
<link media="all" type="text/css" rel="stylesheet" href="css/commons.css">
<link media="all" type="text/css" rel="stylesheet" href="css/style.css">

</head>
<body>
	<header>
		<div class="header-logo">
			<h1><a href="./back" class="ml-40 mt-50 f-56 logoF">Hemasy</a></h1>
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
				<form:form action="record" modelAttribute="index" class="record-button-box" method="get">
				    <input type="hidden" id="todayDate" name="recordDate">
				    <input type="image" src="../../images/record.png" alt="#" class="button-img">
				</form:form>
			  
			    <form:form action="statistics" modelAttribute="index" class="statistics-button-box" method="get">
			  		<input type="hidden" id="todayDate" name="statisticsDate">
			  		<input type="image" src="../../images/statistic.png" alt="#" class="button-img">
			    </form:form>
			</div>

			<div class="human-box">
				<div class="tooltip lung">
					<div class="description">
						<p>${lungWord}</p>
					</div>
					<img src="${lungImg}" alt="#" class="lung-img">
				</div>

				<div class="tooltip liver">
					<div class="description">
						<p>${livarWord}</p>
					</div>
					<img src="${livarImg}" alt="#" class="livar-img">
				</div>

				<div class="tooltip stomach">
					<div class="description">
						<p>${stomachGoalkcal}</p>
						<p>${stomachInputKcal}</p>
						<p>${stomachOutputKcal}</p>
					</div>
					<img src="${stomachImg}" alt="#" class="stomach-img">
				</div>
			</div>

			<div class="bmi-human-box">
				<div class="bmi-human-img-box">
					<p>BMI(肥満度): ${bmiValue}</p>
					<img src="${bmiImg}" alt="#" class="bmi-img">
				</div>
			</div>
		</div>
	</main>
	<footer>
		<div>
			<p>ここはfooterです。</p>
		</div>
	</footer>
	<script>
  		var date = new Date();

    	var yyyy = date.getFullYear();
    	var mm = ("0"+(date.getMonth()+1)).slice(-2);
    	var dd = ("0"+date.getDate()).slice(-2);

		document.getElementById("todayDate").value=yyyy+'-'+mm+'-'+dd;
  	  </script>
	<script src="js/commons.js"></script>
</body>
</html>