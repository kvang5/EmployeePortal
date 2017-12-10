<!-- Employee form validation -->
$(document).ready(function() {
    $('#signup-form').bootstrapValidator({
        button: {
            selector: '[type="submit"]'
        },
        //message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            first_name: {
                validators: {
                    stringLength: {
                        min: 2,
                    },
                    notEmpty: {
                        message: 'First name is required and cannot be empty'
                    }
                }
            },
            last_name: {
                validators: {
                    stringLength: {
                        min: 2,
                    },
                    notEmpty: {
                        message: 'Last name is required and cannot be empty'
                    }
                }
            },
            address1: {
                validators: {
                    stringLength: {
                        min: 8,
                    },
                    notEmpty: {
                        message: 'Please supply street address'
                    }
                }
            },
            address2: {
                optional: true,
                validators: {
                    stringLength: {
                        min: 1,
                    }
                }
            },
            city: {
                validators: {
                    stringLength: {
                        min: 4,
                    },
                    notEmpty: {
                        message: 'Please supply city'
                    }
                }
            },
            state: {
                validators: {
                    notEmpty: {
                        message: 'Please select a state'
                    }
                }
            },
            postal_zip_code: {
                validators: {
                    notEmpty: {
                        message: 'Please supply zip code'
                    },
                    zipCode: {
                        country: 'US',
                        message: 'Please supply a vaild zip code'
                    }
                }
            },
            home_phone: {
                validators: {
                    notEmpty: {
                        message: 'Please supply home phone number'
                    },
                    phone: {
                        country: 'US',
                        message: 'Please supply a vaild home phone number with area code'
                    }
                }
            },
            mobile_phone: {
                optional: true,
                validators: {
                    phone: {
                        country: 'US',
                        message: 'Please supply a vaild mobile phone number with area code'
                    }
                }

            },
            title: {
                validators: {
                    notEmpty: {
                        message: 'Please select a job title'
                    }
                }
            },
            //ToDo: Still need to make sure email does not exit in BD
            email: {
                validators: {
                    notEmpty: {
                        message: 'Please enter email address'
                    },
                    emailAddress: {
                        message: 'Please enter a valid Email Address'
                    }
                }
            },
            employeeRoleName: {
                validators: {
                    notEmpty: {
                        message: 'Please select a employee role'
                    }
                }
            },
            status: {
                optional: true
            }
        }
    })
        .on('success.form.bv', function(e) {
            $('#success_message').slideDown({ opacity: "show" }, "slow");
            $('#signup-form').data('bootstrapValidator').resetForm();

            // Prevent form submission
            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);

            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');

            // Use Ajax to submit form data
            $.post($form.attr('action'), $form.serialize(), function(result) {
                console.log(result);
            }, 'json');

            $form.find('.alert').html("Successfully added new employee.");
            $('#signup-form').data('bootstrapValidator').resetForm();
        });
});

