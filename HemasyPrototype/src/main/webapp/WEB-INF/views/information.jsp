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
<link href="css/commons.css" rel="stylesheet">
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
  <div class="main">
  <h2>お問い合わせ</h2>
  
  <div class="insert">
    <div class="discription">
      <p>お問い合わせ内容を入力してください</p>
    </div>
  
    <div class="form_body">
    
      <c:if test="${not empty msg}"><p class="error">${msg}</p></c:if>
      
      <form:form action="information" method="post" modelAttribute="information">
<%--       	<form:input path="userId" type="hidden" value="${userId}" /> --%>
        <fieldset class="label-130">
          <div>
            <label class="required">件名</label>
            <form:input path="title" type="text" class="base-text" />
          </div>
          <div>
            <label class="required">お問い合わせ内容</label>
            <form:textarea path="contents" name="お問い合わせ内容" cols="50" rows="5"></form:textarea>
          </div>
        </fieldset>
        <div id="modal">
          <div class="btns">
           <form:button type="submit" class="basic_btn">登録</form:button>
           <button type="button" onclick="location.href='/back'" class="cancel_btn">戻る</button>
          </div>
        </div>
      </form:form>
    </div>
  </div>
  
  <%-- <form:form action="information" modelAttribute="information" method="post">
    <form:button><fmt:message key="form.lbl.regist"/></form:button>
  </form:form>
  
  <form:form action="back" modelAttribute="information" method="get">
    <form:button><fmt:message key="form.lbl.back"/></form:button>
  </form:form> --%>
  </div>
<script src="js/commons.js"></script>
</body>
</html>