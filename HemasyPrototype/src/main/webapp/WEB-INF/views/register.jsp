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
<title>新規登録</title>
<link href="css/commons.css" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
  <h1>登録情報を入力してください</h1>
  
  <form:form action="index" modelAttribute="index" method="post">
    
    <div>
		<fmt:message key="form.lbl.name"/>
		<input type="text" name="name" value="">      
    </div>
    
    <div>
		<fmt:message key="form.lbl.mail"/>
		<input type="text" name="mail" value="">      
    </div>
    
    <div>
		<fmt:message key="form.lbl.password"/>
		<input type="password" name="pass" value="">      
    </div>
    
    <div>
    	<fmt:message key="form.lbl.sex"/> 
			<input type="radio" name="sex" value="men" checked>男      
			<input type="radio" name="sex" value="women">女
    </div>
    
    <div>
		<fmt:message key="form.lbl.birthDate"/>
		<input type="date" name="birthDate" value="">      
    </div>
    
    <div>
		<fmt:message key="form.lbl.height"/>
		<input type="text" name="height" value="">      
    </div>
    
    <div>
		<fmt:message key="form.lbl.weight"/>
		<input type="text" name="weight" value="">      
    </div>
    
    <div>
		<fmt:message key="form.lbl.bodyFatPercentage"/>
		<input type="text" name="bodyFatPercentage" value="">      
    </div>
    
    <div>
    	<fmt:message key="form.lbl.rank"/>
    	<c:if test="${rank}">   
			<input type="radio" name="rank" value="yes" checked>参加する      
			<input type="radio" name="rank" value="no">参加しない
		</c:if>   
			<input type="radio" name="rank" value="yes">参加する      
			<input type="radio" name="rank" value="no" checked>参加しない 
    </div>
    
    <div>
    	<fmt:message key="form.lbl.smoke"/>
    	<c:if test="${smoke}">   
			<input type="radio" name="smoke" value="yes" checked>吸っている      
			<input type="radio" name="smoke" value="no">吸わない
		</c:if>   
			<input type="radio" name="smoke" value="yes">吸っている      
			<input type="radio" name="smoke" value="no" checked>吸わない 
    </div>
    
    <div>
    	<fmt:message key="form.lbl.alcohol"/>
    	<c:if test="${alcohol}">   
			<input type="radio" name="alcohol" value="yes" checked>飲んでいる     
			<input type="radio" name="alcohol" value="no">飲まない
		</c:if>   
			<input type="radio" name="alcohol" value="yes">飲んでいる      
			<input type="radio" name="alcohol" value="no" checked>飲まない 
    </div>
    <form:button><fmt:message key="form.lbl.regist"/></form:button>
  </form:form>
  
  <form:form action="index" modelAttribute="index" method="post">
    <form:button><fmt:message key="form.lbl.back"/></form:button>
  </form:form>
<script src="js/commons.js"></script>
</body>
</html>