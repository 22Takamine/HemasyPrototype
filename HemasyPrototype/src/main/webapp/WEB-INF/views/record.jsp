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
	<div class="header-logo"><a href="./back">Hemasy</a></div>
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
	<div id="header"><b>記録画面</b></div>
	<a href="#food">食事</a>
	<a href="#sport">運動</a>
	<a href="#alcohol">酒</a>
	<a href="#smoke">たばこ</a>
	<a href="#weight">体重</a>
	<form action="record">
		<input type="date" name="record_day">
	</form>
	<form action="recordCommit" method="post">
		<div id="food">
			<h2>食事記録</h2>
			<a href="statistics">統計</a>
			<p>目安: ${2}kcal 摂取: ${2}kcal</p>
			<div id="food_bre">
				<p>朝食<button type="button" id="add_bre" onclick="addBreForm()">⊕</button></p>
				<c:forEach var="breakfastRecord" items="${breakfastRecordList}" varStatus="bStatus">
					<p class="breakfastData">
						<input value="${breakfastRecord.value1}" name="value1Bre${bStatus.index}">
						<input type="number" min="0" value="${breakfastRecord.value2}" name="value2Bre${bStatus.index}" id="onceCal_${breakfastRecord.listsAndRecordsId}" onchange="calcCalorie(${breakfastRecord.listsAndRecordsId})">kcal ×
						<input type="number" min="0.1" step="0.1" value="${breakfastRecord.value3}" name="value3Bre${bStatus.index}" id="amount_${breakfastRecord.listsAndRecordsId}" onchange="calcCalorie(${breakfastRecord.listsAndRecordsId})">人前＝
						<span id="calorie_${breakfastRecord.listsAndRecordsId}">${breakfastRecord.value2 * breakfastRecord.value3}</span>kcal
						<input type="hidden" value="${breakfastRecord.createDate}" name="createDate">
						<input type="checkbox" value="del" name="delBre${bStatus.index}">削除
						<input type="checkbox" value="${1}">簡易登録<a href="information.jsp">?</a>
					</p>
				</c:forEach>
			</div>
			<div id="food_lun">
				<p>昼食<button type="button" id="add_lun" onclick="addLunForm()">⊕</button></p>
				<c:forEach var="lunchRecord" items="${lunchRecordList}" varStatus="lStatus">
					<p class="lunchData">
						<input value="${lunchRecord.value1}" name="value1Lun${lStatus.index}">
						<input type="number" min="0" value="${lunchRecord.value2}" name="value2Lun${lStatus.index}" id="onceCal_${lunchRecord.listsAndRecordsId}" onchange="calcCalorie(${lunchRecord.listsAndRecordsId})">kcal ×
						<input type="number" min="0.1" step="0.1" value="${lunchRecord.value3}" name="value3Lun${lStatus.index}" id="amount_${lunchRecord.listsAndRecordsId}" onchange="calcCalorie(${lunchRecord.listsAndRecordsId})">人前＝
						<span id="calorie_${lunchRecord.listsAndRecordsId}">${lunchRecord.value2 * lunchRecord.value3}</span>kcal
						<input type="hidden" value="${lunchRecord.createDate}" name="createDate">
						<input type="checkbox" value="del" name="delLun${lStatus.index}">削除
						<input type="checkbox" value="${1}">簡易登録<a href="information.jsp">?</a>
					</p>
				</c:forEach>
			</div>
			<div id="food_din">
				<p>夕食<button type="button" id="add_din" onclick="addDinForm()">⊕</button></p>
				<c:forEach var="dinnerRecord" items="${dinnerRecordList}" varStatus="dStatus">
					<p class="dinnerData">
						<input value="${dinnerRecord.value1}" name="value1Din${dStatus.index}">
						<input type="number" min="0" value="${dinnerRecord.value2}" name="value2Din${dStatus.index}" id="onceCal_${dinnerRecord.listsAndRecordsId}" onchange="calcCalorie(${dinnerRecord.listsAndRecordsId})"/>kcal ×
						<input type="number" min="0.1" step="0.1" value="${dinnerRecord.value3}" name="value3Din${dStatus.index}" id="amount_${dinnerRecord.listsAndRecordsId}" onchange="calcCalorie(${dinnerRecord.listsAndRecordsId})"/>人前＝
						<span id="calorie_${dinnerRecord.listsAndRecordsId}">${dinnerRecord.value2 * dinnerRecord.value3}</span>kcal
						<input type="hidden" value="${dinnerRecord.createDate}" name="createDate">
						<input type="checkbox" value="del" name="delDin${dStatus.index}">削除
						<input type="hidden" value="${dStatus.index + 100}" id="dIndex">
						<input type="checkbox" value="${1}">簡易登録<a href="information.jsp">?</a>
					</p>
				</c:forEach>
			</div>
			<div id="food_sna">
				<p>間食<button type="button" id="add_sna" onclick="addSnaForm()">⊕</button></p>
				<c:forEach var="snackRecord" items="${snackRecordList}" varStatus="sStatus">
					<p class="snackData">
						<input value="${snackRecord.value1}" name="value1Sna${sStatus.index}">
						<input type="number" min="0" value="${snackRecord.value2}" name="value2Sna${sStatus.index}" id="onceCal_${snackRecord.listsAndRecordsId}" onchange="calcCalorie(${snackRecord.listsAndRecordsId})"/>kcal ×
						<input type="number" min="0.1" step="0.1" value="${snackRecord.value3}" name="value3Sna${sStatus.index}" id="amount_${snackRecord.listsAndRecordsId}" onchange="calcCalorie(${snackRecord.listsAndRecordsId})"/>人前＝
						<span id="calorie_${snackRecord.listsAndRecordsId}">${snackRecord.value2 * snackRecord.value3}</span>kcal
						<input type="hidden" value="${snackRecord.createDate}" name="createDate">
						<input type="checkbox" value="del" name="delSna${sStatus.index}">削除
						<input type="checkbox" value="${1}">簡易登録<a href="information.jsp">?</a>
					</p>
				</c:forEach>
			</div>
		</div>
		<div id="sport">
			<h2>運動記録<button type="button" id="add_spo" onclick="addSpoForm()">⊕</button></h2>
			<a href="statistics">統計</a>
			<p>目安: ${2}kcal 消費: ${2}kcal</p>
			<c:forEach var="sportRecord" items="${sportRecordList}" varStatus="spStatus">
				<p class="sportData">
					<input value="${sportRecord.value1}" name="value1Spo${spStatus.index}">を
					<input type="hidden" value="${sportRecord.value2}" name="value2Spo${spStatus.index}" id="mets_${sportRecord.listsAndRecordsId}">
					<input type="number" min="1" value="${sportRecord.value3}" name="value3Spo${spStatus.index}" id="time_${sportRecord.listsAndRecordsId}" onchange="calcUsedCalorie(${sportRecord.listsAndRecordsId})"/>分運動しました。
					<input type="hidden" value="${sportRecord.createDate}" name="createDate">
					<span id="calorie_${sportRecord.listsAndRecordsId}">${weightRecord.value2 * sportRecord.value2 * sportRecord.value3 / 60 * 1.05}</span>kcal消費
					<input type="checkbox" value="del" name="delSpo${spStatus.index}">削除
				</p>
			</c:forEach>
		</div>
		<div id="smoke">
			<h2>たばこ</h2>
			<a href="statistics">統計</a>
			<p>
				<input type="number" min="0" value="${smokeRecord.value3}" name="value3Smo">本吸いました
				<input type="hidden" value="${smokeRecord.createDate}" name="createDate">
			</p>
		</div>
		<div id="alcohol">
			<h2>アルコール<button type="button" id="add_alc" onclick="addAlcForm()">⊕</button></h2>
			<a href="statistics">統計</a>
			<c:forEach var="alcoholRecord" items="${alcoholRecordList}" varStatus="aStatus">
				<p class="alcoholData">
					<input value="${alcoholRecord.value1}" name="value1Alc${aStatus.index}">
					<input type="number" min="0.1" value="${alcoholRecord.value4}" name="value4Alc${aStatus.index}" id="oncePer_${alcoholRecord.listsAndRecordsId}" step="0.1">%
					<input type="number" min="0" value="${alcoholRecord.value5}" name="value5Alc${aStatus.index}">kcal/杯
					<input type="number" min="1" value="${alcoholRecord.value2}" name="value2Alc${aStatus.index}" id="onceAmount_${alcoholRecord.listsAndRecordsId}">ml/杯を
					<input type="number" min="0.1" step="0.1" value="${alcoholRecord.value3}" name="value3Alc${aStatus.index}" id="amount_${alcoholRecord.listsAndRecordsId}">杯飲みました。
					<input type="hidden" value="${alcoholRecord.createDate}" name="createDate">
					<input type="checkbox" value="del" name="delAlc${aStatus.index}">削除
					<input type="checkbox" value="${1}">簡易登録<a href="information.jsp">?</a>
				</p>
			</c:forEach>
		</div>
		<div id="weight">
			<h2>体重</h2>
			<a href="statistics">統計</a>
			<p>
				体重は<input type="number" min="1" value="${weightRecord.value2}" name="value2Wei" id="weightRecord" step="0.1"/>kg、体脂肪率は<input type="number" min="0.1" value="${weightRecord.value3}" name="value3Wei" step="0.1"/>%です。
				<input type="hidden" value="${weightRecord.createDate}" name="createDate">
			</p>
		</div>
		<input type="submit" value="登録">
	</form>

	

	<button type="button">戻る</button>
	
	<a href="#header">↑</a>
	<script>
	
	
	
	
	
	
	
	
	/* 朝食用処理追加処理 */
	var i = document.getElementsByClassName('breakfastData').length + 1;
	function addBreForm() {
	  var newDiv = document.createElement('div');
	  newDiv.id = 'newBre_' + i;
	  newDiv.innerHTML =
		'<p><input type="hidden" value="2" name="category">' +
		'<input type="hidden" value="1" name="type">' +
		'<input type="text" name="value1"><input type="number" min="0" name="value2" id="onceCal_' + i + 
		'" onchange="calcCalorie(' + i +
		')"/>kcal × <input type="number" min="0.1" step="0.1" name="value3" id="amount_' + i +
		'" onchange="calcCalorie(' + i +
		')"/>人前＝ <span id="calorie_' + i +
		'"></span>kcal' + 
		'<input type="hidden" value="1" name="value4">' + 
		'<input type="hidden" name="createDate">' +
		'<input type="checkbox" value="${1}">簡易登録<a href="information.jsp">?</a></p>';
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
	var i = document.getElementsByClassName('lunchData').length + 1;
	function addLunForm() {
	  var newDiv = document.createElement('div');
	  newDiv.id = 'newLun_' + i;
	  newDiv.innerHTML = '<p><input type="text"><input type="number" id="onceCal_' + i + 
		'" onchange="calcCalorie(' + i +
		')"/>kcal × <input type="number" step="0.1" id="amount_' + i +
		'" onchange="calcCalorie(' + i +
		')"/>人前＝ <span id="calorie_' + i +
		'"></span>kcal<input type="checkbox" value="${1}">簡易登録<a href="information.jsp">?</a></p>';
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
	var i = document.getElementsByClassName('dinnerData').length;
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
	var i = document.getElementsByClassName('snackData').length;
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
	var i = document.getElementsByClassName('sportData').length;
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
	var i = document.getElementsByClassName('alcoholData').length;
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
	
	/* 摂取カロリー計算 */
	function calcCalorie(id){
	    document.getElementById('calorie_' + id).innerHTML = document.getElementById('onceCal_' + id).value * document.getElementById('amount_' + id).value;
	}
	
	/* 消費カロリー計算 */
	function calcUsedCalorie(id) {
		document.getElementById('calorie_' + id).innerHTML = document.getElementById('weightRecord').value * document.getElementById('mets_' + id).value * document.getElementById('time_' + id).value / 60 * 1.05;
	}
	</script>
	<script src="js/commons.js"></script>
</body>
</html>