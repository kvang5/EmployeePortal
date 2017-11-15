<%@include file="../Includes/taglib.jsp"%>
<c:set var="title" value="Employee Sign Up Form"/>

<html>
<%@include file="../Includes/head.jsp"%>
<body>
<%@include file="../Includes/headNavBar.jsp"%>
<div class="container-fluid">
    <!-- These two divs are for Javascript -->
    <div class="row row-offcanvas row-offcanvas-left">
        <div class="row-offcanvas row-offcanvas-right">

        <%@include file="../Includes/leftSideBar-Admin.jsp"%>

        <%@include file="content-employee-signup-form.jsp"%>

        <%@include file="../Includes/rightSideBar-Admin.jsp"%>

        </div>
    </div>
</div>

    <script type="application/javascript">
        $('#offcanvasleft').click(function() {
            $('.row-offcanvas-left').toggleClass('active');
        });

        $('[data-toggle=offcanvasright]').click(function() {
            $('.row-offcanvas-right').toggleClass('active');
        });
    </script>
</body>
</html>