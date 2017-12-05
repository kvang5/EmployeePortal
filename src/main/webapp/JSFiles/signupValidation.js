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
            $('#signup-form').bootstrapValidator("resetForm", true);
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
            $('#client-form').bootstrapValidator("resetForm", true);
        });
});