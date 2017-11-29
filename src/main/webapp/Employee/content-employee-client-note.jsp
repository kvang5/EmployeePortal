<div class="col-xs-12 col-sm-8">
    <div class="jumbotron">
        <div class="container">
            <form class="well form-horizontal" action="" method="POST" id="client-note">
                <fieldset>

                    <!-- Form Name -->
                    <legend><h2><b>Client Note</b></h2></legend><br>

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

                    <!-- Date input -- Date -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Date</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                <input  name="date" class="form-control" type="date">
                            </div>
                        </div>
                    </div>

                    <!-- Number input -- Care Time -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Care Time</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                                <input  name="care_time" class="form-control" type="number">
                            </div>
                        </div>
                    </div>

                    <!-- TextArea -- Description of Care -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Description of Care</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-font"></i></span>
                                <textarea name="description" class="form-control"></textarea>
                            </div>
                        </div>
                    </div>

                    <!-- TextArea -- Additional Comments -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Additional Comments</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-font"></i></span>
                                <textarea name="comments" class="form-control"></textarea>
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
                </fieldset>

            </form>
        </div>
    </div><!-- /.container -->
</div>
<!--/span-->
