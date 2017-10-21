<header id="header" class="headroom">
    <div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
        <div class="container-fluid">

            <div class="navbar-header">
                <p class="pull-left visible-xs">
                    <button id="offcanvasleft" class="btn btn-xs" type="button" data-toggle="offcanvasleft"><i class="glyphicon glyphicon-chevron-left"></i></button>
                </p>
                <p class="pull-right visible-xs">
                    <button id="offcanvasright" class="btn btn-xs" type="button" data-toggle="offcanvasright"><i class="glyphicon glyphicon-chevron-right"></i></button>
                </p>
                <button class="navbar-toggle" type="button" data-target=".navbar-collapse" data-toggle="collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <img src="GSHCLogoImage/golden-sun-home-care-logo-resized.jpeg"/>
            </div>

            <%
                String username = request.getRemoteUser();
            %>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#about">About</a></li>
                    <li><a href="#contact">Contact</a></li>
                    <li>Hello, <%= username %></li>
                    <li><a href="${pageContext.request.contextPath}/logout" class="navbar-brand pull-right">Logout</a></li>
                </ul>
            </div>
            <!-- /.nav-collapse -->


        </div>
        <!-- /.container -->
    </div>
    <!-- /.navbar -->
</header>
