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
<title>Transactions History</title>

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
	<div class="main-content transdiv">

		<form method="POST" action="${contextPath}/solditemssubmit">
			<span>${message}</span>
			<h4 class="form-heading">Items Sold</h4>
			<table class="mytable table-striped" border="1">
				<thead>
					<tr>
						<th>Post Title</th>
						<th>Category</th>
						<th>Cost ($)</th>
						<th>Sold To</th>
						<th>Sold on</th>
						<th>Buyer Rating</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty solditems}">
						<tr>
							<td colspan="6">-- No Sold Items --</td>
						</tr>
					</c:if>
					<c:forEach items="${solditems}" var="post">
						<tr>
							<td>${post.title}</td>
							<td>${post.itemCategoryName}</td>
							<td>${post.itemworth}</td>
							<td>${post.endUserName}</td>
							<td>${post.formattedSoldDate}</td>
							<td>${post.rating}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		<form method="GET" action="${contextPath}/boughtitemsrate">
			<span>${message}</span>
			<h4 class="form-heading">Items Bought</h4>
			<table class="mytable table-striped" border="1">
				<thead>
					<tr>
						<th>Post Title</th>
						<th>Category</th>
						<th>Cost ($)</th>
						<th>Seller Name</th>
						<th>Bought On</th>
						<th>Rate It</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty boughtitems}">
						<tr>
							<td colspan="6">-- No Bought Items--</td>
						</tr>
					</c:if>
					<c:forEach items="${boughtitems}" var="bought">
						<tr>
							<td>${bought.title}</td>
							<td>${bought.itemCategoryName}</td>
							<td>${bought.itemworth}</td>
							<td>${bought.addedUserName}</td>
							<td>${bought.formattedSoldDate}</td>
							<c:choose>
								<c:when test="${empty bought.rating}">
									<td><select id="rated" name="rated">
											<option value="1.0-${bought.itemid}">1.0</option>
											<option value="1.5-${bought.itemid}">1.5</option>
											<option value="2.0-${bought.itemid}">2.0</option>
											<option value="2.5-${bought.itemid}">2.5</option>
											<option value="3.0-${bought.itemid}">3.0</option>
											<option value="3.5-${bought.itemid}">3.5</option>
											<option value="4.0-${bought.itemid}">4.0</option>
											<option value="4.5-${bought.itemid}">4.5</option>
											<option value="5.0-${bought.itemid}">5.0</option>
									</select>
										<button class="btn btn-sm" type="submit" id="rateit"
											name="rateit">Rate It</button></td>
								</c:when>
								<c:otherwise>
									<td>${bought.rating}</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>