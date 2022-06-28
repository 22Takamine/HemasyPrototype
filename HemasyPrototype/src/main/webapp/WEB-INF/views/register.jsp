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
<link
	href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
 <link href="css/common.css" rel="stylesheet">
 <link
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
  				<div class="red">${fn:escapeXml(msgName)}</div>
  			</c:if>
		</div>

		<div class="mb-20">
			<fmt:message key="form.lbl.mail" />
			<form:input type="email" path="mail" />
			<form:errors path="mail" cssStyle="color: red"/>
			<c:if test="${not empty msgMail}">
  				<div class="red">${fn:escapeXml(msgMail)}</div>
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
			<form:input id="datefield" type="date" path="birth" max='2000-13-13'/>
			<c:if test="${not empty msgBirth}" >
  				<div class="red">${fn:escapeXml(msgBirth)}</div>
  			</c:if>
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
		
		<button type="button" onclick="location.href='/index'" class="back-button info-btn"><fmt:message key="form.lbl.back"/></button>

	</form:form>

 </div>
	<script src="js/commons.js"></script>
</body>
<script>
function changeDate(){
var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1; //January is 0!
var yyyy = today.getFullYear();

if (dd < 10) {
   dd = '0' + dd;
}

if (mm < 10) {
   mm = '0' + mm;
} 
    
today = yyyy + '-' + mm + '-' + dd;
document.getElementById("datefield").setAttribute("max", today);
}
window.onload = changeDate();
</script>
</html>