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
<title>Student Barter Street - Posts</title>

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
	<div class="main-content">

		<div class="row">
			<div class="col-sm-2">
				<div>
					<label>Item Categories</label>
				</div>
				<form method="GET" action="${contextPath}/fliterposts"
					id="filterform">
					<c:forEach items="${itemcategories}" var="categoryname">
						<div class="checkbox">
							<label> <input type="checkbox" name="itemcategories"
								id="${categoryname.itemcid}" value="${categoryname.itemcid}"
								<c:if test="${categoryname.status}">checked</c:if>>
								${categoryname.itemcname}
							</label>
						</div>
					</c:forEach>
					<div class="form-group">
						<button class="btn btn-sm" type="submit" id="filterbtn"
							name="filter" value="Y">Filter</button>
						<button class="btn btn-sm" type="submit" id="clearbtn"
							name="filter" value="N">Clear</button>
					</div>
				</form>
			</div>
			<div class="col-sm-10">
				<form method="GET" action="${contextPath}/postdetails"
					id="postsform">
					<h4 align="center">User Posts</h4>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Post Title</th>
								<th>Description</th>
								<th>Category</th>
								<th>Cost ($)</th>
								<th>Posted By</th>
								<th>Posted On</th>
								<th>Have More Questions?</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${empty posts}">
								<tr>
									<td colspan="7">-- No user posts yet. Please check back
										later --</td>
								</tr>
							</c:if>
							<c:forEach items="${posts}" var="post">
								<tr>
									<td>${post.title}</td>
									<td>${post.description}</td>
									<td>${post.itemCategoryName}</td>
									<td>${post.itemworth}</td>
									<td>${post.addedbyName}</td>
									<td>${post.formattedAddedDate}</td>
									<td>
										<p>
											<a
												href="${contextPath}/emailuserpost?email=${post.addedby}&itemid=${post.itemid}">Email</a>
											${post.addedbyName}
										</p>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<p class="emailconfirm">${emailquerysuccesspost}</p>

				</form>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>