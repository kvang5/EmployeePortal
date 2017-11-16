<div class="col-xs-12 col-sm-8">
    <div class="jumbotron">
        <div class="container">
            <form class="well form-horizontal" action="assignClientToEmployee" method="POST" id="assign-form">
                <fieldset>

                    <!-- Form Name -->
                    <legend><h2><b>Employee/Client Assignment Form</b></h2></legend><br>
                    <!-- Text input -- Employee -->
                    <!-- Use HTTP doGet to populate employee from employee table to select option -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Employee</label>
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <select name="employee" class="form-control selectpicker">
                                    <option value="">Select Title</option>
                                    <c:forEach var="emp" items="${employees}">
                                        <option value="${emp.employeeId}">${emp.first_name}&nbsp;${emp.last_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">Client</label>
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon glyphicon-user"></i></span>
                                <select name="client" class="form-control selectpicker">
                                    <option value="">Select Employee Role</option>
                                    <c:forEach var="client" items="${clients}">
                                        <option value="${client.clientId}">${client.first_name}&nbsp;${client.last_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- Button -->
                    <div class="form-group">
                        <label class="col-md-4 control-label"></label>
                        <div class="col-md-4"><br>
                            &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<button type="submit" id="btn" class="btn btn-warning">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspAssign <span class="glyphicon glyphicon-plus"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</button>
                        </div>
                    </div>
                    <div id="success-message" style="text-align:center; color:#5cb85c;">
                        <strong>${message}</strong>
                        <c:remove var="message" scope="session"/>
                    </div>
                </fieldset>

            </form>
        </div>
    </div><!-- /.container -->
</div>
<!--/span-->


