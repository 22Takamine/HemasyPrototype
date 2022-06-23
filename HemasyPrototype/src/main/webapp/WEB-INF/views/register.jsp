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
<link href="css/commons.css" rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<body>
	<h1>登録情報を入力してください</h1>

	<form:form action="loginBack" modelAttribute="index" method="post">

		<div>
			<fmt:message key="form.lbl.name" />
			<form:input path="name" />
			<form:errors path="name" cssStyle="color: red"/>
		</div>

		<div>
			<fmt:message key="form.lbl.mail" />
			<form:input type="mail" path="mail" />
			<form:errors path="mail" cssStyle="color: red"/>
		</div>

		<div>
			<fmt:message key="form.lbl.password" />
			<form:password path="password" />
			<form:errors path="password" cssStyle="color: red"/>
		</div>

		<div>
			<fmt:message key="form.lbl.sex" />
			<form:radiobutton path="sex" value="0"/>
			男
			<form:radiobutton path="sex" value="1"/>
			女
		</div>

		<div>
			<fmt:message key="form.lbl.birthDate" />
			<form:input type="date" path="birthDate" />
			<form:errors path="birthDate" cssStyle="color: red"/>
		</div>

		<div>
			<fmt:message key="form.lbl.height" />
			<form:input path="height" />
			<form:errors path="height" cssStyle="color: red"/>
		</div>

		<div>
			<fmt:message key="form.lbl.weight" />
			<form:input path="weight" />
			<form:errors path="weight" cssStyle="color: red"/>
		</div>

		<div>
			<fmt:message key="form.lbl.bodyFatPercentage" />
			<form:input path="bodyFatPercentage" />
		</div>
		
		<div>
			<fmt:message key="form.lbl.time" />
			<form:input path="goalExerciseTime" />
		</div>

		<div>
			<fmt:message key="form.lbl.rank" />

			<form:radiobutton path="rankFlag" value="0"/>
			参加する
			<form:radiobutton path="rankFlag" value="1"/>
			参加しない
		</div>

		<div>
			<fmt:message key="form.lbl.smoke" />

			<form:radiobutton path="smokeFlag" value="0"/>
			吸う
			<form:radiobutton path="smokeFlag" value="1"/>
			吸わない

		</div>

		<div>
			<fmt:message key="form.lbl.alcohol" />

			<form:radiobutton path="alcoholFlag" value="0"/>
			飲む
			<form:radiobutton path="alcoholFlag" value="1"/>
			飲まない
		</div>

		<form:button>
			<fmt:message key="form.lbl.regist" />
		</form:button>
	</form:form>

	<form:form action="index" modelAttribute="index" method="post">
		<form:button>
			<fmt:message key="form.lbl.back" />
		</form:button>
	</form:form>
	<script src="js/commons.js"></script>
</body>
</html>