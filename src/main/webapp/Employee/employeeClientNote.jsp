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

            <c:choose>
                <c:when test="${pageContext.request.isUserInRole('Administrator')}">
                    <%@include file="../Includes/leftSideBar-Admin.jsp"%>

                    <%@include file="content-employee-client-note.jsp"%>

                    <%@include file="../Includes/rightSideBar-Admin.jsp"%>
                </c:when>
                <c:when test="${pageContext.request.isUserInRole('Registered-user')}">
                    <%@include file="../Includes/leftSideBar-Employee.jsp"%>

                    <%@include file="content-employee-client-note.jsp"%>

                    <%@include file="../Includes/rightSideBar-Employee.jsp"%>
                </c:when>
            </c:choose>

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


