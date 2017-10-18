<%@include file="taglib.jsp"%>
<c:set var="title" value="Dashboard"/>

<html>
<%@include file="head.jsp"%>
<body>
<%@include file="sideNavigation.jsp"%>

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
