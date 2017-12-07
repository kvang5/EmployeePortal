<script type="text/css">
    #success_message {
        display: none;
    }
</script>

<div class="col-xs-12 col-sm-8">
    <div class="jumbotron">
        <div class="container">
            <form class="well form-horizontal" action="employeeSignUp" method="POST" id="signup-form">
                <fieldset>

                    <!-- Form Name -->
                    <legend><h2><b>Employee Sign Up Form</b></h2></legend><br>

                    <!-- Text input -- First Name -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">First Name</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input  name="first_name" placeholder="First Name" class="form-control" type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- Last Name -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Last Name</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input name="last_name" placeholder="Last Name" class="form-control" type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- Address1 -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Address</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-book"></i></span>
                                <input name="address1" placeholder="Street Address" class="form-control" type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- Address2/Apt -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Apartment (Optional)</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-book"></i></span>
                                <input name="address2" placeholder="Apartment #" class="form-control" type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- City -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >City</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-book"></i></span>
                                <input name="city" placeholder="City" class="form-control" type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- State -->
                    <!-- Use HTTP doGet to populate states from state table to select option -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">State</label>
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
                                <select name="state" id="state" class="form-control selectpicker">
                                    <option value="">Select State</option>
                                    <c:forEach var="state" items="${states}">
                                        <option value="${state.stateId}">${state.state_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- Postal Zip Code -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Postal Zip Code</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-book"></i></span>
                                <input name="postal_zip_code" placeholder="Postal Zip Code" class="form-control" type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- Home Phone -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Phone No.</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-phone-alt"></i></span>
                                <input name="home_phone" placeholder="(608)123-4567" class="form-control" type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- Mobile Phone -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Mobile No. (Optional)</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
                                <input name="mobile_phone" placeholder="(608)123-4567" class="form-control" type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- Job Title -->
                    <!-- Use HTTP doGet to populate titles from title table to select option -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Title</label>
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
                                <select name="title" class="form-control selectpicker">
                                    <option value="">Select Title</option>
                                    <c:forEach var="title" items="${titles}">
                                        <option value="${title.titleId}">${title.jobTitle}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- Email -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Email</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                                <input name="email" placeholder="jsmith@mail.com" class="form-control" type="text">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">Employee Role</label>
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon glyphicon-copy"></i></span>
                                <select name="employeeRoleName" class="form-control selectpicker">
                                    <option value="">Select Employee Role</option>
                                    <c:forEach var="employeeRole" items="${employeeRoles}">
                                        <option value="${employeeRole.role_name}">${employeeRole.role_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- Checkbox -- Status -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Status?</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-check"></i></span>
                                <input name="status" class="form-control" id="status" type="checkbox">
                            </div>
                        </div>
                    </div>

                    <!-- Button -->
                    <div class="form-group">
                        <label class="col-md-4 control-label"></label>
                        <div class="col-md-4"><br>
                            &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<button type="submit" id="btn" class="btn btn-warning">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSUBMIT <span class="glyphicon glyphicon-send"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</button>
                        </div>
                    </div>

                    <!-- TODO:
                        Need to add validation for form in order to output correct message,
                        ex: if validated correctly and email does not exist in DB then success message is shown
                            else if email exist in DB then output emailExistMessage.
                    -->

                    <!-- Success message -->
                    <div class="alert alert-success" id="success_message" style="display: none; text-align: center;"></div>

                </fieldset>
            </form>
        </div>
    </div><!-- /.container -->

</div>
<!--/span-->

<script src="JSFiles/formValidation.js"></script>
