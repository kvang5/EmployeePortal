create table Client
(
	clientId int auto_increment
		primary key,
	first_name varchar(30) null,
	last_name varchar(30) null,
	address1 varchar(100) null,
	address2 varchar(100) null,
	city varchar(30) null,
	postal_zip_code varchar(20) null,
	email varchar(60) null,
	home_phone varchar(45) null,
	mobile_phone varchar(45) null,
	USstate_stateId int not null
)
;

create index fk_Client_USstate1_idx
	on Client (USstate_stateId)
;

create table ClientNote
(
	client_noteId int auto_increment
		primary key,
	date date null,
	care_time decimal null,
	description longtext null,
	comments longtext null,
	Client_clientId int not null,
	constraint fk_ClientNote_Client1
		foreign key (Client_clientId) references Client (clientId)
)
;

create index fk_ClientNote_Client1_idx
	on ClientNote (Client_clientId)
;

create table Employee
(
	employeeId int auto_increment
		primary key,
	first_name varchar(30) null,
	last_name varchar(30) null,
	address1 varchar(100) null,
	address2 varchar(100) null,
	city varchar(30) null,
	postal_zip_code varchar(20) null,
	email varchar(60) not null,
	home_phone varchar(45) null,
	mobile_phone varchar(45) null,
	password varchar(15) not null,
	USstate_stateId int not null,
	Title_titleId int not null
)
;

create index fk_Employee_Title1_idx
	on Employee (Title_titleId)
;

create index fk_Employee_USstate1_idx
	on Employee (USstate_stateId)
;

create table EmployeeClient
(
	Employee_employeeId int not null,
	Client_clientId int not null,
	constraint fk_EmployeeClient_Employee
		foreign key (Employee_employeeId) references Employee (employeeId),
	constraint fk_EmployeeClient_Client1
		foreign key (Client_clientId) references Client (clientId)
)
;

create index fk_EmployeeClient_Client1_idx
	on EmployeeClient (Client_clientId)
;

create index fk_EmployeeClient_Employee_idx
	on EmployeeClient (Employee_employeeId)
;

create table EmployeeRole
(
	employee_rolesId int auto_increment
		primary key,
	role_name varchar(20) not null,
	email varchar(60) not null,
	Employee_employeeId int not null,
	constraint fk_EmployeeRole_Employee1
		foreign key (Employee_employeeId) references Employee (employeeId)
)
;

create index fk_EmployeeRole_Employee1_idx
	on EmployeeRole (Employee_employeeId)
;

create table Title
(
	titleId int not null
		primary key,
	jobTitle varchar(45) null
)
;

alter table Employee
	add constraint fk_Employee_Title1
		foreign key (Title_titleId) references Title (titleId)
;

create table USstate
(
	stateId int auto_increment
		primary key,
	state_code varchar(2) null,
	state_name varchar(128) null
)
;

alter table Client
	add constraint fk_Client_USstate1
		foreign key (USstate_stateId) references USstate (stateId)
;

alter table Employee
	add constraint fk_Employee_USstate1
		foreign key (USstate_stateId) references USstate (stateId)
;

