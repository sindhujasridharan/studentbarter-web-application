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
<title>Modify My Post</title>

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
		<form:form method="POST" modelAttribute="modifyPostForm">
			<h4 class="center-align">Modify Post</h4>
			
			<spring:bind path="title">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<div class="col-md-12 line-spacer">
						<div class="col-md-4">Post Title</div>
						<div class="col-md-8">
							<form:input type="text" path="title" class="form-control"
								placeholder="Post Title" autofocus="true" required="true"
								value="${modifyPostFormValues.title}"></form:input>
							<form:errors path="title"></form:errors>
						</div>
					</div>
				</div>
			</spring:bind>

			<spring:bind path="description">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<div class="col-md-12 line-spacer">
						<div class="col-md-4" class="form-label">Description</div>
						<div class="col-md-8">
							<form:input type="text" path="description" class="form-control"
								placeholder="Description of the Post" required="true"
								value = "${modifyPostFormValues.description}"></form:input>
							<form:errors path="description"></form:errors>
						</div>
					</div>
				</div>
			</spring:bind>

			<spring:bind path="itemworth">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<div class="col-md-12 line-spacer">
						<div class="col-md-4">Cost ($)</div>
						<div class="col-md-8">
							<form:input type="text" path="itemworth" class="form-control"
								placeholder="Cost" required="true" value = "${modifyPostFormValues.itemworth}"></form:input>
							<form:errors path="itemworth"></form:errors>
						</div>
					</div>
				</div>
			</spring:bind>

			<div class="col-md-12 line-spacer">
				<div class="col-md-4">Item Category</div>
				<div class="col-md-6">
					<select id="itemcategoriesmodifypost" name="itemcategoriesmodifypost">
						<c:forEach items="${itemcategoriesmodifypost}"
							var="itemcategoriespost">
							<option value="${itemcategoriespost.itemcid}" 
							 <c:if test="${itemcategoriespost.itemcid == modifyPostFormValues.itemcid}">selected</c:if>>
								${itemcategoriespost.itemcname}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="right-align">
				<div class="col-md-12">
					<div class="col-md-6">
						<button class="btn btn-lg btn-primary btn-block" type="submit" name="remove" value="true">Remove
							</button>
					</div>
					<div class="col-md-6">
						<button class="btn btn-lg btn-primary btn-block" type="submit">Edit Post
							</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>