<!-- Client form validation -->
$(document).ready(function() {
    $('#client-form').bootstrapValidator({
        button: {
            selector: '[type="submit"]'
        },
        //message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            first_name: {
                validators: {
                    stringLength: {
                        min: 2,
                    },
                    notEmpty: {
                        message: 'First name is required and cannot be empty'
                    }
                }
            },
            last_name: {
                validators: {
                    stringLength: {
                        min: 2,
                    },
                    notEmpty: {
                        message: 'Last name is required and cannot be empty'
                    }
                }
            },
            address1: {
                validators: {
                    stringLength: {
                        min: 8,
                    },
                    notEmpty: {
                        message: 'Please supply street address'
                    }
                }
            },
            address2: {
                optional: true,
                validators: {
                    stringLength: {
                        min: 1,
                    }
                }
            },
            city: {
                validators: {
                    stringLength: {
                        min: 4,
                    },
                    notEmpty: {
                        message: 'Please supply city'
                    }
                }
            },
            state: {
                validators: {
                    notEmpty: {
                        message: 'Please select a state'
                    }
                }
            },
            postal_zip_code: {
                validators: {
                    notEmpty: {
                        message: 'Please supply zip code'
                    },
                    zipCode: {
                        country: 'US',
                        message: 'Please supply a vaild zip code'
                    }
                }
            },
            home_phone: {
                validators: {
                    notEmpty: {
                        message: 'Please supply home phone number'
                    },
                    phone: {
                        country: 'US',
                        message: 'Please supply a vaild home phone number with area code'
                    }
                }
            },
            mobile_phone: {
                optional: true,
                validators: {
                    phone: {
                        country: 'US',
                        message: 'Please supply a vaild mobile phone number with area code'
                    }
                }

            },
            //ToDo: Still need to make sure email does not exit in BD
            email: {
                validators: {
                    notEmpty: {
                        message: 'Please enter email address'
                    },
                    emailAddress: {
                        message: 'Please enter a valid Email Address'
                    }
                }
            },
            status: {
                optional: true
            }
        }
    })

        .on('success.form.bv', function(e) {
            $('#success_message').slideDown({ opacity: "show" }, "slow");
            $('#client-form').data('bootstrapValidator').resetForm();

            // Prevent form submission
            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);

            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');

            // Use Ajax to submit form data
            $.post($form.attr('action'), $form.serialize(), function(result) {
                console.log(result);
            }, 'json');

            $form.find('.alert').html("Successfully added new client.");
            $('#client-form').data('bootstrapValidator').resetForm();
        });
});

// Client assignment to employee
$(document).ready(function() {
    $('#assign-form').bootstrapValidator({
        button: {
            selector: '[type="submit"]'
        },
        //message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            client: {
                validators: {
                    notEmpty: {
                        message: 'Please select a client'
                    }
                }
            },
            employee: {
                validators: {
                    notEmpty: {
                        message: 'Please select an employee'
                    }
                }
            }
        }
    })

        .on('success.form.bv', function(e) {
            $('#success_message').slideDown({ opacity: "show" }, "slow");
            $('#assign-form').data('bootstrapValidator').resetForm();

            // Prevent form submission
            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);

            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');

            // Use Ajax to submit form data
            $.post($form.attr('action'), $form.serialize(), function(result) {
                console.log(result);
            }, 'json');

            $form.find('.alert').html("Successfully assign client to employee.");

            $('#assign-form').data('bootstrapValidator').resetForm();
        });
});

// Employee enter about care of client
$(document).ready(function() {
    $('#client-note').bootstrapValidator({
        button: {
            selector: '[type="submit"]'
        },
        //message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            client: {
                validators: {
                    notEmpty: {
                        message: 'Please select a client'
                    }
                }
            },
            date: {
                validators: {
                    notEmpty: {
                        message: 'Please select date of care'
                    },
                    date: {
                        format: 'YYYY/MM/DD',
                        message: 'The value is not a valid date'
                    }
                }
            },
            care_time: {
                validators: {
                    notEmpty: {
                        message: 'Please enter care time'
                    }
                    //numeric: {
                    //    message: 'The value is not a number',
                        // The default separators
                    //    thousandsSeparator: '',
                    //    decimalSeparator: '.'
                    //}
                }
            },
            description: {
                validators: {
                    stringLength: {
                        min: 10,
                    },
                    notEmpty: {
                        message: 'Please enter description of care'
                    }
                }
            },
            comments: {
                optional: true,
                validators: {
                    stringLength: {
                        min: 2,
                    }
                }
            }
        }
    })

        .on('success.form.bv', function(e) {
            $('#success_message').slideDown({ opacity: "show" }, "slow");
            $('#client-note').data('bootstrapValidator').resetForm();

            // Prevent form submission
            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);

            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');

            // Use Ajax to submit form data
            $.post($form.attr('action'), $form.serialize(), function(result) {
                console.log("The result is: " + result);
            }, 'json');

            $form.find('.alert').html("Successfully added note for care to client.");

            $('#client-note').data('bootstrapValidator').resetForm();
        });
});


