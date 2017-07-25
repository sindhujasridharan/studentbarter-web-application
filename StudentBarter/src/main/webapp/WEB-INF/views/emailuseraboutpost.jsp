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
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<title>Email User About the Post</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
	<div class="navbar navbar-default">
		<div class="container-fluid">
			<div class="userwelcome">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<form:form id="logoutForm" method="POST"
						action="${contextPath}/logout">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form:form>
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
					<c:set var="isAdminSession" value="${isAdmin}" scope="session" />
					<c:if test="${isAdmin || isAdminSession}">
						<li><a href="${contextPath}/adminfunctions">Admin
								Services</a></li>
					</c:if>

				</ul>
			</div>
		</div>
	</div>
	<div style="clear: both"></div>
	<div class="main-content" align="center">

		<div>
			<h4 class="form-heading">Post Details</h4>
		</div>
		<form method="POST" action="" id="postdetailsform">
			<table>
				<tr>
					<td><label>Title:</label></td>
					<td>
						<p>${postdetails.title}</p>
					</td>
				</tr>
				<tr>
					<td><label>Description:</label></td>
					<td>
						<p>${postdetails.description}</p>
					</td>
				</tr>
				<tr>
					<td><label>Cost ($):</label></td>
					<td>
						<p>${postdetails.itemworth}</p>
					</td>
				</tr>
				<tr>
					<td><label>Posted On:</label></td>
					<td>
						<p>${postdetails.formattedAddedDate}</p>
					</td>
				</tr>
			</table>
		</form>
		<form method="POST" action="${contextPath}/emailqueries"
			id="emailquery">
			<span>${message}</span>
			<div class="form-group ${error != null ? 'has-error' : ''}">
				<textarea id="message" name="message" cols="35" rows="10"
					placeholder="Enter your queries here" required="true"></textarea>
				<span>${error}</span> <input type="hidden"
					name="${_csrf.parameterName}" value="${_csrf.token}" /> <br />
				<button class="btn btn-sm btn-primary" type="submit">Email
					Query</button>
			</div>
		</form>


	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>