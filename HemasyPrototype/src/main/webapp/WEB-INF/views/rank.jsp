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
<title>ランキング画面</title>
<link href="css/commons.css" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<meta name="viewport" content="width=device-width">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
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
  	<h2>ランキング</h2>
  	
  	<table>
   	<div class="caption"><p>検索結果：${DayRanking.size()}件</p></div>
    <thead>
      <tr>
        <th></th>
        <th>名前</th>
        <th>運動時間</th>
        <th>ユーザーの称号</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="DayRanking" items="${DayRanking}" varStatus="status">
        <tr>
          <td></td>
          <td><c:out value="${DayRanking.getUser_name()}"/></td>
          <td><c:out value="${DayRanking.getSum_time()}"/></td>
          <td><c:out value="${DayRanking.getAchievement_name()}"/></td>
          <%-- <td><a class="detail_btn" href="DitailServlet?id=${resultA.id}">編集</a></td> --%>
          <%-- <td><a class="detail_btn" href="DitailServlet?id=${resultA.id}">削除</a></td> --%>
        </tr>
	  </c:forEach>
    </tbody>
    </table>
    
    <table>
	   <div class="caption"><p>検索結果：${WeekRanking.size()}件</p></div>
	    <thead>
	      <tr>
	        <th></th>
        	<th>名前</th>
        	<th>運動時間</th>
        	<th>ユーザーの称号</th>
	      </tr>
	    </thead>
	    <tbody>
	      <c:forEach var="WeekRanking" items="${WeekRanking}" varStatus="status">
	        <tr>
	          <td></td>
	          <td><c:out value="${WeekRanking.getUser_name()}"/></td>
	          <td><c:out value="${WeekRanking.getSum_time()}"/></td>
	          <td><c:out value="${WeekRanking.getAchievement_name()}"/></td>
	          <%-- <td><a class="detail_btn" href="DitailServlet?id=${resultA.id}">詳細</a></td> --%>
	          <%-- <td><a class="detail_btn" href="DitailServlet?id=${resultA.id}">削除</a></td> --%>
	        </tr>
		  </c:forEach>
	    </tbody>
    </table>
    
    <table>
	   <div class="caption"><p>検索結果：${AchievementRanking.size()}件</p></div>
	    <thead>
	      <tr>
	        <th></th>
	        <th>名前</th>
	        <th>合計ポイント</th>
	        <th>ユーザーの称号</th>
	      </tr>
	    </thead>
	    <tbody>
	      <c:forEach var="AchievementRanking" items="${AchievementRanking}" varStatus="status">
	        <tr>
	          <td></td>
	          <td><c:out value="${AchievementRanking.getUser_name()}"/></td>
	          <td><c:out value="${AchievementRanking.getSum_score()}"/></td>
	          <td><c:out value="${AchievementRanking.getAchievement_name()}"/></td>
	          <%-- <td><a class="detail_btn" href="DitailServlet?id=${resultA.id}">詳細</a></td> --%>
	          <%-- <td><a class="detail_btn" href="DitailServlet?id=${resultA.id}">削除</a></td> --%>
	        </tr>
		  </c:forEach>
	    </tbody>
    </table>
  </div>
<script src="js/commons.js"></script>
</body>
</html>