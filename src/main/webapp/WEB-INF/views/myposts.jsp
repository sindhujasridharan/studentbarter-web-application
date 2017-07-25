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
<title>My Posts</title>

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
	<div class="main-content mypostdiv">
		<form method="POST" action="${contextPath}/mypostsaction">
			<span>${message}</span>
			<h4>My Posts</h4>
			<table class="mytable table-striped" border="1">
				<thead>
					<tr>
						<th>Post Title</th>
						<th>Description</th>
						<th>Category</th>
						<th>Cost ($)</th>
						<th>Added On</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty myposts}">
						<tr>
							<td colspan="5">-- You did not post anything yet --</td>
						</tr>
					</c:if>
					<c:forEach items="${myposts}" var="mypost">
						<tr>
							<td><a href="${contextPath}/modifypost?itemid=${mypost.itemid}">${mypost.title}</a></td>
							<td>${mypost.description}</td>
							<td>${mypost.itemCategoryName}</td>
							<td>${mypost.itemworth}</td>
							<td>${mypost.formattedAddedDate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		<div>
			<p class="addpost">
				Want to add a post? <a href="${contextPath}/addnewpost">Add New
					Post</a>
			</p>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>