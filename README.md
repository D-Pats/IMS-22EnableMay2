Coverage: 53%
# Iventory Management System

Inventory management system with Users, items, and orders. This program will connect to a database and through the program you will be able to Create, Read, Add, and Delete Users, items, and orders.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Pre-requisits:
Java https://www.java.com/download/ie_manual.jsp
Eclipse IDE from https://www.eclipse.org/downloads/
MySQL and Workbench from https://www.mysql.com/products/workbench/
Apache https://maven.apache.org/
Dependencies for POM.XML from https://mvnrepository.com/
Git https://git-scm.com/downloads and GitHub https://github.com/
JIRA https://www.atlassian.com/software/jira

### Installing

To install this you will need to clone it down from the repo then import it into Eclipse

Once imported into Eclipse get the Schema and Data and open this in MySQL workbench.

Connect the MySQL localinstance by going to "connect database".

Run the schema to create the database on your local machine then run the data to enter the test data into the tables.

As long as the dbproperties file has the correct address (in this case as it is ran locally it will be a localhost address), the correct username for MySQL, and the correct password, then the Runner class should work.

To use the program use the runner and follow the commands as seen below to get customer data:

![image](https://user-images.githubusercontent.com/30010257/173243425-4c20c853-6a6e-4e07-ae0d-69c68766ed2a.png)

Or even the items as shown below: 

![image](https://user-images.githubusercontent.com/30010257/173243472-bc4e02e5-180a-4693-abcf-63e1d355a2d2.png)

## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

A JUnit test is where the system goes through the code with an expected result you have given and checks to see that it runs and outputs the expected result, this can be done using another class set up with an @Test annotation as seen below:

![image](https://user-images.githubusercontent.com/30010257/173244053-a6aa36d3-e272-4d98-bfff-0943ecf37251.png)

### Integration Tests 

Integration tests are done in this system using Mockito.
Below is a test to check if the Read (or ReadAll) function works.

![image](https://user-images.githubusercontent.com/30010257/173244123-7dabe31a-96e0-418e-b1a3-695db198c49e.png)


## Deployment

This can be deployed using Maven.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use Git and GitHub (http://github.com/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Devin Patel** - *Project work from initial created by Chirs Perrins* - [D-Pats](https://github.com/D-Pats)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* Thanks to my trainer Jordan Benbelaid for showing us how to do the task and teaching us Java until this point.
