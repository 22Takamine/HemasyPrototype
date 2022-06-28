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
<title>統計画面</title>
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
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.2.0/chart.min.js"
	integrity="sha512-VMsZqo0ar06BMtg0tPsdgRADvl0kDHpTbugCBBrL55KmucH6hP9zWdLIWY//OTfMnzz6xWQRxQqsUFefwHuHyg=="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns@next/dist/chartjs-adapter-date-fns.bundle.min.js"></script>
</head>

<body>
	<header>
		<div class="header-logo">
			<a class="f-24 logoF" href="./back">Hemasy</a>
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
	<script src="js/commons.js"></script>

	<div class="main ranking">
	
	<h1>統計</h1>
	<input type="text" id="hide" value="0">
	<input type="text" id="hideType" value="1">
	<div id="selectGraph">
		<button name="0" data-index="food" onclick="entryClick(1)">食事</button>
		<button name="1" data-index="exercise" onclick="entryClick(2)">運動</button>
		<button name="2" data-index="alcohol" onclick="entryClick(3)">酒</button>
		<button name="3" data-index="smoke" onclick="entryClick(4)">タバコ</button>
		<button name="4 " data-index="bmi" onclick="entryClick(5)">体重</button>
	</div>
	<div>
		<label><input type="radio" name="poti" value="0" checked
			onclick="poti(0)">週</label> <label><input type="radio"
			name="poti" value="1" onclick="poti(1)">月</label> <label><input
			type="radio" name="poti" value="2" onclick="poti(2)">年</label>

	</div>
	<input type="button" value="左" onclick="getName()">
	<input type="button" value="右"> ${name}
	<div>
		<input type="date" id="date" value="${user.getCreatedAt()}">
	</div>
	<div style="width: 800px">
		<canvas id="foodGraph"></canvas>
	</div>
	<div style="width: 800px">
		<canvas id="exerciseGraph"></canvas>
	</div>
	<div style="width: 800px">
		<canvas id="alcoholGraph"></canvas>
	</div>
	<div style="width: 800px">
		<canvas id="smokeGraph"></canvas>
	</div>
	<div style="width: 800px">
		<canvas id="bmiGraph"></canvas>
	</div>
</div>
	
	<script>
function poti(checkValue) {
	let elements = document.getElementsByName('poti');
	let len = elements.length;
	document.getElementById("hide").value = checkValue;
	
	if(document.getElementById("hideType").value == 1){
		getFoodList();
	}else if(document.getElementById("hideType").value == 2){
		getExerciseList();
	}else if(document.getElementById("hideType").value == 3){
		getAlcoholList();
	}else if(document.getElementById("hideType").value == 4){
		getSmokeList();
	}else if(document.getElementById("hideType").value == 5){
		getBmiList();
	}

};
</script>
	<script>
//食事記録グラフの作成　ユーザーの目標摂取カロリーをセッションから取得するようにする。
let foodList = [];
let goalCalorie = [];
var day = document.getElementById("date");
var scope = document.getElementById("hide");
//データの取得
function getFoodList() {
	console.log('day', day.value)
	console.log('scope', scope.value)
	fetch('/getFoodListWeek?id=' + ${user.getUserId()} + '&day=' + day.value + '&scope=' + scope.value)
	.then(res => res.json()
	.then(data => {
					foodList = data
					foodList.forEach(
						function(createDay) {
							goalCalorie.push(${user.getGoalCalorie()});
						}
					);
					//食事記録グラフに使うデータ
					var foodGraphData = {
						//グラフの下　日付
						labels: foodList.map(item => item.createDay),
						datasets: [
							{
								label: '摂取カロリーの推移',
								data: foodList.map(item => item.value2),
								borderColor: '#484',
							}, {
								label: 'ユーザーの目標摂取カロリー',
								data: goalCalorie,
								borderColor: '#ff0000',
							}
						],
					}
					//食事記録グラフの描画
					var ctx = document.getElementById("foodGraph");
					
					if (typeof foodListChart !== 'undefined') {
					    foodListChart.destroy();
					}
					
					foodListChart = new Chart(ctx, {
						type: 'line',
						data: foodGraphData,
						options: complexChartOption
					})
	}))
	.catch(error => console.log(error))
};
</script>

	<script>
