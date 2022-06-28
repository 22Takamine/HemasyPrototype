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
<title>マイリスト編集画面</title>
<link href="css/common.css" rel="stylesheet">
<link href="css/ameku.css" rel="stylesheet">
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
			<a href="./back" class="f-24 logoF">Hemasy</a>
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
	<div class="list-page-main">
		<h2 class="center f-32">マイリスト編集</h2>
		<form action="listCommit" method="post">
			<input type="hidden" name="createDate" id="todayDate">
			<div id="food-list" class="center mb-45 bgc">
				<h2 class="mb-10">食べ物リスト<button type="button" onclick="addFoodData()">⊕</button></h2>
				<c:forEach var="foodData" items="${foodList}" varStatus="fStatus">
					<p class="foodData mb-10">
						<input type="text" value="${foodData.value1}"
							name="value1Food${fStatus.index}" required> <input
							type="number" min="0" value="${foodData.value2}"
							name="value2Food${fStatus.index}" required>kcal
						<input type="checkbox" value="del" name="delFood${fStatus.index}">削除
					</p>
				</c:forEach>
			</div>
			<div id="alcohol-list" class="center mb-45 bgc">
				<h2 class="mb-10">
					アルコール
					<button type="button" onclick="addAlcData()">⊕</button>
				</h2>
				<c:forEach var="alcoholData" items="${alcoholList}"
					varStatus="aStatus">
					<p class="alcoholData mb-10">
						<input type="text" value="${alcoholData.value1}"
							name="value1Alc${aStatus.index}" required> <input
							type="number" min="0.1" max="100" value="${alcoholData.value4}"
							name="value4Alc${aStatus.index}" step="0.1" required>%
						<input type="number" min="0" value="${alcoholData.value5}"
							name="value5Alc${aStatus.index}" required>kcal/杯 <input type="number" min="1"
							value="${alcoholData.value2}" name="value2Alc${aStatus.index}" required>ml/杯
						<input type="checkbox" value="del" name="delAlc${aStatus.index}">削除
					</p>
				</c:forEach>
			</div>
			<div class="center mb-45">
				<input type="submit" value="登録" class="back-button info-btn">
				<button type="button" onclick="location.href='/back'" class="back-button info-btn"><fmt:message key="form.lbl.back"/></button>
			</div>
		</form>



	</div>
	<script>
	
	/* 食事用処理追加処理 */
	var fnum = document.getElementsByClassName('foodData').length;
	function addFoodData() {
		  var newP = document.createElement('p');
		  newP.innerHTML =
			  '<input name="value1Food' + fnum +
			  '" required> <input type="number" min="0" name="value2Food' + fnum +
			  '" required>kcal<input type="checkbox" value="del" name="delFood' + fnum +
			  '">削除';
	  var parent = document.getElementById('food-list');
	  parent.appendChild(newP);
	  fnum++ ;
	}
	
	/* アルコール用処理追加処理 */
	var anum = document.getElementsByClassName('alcoholData').length;
	function addAlcData() {
	  var newP = document.createElement('p');
	  newP.innerHTML = 
		  '<input name="value1Alc' + anum +
		  '" required> <input type="number" min="0.1" max="100" name="value4Alc' + anum +
		  '" step="0.1" required>%<input type="number" min="0" name="value5Alc' + anum +
		  '" required>kcal/杯 <input type="number" min="1" name="value2Alc' + anum +
		  '" required>ml/杯 <input type="checkbox" value="del" name="delAlc' + anum +
		  '">削除';
	  var parent = document.getElementById('alcohol-list');
	  parent.appendChild(newP);
	  anum++ ;
	}
	
	var date = new Date();
	var yyyy = date.getFullYear();
	var mm = ("0"+(date.getMonth()+1)).slice(-2);
	var dd = ("0"+date.getDate()).slice(-2);
	document.getElementById("todayDate").value=yyyy+'-'+mm+'-'+dd;
	</script>
		<script src="js/commons.js"></script>
</body>
</html>