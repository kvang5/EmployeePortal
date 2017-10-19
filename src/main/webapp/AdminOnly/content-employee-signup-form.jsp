<div class="col-xs-12 col-sm-8">
    <div class="jumbotron">
        <div class="container">
            <form class="well form-horizontal" action=" " method="post"  id="signup-form">
                <fieldset>

                    <!-- Form Name -->
                    <legend><h2><b>Registration Form</b></h2></legend><br>

                    <!-- Text input -- First Name -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">First Name</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input  name="first_name" placeholder="First Name" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- Last Name -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Last Name</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input name="last_name" placeholder="Last Name" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- Address1 -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Address</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-book"></i></span>
                                <input name="address1" placeholder="Street Address" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- Address2/Apt -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Apartment</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-book"></i></span>
                                <input name="address2" placeholder="Apartment #" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- City -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >City</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-book"></i></span>
                                <input name="city" placeholder="City" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- State -->
                    <!-- Need jquery for state table -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">State</label>
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
                                <select name="state" class="form-control selectpicker">
                                    <option value="">Select State</option>
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
                                <input name="postal_zip_code" placeholder="Postal Zip Code" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- Home Phone -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Phone No.</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-phone-alt"></i></span>
                                <input name="home_phone" placeholder="(608)" class="form-control" type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- Mobile Phone -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Mobile No.</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
                                <input name="mobile_phone" placeholder="(608)" class="form-control" type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Text input -- Job Title -->
                    <!-- Need jquery for title table -->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Title</label>
                        <div class="col-md-4 selectContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
                                <select name="title" class="form-control selectpicker">
                                    <option value="">Select Title</option>
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
                                <input name="address2" placeholder="Apt 2" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>

                    <!-- Select Basic -->

                    <!-- Success message -->
                    <div class="alert alert-success" role="alert" id="success_message">Success <i class="glyphicon glyphicon-thumbs-up"></i> Success!.</div>

                    <!-- Button -->
                    <div class="form-group">
                        <label class="col-md-4 control-label"></label>
                        <div class="col-md-4"><br>
                            &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<button type="submit" class="btn btn-warning" >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSUBMIT <span class="glyphicon glyphicon-send"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</button>
                        </div>
                    </div>

                </fieldset>
            </form>
        </div>
    </div><!-- /.container -->

</div>
<!--/span-->

