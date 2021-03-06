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
	src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns@next/dist/chartjs-adapter-date-fns.bundle.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.3.0/Chart.bundle.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/chartjs-plugin-annotation@0.5.7/chartjs-plugin-annotation.min.js"
	integrity="sha256-Olnajf3o9kfkFGloISwP1TslJiWUDd7IYmfC+GdCKd4="
	crossorigin="anonymous"></script>
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
	<input type="hidden" id="hide" value="0">
	<!-- 週月年の記録　 -->
	<input type="hidden" id="hideType" value="1">
	<!-- 食事・運動・酒・タバコ　の記録　 -->
	<input type="hidden" id="hideMan" value="1.5">
	<input type="hidden" id="test" value="1.5">

	<div id="selectGraph">
		<button name="0" data-index="food" onclick="entryClick(1)">食事</button>
		<button name="1" data-index="exercise" onclick="entryClick(2)">運動</button>
		<button name="2" data-index="alcohol" onclick="entryClick(3)">酒</button>
		<button name="3" data-index="smoke" onclick="entryClick(4)">タバコ</button>
		<button name="4 " data-index="bmi" onclick="entryClick(5)">体重</button>
	</div>
	<div>
		<label><input id="syuu" type="radio" name="poti" value="0" checked onclick="poti(0), changeHideMan(1.5)">週</label>
		<label><input id="tuki" type="radio" name="poti" value="1" onclick="poti(1), changeHideMan(1.5)">月</label>
		<label><input id="nen"  type="radio" name="poti" value="2" onclick="poti(2), changeHideMan(45)">年</label>
	</div>
	<div>
		<input type="date" id="date" value="${statisticsDate}" onchange="poti()">
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
</body>
<script>
//食事Graphがクリックされたとき
document.getElementById('foodGraph').addEventListener('click', e => {
	const elements = window.foodListChart.getElementAtEvent(e);
	if (elements[0]) {
		if (document.getElementById("hide").value == 2){
			hideMan = 1.5;
			document.getElementById("hideMan").value = 1.5;
			document.getElementById("tuki").checked = true;
			document.getElementById("hide").value = 1;
			document.getElementById("date").value = elements[0]._xScale.min.substr(0,5) + ('000' + (Number(elements[0]._index) + 1)).slice(-2) + '-01';
			entryClick(1);
 	 	}else {
			window.location.href = '/record?recordDate=' + elements[0]._xScale.min.substr(0,7) + '-' + (Number(elements[0]._xScale.min.substr(8,2)) + (Number(elements[0]._index)));
		}	
	}
});
</script>
<script>
//運動Graphがクリックされたとき
document.getElementById('exerciseGraph').addEventListener('click', e => {
	const elements = exerciseListChart.getElementAtEvent(e);
	if (elements[0]) {
		if (document.getElementById("hide").value == 2){
			hideMan = 1.5;
			document.getElementById("hideMan").value = 1.5;
			document.getElementById("tuki").checked = true;
			document.getElementById("hide").value = 1;
			document.getElementById("date").value = elements[0]._xScale.min.substr(0,5) + ('000' + (Number(elements[0]._index) + 1)).slice(-2) + '-01';
			entryClick(2);
		}else {
			window.location.href = '/record?recordDate=' + elements[0]._xScale.min.substr(0,7) + '-' + (Number(elements[0]._xScale.min.substr(8,2)) + (Number(elements[0]._index)));
		}	
	}
});	
</script>
<script>
//酒Graphがクリックされたとき
document.getElementById('alcoholGraph').addEventListener('click', e => {
	const elements = alcoholListChart.getElementAtEvent(e);
	if (elements[0]) {
		if (document.getElementById("hide").value == 2){
			hideMan = 1.5;
			document.getElementById("hideMan").value = 1.5;
			document.getElementById("tuki").checked = true;
			document.getElementById("hide").value = 1;
			document.getElementById("date").value = elements[0]._xScale.min.substr(0,5) + ('000' + (Number(elements[0]._index) + 1)).slice(-2) + '-01';
			entryClick(3);
		}else {
			window.location.href = '/record?recordDate=' + elements[0]._xScale.min.substr(0,7) + '-' + (Number(elements[0]._xScale.min.substr(8,2)) + (Number(elements[0]._index)));
		}	
	}
});	
</script>
<script>
//体重Graphがクリックされたとき
document.getElementById('bmiGraph').addEventListener('click', e => {
	const elements = bmiListChart.getElementAtEvent(e);
	if (elements[0]) {
		if (document.getElementById("hide").value == 2){
			hideMan = 1.5;
			document.getElementById("hideMan").value = 1.5;
			document.getElementById("tuki").checked = true;
			document.getElementById("hide").value = 1;
			document.getElementById("date").value = elements[0]._xScale.min.substr(0,5) + ('000' + (Number(elements[0]._index) + 1)).slice(-2) + '-01';
			entryClick(5);
		}else {
			window.location.href = '/record?recordDate=' + elements[0]._xScale.min.substr(0,7) + '-' + (Number(elements[0]._xScale.min.substr(8,2)) + (Number(elements[0]._index)));
		}	
	}
});	
</script>
<script>
//タバコグラフがクリックされたとき
document.getElementById('smokeGraph').addEventListener('click', e => {
	const elements = smokeListChart.getElementAtEvent(e);
	if (elements[0]._model.label.length) {
		if (document.getElementById("hide").value == 2){
			hideMan = 1.5;
			document.getElementById("hideMan").value = 1.5;
			document.getElementById("tuki").checked = true;
			document.getElementById("hide").value = 1;
			document.getElementById("date").value = elements[0]._xScale.min.substr(0,5) + ('000' + (Number(elements[0]._index) + 1)).slice(-2) + '-01';
			entryClick(4);
		}else {
			window.location.href = '/record?recordDate=' + elements[0]._model.label;
		}		
	}
});
</script>
<script>
//倍率の保存
window.addEventListener('load', changeHideMan(1.5));
	function changeHideMan(id){
		hideMan = id;
		document.getElementById("test").value = id;
};
</script>
<script>
//保存された週・月・年の利用
function poti(checkValue) {
	let elements = document.getElementsByName('poti');
	let len = elements.length;
	if (checkValue != null) {
		document.getElementById("hide").value = checkValue;
		if (checkValue == 2){
			document.getElementById("hideMan").value = 40;
		}else {
			document.getElementById("hideMan").value = 1.5;
		}
	}
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
var foodListChart;
//データの取得
function getFoodList() {
	fetch('/getFoodListWeek?id=' + ${user.getUserId()} + '&day=' + day.value + '&scope=' + scope.value)
	.then(res => res.json()
	.then(data => {
		foodList = data
		foodList.forEach(
		function(createDay) {
			goalCalorie.push(${user.getGoalCalorie()});
		}
	);
	//食事グラフに使うデータ
	var foodGraphData = {
		//グラフの下　日付
		labels: foodList.map(item => item.createDay),
		datasets: [{
			label: '摂取カロリーの推移',
			data: foodList.map(item => item.value2),
			borderColor: '#484',
			fill: false,
			lineTension: 0,
		},],
	}
	//食事グラフのオプション
	var foodChartOption = {
		responsive: true,
		scales: {
			yAxes: [{
				id: "y-axis-1",
				type: "linear",
				position: "left",
				ticks: {
					max: ${user.getGoalCalorie()} * hideMan,
					min: 0,
				},
				scaleLabel: {
					display: true,
					labelString: "摂取カロリー"
				},
			},],
		},
		annotation: {
			annotations: [{
			type: 'line',
			drawTime: 'afterDatasetsDraw',
			id: 'a-line-1',
			mode: 'horizontal',
			scaleID: 'y-axis-1',
			value: ${user.getGoalCalorie()}*hideMan*2/3, 
			endValue: ${user.getGoalCalorie()}*hideMan*2/3, 
			borderColor: 'red', 
			borderWidth: 3,
			borderDash: [2, 2],
			borderDashOffset: 1,
			label: {
				backgroundColor: 'rgba(255,255,255,0.8)',
				bordercolor: 'rgba(200,60,60,0.8)',
				borderwidth: 2,
				fontSize: 10,
				fontStyle: 'bold',
				fontColor: 'rgba(200,60,60,0.8)',
				xPadding: 10,
				yPadding: 10,
				cornerRadius: 3,
				position: 'left',
				xAdjust: 0,
				yAdjust: 0,
				enabled: true,
				content: 'ユーザーの目標カロリー'
			}
		},
		{
			type: 'line', 
			drawTime: 'afterDatasetsDraw',
			id: 'a-line-2',
			mode: 'horizontal', 
			scaleID: '',
			value: 0,
			endValue: 0,
			borderColor: 'rgba(60,200,60,0.8)',
			borderWidth: 3,
			borderDash: [2, 2],
			borderDashOffset: 1,
			label: {
				backgroundColor: 'rgba(255,255,255,0.8)',
				bordercolor: 'rgba(60,200,60,0.8)',
				borderwidth: 2,
				fontSize: 10,
				fontStyle: 'bold',
				fontColor: '#333',
				xPadding: 10,
				yPadding: 10,
				cornerRadius: 3,
				position: 'left',
				xAdjust: 0,
				yAdjust: 0,
				enabled: true,
				content: ''
			}
		}
	]}}
	//食事記録グラフの描画
	var ctx = document.getElementById("foodGraph");	
		if (typeof foodListChart !== 'undefined') {
			foodListChart.destroy();
		}
		window.foodListChart = new Chart(ctx, {
			type: 'line',
			data: foodGraphData,
			options: foodChartOption
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
				yAxisID : "exerciseTimeShaft",
				fill: false,
				lineTension: 0,
			},{
				label: '消費カロリーの推移',
				data: exerciseList.map(item => item.value3),
				borderColor: '#48f',
				yAxisID : "y-axis-2",
				fill: false,
				lineTension: 0,
			}],
		}
		var exerciseChartOption = {
			responsive: true,
			scales: {
				yAxes: [{
					id: "exerciseTimeShaft",
					type: "linear",
					position: "left",
					ticks: {
						max:${user.getGoalExerciseTime()}*hideMan,	                		
						min: 0
					},	
					scaleLabel: {
						display: true,
						labelString: "運動時間（分）" 
					},
				},{
					id: "y-axis-2",
					type: "linear", 
					position: "right",

					scaleLabel: {
						display: true,
						labelString: "消費カロリー"  	  
					},
				}],
			},
			annotation: {
				annotations: [{
					type: 'line',
					drawTime: 'afterDatasetsDraw',
					id: 'a-line-1',
					mode: 'horizontal',
					scaleID: 'exerciseTimeShaft',
					value: ${user.getGoalExerciseTime()}*hideMan*2/3,
					endValue: ${user.getGoalExerciseTime()}*hideMan*2/3, 
					borderColor: 'red',
					borderWidth: 3,
					borderDash: [2, 2],
					borderDashOffset: 1,
					label: {
						backgroundColor: 'rgba(255,255,255,0.8)',
						bordercolor: 'rgba(200,60,60,0.8)',
						borderwidth: 2,
						fontSize: 10,
						fontStyle: 'bold',
						fontColor: 'rgba(200,60,60,0.8)',
						xPadding: 10,
						yPadding: 10,
						cornerRadius: 3,
						position: 'left',
						xAdjust: 0,
						yAdjust: 0,
						enabled: true,
						content: 'ユーザーの目標運動時間'
					}
				},
				{
					type: 'line',
					drawTime: 'afterDatasetsDraw',
					id: 'a-line-2',
					mode: 'horizontal',
					scaleID: '', 
					value: 13.7, 
					endValue: 13.7, 
					borderColor: 'rgba(60,200,60,0.8)',
					borderWidth: 3,
					borderDash: [2, 2],
					borderDashOffset: 1,
					label: {
						backgroundColor: 'rgba(255,255,255,0.8)',
						bordercolor: 'rgba(60,200,60,0.8)',
						borderwidth: 2,
						fontSize: 10,
						fontStyle: 'bold',
						fontColor: '#333',
						xPadding: 10,
						yPadding: 10,
						cornerRadius: 3,
						position: 'left',
						xAdjust: 0,
						yAdjust: 0,
						enabled: true,
						content: ''
				}
			}]
		}
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
//データの取得
function getAlcoholList() {
fetch('/getAlcoholListWeek?id=' + ${user.getUserId()} + '&day=' + day.value + '&scope=' + scope.value)
	.then(res => res.json().then(data => {
		alcoholList = data
		//アルコール量記録グラフに使うデータ
		var alcoholGraphData = {
			//グラフの下　日付
			labels: alcoholList.map(item => item.createDay),
			datasets: [{
				label: '摂取アルコール量の推移',
				data: alcoholList.map(item => item.value2),
				borderColor: '#F36C21',
				yAxisID : "getAlcoholShaft",
				fill: false,
				lineTension: 0,
			}],
		}
	
		var alcoholChartOption = {
			    responsive: true,
			    scales: {
			        yAxes: [{
			            id: "getAlcoholShaft",  
			            type: "linear",
			            position: "left",
			            scaleLabel: {
							display: true,
							labelString: "摂取アルコール量"	                  
						},
		                ticks: {
		 	                max:20* hideMan,              		
			                min: 0
			            },	
					}],
			    },
				annotation: {
					annotations: [{
					type: 'line',
					drawTime: 'afterDatasetsDraw',
					id: 'a-line-1',
					mode: 'horizontal',
					scaleID: 'getAlcoholShaft',
					value: 20*hideMan*2/3,
					endValue: 20*hideMan*2/3,
					borderColor: 'red',
					borderWidth: 3,
					borderDash: [2, 2],
					borderDashOffset: 1,
					label: {
						backgroundColor: 'rgba(255,255,255,0.8)',
						bordercolor: 'rgba(200,60,60,0.8)',
						borderwidth: 2,
						fontSize: 10,
						fontStyle: 'bold',
						fontColor: 'rgba(200,60,60,0.8)',
						xPadding: 10,
						yPadding: 10,
						cornerRadius: 3,
						position: 'left',
						xAdjust: 0,
						yAdjust: 0,
						enabled: true,
						content: 'DEADLINE'
					}
				},
				{
					type: 'line',
					drawTime: 'afterDatasetsDraw',
					id: 'a-line-2',
					mode: 'horizontal',
					scaleID: '',
					value: 13.7,
					endValue: 13.7,
					borderColor: 'rgba(60,200,60,0.8)',
					borderWidth: 3,
					borderDash: [2, 2],
					borderDashOffset: 1,
					label: {
					backgroundColor: 'rgba(255,255,255,0.8)',
					bordercolor: 'rgba(60,200,60,0.8)',
					borderwidth: 2,
					fontSize: 10,
					fontStyle: 'bold',
					fontColor: '#333',
					xPadding: 10,
					yPadding: 10,
					cornerRadius: 3,
					position: 'left',
					xAdjust: 0,
					yAdjust: 0,
					enabled: true,
					content: ''
				}
			}]
		}
	}	
	
	var ctx = document.getElementById("alcoholGraph");
	if (typeof alcoholListChart !== 'undefined') {
	    alcoholListChart.destroy();
	}
		
	alcoholListChart = new Chart(ctx, {
		type: 'line',
		data: alcoholGraphData,
		options: alcoholChartOption
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
		//タバコ記録グラフに使うデータ
		var smokeGraphData = {
			//グラフの下　日付
			labels: smokeList.map(item => item.createDay),
			datasets: [{
				label: 'タバコを吸った本数',
				data: smokeList.map(item => item.value3),
				 backgroundColor: 'black',
				yAxisID : "smokeShaft",		
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
<script>//体重記録グラフ

let bmiList = [];
//データの取得
function getBmiList() {
    fetch('/getBmiListWeek?id=' + ${user.getUserId()} + '&day=' + day.value + '&scope=' + scope.value)
        .then(res => res.json().then(data => {
            bmiList = data
                //体重記録グラフに使うデータ
            var bmiGraphData = {
                //グラフの下　日付
                labels: bmiList.map(item => item.createDay),
                datasets: [{
                    label: 'BMIの推移',
                    data: bmiList.map(item => item.value3),
                    borderColor: 'red',
                    yAxisID: "BMIShaft",
                    fill: false,
                    lineTension: 0,
                }, {
                    label: '体重の推移',
                    data: bmiList.map(item => item.value2),
                    borderColor: 'blue',
                    yAxisID: "TAIJUShaft",
                    fill: false,
                    lineTension: 0,
                }]
            }
    		var bmiChartOption = {
				responsive: true,
				scales: {
					yAxes: [{
						id: "BMIShaft",
						type: "linear",
						position: "right",
						scaleLabel: {
							display: true,
							labelString: "BMI"	                  
						},
					}, {
						id: "TAIJUShaft",
						type: "linear",
						position: "left",
						scaleLabel: {
							display: true,
							labelString: "体重(Kg)"	                  
						},	
					}],
				},
				annotation: {
					annotations: [{
					type: 'line',
					drawTime: 'afterDatasetsDraw',
					id: 'a-line-1',
					mode: 'horizontal',
					scaleID: 'getAlcoholShaft',
					value: 20*hideMan*2/3,
					endValue: 20*hideMan*2/3,
					borderColor: 'red',
					borderWidth: 3,
					borderDash: [2, 2],
					borderDashOffset: 1,
					label: {
						backgroundColor: 'rgba(255,255,255,0.8)',
						bordercolor: 'rgba(200,60,60,0.8)',
						borderwidth: 2,
						fontSize: 10,
						fontStyle: 'bold',
						fontColor: 'rgba(200,60,60,0.8)',
						xPadding: 10,
						yPadding: 10,
						cornerRadius: 3,
						position: 'left',
						xAdjust: 0,
						yAdjust: 0,
						enabled: true,
						content: 'DEADLINE'
					}
				},
				{
					type: 'line',
					drawTime: 'afterDatasetsDraw',
					id: 'a-line-2', 
					mode: 'horizontal',
					scaleID: '', 
					value: 13.7,
					endValue: 13.7,
					borderColor: 'rgba(60,200,60,0.8)',
					borderWidth: 3, 
					borderDash: [2, 2],
					borderDashOffset: 1,
					label: { 
						backgroundColor: 'rgba(255,255,255,0.8)',
						bordercolor: 'rgba(60,200,60,0.8)',
						borderwidth: 2,
						fontSize: 10,
						fontStyle: 'bold',
						fontColor: '#333',
						xPadding: 10,
						yPadding: 10,
						cornerRadius: 3,
						position: 'left',
						xAdjust: 0,
						yAdjust: 0,
						enabled: true,
						content: ''
					}
				}]
			}
		}
		var ctx = document.getElementById("bmiGraph");
			if (typeof bmiListChart !== 'undefined') {
				bmiListChart.destroy();
			}
			bmiListChart = new Chart(ctx, {
			type: 'line',
			data: bmiGraphData,
			options: bmiChartOption
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
//タバコグラフのオプション
var smokeChartOption = {
	responsive: true,
	scales: {
		yAxes: [{
			id: "smokeShaft",
			type: "linear",
			position: "left",
			min: 0,
		}],
	}
};
</script>
</html>