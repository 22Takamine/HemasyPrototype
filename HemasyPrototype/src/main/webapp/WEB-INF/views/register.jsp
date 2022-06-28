<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link href="css/ameku.css" rel="stylesheet">
<!-- <link href="css/commons.css" rel="stylesheet">
 --><link href="css/common.css" rel="stylesheet">
<!-- <link href="css/style.css" rel="stylesheet">
 --><link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<body>
 <div class="center">
 
	<h1>登録情報を入力してください</h1>
	

	<form:form action="loginBack" modelAttribute="index" method="post">
	<form:input path="achievementId" type="hidden" value="1" readonly="readonly" />
		<div class="mb-20">
			<fmt:message key="form.lbl.name" />
			<form:input path="name" id="registerinput"/>
			<form:errors path="name" cssStyle="color: red"/>
			<c:if test="${not empty msgName}">
  				<div>${fn:escapeXml(msgName)}</div>
  			</c:if>
		</div>

		<div class="mb-20">
			<fmt:message key="form.lbl.mail" />
			<form:input type="email" path="mail" />
			<form:errors path="mail" cssStyle="color: red"/>
			<c:if test="${not empty msgMail}">
  				<div>${fn:escapeXml(msgMail)}</div>
  			</c:if>
		</div>

		<div class="mb-20">
			<fmt:message key="form.lbl.password" />
			<form:password path="password" />
			<form:errors path="password" cssStyle="color: red"/>
		</div>

		<div class="mb-20">
			<fmt:message key="form.lbl.sex" />
			<form:radiobutton path="sex" value="0" checked="checked"/>
			男
			<form:radiobutton path="sex" value="1"/>
			女
		</div>

		<div class="mb-20">
			<fmt:message key="form.lbl.birth" />
			<form:input type="date" path="birth" />
			<form:errors path="birth" cssStyle="color: red"/>
		</div>

		<div class="mb-20">
			<fmt:message key="form.lbl.height" />
			<form:input path="height" />cm
			<form:errors path="height" cssStyle="color: red"/>
		</div>

		<div class="mb-20">
			<fmt:message key="form.lbl.weight" />
			<form:input path="weight" />Kg
			<form:errors path="weight" cssStyle="color: red"/>
		</div>

		<div class="mb-20">
			<fmt:message key="form.lbl.bodyFatPercentage" />
			<form:input path="bodyFatPercentage" />%
		</div>
		
		<div class="mb-20">
			<fmt:message key="form.lbl.time" />
			<form:input path="goalExerciseTime" />分
		</div>

		<div class="mb-20">
			<fmt:message key="form.lbl.rank" />

			<form:radiobutton path="rankFlag" value="0"/>
			参加する
			<form:radiobutton path="rankFlag" value="1" checked="checked"/>
			参加しない
		</div>

		<div class="mb-20">
			<fmt:message key="form.lbl.smoke" />

			<form:radiobutton path="smokeFlag" value="0"/>
			吸う
			<form:radiobutton path="smokeFlag" value="1" checked="checked"/>
			吸わない

		</div>

		<div class="mb-20">
			<fmt:message key="form.lbl.alcohol" />

			<form:radiobutton path="alcoholFlag" value="0"/>
			飲む
			<form:radiobutton path="alcoholFlag" value="1" checked="checked"/>
			飲まない
		</div>

		<form:button class="mb-20 info-btn">
			<fmt:message key="form.lbl.regist" />
		</form:button>
		<form:form action="index" modelAttribute="index" method="post" >
			<form:button class="info-btn">
				<fmt:message key="form.lbl.back" />
			</form:button>
		</form:form>
	</form:form>

 </div>
	<script src="js/commons.js"></script>
</body>
</html>