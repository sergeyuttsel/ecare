<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.*, java.util.*, com.ecare.dao.model.Option"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
<title>All options</title>
<link href="<c:url value="/bootstrap4/css/bootstrap.css"/>" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12" align="center">
				<h2>All options</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<form action="/ecare/option" method="GET">
					<table style="width: 100%"
						class='table table-bordered table-condensed table-striped table-hover'>

						<thead>
							<tr>
								<th>№</th>
								<th>Name</th>
								<th>Price</th>
								<th>Price connect</th>
								<th>Archive</th>
								<th>Edit</th>
							</tr>
						</thead>
						<%
						    int indexRow = 0;
						%>
						<tbody>
							<c:forEach items="${optionList}" var="option">
								<tr>
									<%
									    ++indexRow;
									%>
									<td><%=indexRow%></td>
									<td><c:out value="${option.name}" /></td>
									<td><c:out value="${option.price}" /></td>
									<td><c:out value="${option.priceConnect}" /></td>
									<c:if test="${option.archival == true}">
										<td>archival</td>
									</c:if>
									<c:if test="${option.archival == false}">
										<td>not archival</td>
									</c:if>
									<td><button type="submit" name="idOption"
											value="${option.id}">More</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>

</body>
</html>