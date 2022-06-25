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
	<div id="header">
		<b>記録画面</b>
	</div>
	<a href="#food">食事</a>
	<a href="#sport">運動</a>
	<a href="#alcohol">酒</a>
	<a href="#smoke">たばこ</a>
	<a href="#weight">体重</a>
	<form action="record">
		<input type="date" name="record_day">
	</form>
	<form action="recordCommit" method="post">
		<input type="hidden" value="${dataDate}" name="createDate">
		<div id="food">
			<h2>食事記録</h2>
			<a href="statistics">統計</a>
			<p>目安: ${2}kcal 摂取: ${2}kcal</p>
			<div id="food_bre">
				<p>
					朝食
					<button type="button" id="add_bre" onclick="addBreForm()">⊕</button>
				</p>
				<c:forEach var="breakfastRecord" items="${breakfastRecordList}"
					varStatus="bStatus">
					<p class="breakfastData">
						<input value="${breakfastRecord.value1}"
							name="value1Bre${bStatus.index}" list="foodList" onchange="changeBre(${bStatus.index})" id="nameMor_${bStatus.index}"> <input type="number"
							min="0" value="${breakfastRecord.value2}"
							name="value2Bre${bStatus.index}" id="onceCalMor_${bStatus.index}"
							onchange="calcCalorieMor(${bStatus.index})">kcal × <input
							type="number" min="0.1" step="0.1"
							value="${breakfastRecord.value3}"
							name="value3Bre${bStatus.index}" id="amountMor_${bStatus.index}"
							onchange="calcCalorieMor(${bStatus.index})">人前＝ <span
							id="calorieMor_${bStatus.index}">${breakfastRecord.value2 * breakfastRecord.value3}</span>kcal
						<input type="checkbox" value="del" name="delBre${bStatus.index}">削除
						<input type="checkbox" value="${1}">簡易登録<a
							href="information.jsp">?</a>
					</p>
				</c:forEach>
			</div>
			<div id="food_lun">
				<p>
					昼食
					<button type="button" id="add_lun" onclick="addLunForm()">⊕</button>
				</p>
				<c:forEach var="lunchRecord" items="${lunchRecordList}"
					varStatus="lStatus">
					<p class="lunchData">
						<input value="${lunchRecord.value1}"
							name="value1Lun${lStatus.index}" list="foodList"> <input type="number"
							min="0" value="${lunchRecord.value2}"
							name="value2Lun${lStatus.index}" id="onceCalLun_${lStatus.index}"
							onchange="calcCalorieLun(${lStatus.index})">kcal × <input
							type="number" min="0.1" step="0.1" value="${lunchRecord.value3}"
							name="value3Lun${lStatus.index}" id="amountLun_${lStatus.index}"
							onchange="calcCalorieLun(${lStatus.index})">人前＝ <span
							id="calorieLun_${lStatus.index}">${lunchRecord.value2 * lunchRecord.value3}</span>kcal
						<input type="checkbox" value="del" name="delLun${lStatus.index}">削除
						<input type="checkbox" value="${1}">簡易登録<a
							href="information.jsp">?</a>
					</p>
				</c:forEach>
			</div>
			<div id="food_din">
				<p>
					夕食
					<button type="button" id="add_din" onclick="addDinForm()">⊕</button>
				</p>
				<c:forEach var="dinnerRecord" items="${dinnerRecordList}"
					varStatus="dStatus">
					<p class="dinnerData">
						<input value="${dinnerRecord.value1}"
							name="value1Din${dStatus.index}" list="foodList"> <input type="number"
							min="0" value="${dinnerRecord.value2}"
							name="value2Din${dStatus.index}" id="onceCalDin_${dStatus.index}"
							onchange="calcCalorieDin(${dStatus.index})" />kcal × <input
							type="number" min="0.1" step="0.1" value="${dinnerRecord.value3}"
							name="value3Din${dStatus.index}" id="amountDin_${dStatus.index}"
							onchange="calcCalorieDin(${dStatus.index})" />人前＝ <span
							id="calorieDin_${dStatus.index}">${dinnerRecord.value2 * dinnerRecord.value3}</span>kcal
						<input type="checkbox" value="del" name="delDin${dStatus.index}">削除
						<input type="hidden" value="${dStatus.index + 100}" id="dIndex">
						<input type="checkbox" value="${1}">簡易登録<a
							href="information.jsp">?</a>
					</p>
				</c:forEach>
			</div>
			<div id="food_sna">
				<p>
					間食
					<button type="button" id="add_sna" onclick="addSnaForm()">⊕</button>
				</p>
				<c:forEach var="snackRecord" items="${snackRecordList}"
					varStatus="sStatus">
					<p class="snackData">
						<input value="${snackRecord.value1}"
							name="value1Sna${sStatus.index}" list="foodList"> <input type="number"
							min="0" value="${snackRecord.value2}"
							name="value2Sna${sStatus.index}" id="onceCalSna_${sStatus.index}"
							onchange="calcCalorieSna(${sStatus.index})" />kcal × <input
							type="number" min="0.1" step="0.1" value="${snackRecord.value3}"
							name="value3Sna${sStatus.index}" id="amountSna_${sStatus.index}"
							onchange="calcCalorieSna(${sStatus.index})" />人前＝ <span
							id="calorieSna_${sStatus.index}">${snackRecord.value2 * snackRecord.value3}</span>kcal
						<input type="checkbox" value="del" name="delSna${sStatus.index}">削除
						<input type="checkbox" value="${1}">簡易登録<a
							href="information.jsp">?</a>
					</p>
				</c:forEach>
			</div>
			<datalist id="foodList">
			</datalist>
		</div>
		<div id="sport">
			<h2>
				運動記録
				<button type="button" id="add_spo" onclick="addSpoForm()">⊕</button>
			</h2>
			<a href="statistics">統計</a>
			<p>目安: ${2}kcal 消費: ${2}kcal</p>
			<c:forEach var="sportRecord" items="${sportRecordList}"
				varStatus="spStatus">
				<p class="sportData">
					<input value="${sportRecord.value1}"
						name="value1Spo${spStatus.index}" list="sportList">を <input type="hidden"
						value="${sportRecord.value2}" name="value2Spo${spStatus.index}"
						id="mets_${spStatus.index}"> <input type="number" min="1"
						value="${sportRecord.value3}" name="value3Spo${spStatus.index}"
						id="time_${spStatus.index}"
						onchange="calcUsedCalorie(${spStatus.index})" />分運動しました。 <span
						id="calorie_${spStatus.index}">${weightRecord.value2 * sportRecord.value2 * sportRecord.value3 / 60 * 1.05}</span>kcal消費
					<input type="checkbox" value="del" name="delSpo${spStatus.index}">削除
				</p>
			</c:forEach>
			<datalist id="sportList">
			</datalist>
		</div>
		<div id="smoke">
			<h2>たばこ</h2>
			<a href="statistics">統計</a>
			<p>
				<input type="number" min="0" value="${smokeRecord.value3}"
					name="value3Smo">本吸いました
			</p>
		</div>
		<div id="alcohol">
			<h2>
				アルコール
				<button type="button" id="add_alc" onclick="addAlcForm()">⊕</button>
			</h2>
			<a href="statistics">統計</a>
			<c:forEach var="alcoholRecord" items="${alcoholRecordList}"
				varStatus="aStatus">
				<p class="alcoholData">
					<input value="${alcoholRecord.value1}"
						name="value1Alc${aStatus.index}" list="alcoholList"> <input type="number"
						min="0.1" max="100" value="${alcoholRecord.value4}"
						name="value4Alc${aStatus.index}" id="oncePer_${aStatus.index}"
						step="0.1" onchange="calcAlc(${aStatus.index})">% <input
						type="number" min="0" value="${alcoholRecord.value5}"
						name="value5Alc${aStatus.index}">kcal/杯 <input
						type="number" min="1" value="${alcoholRecord.value2}"
						name="value2Alc${aStatus.index}" id="onceAmount_${aStatus.index}"
						onchange="calcAlc(${aStatus.index})">ml/杯を <input
						type="number" min="0.1" step="0.1" value="${alcoholRecord.value3}"
						name="value3Alc${aStatus.index}" id="amount_${aStatus.index}"
						onchange="calcAlc(${aStatus.index})">杯飲みました。 アルコール <span
						id="alcoholAmount_${aStatus.index}">${alcoholRecord.value2 * alcoholRecord.value3 * alcoholRecord.value4 / 100}</span>g
					<input type="checkbox" value="del" name="delAlc${aStatus.index}">削除
					<input type="checkbox" value="${1}">簡易登録<a
						href="information.jsp">?</a>
				</p>
			</c:forEach>
			<datalist id="alcoholList">
			</datalist>
		</div>
		<div id="weight">
			<h2>体重</h2>
			<a href="statistics">統計</a>
			<p>
				体重は<input type="number" min="1" value="${weightRecord.value2}"
					name="value2Wei" id="weightRecord" step="0.1" />kg、体脂肪率は<input
					type="number" min="0.1" value="${weightRecord.value3}"
					name="value3Wei" step="0.1" />%です。
			</p>
		</div>
		<input type="submit" value="登録">
	</form>
	
	<button type="button">戻る</button>

	<a href="#header">↑</a>
	<script>
	
	foodList = [];
	
	fetch("/getList?type=1")
	.then(function(response1) { //成功時に実行される
		return response1.json();
	})
	.then(function(data1) { //成功時に実行される
		console.log(data1)
		foodList = data1
		console.log(foodList)
	})
	

	sportList = [];
	
	fetch("/getList?type=2")
	.then(function(response2) { //成功時に実行される
		return response2.json();
	})
	.then(function(data2) { //成功時に実行される
		sportList = data2
	})
	
	
	alcoholList = [];
	
	fetch("/getList?type=4")
	.then(function(response3) { //成功時に実行される
		return response3.json();
	})
	.then(function(data3) { //成功時に実行される
		alcoholList = data3
	})
	
	window.setTimeout(function(){
		
		var foodListSelect = document.getElementById('foodList');
		var sportListSelect = document.getElementById('sportList');
		var alcoholListSelect = document.getElementById('alcoholList');
		
		for (let i = 0; i < foodList.length; i++) {
			var option = document.createElement('option');
			option.value = foodList[i]['value1'];
			foodListSelect.appendChild(option);
		}
		
		for (let i = 0; i < sportList.length; i++) {
			var option = document.createElement('option');
			option.value = sportList[i]['value1'];
			sportListSelect.appendChild(option);
		}
		
		for (let i = 0; i < alcoholList.length; i++) {
			var option = document.createElement('option');
			option.value = alcoholList[i]['value1'];
			alcoholListSelect.appendChild(option);
		}
		
	}, 500);
	
	
	/* 朝食をリストから選んだ際にカロリーを自動入力 */
	function changeBre(id) {
		var targetName = document.getElementById('nameMor_' + id);
		var targetCalorie = document.getElementById('onceCalMor_' + id);
		for (let i = 0; i < foodList.length; i++) {
			if (foodList[i]['value1'] == targetName.value) {
				targetCalorie.value = foodList[i]['value2'];
				break;
			}
		}
	}
	
	/* 朝食用処理追加処理 */
	var mnum = document.getElementsByClassName('breakfastData').length;
	function addBreForm() {
		  console.log("朝食用" + mnum);
		  var newP = document.createElement('p');
		  newP.innerHTML =
		'<input name="value1Bre' + mnum +
		'" list="foodList" onchange="changeBre(' + mnum +
		')" id="nameMor_' + mnum + 
		'"><input type="number" min="0" name="value2Bre' + mnum + 
		'" id="onceCalMor_' + mnum +
		'" onchange="calcCalorieMor(' + mnum +
		')">kcal ×<input type="number" min="0.1" step="0.1" name="value3Bre' + mnum + 
		'" id="amountMor_' + mnum + 
		'" onchange="calcCalorieMor(' + mnum +
		')">人前＝ <span id="calorieMor_' + mnum +
		'"></span>kcal' +
		'<input type="checkbox" value="del" name="delBre' + mnum +
		'">削除<input type="checkbox" value="${1}">簡易登録<a href="information.jsp">?</a>';
	  var parent = document.getElementById('food_bre');
	  parent.appendChild(newP);
	  mnum++ ;
	}
	
	/* 昼食用処理追加処理 */
	var lnum = document.getElementsByClassName('lunchData').length;
	function addLunForm() {
		console.log("昼食用" + lnum);
	  	var newP = document.createElement('p');
	  	newP.innerHTML =
		 	'<input name="value1Lun' + lnum +
			'" list="foodList"><input type="number" min="0" name="value2Lun' + lnum + 
			'" id="onceCalLun_' + lnum +
			'" onchange="calcCalorieLun(' + lnum +
			')">kcal ×<input type="number" min="0.1" step="0.1" name="value3Lun' + lnum + 
			'" id="amountLun_' + lnum + 
			'" onchange="calcCalorieLun(' + lnum +
			')">人前＝ <span id="calorieLun_' + lnum +
			'"></span>kcal' +
			'<input type="checkbox" value="del" name="delLun' + lnum +
			'">削除<input type="checkbox" value="${1}">簡易登録<a href="information.jsp">?</a>';
	  var parent = document.getElementById('food_lun');
	  parent.appendChild(newP);
	  lnum++ ;
	}
	
	/* 夕食用処理追加処理 */
	var dnum = document.getElementsByClassName('dinnerData').length;
	function addDinForm() {
		console.log("夕食用" + dnum);
	  var newP = document.createElement('p');
	  newP.innerHTML =
		    '<input name="value1Din' + dnum +
			'" list="foodList"><input type="number" min="0" name="value2Din' + dnum + 
			'" id="onceCalDin_' + dnum +
			'" onchange="calcCalorieDin(' + dnum +
			')">kcal ×<input type="number" min="0.1" step="0.1" name="value3Din' + dnum + 
			'" id="amountDin_' + dnum + 
			'" onchange="calcCalorieDin(' + dnum +
			')">人前＝ <span id="calorieDin_' + dnum +
			'"></span>kcal' +
			'<input type="checkbox" value="del" name="delDin' + dnum +
			'">削除<input type="checkbox" value="${1}">簡易登録<a href="information.jsp">?</a>';
	  var parent = document.getElementById('food_din');
	  parent.appendChild(newP);
	  dnum++ ;
	}
	
	/* 間食用処理追加処理 */
	var snum = document.getElementsByClassName('snackData').length;
	function addSnaForm() {
		console.log("間食用" + snum);
	  var newP = document.createElement('p');
	  newP.innerHTML =
		    '<input name="value1Sna' + snum +
			'" list="foodList"><input type="number" min="0" name="value2Sna' + snum + 
			'" id="onceCalSna_' + snum +
			'" onchange="calcCalorieSna(' + snum +
			')">kcal ×<input type="number" min="0.1" step="0.1" name="value3Sna' + snum + 
			'" id="amountSna_' + snum + 
			'" onchange="calcCalorieSna(' + snum +
			')">人前＝ <span id="calorieSna_' + snum +
			'"></span>kcal' +
			'<input type="checkbox" value="del" name="delSna' + snum +
			'">削除<input type="checkbox" value="${1}">簡易登録<a href="information.jsp">?</a>';
	  var parent = document.getElementById('food_sna');
	  parent.appendChild(newP);
	  snum++ ;
	}
	
	/* 運動用処理追加処理 */
	var spnum = document.getElementsByClassName('sportData').length;
	function addSpoForm() {
	  var newP = document.createElement('p');
	  newP.innerHTML =
		  '<input name="value1Spo' + spnum +
		  '" list="sportList">を <input type="hidden" name="value2Spo' + spnum +
		  '"id="mets_' + spnum +
		  '"> <input type="number" min="1" name="value3Spo' + spnum +
		  '" id="time_' + spnum +
		  '" onchange="calcUsedCalorie(' + spnum +
		  ')" />分運動しました。<span id="calorie_' + spnum +
		  '">${weightRecord.value2 * sportRecord.value2 * sportRecord.value3 / 60 * 1.05}</span>kcal消費' +
		  '<input type="checkbox" value="del" name="delSpo' + spnum +
		  '">削除';
	  var parent = document.getElementById('sport');
	  parent.appendChild(newP);
	  spnum++ ;
	}
	
	/* アルコール用処理追加処理 */
	var anum = document.getElementsByClassName('alcoholData').length;
	function addAlcForm() {
	  var newP = document.createElement('p');
	  newP.innerHTML = 
		  '<input name="value1Alc' + anum +
		  '" list="alcoholList"> <input type="number" min="0.1" max="100" name="value4Alc' + anum +
		  '"id="oncePer_' + anum +
		  '" step="0.1" onchange="calcAlc(' + anum +
		  ')">% <input type="number" min="0" name="value5Alc' + anum + 
		  '">kcal/杯 <input type="number" min="1" name="value2Alc' + anum +
		  '"id="onceAmount_' + anum + 
		  '" onchange="calcAlc(' + anum +
		  ')">ml/杯を <input type="number" min="0.1" step="0.1" name="value3Alc' + anum +
		  '" id="amount_' + anum +
		  '" onchange="calcAlc(' + anum +
		  ')">杯飲みました。 アルコール<span id="alcoholAmount_' + anum +
		  '"></span>g<input type="checkbox" value="del" name="delAlc' + anum +
		  '">削除 <input type="checkbox" value="${1}">簡易登録<a href="information.jsp">?</a>';
	  var parent = document.getElementById('alcohol');
	  parent.appendChild(newP);
	  anum++ ;
	}
	
	/* 摂取カロリー計算 */
	function calcCalorieMor(id){
		console.log(id);
		console.log('一戸カロリー' + document.getElementById('onceCalMor_' + id).value);
		console.log('人前' + document.getElementById('amountMor_' + id).value);
	    document.getElementById('calorieMor_' + id).innerHTML = document.getElementById('onceCalMor_' + id).value * document.getElementById('amountMor_' + id).value;
	}
	
	function calcCalorieLun(id){
		console.log(id);
		console.log('一戸カロリー' + document.getElementById('onceCalLun_' + id).value);
		console.log('人前' + document.getElementById('amountLun_' + id).value);
	    document.getElementById('calorieLun_' + id).innerHTML = document.getElementById('onceCalLun_' + id).value * document.getElementById('amountLun_' + id).value;
	}
	
	function calcCalorieDin(id){
		console.log(id);
		console.log('一戸カロリー' + document.getElementById('onceCalDin_' + id).value);
		console.log('人前' + document.getElementById('amountDin_' + id).value);
	    document.getElementById('calorieDin_' + id).innerHTML = document.getElementById('onceCalDin_' + id).value * document.getElementById('amountDin_' + id).value;
	}
	
	function calcCalorieSna(id){
		console.log(id);
		console.log('一戸カロリー' + document.getElementById('onceCalSna_' + id).value);
		console.log('人前' + document.getElementById('amountSna_' + id).value);
	    document.getElementById('calorieSna_' + id).innerHTML = document.getElementById('onceCalSna_' + id).value * document.getElementById('amountSna_' + id).value;
	}
	
	/* 消費カロリー計算 */
	function calcUsedCalorie(id) {
		document.getElementById('calorie_' + id).innerHTML = document.getElementById('weightRecord').value * document.getElementById('mets_' + id).value * document.getElementById('time_' + id).value / 60 * 1.05;
	}
	
	/* アルコール計算 */
	function calcAlc(id) {
		console.log(id);
		console.log(document.getElementById('onceAmount_' + id).value);
		console.log(document.getElementById('oncePer_' + id).value);
		console.log(document.getElementById('alcoholAmount_' + id).className);
		document.getElementById('alcoholAmount_' + id).innerHTML = document.getElementById('onceAmount_' + id).value * document.getElementById('oncePer_' + id).value * document.getElementById('amount_' + id).value / 100;
	}
	</script>
	<script src="js/commons.js"></script>
</body>
</html>