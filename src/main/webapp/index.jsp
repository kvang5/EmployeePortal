<%@include file="taglib.jsp"%>
<c:set var="title" value="GSHC Log In"/>

<html>
    <%@include file="head.jsp"%>
    <body>
        <!-- Login form goes here -->
        <div class="panel panel-warning">
            <div class="panel-heading"><img src="GSHCLogoImage/golden-sun-home-care-logo.jpeg" alt="GSHC Logo"></div>
            <div class="panel-body">
                <form class="login-form">
                    <div class="form-group">
                        <label for="email">Email address:</label>
                        <input type="email" class="form-control" id="email">
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" class="form-control" id="password">
                    </div>
                    <button type="submit" class="btn btn-warning">Log In</button>
                </form>
            </div>
        </div>
    </body>
</html>