//運動記録グラフの作成　ユーザーの目標運動時間をセッションから取得するようにする。
let exerciseList = [];
let goalExerciseTime = [];
var scope = document.getElementById("hide");
//データの取得
function getExerciseList() {

	fetch('/getExerciseListWeek?id=' + ${user.getUserId()} + '&day=' + day.value + '&scope=' + scope.value)
	.then(res => res.json().then(data => {
		exerciseList = data
		console.log(data)
		console.log(exerciseList)
		exerciseList.forEach(function(createDate) {
			goalExerciseTime.push(${user.getGoalExerciseTime()});
		});

		//運動記録グラフに使うデータ
		var exerciseGraphData = {
			//グラフの下　日付
			labels: exerciseList.map(item => item.createDay),
			datasets: [{
				label: '運動時間の推移',
				data: exerciseList.map(item => item.value4),
				borderColor: '#F36C21',
				yAxisID : "exerciseTimeShaft",// 追加
			},{
				label: 'ユーザーの目標運動時間',
				data: goalExerciseTime,
				borderColor: '#ff0000',
				yAxisID : "exerciseTimeShaft",// 追加
			}, {
				label: '消費カロリーの推移',
				data: exerciseList.map(item => item.value3),
				borderColor: '#48f',
				yAxisID : "y-axis-2",// 追加
			}],
		}
		var ctx = document.getElementById("exerciseGraph");
		
		
		if (typeof exerciseListChart !== 'undefined') {
		    exerciseListChart.destroy();
		}
		exerciseListChart = new Chart(ctx, {
			type: 'line',
			data: exerciseGraphData,
			options: exerciseChartOption
		})
	}))
	.catch(error => console.log(error))
};
</script>

	<script>
//アルコール記録グラフ
let alcoholList = [];
let deadLine = [];
//データの取得
function getAlcoholList() {
	fetch('/getAlcoholListWeek?id=' + ${user.getUserId()} + '&day=' + day.value + '&scope=' + scope.value)
	.then(res => res.json().then(data => {
		alcoholList = data
		console.log(data)
		console.log(exerciseList)
		alcoholList.forEach(function(createDay) {
			deadLine.push(20);
		});
		//アルコール量記録グラフに使うデータ
		var alcoholGraphData = {
			//グラフの下　日付
			labels: alcoholList.map(item => item.createDay),
			datasets: [{
				label: '摂取アルコール量の推移',
				data: alcoholList.map(item => item.value2),
				borderColor: '#F36C21',
			},{
				label: 'デッドライン',
				data: deadLine,
				borderColor: '#ff0000',
			}],
		}
	var ctx = document.getElementById("alcoholGraph");
		
	if (typeof alcoholListChart !== 'undefined') {
	    alcoholListChart.destroy();
	}
		
	alcoholListChart = new Chart(ctx, {
		type: 'line',
		data: alcoholGraphData,
		options: complexChartOption
	})
}))
	.catch(error => console.log(error))
};
</script>
	<script>
//タバコ記録グラフ
let smokeList = [];
//データの取得
function getSmokeList() {
	fetch('/getSmokeListWeek?id=' + ${user.getUserId()} + '&day=' + day.value + '&scope=' + scope.value)
	.then(res => res.json().then(data => {
		smokeList = data
		console.log(data)
		console.log(smokeList)
		//タバコ記録グラフに使うデータ
		var smokeGraphData = {
			//グラフの下　日付
			labels: smokeList.map(item => item.createDay),
			datasets: [{
				label: 'タバコを吸った本数',
				data: smokeList.map(item => item.value3),
				 backgroundColor: 'black',
				yAxisID : "smokeShaft",// 追加
				
			}],
		}
	var ctx = document.getElementById("smokeGraph");
		
	if (typeof smokeListChart !== 'undefined') {
	    smokeListChart.destroy();
	}
		
	smokeListChart = new Chart(ctx, {
		type: 'bar',
		data: smokeGraphData,
		options: smokeChartOption
	})
}))
	.catch(error => console.log(error))
};
</script>
	<script>
