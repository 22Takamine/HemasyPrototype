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
                        <meta name="viewport" content="width=device-width,initial-scale=1.0">
                        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.2.0/chart.min.js" integrity="sha512-VMsZqo0ar06BMtg0tPsdgRADvl0kDHpTbugCBBrL55KmucH6hP9zWdLIWY//OTfMnzz6xWQRxQqsUFefwHuHyg==" crossorigin="anonymous"></script>
                        <script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns@next/dist/chartjs-adapter-date-fns.bundle.min.js"></script>
                    </head>

                    <body>
                        <h1>統計</h1>
                        <div id="selectGraph">
                            <button data-index="food" onclick="entryClick(1)">食事</button>
                            <button data-index="exercise" onclick="entryClick(2)">運動</button>
                            <button data-index="alcohol" onclick="entryClick(3)">酒</button>
                            <button data-index="smoke" onclick="entryClick(4)">タバコ</button>
                            <button data-index="bmi" onclick="entryClick(5)">体重</button>
                        </div>
                        <br><input type="button" value="左" onclick="getName()"><input type="button" value="右"> ${name}
                        <div style="width: 1000px">
                            <canvas id="foodGraph"></canvas>
                        </div>
                        <div style="width: 1000px">
                            <canvas id="exerciseGraph"></canvas>
                        </div>
                        <div style="width: 1000px">
                            <canvas id="alcoholGraph"></canvas>
                        </div>
                        <div style="width: 1000px">
                            <canvas id="smokeGraph"></canvas>
                        </div>
                        <div style="width: 1000px">
                            <canvas id="bmiGraph"></canvas>
                        </div>
<script>
//食事記録グラフの作成　ユーザーの目標摂取カロリーをセッションから取得するようにする。
let foodList = [];
let goalCalorie = [];
//データの取得
function getFoodList() {
	fetch('/getFoodList?id=' + 1)
	.then(res => res.json().then(data => {
		foodList = data
		console.log(data)
		console.log(foodList)
		foodList.forEach(function(createDate) {
			goalCalorie.push(1000); //ToDo session から ${user.goal_calorie}　の値をとる。
		});
	//食事記録グラフに使うデータ
	var foodGraphData = {
		//グラフの下　日付
		labels: foodList.map(item => item.createDate),
		datasets: [{
			label: '摂取カロリーの推移',
			data: foodList.map(item => item.value2),
			borderColor: '#484',
		}, {
			label: 'ユーザーの目標摂取カロリー',
			data: goalCalorie,
			borderColor: '#ff0000',
		}],
	}
	//食事記録グラフの描画
	window.addEventListener('load', makeChart);
	var ctx = document.getElementById("foodGraph");
	var myChart = new Chart(ctx, {
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
//データの取得
function getExerciseList() {
	fetch('/getExerciseList?id=' + 1)
	.then(res => res.json().then(data => {
		exerciseList = data
		console.log(data)
		console.log(exerciseList)
		exerciseList.forEach(function(createDate) {
			goalExerciseTime.push(100); //ToDo session から ${user.goal_exercise_time}　の値をとる。
		});
		//運動記録グラフに使うデータ
		var exerciseGraphData = {
			//グラフの下　日付
			labels: exerciseList.map(item => item.createDate),
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
	window.addEventListener('load', makeChart);
	var ctx = document.getElementById("exerciseGraph");
	var myChart = new Chart(ctx, {
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
	fetch('/getAlcoholList?id=' + 1)
	.then(res => res.json().then(data => {
		alcoholList = data
		console.log(data)
		console.log(exerciseList)
		alcoholList.forEach(function(createDate) {
			deadLine.push(20);
		});
		//アルコール量記録グラフに使うデータ
		var alcoholGraphData = {
			//グラフの下　日付
			labels: alcoholList.map(item => item.createDate),
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
	window.addEventListener('load', makeChart);
	var ctx = document.getElementById("alcoholGraph");
	var myChart = new Chart(ctx, {
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
	fetch('/getSmokeList?id=' + 1)
	.then(res => res.json().then(data => {
		smokeList = data
		console.log(data)
		console.log(smokeList)
		//アルコール量記録グラフに使うデータ
		var smokeGraphData = {
			//グラフの下　日付
			labels: smokeList.map(item => item.createDate),
			datasets: [{
				label: 'タバコを吸った本数',
				data: smokeList.map(item => item.value3),
				 backgroundColor: 'black',
				yAxisID : "smokeShaft",// 追加
				
			}],
		}
	window.addEventListener('load', makeChart);
	var ctx = document.getElementById("smokeGraph");
	var myChart = new Chart(ctx, {
		type: 'bar',
		data: smokeGraphData,
		options: smokeChartOption
	})
}))
	.catch(error => console.log(error))
};
</script>
<script>
                            //体重用のグラフ
                            window.addEventListener('load', makeChart);

                            function makeChart() {
                                var ctx = document.getElementById("bmiGraph");
                                var myChart = new Chart(ctx, {
                                    type: 'line',
                                    data: bmiGraphData,
                                    options: complexChartOption
                                })
                            };
                            document.getElementById('canvas').addEventListener('click', e => {
                                console.log(e)
                                const elements = window.myCanvas.getElementAtEvent(e);
                                if (elements[0]._model.label.length) {
                                    window.location.href = './eat?day=' + [elements[0]._model.label];
                                    console.log('elements', [elements[0]._model.label]);
                                } else {
                                    alert('aa');
                                }

                            });
                        </script>
                        <script>
                            //体重用のデータ
                            var bmiGraphData = {
                                labels: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
                                datasets: [{
                                    label: '体重',
                                    data: [20, 35, 40, 30, 45, 35, 40],
                                    borderColor: '#f88',
                                }, {
                                    label: 'Green',
                                    data: [20, 15, 30, 25, 30, 40, 35],
                                    borderColor: '#484',
                                }, {
                                    label: 'Blue',
                                    data: [30, 25, 10, 5, 25, 30, 20],
                                    borderColor: '#48f',
                                }],
                            };
                        </script>
                        <script type="text/javascript">
                            function entryClick(id) {
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
                                    document.getElementById('foodGraph').style.display = "none";
                                    document.getElementById('exerciseGraph').style.display = "none";
                                    document.getElementById('alcoholGraph').style.display = "none";
                                    document.getElementById('smokeGraph').style.display = "none";
                                    document.getElementById('bmiGraph').style.display = "";
                                }
                            }
                            window.onload = entryClick;
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
                                            // 						max : 1.5,
                                            // 						min : 0,
                                            // 						stepSize : .5
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