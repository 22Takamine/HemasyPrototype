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
<title>マイリスト編集画面</title>
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
  <h2>マイリスト編集</h2>
  
  
  
  
  
  
  <table>
   <div class="caption"><p>検索結果：${foodList.size()}件</p></div>
    <thead>
      <tr>
        <th>id</th>
        <th>名前</th>
        <th>カロリー</th>
        <th>編集</th>
        <th>削除</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="foodList" items="${foodList}" varStatus="status">
        <tr>
          <td><c:out value="${foodList.getListsAndRecordsId()}"/></td>
          <td><c:out value="${foodList.getValue1()}"/></td>
          <td><c:out value="${foodList.getValue2()}"/></td>
          <%-- <td><a class="detail_btn" href="DitailServlet?id=${resultA.id}">編集</a></td> --%>
          <%-- <td><a class="detail_btn" href="DitailServlet?id=${resultA.id}">削除</a></td> --%>
        </tr>
	  </c:forEach>
    </tbody>
    </table>
    
    <table>
	   <div class="caption"><p>検索結果：${alcoholList.size()}件</p></div>
	    <thead>
	      <tr>
	        <th>id</th>
	        <th>名前</th>
	        <th>1杯（ml）</th>
	        <th>度数</th>
	        <th>カロリー</th>
	        <th>編集</th>
        	<th>削除</th>
	      </tr>
	    </thead>
	    <tbody>
	      <c:forEach var="alcoholList" items="${alcoholList}" varStatus="status">
	        <tr>
	          <td><c:out value="${alcoholList.getListsAndRecordsId()}"/></td>
	          <td><c:out value="${alcoholList.getValue1()}"/></td>
	          <td><c:out value="${alcoholList.getValue2()}"/></td>
	          <td><c:out value="${alcoholList.getValue3()}"/></td>
	          <td><c:out value="${alcoholList.getValue4()}"/></td>
	          <%-- <td><a class="detail_btn" href="DitailServlet?id=${resultA.id}">詳細</a></td> --%>
	          <%-- <td><a class="detail_btn" href="DitailServlet?id=${resultA.id}">削除</a></td> --%>
	        </tr>
		  </c:forEach>
	    </tbody>
    </table>
  
  
  <form:form action="list" modelAttribute="index" method="post">
    <form:button><fmt:message key="form.lbl.regist"/></form:button>
  </form:form>
  
  <form:form action="back" modelAttribute="index" method="get">
    <form:button><fmt:message key="form.lbl.back"/></form:button>
  </form:form>
  </div>
<script src="js/commons.js"></script>
</body>
</html>