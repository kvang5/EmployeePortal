# Employee Portal

![](images/golden-sun-home-care-logo.png)

<http://www.goldensunhomecare.com>

### Problem Statement
Golden Sun Home Care is a Home Healthcare business that was establish in the year of 2017. Golden Sun Home Care is licenses with the State of North Carolina to cater to the elderly and individuals that are need personal care services. The clients are either personal pay, private insurance pay, or Medicaid clients. With these clients, Golden Sun Home Care hired Register Nurses (RN), Certified Nursing Assistants (CNAs), and care takers to meet the need of clients. 

Golden Sun Home Care currently have a live website that serves the clienteles. The problem is, most of their office procedures are still done by paper, like time sheets and employee notes per visit. This delays the process because employees make notes on paper and drop them off at the office. 

The solution to help Golden Sun Home Care is to set a Web Application employee portal that will enable all employees to log in to submit their time sheet, description of services per client and other concerns for clients to Golden Sun Home Care with ease. This will allow the business to grow, improve office procedures and control revenues loss for client services Golden Sun Home Care provides.

### Project Technologies/Techniques 

* Security/Authentication
  * Admin role: create/read/update/delete (crud) of all data
  * User role: submit time sheet, client care description, and concerns
* Database (MySQL and Hibernate)
  * Store users and roles
  * Store employee time sheet
  * Store client information and care description
* Web Services or APIs
  * Replicon API for timesheet
* Independent research topic (TBD)
* Logging
  * Configurable logging using Log4J. In production, only errors will normally be logged, but logging at a debug level can be turned on to facilitate trouble-shooting. 
* UI Framework Styling and Responsive application
  * Bootstrap
  * CSS
* Site and database hosted on AWS
* Unit Testing
  * JUnit tests to achieve 85% code coverage 
  
### Design

* [Screen Design](DesignDocuments/ScreenDesigns)
* [Database Design](DesignDocuments/employeePortalDB_1.png)

### [Project Plan](ProjectPlan.md)

### [Time Log](TimeLog.md) 