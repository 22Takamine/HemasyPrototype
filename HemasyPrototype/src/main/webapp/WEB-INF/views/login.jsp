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
<title>トップ画面</title>
</head>
<body>
  <h1>メールアドレス及びパスワードを入力してください</h1>
  
  
  <c:if test="${not empty msg}">
  	<p>${fn:escapeXml(msg)}</p>
  </c:if>
  <form:form action="result" modelAttribute="index" method="post">
    
    <div>
      <label><fmt:message key="form.lbl.mail"/></label><form:input path="mail" type="text"/>
      <form:errors path="mail" cssStyle="color: red"/>
    </div>
    
    <div>
      <label><fmt:message key="form.lbl.password"/></label><form:input path="password" type="password"/>
      <form:errors path="password" cssStyle="color: red"/>
    </div>
    
    <form:button name="login"><fmt:message key="form.lbl.login"/></form:button>
    <form:button name="register"><fmt:message key="form.lbl.register"/></form:button>
  
  </form:form>
</body>
</html>