<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.*, java.util.*, com.ecare.dao.model.Plan"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="./bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="./bootstrap/css/bootstrap-theme.min.css">
<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
<title>Change plan</title>
<link href="<c:url value="/bootstrap4/css/bootstrap.css"/>" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12" align="center">
				<h2>All plans</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<form action="/ecare/changeplanhandler" method="POST">
					<table style="width: 100%"
						class='table table-bordered table-condensed table-striped table-hover'>

						<thead>
							<tr>
								<th>№</th>
								<th>Name</th>
								<th>Price</th>
								<th>Choose</th>
							</tr>
						</thead>
						<%
						    int indexRow = 0;
						%>
						<tbody>
							<c:forEach items="${planList}" var="plan">
								<tr>
									<%
									    ++indexRow;
									%>
									<td><%=indexRow%></td>
									<td><c:out value="${plan.name}" /></td>
									<td><c:out value="${plan.price}" /></td>
									<td><button type="submit" name="idPlan"
											value="${plan.id}">Choose</button></td>
								</tr>
							</c:forEach>
						</tbody>
						
					</table>
					<input type="hidden" name="idContract" value="${contract.id}">
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="./js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript" src="./bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./bootstrap/js/npm.js"></script>
</body>
</html>