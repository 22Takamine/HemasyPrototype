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
<title>管理者画面</title>
<link href="css/common.css" rel="stylesheet">
<link href="css/ameku.css" rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<meta name="viewport" content="width=device-width">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
</head>
<body>
	<header>
		<div class="header-logo">
			<a href="./admin">Hemasy</a>
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
	<div class="main admin-page-main">
		<h2 class="center f-32">管理ページ</h2>
		<form action="adminListCommit" method="post">
			<input type="hidden" name="createDate" id="todayDate">
			<div id="user admin-user" class="center mb-45 bgc">
				<h2>
					ユーザー一覧
				</h2>
				<table>
				    <thead>
				      <tr>
				        <th class="center">名前</th>
				        <th class="center">メールアドレス</th>
				      </tr>
				    </thead>
				    <tbody>
				      <c:forEach var="userData" items="${userList}" varStatus="uStatus">
				      <input type="hidden" name="userId${uStatus.index}" value="${userData.userId}">
				        <tr>
				          <td><c:out value="${userData.userName}"/></td>
				          <td><c:out value="${userData.mail}"/></td>
				        </tr>
					  </c:forEach>
				    </tbody>
				 </table>
				
			</div>
			<div id="food admin-food" class="center mb-45 bgc">
				<h2>
					食べ物リスト
					<button type="button" onclick="addFoodData()">⊕</button>
				</h2>
				<c:forEach var="foodData" items="${foodList}" varStatus="fStatus">
					<p class="foodData mb-10">
						<input type="text" value="${foodData.value1}"
							name="value1Food${fStatus.index}" required> <input
							type="number" min="0" value="${foodData.value2}"
							name="value2Food${fStatus.index}" required>kcal <input
							type="checkbox" value="del" name="delFood${fStatus.index}">削除
					</p>
				</c:forEach>
			</div>
			<div id="sport admin-sport" class="center mb-45 bgc">
				<h2>
					運動リスト
					<button type="button" onclick="addSpoData()">⊕</button>
				</h2>
				<c:forEach var="sportData" items="${sportList}" varStatus="sStatus">
					<p class="sportData mb-10">
						<input type="text" value="${sportData.value1}"
							name="value1Spo${sStatus.index}" required> <input
							type="number" min="0" value="${sportData.value2}"
							name="value2Spo${sStatus.index}" required>メッツ <input
							type="checkbox" value="del" name="delSpo${sStatus.index}">削除
					</p>
				</c:forEach>
			</div>
			<div id="alcohol admin-alcohol" class="center mb-45 bgc">
				<h2>
					アルコール
					<button type="button" onclick="addAlcData()">⊕</button>
				</h2>
				<c:forEach var="alcoholData" items="${alcoholList}"
					varStatus="aStatus">
					<p class="alcoholData mb-10">
						<input type="text" value="${alcoholData.value1}"
							name="value1Alc${aStatus.index}" required> <input
							type="number" min="0.1" max="100" value="${alcoholData.value4}"
							name="value4Alc${aStatus.index}" step="0.1" required>% <input
							type="number" min="0" value="${alcoholData.value5}"
							name="value5Alc${aStatus.index}" required>kcal/杯 <input
							type="number" min="1" value="${alcoholData.value2}"
							name="value2Alc${aStatus.index}" required>ml/杯 <input
							type="checkbox" value="del" name="delAlc${aStatus.index}">削除
					</p>
				</c:forEach>
			</div>
			<div class="center mb-30">
				<input type="submit" value="登録" class="info-btn">
			<button type="button" onclick="location.href='/back'" class="back-button info-btn"><fmt:message key="form.lbl.back"/></button>
			</div>
		</form>


	</div>
	<script>
		/* 食事用処理追加処理 */
		var fnum = document.getElementsByClassName('foodData').length;
		function addFoodData() {
			console.log("食事" + fnum);
			var newP = document.createElement('p');
			newP.innerHTML = '<input name="value1Food' + fnum +
			  '" required> <input type="number" min="0" name="value2Food' + fnum +
			  '" required>kcal<input type="checkbox" value="del" name="delFood' + fnum +
			  '">削除';
			var parent = document.getElementById('food');
			parent.appendChild(newP);
			fnum++;
		}
		/* 運動用処理追加処理 */
		var snum = document.getElementsByClassName('sportData').length;
		function addSpoData() {
			console.log("運動" + fnum);
			var newP = document.createElement('p');
			newP.innerHTML = '<input name="value1Spo' + snum +
			  '" required> <input type="number" min="0" name="value2Spo' + snum +
			  '" required>メッツ<input type="checkbox" value="del" name="delSpo' + snum +
			  '">削除';
			var parent = document.getElementById('sport');
			parent.appendChild(newP);
			snum++;
		}
		/* アルコール用処理追加処理 */
		var anum = document.getElementsByClassName('alcoholData').length;
		function addAlcData() {
			var newP = document.createElement('p');
			newP.innerHTML = '<input name="value1Alc' + anum +
		  '" required> <input type="number" min="0.1" max="100" name="value4Alc' + anum +
		  '" step="0.1" required>%<input type="number" min="0" name="value5Alc' + anum +
		  '" required>kcal/杯 <input type="number" min="1" name="value2Alc' + anum +
		  '" required>ml/杯 <input type="checkbox" value="del" name="delAlc' + anum +
		  '">削除';
			var parent = document.getElementById('alcohol');
			parent.appendChild(newP);
			anum++;
		}
		var date = new Date();
		var yyyy = date.getFullYear();
		var mm = ("0" + (date.getMonth() + 1)).slice(-2);
		var dd = ("0" + date.getDate()).slice(-2);
		document.getElementById("todayDate").value = yyyy + '-' + mm + '-' + dd;
	</script>
	<script src="js/commons.js"></script>
</body>
</html>