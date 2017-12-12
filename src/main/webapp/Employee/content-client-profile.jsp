<div class="col-xs-12 col-sm-8">
    <div class="jumbotron">
        <div class="container">
            <div class="container-fluid">
                <h2></h2>
                <table class="table client-profile">
                    <tbody>
                    <% pageContext.setAttribute("newLineChar", "\n"); %>
                    <c:forEach var="client" items="${clientProfile}" end="0">
                        <tr>
                            <td><h2><c:out value="${client.first_name} ${client.last_name}"/></h2></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td><c:out value="${client.email}"/></td>
                        </tr>
                        <tr>
                            <td>Address:</td>
                            <td>
                                <c:out value="${client.address1}"/>
                                <br/>
                                <c:choose>
                                    <c:when test="${client.address2 == ''}">
                                        <!--<c:out value="${client.address2}"/>-->
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="Apt. ${client.address2}"/>
                                        <br/>
                                    </c:otherwise>
                                </c:choose>
                                <c:out value="${client.city}, ${client.state.state_code} ${client.postal_zip_code}"/>
                            </td>
                        </tr>
                        <tr>
                        <tr>
                            <td>Home Phone:</td>
                            <td><c:out value="${client.home_phone}"/></td>
                        </tr>
                        <tr>
                            <td>Mobile Phone:</td>
                            <td><c:out value="${client.mobile_phone}"/></td>
                        </tr>
                        <tr>
                            <td>Status:</td>
                            <td>
                                <c:choose>
                                    <c:when test="${client.status == true}">
                                        <c:out value="Active"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="Not Active"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <table id="clientNoteTable" class="display" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <td><h3>Client Notes</h3></td>
                        <td></td>
                    </tr>
                    <th>Date</th>
                    <th>Care of Time</th>
                    <th>Description</th>
                    <th>Comments</th>
                    </thead>
                    <tbody>
                    <c:forEach var="note" items="${clientNotes}">
                        <tr>
                            <td>${note.date}</td>
                            <td>${note.care_time}</td>
                            <td>${note.description}</td>
                            <td>${note.comments}</td>
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

