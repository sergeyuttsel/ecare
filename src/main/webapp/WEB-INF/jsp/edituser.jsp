<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.*, java.util.*, com.ecare.dao.model.Option, javax.servlet.*, javax.servlet.annotation.*, javax.servlet.http.*, com.ecare.dao.model.User, com.ecare.dao.model.User.Role"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<!-- attribute formaction="" HTML5.0 -->
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Edit option</title>
<link href="<c:url value="/bootstrap4/css/bootstrap.css"/>" rel="stylesheet">
</head>
<body>
<%
		session = request.getSession(false);
		User sessionUser = null;
		if (session.getAttribute("user") != null) {
			sessionUser = (User) session.getAttribute("user");
		}
		boolean sessionEmployee = sessionUser.getRole().equals(Role.EMPLOYEE);
		pageContext.setAttribute("sessionEmployee", sessionEmployee);
	%>
	<form action="/ecare/updateuser" method="POST">
		<div class="container">
			<%-- User --%>
			<div class="row">
				<div class="col-sm-12" align="center">
					<h2>User</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">

					First name <br> <input type="text" name="firstName"
						value="${user.firstName}"> <br> Last name <br> <input
						type="text" name="lastName" value="${user.lastName}"> <br>
					Email <br> <input type="text" name="email"
						value="${user.email}"> <br> Passport <br> <input
						type="text" name="passport" value="${user.passport}"> <br>
					Adress <br> <input type="text" name="adress"
						value="${user.adress}"> <br>
					<fmt:formatDate type="date" value="${user.getBirthday().getTime()}"
						pattern="yyyy-MM-dd" var="theFormattedDate" />
					Birthday <br> <input type="date" name="birthday"
						value="${theFormattedDate}"> <br> <br>

					<%-- End user --%>
				</div>
			</div>


		</div>
		<div class="row">
			<div class="col-sm-12" align="center">
				<button class="btn btn-default" type="submit" name="idUser" value="${user.id}">Apply</button>
			</div>
		</div>
	</form>
	<%-- End user --%>
	<%-- Contract --%>
	<form action="/ecare/editcontract" method="GET">
		<div class="container">
			<table style="width: 100%"
				class='table table-bordered table-condensed table-striped table-hover'>

				<thead>
					<tr>
						<th>№</th>
						<th>Phone number</th>
						<th>Plan</th>
						<th>Change plan</th>
						<th>Change options</th>
						<c:if test="${sessionEmployee == true}">
							<th>Remove contract</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<%
						int indexRow = 0;
					%>
					<c:forEach items="${user.contracts}" var="iContract">
						<tr>
							<%
								++indexRow;
							%>
							<td><%=indexRow%></td>
							<td><c:out value="${iContract.phoneNumber}" /></td>
							<td><c:out value="${iContract.plan.name}" /></td>
							<td><button type="submit" formaction="/ecare/changeplan"
									name="idContract" value="${iContract.id}">Change plan</button></td>
							<td><button type="submit" formaction="/ecare/changeoptions"
									name="idContract" value="${iContract.id}">Change
									options</button></td>
							<c:if test="${sessionEmployee == true}">
							<td><button type="submit" formaction="/ecare/removecontracthandler" formmethod="POST"
									name="idContract" value="${iContract.id}">Remove contract</button></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</form>
	<%-- End contract --%>

	<%-- Create contract --%>
	
	<c:if test="${sessionEmployee == true}">
		<form action="/ecare/createcontracthandler" method="POST">
			<div class="container">
				<%-- User --%>
				<div class="row">
					<div class="col-sm-12" align="center">
						<h2>New contract</h2>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">

						Phone number <br> <input type="text" name="phoneNumber">
						<br>
						<button type="submit" name="idUser" value="${user.id}">Create
							contract</button>

						<%-- End user --%>
					</div>
				</div>


			</div>
		</form>
	</c:if>
	<%-- End create contract --%>

	<!--111
	<form action="/ecare/editcontract" method="GET">
		<div class="container">
			<table style="width: 100%"
				class='table table-bordered table-condensed table-striped table-hover'>

				<thead>
					<tr>
						<th>№</th>
						<th>Phone number</th>
						<th>Edit</th>

					</tr>
				</thead>
				<tbody>
					<
						int indexRow = 0;
					%>
					<c:forEach items="${user.contracts}" var="iContract">
						<tr>
							<
								++indexRow;
							%>
							<td><=indexRow%></td>
							<td><c:out value="${iContract.phoneNumber}" /></td>
							<td><button type="submit" name="idContract"
									value="${iContract.id}">Edit</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
	</form>
	111-->

	<script src="<c:url value="/bootstrap4/js/bootstrap.js"/>"></script>
</body>
</html>