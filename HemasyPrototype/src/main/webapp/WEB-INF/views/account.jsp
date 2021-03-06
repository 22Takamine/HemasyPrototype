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
<title>アカウント管理</title>
<link href="css/common.css" rel="stylesheet">
<link href="css/ameku.css" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<meta name="viewport" content="width=device-width">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="style.css">
</head>
<body>
<header>
	<div class="header-logo"><a class="f-24 logoF" href="./back">Hemasy</a></div>
	<form:form action="hamburger" modelAttribute="index" method="post">
	    <button type="button" class="menu-btn">
	      <i class="fa fa-bars" aria-hidden="true"></i>
	    </button>
	    <div class="menu">
	      <div class="menu__item"><a href="./account">アカウント管理</a></div>
	      <div class="menu__item"><a href="./rank">ランキング</a></div>
	      <div class="menu__item"><a href="./list">リスト編集</a></div>
	      <div class="menu__item"><a href="./information">お問い合わせ</a></div>
	      <div class="menu__item"><a href="./logout">ログアウト</a></div>
	    </div>
    </form:form>
</header>
	<div class="main display-flex-center">
		<div>
		<form:form action="accountRegist" modelAttribute="index" method="post">
			<form:input path="userId" type="hidden" value="${user.getUserId()}" readonly="readonly" />
		<!-- 	UserFormの体重のバリデーションを通すために、仮のデータを挿入しています。 -->
			<form:input path="weight" type="hidden" value="10" readonly="readonly" />
	
			<div class="mb-20">
				<fmt:message key="form.lbl.name"/>
				<form:input type="text" path="name" value="${user.getUserName()}"/>
				<form:errors path="name" cssStyle="color: red"/>
				<c:if test="${not empty msgName}">
  					<div class="red">${fn:escapeXml(msgName)}</div>
  				</c:if>
		    </div>

	    
		    <div class="mb-20">
				<fmt:message key="form.lbl.mail"/>
				<form:input type="text" path="mail" value="${user.getMail()}"/>
				<form:errors path="mail" cssStyle="color: red"/>
				<c:if test="${not empty msgMail}">
  					<div class="red">${fn:escapeXml(msgMail)}</div>
  				</c:if>
		    </div>
	    
		    <div class="mb-20">
				<fmt:message key="form.lbl.password"/>
				<form:input type="password" path="password" value="${user.getPassword()}"/>
				<form:errors path="password" cssStyle="color: red"/>
		    </div>
	    
		    <div class="mb-20">
		    	<fmt:message key="form.lbl.sex"/>
		    	<c:if test="${user.getSex() == 0}">
		    		<form:radiobutton path="sex" value="0" checked="checked"/>男   
		    		<form:radiobutton path="sex" value="1"/>女   
				</c:if>
				<c:if test="${user.getSex() == 1}">   
					<form:radiobutton path="sex" value="0"/>男   
		    		<form:radiobutton path="sex" value="1" checked="checked"/>女
				</c:if>
		    </div>
	    
		    <div class="mb-20">
				<fmt:message key="form.lbl.birth"/>
				<form:input id="datefield" type="date" path="birth" value="${user.getBirth()}"/>
		    </div>
		    <div class="mb-20">
				<fmt:message key="form.lbl.height"/>
				<form:input type="text" path="height" value="${user.getHeight()}"/>cm
				<form:errors path="height" cssStyle="color: red"/>
		    </div>
	    
		    <div class="mb-20">
				<fmt:message key="form.lbl.achievement"/>
				<div id="open">
			      称号
			    </div>
			    <div id="mask" class="hidden"></div>
			    <section id="modal" class="hidden">
			    
			    <table>
			      <thead>
			        <tr>
			          <th>称号ID</th>
			          <th>称号名</th>
			          <th>条件</th>
			          <th>チェック</th>
			        </tr>
			      </thead>
			      <tbody>
			        	<c:forEach var="achievements" items="${achievementsList}">
			          		<tr>
					            <td>${achievements.achievementId}</td>
					            <td>${achievements.achievementName}</td>
					            <td>${achievements.requirementToGet}</td>
					            <td>
						            <c:choose>
						            	<c:when test="${achievements.achievementId == user.getAchievementId()}">
						            		<form:radiobutton path="achievementId" value="${achievements.achievementId}" checked="checked"/>
						            	</c:when>
						            
						            	<c:otherwise>
						            		<form:radiobutton path="achievementId" value="${achievements.achievementId}"/>
						            	</c:otherwise>
						            	
						            </c:choose>
					            </td>
			          		</tr>
			          	</c:forEach>
			      </tbody>
			    </table>
			    
			    <div id="close" onclick="getAchievementName()">
			      登録
			    </div>
			    
			    </section>
			    
			    <input type=text value="${achievementName}" readonly="readonly" id="tatsuki">
			    <form:errors path="achievementId" cssStyle="color: red"/>
			    
		    </div>
	    
		    <div class="mb-20">
				<fmt:message key="form.lbl.time"/>
				<form:input type="text" path="goalExerciseTime" value="${user.getGoalExerciseTime()}"/>分
				<form:errors path="goalExerciseTime" cssStyle="color: red"/>
		    </div>
	    
		    <div class="mb-20">
				<fmt:message key="form.lbl.calorie"/>
				<form:input type="text" path="goalCalorise" value="${user.getGoalCalorie()}"/>Kcal
				<form:errors path="goalCalorise" cssStyle="color: red"/>
		    </div>
	    
		    <div class="mb-20">
		    	<fmt:message key="form.lbl.rank"/>
		    	<c:if test="${user.getRankFlag() == 0}"> 
		    		<form:radiobutton path="rankFlag" value="0" id="rank1" checked="checked"/>
		    		<label for="rank1">参加する</label>
		    		<form:radiobutton path="rankFlag" value="1" id="rank2"/>
		    		<label for="rank2">参加しない</label>   
				</c:if>
				<c:if test="${user.getRankFlag() == 1}">   
					<form:radiobutton path="rankFlag" value="0" id="rank1"/>
					<label for="rank1">参加する</label>
		    		<form:radiobutton path="rankFlag" value="1" id="rank2" checked="checked"/>
		    		<label for="rank2">参加しない</label>   
				</c:if>
		    </div>
	    
		    <div class="mb-20">
		    	<fmt:message key="form.lbl.smoke"/>
		    	<c:if test="${user.getSmokeFlag() == 0}"> 
		    		<form:radiobutton path="smokeFlag" value="0" id="smoke1" checked="checked"/>
		    		<label for="smoke1">吸う</label>
		    		<form:radiobutton path="smokeFlag" value="1" id="smoke2"/>
					<label for="smoke2">吸わない</label>  
				</c:if>
				<c:if test="${user.getSmokeFlag() == 1}">   
					<form:radiobutton path="smokeFlag" value="0" id="smoke1"/>
					<label for="smoke1">吸う</label>
		    		<form:radiobutton path="smokeFlag" value="1" id="smoke2" checked="checked"/>
		    		<label for="smoke2">吸わない</label>
				</c:if>
		    </div>
		    
		    <div class="mb-20">
		    	<fmt:message key="form.lbl.alcohol"/>
		    	<c:if test="${user.getAlcoholFlag() == 0}">
		    		<form:radiobutton path="alcoholFlag" value="0" id="alcohol1" checked="checked"/>
		    		<label for="alcohol1">飲んでいる</label>
		    		<form:radiobutton path="alcoholFlag" value="1" id="alcohol2"/> 
		    		<label for="alcohol2">飲まない</label>   
				</c:if>
				<c:if test="${user.getAlcoholFlag() == 1}">   
					<form:radiobutton path="alcoholFlag" value="0" id="alcohol1"/>
					<label for="alcohol1">飲んでいる</label>
		    		<form:radiobutton path="alcoholFlag" value="1" id="alcohol2" checked="checked"/>
		    		<label for="alcohol2">飲まない</label>
				</c:if>
		    </div>
	    
	       <div class="mb-20 btns">
		    <form:button class="info-btn"><fmt:message key="form.lbl.regist"/></form:button>
		    
			<button type="button" onclick="location.href='/back'" class="back-button info-btn"><fmt:message key="form.lbl.back"/></button>
	       </div>
	    
	  </form:form>
	  
	  </div>
  </div>
<script>
function changeDate(){
var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1; //January is 0!
var yyyy = today.getFullYear();

if (dd < 10) {
   dd = '0' + dd;
}

if (mm < 10) {
   mm = '0' + mm;
} 
    
today = yyyy + '-' + mm + '-' + dd;
document.getElementById("datefield").setAttribute("max", today);
}
window.addEventListner('load', changeDate());
</script>
<script src="js/commons.js"></script>
</body>
</html>