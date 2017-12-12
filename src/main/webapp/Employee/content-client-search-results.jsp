<div class="col-xs-12 col-sm-8">
    <div class="jumbotron">
        <div class="container">
            <div class="container-fluid">
                <h2>Search Results: </h2>
                <table id="clientTable" class="display" cellspacing="0" width="100%">
                    <thead>
                    <th>Client Name</th>
                    <!--<th>Last Name</th>
                    <th>Address</th>
                    <th>Apartment</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Postal Zip Code</th>
                    <th>Home Phone</th>
                    <th>Mobile Phone</th>
                    -->
                    <th>Email</th>
                    </thead>
                    <tbody>
                    <c:forEach var="client" items="${clients}">
                        <tr>
                            <td><a href="clientProfile?email=${client.email}&id=${client.clientId}">${client.first_name}&nbsp;${client.last_name}</a></td>
                            <!--<td>${client.last_name}</td>
                            <td>${client.address1}</td>
                            <td>${client.address2}</td>
                            <td>${client.city}</td>
                            <td>${client.state}</td>
                            <td>${client.postal_zip_code}</td>
                            <td>${client.home_phone}</td>
                            <td>${client.mobile_phone}</td>
                            -->
                            <td>${client.email}</td>
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
        $('#clientTable').DataTable();
    });
</script>