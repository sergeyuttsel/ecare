<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.*, java.util.*, com.ecare.dao.model.Option,
	com.ecare.dao.model.Option"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="./bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="./bootstrap/css/bootstrap-theme.min.css">
<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
<title>Change options</title>
<link href="<c:url value="/bootstrap4/css/bootstrap.css"/>" rel="stylesheet">
</head>
<body>
	<form action="/ecare/changeoptionshandler" method="POST">
		<div class="container">
			
			<%-- Options --%>
			<div class="row">
				<div class="col-sm-12" align="center">
					<h4>Options</h4>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<table style="width: 100%"
						class='table table-bordered table-condensed table-striped table-hover'>

						<thead>
							<tr>
								<th>№</th>
								<th>Name</th>
								<th>Price</th>
								<th>Price connect</th>
								<th>Connected</th>
							</tr>
						</thead>
						<%
						    int indexRow = 0;
						%>
						<tbody>
							<c:forEach items="${optionList}" var="iOption">
								<tr>
									<%
									    ++indexRow;
									%>
									<td><%=indexRow%></td>
									<td><c:out value="${iOption.name}" /></td>
									<td><c:out value="${iOption.price}" /></td>
									<td><c:out value="${iOption.priceConnect}" /></td>
									<c:if
										test="${contract.getContractOptions().contains(iOption) == true}">
										<td><input type="checkbox" name="connected"
											value="${iOption.id}" checked></td>
									</c:if>
									<c:if
										test="${contract.getContractOptions().contains(iOption) == false}">
										<td><input type="checkbox" name="connected"
											value="${iOption.id}"></td>
									</c:if>
									<!--<td><input type="checkbox" name="connected"
											value="${iOption.id}"></td>-->
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<%-- End options --%>

		</div>
		<div class="row">
			<div class="col-sm-12" align="center">
				<button type="submit" name="idContract" value="${contract.id}">Apply</button>
			</div>
		</div>
	</form>
	<script type="text/javascript" src="./js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript" src="./bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./bootstrap/js/npm.js"></script>
</body>
</html>