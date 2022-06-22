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
	<form:form action="hamburger" modelAttribute="account" method="post">
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
	<form:form action="recordRegist" modelAttribute="account" method="post">
	<form:input path="id" type="hidden" value="${user.getId()}" readonly="readonly" />
	<div>
		<fmt:message key="form.lbl.name"/>
		<input type="text" name="name" value="${user.getUserName()}">      
    </div>
    
    <div>
		<fmt:message key="form.lbl.mail"/>
		<input type="text" name="mail" value="${user.getMail()}">      
    </div>
    
    <div>
		<fmt:message key="form.lbl.password"/>
		<input type="text" name="pass" value="${user.getPassword()}">      
    </div>
    
    <div>
    	<fmt:message key="form.lbl.sex"/>
    	<c:if test="${user.getSex() == 0}">   
			<input type="radio" name="sex" value="men" checked>男      
			<input type="radio" name="sex" value="women">女
		</c:if>
		<c:if test="${user.getSex() == 1}">   
			<input type="radio" name="sex" value="men">男      
			<input type="radio" name="sex" value="women" checked>女 
		</c:if>
    </div>
    
    <div>
		<fmt:message key="form.lbl.birthDate"/>
		<input type="date" name="birthDate" value="${user.getBirth()}">      
    </div>
    
    <div>
		<fmt:message key="form.lbl.height"/>
		<input type="text" name="height" value="${user.getHeight()}">      
    </div>
    
    <div>
		<fmt:message key="form.lbl.achievement"/>
		<input type="text" name="achievementId" value="${user.getAchievementFlag()}">      
    </div>
    
    <div>
		<fmt:message key="form.lbl.time"/>
		<input type="text" name="goalExerciseTime" value="${user.getGoalExerciseTime()}">      
    </div>
    
    <div>
		<fmt:message key="form.lbl.calorie"/>
		<input type="text" name="goalCalorise" value="${user.getGoalCalorie()}">      
    </div>
    
    <div>
    	<fmt:message key="form.lbl.rank"/>
    	<c:if test="${user.getRankFlag() == 1}">   
			<input type="radio" name="rank" value="yes" checked>参加する      
			<input type="radio" name="rank" value="no">参加しない
		</c:if>
		<c:if test="${user.getRankFlag() == 0}">   
			<input type="radio" name="rank" value="yes">参加する      
			<input type="radio" name="rank" value="no" checked>参加しない 
		</c:if>
    </div>
    
    <div>
    	<fmt:message key="form.lbl.smoke"/>
    	<c:if test="${user.getSmokeFlag() == 1}">   
			<input type="radio" name="smoke" value="yes" checked>吸っている      
			<input type="radio" name="smoke" value="no">吸わない
		</c:if>
		<c:if test="${user.getSmokeFlag() == 0}">   
			<input type="radio" name="smoke" value="yes">吸っている      
			<input type="radio" name="smoke" value="no" checked>吸わない 
		</c:if>
    </div>
    
    <div>
    	<fmt:message key="form.lbl.alcohol"/>
    	<c:if test="${user.getAlcoholFlag() == 1}">   
			<input type="radio" name="alcohol" value="yes" checked>飲んでいる     
			<input type="radio" name="alcohol" value="no">飲まない
		</c:if>
		<c:if test="${user.getAlcoholFlag() == 0}">   
			<input type="radio" name="alcohol" value="yes">飲んでいる      
			<input type="radio" name="alcohol" value="no" checked>飲まない 
		</c:if>
    </div>
    
    
    
    










  
    <form:button><fmt:message key="form.lbl.regist"/></form:button>
  </form:form>
  
  <form:form action="back" modelAttribute="account" method="get">
    <form:button><fmt:message key="form.lbl.back"/></form:button>
  </form:form>
  </div>

<script src="js/commons.js"></script>
</body>
</html>