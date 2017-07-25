<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Add New Item Category</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>
	<div class="navbar navbar-default">
		<div class="container-fluid">
			<div class="userwelcome">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<form id="logoutForm" method="POST" action="${contextPath}/logout">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
					<h4>
						Welcome ${pageContext.request.userPrincipal.name} | <a
							onclick="document.forms['logoutForm'].submit()">Logout</a>
					</h4>

				</c:if>
			</div>
			<div class="site-title">Student Barter Street</div>
			<div>
				<ul class="nav nav-tabs">
					<li><a href="<c:url value="/about" />">About</a></li>
					<li><a href="<c:url value="/posts" />">User Posts</a></li>
					<li><a href="<c:url value="/myposts" />">My Posts</a></li>
					<li><a href="<c:url value="/transactionhistory" />">Transaction
							History</a></li>
					<li><a href="<c:url value="/contactus"/>">Contact Us</a></li>
					<c:if test="${isAdminSession}">
						<li><a href="${contextPath}/adminfunctions">Admin
								Services</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<div style="clear: both"></div>
	<div class="main-content">
		<div class="row">
			<div class="col-sm-2">
				<label>Existing Item Categories:</label>
				<ul>
					<c:forEach items="${itemcategories}" var="itemcategory">
						<li>${itemcategory.itemcname}</li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-sm-4">
				<form method="POST" modelAttribute="itemcnameForm"
					action="${contextPath}/insertnewitemcategory" class="form-signin">
					<span>${message}</span>
					<h4 align="center" class="form-heading">Add Item Category</h4>

					<div class="form-group ${error != null ? 'has-error' : ''}">
						<input name="itemcname" type="text" class="form-control"
							placeholder="Item category name" autofocus="true" required="true" />
						<span style = "color: red"> <c:if test="${sessionScope.CategoryExists == 'yes'}">Category Name exists. Try a different one.
								</c:if></span> <input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<button class="btn btn-sm btn-primary btn-block type="submit">Add</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>