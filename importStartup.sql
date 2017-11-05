/* THIS IS START-UP LOGIN USERS */
/*################################################################*/
/*################################################################*/
/*################################################################*/
/*################################################################*/

/* Title table inserts (GSHC Employee Titles) */
INSERT into Title (titleId, jobTitle) values ('1', 'Agency Director'),
('2', 'Office Manager'),
('3', 'Registered Nurse'),
('4', 'Certified Nurse Assistant'),
('5', 'Caretaker'),
('6', 'In-home Aid'),
('7', 'Companion');

/* 50 US States inserts for USstate Table */
INSERT into USstate (stateId, state_code, state_name) values ('1', 'AL', 'Alabama'),
('2', 'AK', 'Alaska'),
('3', 'AZ', 'Arizona'),
('4', 'AR', 'Arkansas'),
('5', 'CA', 'California'),
('6', 'CO', 'Colorado'),
('7', 'CT', 'Connecticut'),
('8', 'DE', 'Delaware'),
('9', 'FL', 'Florida'),
('10', 'GA', 'Georgia'),
('11', 'HI', 'Hawaii'),
('12', 'ID', 'Idaho'),
('13', 'IL', 'Illinois'),
('14', 'IN', 'Indiana'),
('15', 'IA', 'Iowa'),
('16', 'KS', 'Kansas'),
('17', 'KY', 'Kentucky'),
('18', 'LA', 'Louisiana'),
('19', 'ME', 'Maine'),
('20', 'MD', 'Maryland'),
('21', 'MA', 'Massachusetts'),
('22', 'MI', 'Michigan'),
('23', 'MN', 'Minnesota'),
('24', 'MS', 'Mississippi'),
('25', 'MO', 'Missouri'),
('26', 'MT', 'Montana'),
('27', 'NE', 'Nebraska'),
('28', 'NV', 'Nevada'),
('29', 'NH', 'New Hampshire'),
('30', 'NJ', 'New Jersey'),
('31', 'NM', 'New Mexico'),
('32', 'NY', 'New York'),
('33', 'NC', 'North Carolina'),
('34', 'ND', 'North Dakota'),
('35', 'OH', 'Ohio'),
('36', 'OK', 'Oklahoma'),
('37', 'OR', 'Oregon'),
('38', 'PA', 'Pennsylvania'),
('39', 'RI', 'Rhode Island'),
('40', 'SC', 'South Carolina'),
('41', 'SD', 'South Dakota'),
('42', 'TN', 'Tennessee'),
('43', 'TX', 'Texas'),
('44', 'UT', 'Utah'),
('45', 'VT', 'Vermont'),
('46', 'VA', 'Virginia'),
('47', 'WA', 'Washington'),
('48', 'WV', 'West Virginia'),
('49', 'WI', 'Wisconsin'),
('50', 'WY', 'Wyoming');

/* Employee table (admin and ruser) inserts */
INSERT into Employee (employeeId, first_name, last_name, address1, address2, city, postal_zip_code, email, home_phone, mobile_phone, password, USstate_stateId, Title_titleId) values ('1', 'admin', 'admin', '123 Admin Street', '', 'Madison', '53590', 'admin@admin.com', '828-455-6682', '828-455-6682', 'admin', '49', '1'),
('2', 'ruser', 'ruser', '123 User Street', '', 'Madison', '53590', 'ruser@ruser.com', '828-455-6682', '828-455-6682', 'ruser', '49', '3');

/* EmployeeRole table (admin and ruser) inserts */
INSERT into EmployeeRole (employee_rolesId, role_name, email, Employee_employeeId) values ('1', 'Administrator', 'admin@admin.com', '1'),
('2', 'Registered-user', 'ruser@ruser.com', '2');
