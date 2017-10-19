<div class="col-xs-12 col-sm-8">
    <div class="jumbotron">
        <div class="container">
            <div class="container-fluid">
                <h2>Search Results: </h2>
                <table id="employeeTable" class="display" cellspacing="0" width="100%">
                    <thead>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <!--
                    <th>Address</th>
                    <th>Apartment</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Postal Zip Code</th>
                    <th>Job Title</th>
                    <th>Home Phone</th>
                    <th>Mobile Phone</th>
                    -->
                    <th>Email</th>
                    </thead>
                    <tbody>
                    <c:forEach var="employee" items="${employees}">
                        <tr>
                            <td>${employee.first_name}</td>
                            <td>${employee.last_name}</td>
                            <!--
                            <td>${employee.address1}</td>
                            <td>${employee.address2}</td>
                            <td>${employee.city}</td>
                            <td>${employee.state}</td>
                            <td>${employee.postal_zip_code}</td>
                            <td>${employee.title}</td>
                            <td>${employee.home_phone}</td>
                            <td>${employee.mobile_phone}</td>
                            -->
                            <td>${employee.email}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#employeeTable').DataTable();
    });
</script>