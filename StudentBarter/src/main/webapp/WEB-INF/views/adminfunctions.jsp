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
<title>Admin Services</title>

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
		<h4 class="form-heading">Admin Services</h4>
		<ul>
			<li><a href="${contextPath}/additemcategory">Add Item
					Category</a></li>
			<li><a href="${contextPath}/userrolemodify">Modify User Role</a></li>
			<li><a href="${contextPath}/viewuserfeedback">View Users Feedback</a></li>
		</ul>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>