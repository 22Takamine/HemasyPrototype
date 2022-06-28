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
<title>お問い合わせ画面</title>
<link href="css/common.css" rel="stylesheet">
<link href="css/ameku.css" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<meta name="viewport" content="width=device-width">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
</head>
<body>
<header>
	<div class="header-logo"><a href="./back">Hemasy</a></div>
	<form:form action="hamburger" modelAttribute="information" method="post">
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
<main class="information">
  <h2>お問い合わせ</h2>
  <c:if test="${not empty msg}"><p class="error">${msg}</p></c:if>
  <div class="form_body">
    <form:form action="information" method="post" modelAttribute="information">
	    <div class="title">
	      <label>件名</label><br>
	      <form:input path="title" type="text"/>
	    </div>
	    <div class="contents">
	      <label>内容</label><br>
	      <form:textarea id="textarea" path="contents" name="お問い合わせ内容" cols="100" rows="10" placeholder="こちらに記述してください。"></form:textarea>
	    </div>
        <div class="btns">
          <form:button type="submit" class="insert-button info-btn"><fmt:message key="form.lbl.regist"/></form:button>
          <button type="button" onclick="location.href='/back'" class="back-button info-btn"><fmt:message key="form.lbl.back"/></button>
        </div>
    </form:form>
  </div>
</main>

<script src="js/commons.js"></script>
</body>
</html>