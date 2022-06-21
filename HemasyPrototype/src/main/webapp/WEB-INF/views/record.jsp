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
<title>記録画面</title>
<link href="css/commons.css" rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<meta name="viewport" content="width=device-width">
</head>
<body>
  

	<header>
		<div class="header-logo" id="header">Hemasy</div>
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
	<h1>記録画面</h1>
	<a href="#food">食事</a>
	<a href="#sport">運動</a>
	<a href="#alcohol">酒</a>
	<a href="#smoke">たばこ</a>
	<a href="#weight">体重</a>
	<form action="record">
		<input type="date" name="record_day">
	</form>
	<form>
		<div id="food">
			<h2>食事記録</h2>
			<a href="statistics">統計</a>
			<p>目安: ${2}kcal 摂取: ${2}kcal</p>
			<div id="food_bre">
				<p>朝食<button type="button" id="add_bre" onclick="addBreForm()">⊕</button></p>
			</div>
			<div id="food_lun">
				<p>昼食<button type="button" id="add_lun" onclick="addLunForm()">⊕</button></p>
			</div>
			<div id="food_din">
				<p>夕食<button type="button" id="add_din" onclick="addDinForm()">⊕</button></p>
			</div>
			<div id="food_sna">
				<p>間食<button type="button" id="add_sna" onclick="addSnaForm()">⊕</button></p>
			</div>
		</div>
		<div id="sport">
			<h2>運動記録<button type="button" id="add_spo" onclick="addSpoForm()">⊕</button></h2>
			<a href="statistics">統計</a>
			<p>目安: ${2}kcal 消費: ${2}kcal</p>
		</div>
		<div id="smoke">
			<h2>たばこ</h2>
			<a href="statistics">統計</a>
			<p>
				今日は<input type="number" value="${2}">本吸いました
			</p>
		</div>
		<div id="alcohol">
			<h2>アルコール<button type="button" id="add_alc" onclick="addAlcForm()">⊕</button></h2>
			<a href="statistics">統計</a>
		</div>
		<div id="weight">
			<h2>体重</h2>
			<a href="statistics">統計</a>
			<p>
				現在の体重は<input type="number" value="${2}" step="0.1">kg、体脂肪率は<input type="number" value="${2}" step="0.1">%です。
			</p>
		</div>
	</form>

	<form:form action="recordRegist" modelAttribute="index" method="post">
		<form:button>
			<fmt:message key="form.lbl.regist" />
		</form:button>
	</form:form>

	<form:form action="back" modelAttribute="index" method="post">
		<form:button>
			<fmt:message key="form.lbl.back" />
		</form:button>
	</form:form>
	
	<a href="#header">↑</a>
	<script>
	
	/* 朝食用処理追加処理 */
	var i = 1 ;
	function addBreForm() {
	  var newDiv = document.createElement('div');
	  newDiv.id = 'newBre_' + i;
	  newDiv.innerHTML = '<p>\
			<input type="text">\
			<input type="number">kcal ×\
			<input type="number" step="0.1">人前＝\
			<input type="number" value="${100}">kcal\
			<input type="checkbox" value="${1}">簡易登録<a href="information.jsp">?</a>\
		</p> ';
	  var parent = document.getElementById('food_bre');
	  parent.appendChild(newDiv);

	  var button_data = document.createElement('button');
	  button_data.type = 'button';
	  button_data.id = i;
	  button_data.onclick = function(){deleteBreBtn(this);}
	  button_data.innerHTML = '削除';
	  var input_area = document.getElementById(newDiv.id);
	  parent.appendChild(button_data);

	  i++ ;
	}

	function deleteBreBtn(target) {
	  var target_id = target.id;
	  var parent = document.getElementById('food_bre');
	  var ipt_id = document.getElementById('newBre_' + target_id);
	  var tgt_id = document.getElementById(target_id);
	  parent.removeChild(ipt_id);
	  parent.removeChild(tgt_id);	
	}
	
	/* 昼食用処理追加処理 */
	var i = 1 ;
	function addLunForm() {
	  var newDiv = document.createElement('div');
	  newDiv.id = 'newLun_' + i;
	  newDiv.innerHTML = '<p>\
			<input type="text">\
			<input type="number">kcal ×\
			<input type="number" step="0.1">人前＝\
			<input type="number" value="${100}">kcal\
			<input type="checkbox" value="${1}">簡易登録<a href="information.jsp">?</a>\
		</p> ';
	  var parent = document.getElementById('food_lun');
	  parent.appendChild(newDiv);

	  var button_data = document.createElement('button');
	  button_data.type = 'button';
	  button_data.id = i;
	  button_data.onclick = function(){deleteLunBtn(this);}
	  button_data.innerHTML = '削除';
	  var input_area = document.getElementById(newDiv.id);
	  parent.appendChild(button_data);

	  i++ ;
	}

	function deleteLunBtn(target) {
	  var target_id = target.id;
	  var parent = document.getElementById('food_lun');
	  var ipt_id = document.getElementById('newLun_' + target_id);
	  var tgt_id = document.getElementById(target_id);
	  parent.removeChild(ipt_id);
	  parent.removeChild(tgt_id);	
	}
	
	/* 夕食用処理追加処理 */
	var i = 1 ;
	function addDinForm() {
	  var newDiv = document.createElement('div');
	  newDiv.id = 'newDin_' + i;
	  newDiv.innerHTML = '<p>\
			<input type="text">\
			<input type="number">kcal ×\
			<input type="number" step="0.1">人前＝\
			<input type="number" value="${100}">kcal\
			<input type="checkbox" value="${1}">簡易登録<a href="information.jsp">?</a>\
		</p> ';
	  var parent = document.getElementById('food_din');
	  parent.appendChild(newDiv);

	  var button_data = document.createElement('button');
	  button_data.type = 'button';
	  button_data.id = i;
	  button_data.onclick = function(){deleteDinBtn(this);}
	  button_data.innerHTML = '削除';
	  var input_area = document.getElementById(newDiv.id);
	  parent.appendChild(button_data);

	  i++ ;
	}

	function deleteDinBtn(target) {
	  var target_id = target.id;
	  var parent = document.getElementById('food_din');
	  var ipt_id = document.getElementById('newDin_' + target_id);
	  var tgt_id = document.getElementById(target_id);
	  parent.removeChild(ipt_id);
	  parent.removeChild(tgt_id);	
	}
	
	/* 間食用処理追加処理 */
	var i = 1 ;
	function addSnaForm() {
	  var newDiv = document.createElement('div');
	  newDiv.id = 'newSna_' + i;
	  newDiv.innerHTML = '<p>\
			<input type="text">\
			<input type="number">kcal ×\
			<input type="number" step="0.1">人前＝\
			<input type="number" value="${100}">kcal\
			<input type="checkbox" value="${1}">簡易登録<a href="information.jsp">?</a>\
		</p> ';
	  var parent = document.getElementById('food_sna');
	  parent.appendChild(newDiv);

	  var button_data = document.createElement('button');
	  button_data.type = 'button';
	  button_data.id = i;
	  button_data.onclick = function(){deleteSnaBtn(this);}
	  button_data.innerHTML = '削除';
	  var input_area = document.getElementById(newDiv.id);
	  parent.appendChild(button_data);

	  i++ ;
	}

	function deleteSnaBtn(target) {
	  var target_id = target.id;
	  var parent = document.getElementById('food_sna');
	  var ipt_id = document.getElementById('newSna_' + target_id);
	  var tgt_id = document.getElementById(target_id);
	  parent.removeChild(ipt_id);
	  parent.removeChild(tgt_id);	
	}
	
	/* 運動用処理追加処理 */
	var i = 1 ;
	function addSpoForm() {
	  var newDiv = document.createElement('div');
	  newDiv.id = 'newSpo_' + i;
	  newDiv.innerHTML = '<p>\
			<input type="text">を\
			<input type="number">分運動しました。\
			<input type="number" value="${100}">kcal\
			</p>';
	  var parent = document.getElementById('sport');
	  parent.appendChild(newDiv);

	  var button_data = document.createElement('button');
	  button_data.type = 'button';
	  button_data.id = i;
	  button_data.onclick = function(){deleteSpoBtn(this);}
	  button_data.innerHTML = '削除';
	  var input_area = document.getElementById(newDiv.id);
	  parent.appendChild(button_data);

	  i++ ;
	}

	function deleteSpoBtn(target) {
	  var target_id = target.id;
	  var parent = document.getElementById('sport');
	  var ipt_id = document.getElementById('newSpo_' + target_id);
	  var tgt_id = document.getElementById(target_id);
	  parent.removeChild(ipt_id);
	  parent.removeChild(tgt_id);	
	}
	
	/* アルコール用処理追加処理 */
	var i = 1 ;
	function addAlcForm() {
	  var newDiv = document.createElement('div');
	  newDiv.id = 'newAlc_' + i;
	  newDiv.innerHTML = '<p>\
	  	<input type="text" value="${1}">\
		<input type="number" value="${1}" step="0.1">%\
		<input type="number" value="${1}">kcal/杯\
		<input type="number" value="${1}">ml/杯を\
		<input type="number" value="${1}">杯飲みました。\
		<input type="checkbox" value="${1}">簡易登録<a href="information.jsp"></a>\
		</p>';
	  var parent = document.getElementById('alcohol');
	  parent.appendChild(newDiv);

	  var button_data = document.createElement('button');
	  button_data.type = 'button';
	  button_data.id = i;
	  button_data.onclick = function(){deleteAlcBtn(this);}
	  button_data.innerHTML = '削除';
	  var input_area = document.getElementById(newDiv.id);
	  parent.appendChild(button_data);

	  i++ ;
	}

	function deleteAlcBtn(target) {
	  var target_id = target.id;
	  var parent = document.getElementById('alcohol');
	  var ipt_id = document.getElementById('newAlc_' + target_id);
	  var tgt_id = document.getElementById(target_id);
	  parent.removeChild(ipt_id);
	  parent.removeChild(tgt_id);	
	}
	</script>
	<script src="js/commons.js"></script>
</body>
</html>