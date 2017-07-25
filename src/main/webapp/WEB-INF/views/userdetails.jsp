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

<title>Update user profile</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<div class="navbar navbar-default">
		<div class="container-fluid">
			<div class="site-title">Student Barter Street</div>
		</div>
	</div>
	<div style="clear: both"></div>

	<div class="main-content">

		<form:form method="POST" modelAttribute="userForm">
			<h3 class="center-align">Update your Profile</h3>

			<spring:bind path="firstName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<div class="col-md-12 line-spacer">
						<div class="col-md-4">First Name</div>
						<div class="col-md-8">
							<form:input type="text" path="firstName" class="form-control"
								placeholder="First name" autofocus="true" required="true"></form:input>
							<form:errors path="firstName"></form:errors>
						</div>
					</div>
				</div>
			</spring:bind>

			<spring:bind path="middleName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<div class="col-md-12 line-spacer">
						<div class="col-md-4" class="form-label">Middle Name</div>
						<div class="col-md-8">
							<form:input type="text" path="middleName" class="form-control"
								placeholder="Middle name"></form:input>
							<form:errors path="middleName"></form:errors>
						</div>
					</div>
				</div>
			</spring:bind>

			<spring:bind path="lastName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<div class="col-md-12 line-spacer">
						<div class="col-md-4">Last Name</div>
						<div class="col-md-8">
							<form:input type="text" path="lastName" class="form-control"
								placeholder="Last name" required="true"></form:input>
							<form:errors path="lastName"></form:errors>
						</div>
					</div>
				</div>
			</spring:bind>

			<spring:bind path="dateOfBirth">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<div class="col-md-12 line-spacer">
						<div class="col-md-4">Date of Birth</div>
						<div class="col-md-8">
							<form:input type="date" path="dateOfBirth" class="form-control"
								placeholder="Date of Birth"></form:input>
							<form:errors path="dateOfBirth"></form:errors>
						</div>
					</div>
				</div>
			</spring:bind>

			<spring:bind path="phoneNumber">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<div class="col-md-12 line-spacer">
						<div class="col-md-4">Phone number</div>
						<div class="col-md-8">
							<form:input type="text" path="phoneNumber" class="form-control"
								placeholder="Phone Number (Mobile)"></form:input>
							<form:errors path="phoneNumber"></form:errors>
						</div>
					</div>
				</div>
			</spring:bind>

			<spring:bind path="address">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<div class="col-md-12 line-spacer">
						<div class="col-md-4">Address</div>
						<div class="col-md-8">
							<form:textarea path="address" class="form-control"
								placeholder="Address"></form:textarea>
							<form:errors path="address"></form:errors>
						</div>
					</div>
				</div>
			</spring:bind>

			<%-- <spring:bind path="profileImageName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
            	<div class="col-md-12 line-spacer">
    				<div class="col-md-4">Profile Image</div>
    				<div class="col-md-8">
                		<form:input type="file" path="profileImageName" class="form-control" placeholder="Profile Image"></form:input>
                		<form:errors path="profileImageName"></form:errors>
                	</div>
            	</div>
            </div>
        </spring:bind> --%>

			<div class="right-align">
				<div class="col-md-12">
					<div class="col-md-2">
						<%-- <a class="btn btn-lg btn-default btn-link" href="${contextPath}/posts">Skip for now!!</a> --%>
					</div>
					<div class="col-md-10">
						<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
					</div>
				</div>
			</div>
		</form:form>

	</div>
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
