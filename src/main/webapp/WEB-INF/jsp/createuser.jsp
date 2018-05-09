<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.*, java.util.*, com.ecare.dao.model.Option,
	com.ecare.dao.model.Option"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="./bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="./bootstrap/css/bootstrap-theme.min.css">
<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
<title>Create user</title>
<link href="<c:url value="/bootstrap4/css/bootstrap.css"/>" rel="stylesheet">
</head>
<body>
	<form action="/ecare/createuserhandler" method="POST">
		<div class="container">
			<%-- User --%>
			<div class="row">
				<div class="col-sm-12" align="center">
					<h2>Create User</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">

					First name <br>
					<input type="text" name="firstName"> <br>
					Last name <br>
					<input type="text" name="lastName"> <br>
					Email <br>
					<input type="text" name="email"> <br>
					Passport <br>
					<input type="text" name="passport"> <br>
					Adress <br>
					<input type="text" name="adress"> <br>
					<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd" var="theFormattedDate"/>
					Birthday <br>
					<input type="date" name="birthday" value="${theFormattedDate}"> <br>
					Password <br>
					<input type="text" name="password"> <br>
					
                </div>
			</div>
			<%-- End User --%>
			
			

		</div>
		<div class="row">
			<div class="col-sm-12" align="center">
				<button type="submit">Apply</button>
			</div>
		</div>
	</form>
	<script type="text/javascript" src="./js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript" src="./bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./bootstrap/js/npm.js"></script>
</body>
</html>