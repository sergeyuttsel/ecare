<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.*, java.util.*, com.ecare.dao.model.Option,
	com.ecare.dao.model.Plan, com.ecare.dao.model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="./bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="./bootstrap/css/bootstrap-theme.min.css">
<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
<title>Users</title>
<link href="<c:url value="/bootstrap4/css/bootstrap.css"/>" rel="stylesheet">
</head>
<body>

	<div class="container">
		<%-- Users --%>
		<div class="row">
			<div class="col-sm-12" align="center">
				<h2>Users</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<form action="/ecare/user" method="GET">
					<table style="width: 100%"
						class='table table-bordered table-condensed table-striped table-hover'>

						<thead>
							<tr>
								<th>№</th>
								<th>First name</th>
								<th>Last name</th>
								<th>Email</th>
								<th>Passport</th>
								<th>Adress</th>
								<th>Birthday</th>
								<th>Edit</th>

							</tr>
						</thead>
						<tbody>
							<%
								int indexRow = 0;
							%>
							<c:forEach items="${userList}" var="iUser">
								<tr>
									<%
										++indexRow;
									%>
									<td><%=indexRow%></td>
									<td><c:out value="${iUser.firstName}" /></td>
									<td><c:out value="${iUser.lastName}" /></td>
									<td><c:out value="${iUser.email}" /></td>
									<td><c:out value="${iUser.passport}" /></td>
									<td><c:out value="${iUser.adress}" /></td>
									<td><fmt:formatDate type="date"
											value="${iUser.getBirthday().getTime()}" /></td>
									<td><button type="submit" name="idUser"
											value="${iUser.id}">More</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
		</div>
		<%-- End users --%>
	</div>
	<script type="text/javascript" src="./js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript" src="./bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./bootstrap/js/npm.js"></script>
</body>
</html>