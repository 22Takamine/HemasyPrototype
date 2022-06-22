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
<link href="css/commons.css" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
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
	      <div class="menu__item"><a href="./account">アカウント管理</a></div>
	      <div class="menu__item"><a href="./rank">ランキング</a></div>
	      <div class="menu__item"><a href="./list">リスト編集</a></div>
	      <div class="menu__item"><a href="./information">お問い合わせ</a></div>
	      <div class="menu__item"><a href="./logout">ログアウト</a></div>
	    </div>
    </form:form>
</header>
	<div class="main">
	<form:form action="accountRegist" modelAttribute="index" method="post">
	<form:input path="id" type="hidden" value="${user.getUserId()}" readonly="readonly" />
	
<!-- 	UserFormの体重のバリデーションを通すために、仮のデータを挿入しています。 -->
	<form:input path="weight" type="hidden" value="10" readonly="readonly" />
	<div>
		<fmt:message key="form.lbl.name"/>
		<form:input type="text" path="name" value="${user.getUserName()}"/>
		<form:errors path="name" cssStyle="color: red"/>
<%-- 		<input type="text" name="name" value="${user.getUserName()}">       --%>
    </div>
    
    <div>
		<fmt:message key="form.lbl.mail"/>
		<form:input type="text" path="mail" value="${user.getMail()}"/>
		<form:errors path="mail" cssStyle="color: red"/>
<%-- 		<input type="text" name="mail" value="${user.getMail()}">       --%>
    </div>
    
    <div>
		<fmt:message key="form.lbl.password"/>
		<form:input type="text" path="pass" value="${user.getPassword()}"/>
		<form:errors path="pass" cssStyle="color: red"/>
<%-- 		<input type="text" name="pass" value="${user.getPassword()}">       --%>
    </div>
    
    <div>
    	<fmt:message key="form.lbl.sex"/>
    	<c:if test="${user.getSex() == 0}">
    		<form:radiobutton path="sex" value="0" checked="checked"/>男   
    		<form:radiobutton path="sex" value="1"/>女   
<!-- 			<input type="radio" name="sex" value="0" checked>男       -->
<!-- 			<input type="radio" name="sex" value="1">女 -->
		</c:if>
		<c:if test="${user.getSex() == 1}">   
			<form:radiobutton path="sex" value="0"/>男   
    		<form:radiobutton path="sex" value="1" checked="checked"/>女
		</c:if>
    </div>
    
    <div>
		<fmt:message key="form.lbl.birthDate"/>
		<form:input type="date" path="birthDate" value="${user.getBirth()}"/>
		<form:errors path="birthDate" cssStyle="color: red"/>
<%-- 		<input type="date" name="birthDate" value="${user.getBirth()}">       --%>
    </div>
    
    <div>
		<fmt:message key="form.lbl.height"/>
		<form:input type="text" path="height" value="${user.getHeight()}"/>cm
		<form:errors path="height" cssStyle="color: red"/>
<%-- 		<input type="text" name="height" value="${user.getHeight()}">       --%>
    </div>
    
    <div>
		<fmt:message key="form.lbl.achievement"/>
		<form:input type="text" path="achievementId" value="${user.getAchievementFlag()}"/>
		<form:errors path="achievementId" cssStyle="color: red"/>
<%-- 		<input type="text" name="achievementId" value="${user.getAchievementFlag()}">       --%>
    </div>
    
    <div>
		<fmt:message key="form.lbl.time"/>
		<form:input type="text" path="goalExerciseTime" value="${user.getGoalExerciseTime()}"/>分
		<form:errors path="goalExerciseTime" cssStyle="color: red"/>
<%-- 		<input type="text" name="goalExerciseTime" value="${user.getGoalExerciseTime()}">       --%>
    </div>
    
    <div>
		<fmt:message key="form.lbl.calorie"/>
		<form:input type="text" path="goalCalorise" value="${user.getGoalCalorie()}"/>Kcal
		<form:errors path="goalCalorise" cssStyle="color: red"/>
<%-- 		<input type="text" name="goalCalorise" value="${user.getGoalCalorie()}">       --%>
    </div>
    
    <div>
    	<fmt:message key="form.lbl.rank"/>
    	<c:if test="${user.getRankFlag() == 1}"> 
    		<form:radiobutton path="rank" value="1" checked="checked"/>参加する 
    		<form:radiobutton path="rank" value="0"/>参加しない     
<!-- 			<input type="radio" name="rank" value="1" checked>参加する       -->
<!-- 			<input type="radio" name="rank" value="0">参加しない -->
		</c:if>
		<c:if test="${user.getRankFlag() == 0}">   
			<form:radiobutton path="rank" value="1" />参加する 
    		<form:radiobutton path="rank" value="0" checked="checked"/>参加しない
		</c:if>
    </div>
    
    <div>
    	<fmt:message key="form.lbl.smoke"/>
    	<c:if test="${user.getSmokeFlag() == 1}"> 
    		<form:radiobutton path="smoke" value="1" checked="checked"/>吸っている 
    		<form:radiobutton path="smoke" value="0"/>吸わない   
<!-- 			<input type="radio" name="smoke" value="1" checked>吸っている       -->
<!-- 			<input type="radio" name="smoke" value="0">吸わない -->
		</c:if>
		<c:if test="${user.getSmokeFlag() == 0}">   
			<form:radiobutton path="smoke" value="1"/>吸っている 
    		<form:radiobutton path="smoke" value="0" checked="checked"/>吸わない  
		</c:if>
    </div>
    
    <div>
    	<fmt:message key="form.lbl.alcohol"/>
    	<c:if test="${user.getAlcoholFlag() == 1}">
    		<form:radiobutton path="alcohol" value="1" checked="checked"/>飲んでいる
    		<form:radiobutton path="alcohol" value="0"/>飲まない    
<!-- 			<input type="radio" name="alcohol" value="1" checked>飲んでいる      -->
<!-- 			<input type="radio" name="alcohol" value="0">飲まない -->
		</c:if>
		<c:if test="${user.getAlcoholFlag() == 0}">   
			<form:radiobutton path="alcohol" value="1"/>飲んでいる
    		<form:radiobutton path="alcohol" value="0" checked="checked"/>飲まない
		</c:if>
    </div>
    



  
    <form:button><fmt:message key="form.lbl.regist"/></form:button>
  </form:form>
  
  <form:form action="back" modelAttribute="index" method="get">
    <form:button><fmt:message key="form.lbl.back"/></form:button>
  </form:form>
  </div>

<script src="js/commons.js"></script>
</body>
</html>