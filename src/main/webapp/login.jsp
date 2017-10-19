<%@include file="/Includes/taglib.jsp"%>
<c:set var="title" value="GSHC Log In"/>

<html>
<%@include file="/Includes/head.jsp"%>
<body>
<!-- Login form goes here -->
<div class="panel panel-warning">
    <div class="panel-heading"><img src="GSHCLogoImage/golden-sun-home-care-logo.jpeg" alt="GSHC Logo"></div>
    <div class="panel-body">
        <form class="login-form" action="j_security_check" method="POST">
            <div class="form-group">
                <label for="email">Email address:</label>
                <input type="email" name="j_username" class="form-control" id="email">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" name="j_password" class="form-control" id="password">
            </div>
            <button type="submit" class="btn btn-warning" name="login-btn" value="login">Log In</button>
        </form>
    </div>
</div>
</body>
</html>
