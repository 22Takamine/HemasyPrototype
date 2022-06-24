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
<title>管理者お問い合わせ画面</title>
<link href="css/commons.css" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<meta name="viewport" content="width=device-width">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
</head>
<body>
<header>
	<div class="header-logo"><a href="./admin">Hemasy</a></div>
	<form:form action="hamburger" modelAttribute="index" method="post">
	    <button type="button" class="menu-btn">
	      <i class="fa fa-bars" aria-hidden="true"></i>
	    </button>
	    <div class="menu">
	      <div class="menu__item"><a href="./rank">ランキング</a></div>
	      <div class="menu__item"><a href="./adminInformation">リクエスト</a></div>
	      <div class="menu__item"><a href="./logout">ログアウト</a></div>
	    </div>
    </form:form>
</header>
  <div class="main">
  <h2>管理者お問い合わせ</h2>
  
  <table>
	      <thead>
	        <tr>
	          <th>No.</th>
	          <th>ユーザー名</th>
	          <th>メールアドレス</th>
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
  
  
  
  
  <form:form action="process" modelAttribute="index" method="post">
    <form:button><fmt:message key="form.lbl.process"/></form:button>
  </form:form>
  
  <form:form action="backList" modelAttribute="index" method="post">
    <form:button><fmt:message key="form.lbl.backList"/></form:button>
  </form:form>
  </div>
<script src="js/commons.js"></script>
</body>
</html>