//体重記録グラフ
let bmiList = [];
//データの取得
function getBmiList() {
	fetch('/getBmiListWeek?id=' + ${user.getUserId()} + '&day=' + day.value)
	.then(res => res.json().then(data => {
		bmiList = data
		console.log(data)
		console.log(bmiList)
		//体重記録グラフに使うデータ
		var bmiGraphData = {
			//グラフの下　日付
			labels: bmiList.map(item => item.createDate),
			datasets: [{
				label: 'BMIの推移',
				data: bmiList.map(item => item.value3),
				borderColor: 'red',				
			},{
				label: '体重の推移',
				data: bmiList.map(item => item.value2),
				borderColor: 'blue',				
			}],
		}
	var ctx = document.getElementById("bmiGraph");
		
	if (typeof bmiListChart !== 'undefined') {
	    bmiListChart.destroy();
	}
	
	bmiListChart = new Chart(ctx, {
		type: 'line',
		data: bmiGraphData,
		options: complexChartOption
	})
}))
	.catch(error => console.log(error))
};
</script>
	<script type="text/javascript">
function entryClick(id) {
	document.getElementById("hideType").value = id;
	if (id == 1) {	
		getFoodList();
		console.log(foodList);
		document.getElementById('foodGraph').style.display = "";
		document.getElementById('exerciseGraph').style.display = "none";
		document.getElementById('alcoholGraph').style.display = "none";
		document.getElementById('smokeGraph').style.display = "none";
		document.getElementById('bmiGraph').style.display = "none";
	} else if (id == 2) {
		getExerciseList();
		document.getElementById('foodGraph').style.display = "none";
		document.getElementById('exerciseGraph').style.display = "";
		document.getElementById('alcoholGraph').style.display = "none";
		document.getElementById('smokeGraph').style.display = "none";
		document.getElementById('bmiGraph').style.display = "none";
	} else if (id == 3) {
		getAlcoholList();
		document.getElementById('foodGraph').style.display = "none";
		document.getElementById('exerciseGraph').style.display = "none";
		document.getElementById('alcoholGraph').style.display = "";
		document.getElementById('smokeGraph').style.display = "none";
		document.getElementById('bmiGraph').style.display = "none";
	} else if (id == 4) {
		getSmokeList();
		document.getElementById('foodGraph').style.display = "none";
		document.getElementById('exerciseGraph').style.display = "none";
		document.getElementById('alcoholGraph').style.display = "none";
		document.getElementById('smokeGraph').style.display = "";
		document.getElementById('bmiGraph').style.display = "none";
	} else if (id == 5) {
		getBmiList();
		document.getElementById('foodGraph').style.display = "none";
		document.getElementById('exerciseGraph').style.display = "none";
		document.getElementById('alcoholGraph').style.display = "none";
		document.getElementById('smokeGraph').style.display = "none";
		document.getElementById('bmiGraph').style.display = "";
	}
}
window.onload = entryClick(1);
</script>

	<script>
var exerciseChartOption = {
	responsive: true,
	scales: {
		'exerciseTimeShaft': {
			type: 'linear',
			position: 'right',
			min: 0,      // 最小値
			//ユーザーの目標消費運動時間を100に入れる。
			max: 1.5*100,    // 最大値
			display: false,
		},
		'y-axis-2': {
			type: 'linear',
			position: 'left'
		}
	}
};
</script>

	<script>	
var complexChartOption = {
	responsive: true,
	scales: {
		yAxes: [{
			id: "y-axis-1",
			type: "linear",
			position: "left"
		}, {
			id: "y-axis-2",
			type: "linear",
			position: "right",
			ticks: {
				//max : 1.5,
				//min : 0,
				// stepSize : .5
			},
			gridLines: {
				drawOnChartArea: false,
			},
		}],
	}
};
</script>

	<script>
var smokeChartOption = {
	responsive: true,
	scales: {
		'smokeShaft': {
			type: 'linear',
			position: 'left',
			min: 0,      // 最小値
		},
	}
};
</script>
</html>