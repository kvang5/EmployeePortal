<%@include file="../Includes/taglib.jsp"%>
<c:set var="title" value="Client Note"/>

<html>
<%@include file="../Includes/head.jsp"%>
<body>
<%@include file="../Includes/headNavBar.jsp"%>
<div class="container-fluid">
    <!-- These two divs are for Javascript -->
    <div class="row row-offcanvas row-offcanvas-left">
        <div class="row-offcanvas row-offcanvas-right">

            <%@include file="../Includes/leftSideBar-Employee.jsp"%>

            <%@include file="content-employee-client-aide-weekly-task-schedule.jsp"%>

            <%@include file="../Includes/rightSideBar-Employee.jsp"%>

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


