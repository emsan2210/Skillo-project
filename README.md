# Description:

This is an automation test for a web application for managing posts.\
The project uses Selenium, TestNG, Java and Maven for automated UI tests.

# Dependencies:

### The project uses the following dependencies:

- Java - programming language
- Selenium - library for automated web testing
- TestNG - testing framework
- Maven - dependency management tool

All Maven dependencies can be found in the pom.xml

## Installation:

### To get the project running, follow these steps:

1. Clone the repository:

   git clone https://github.com/emsan2210/Skillo-project


2. Download the dependencies with Maven:

   mvn install


3. Run the tests with:

   mvn test

### If you're using an IDE like IntelliJ IDEA proceed with the following steps:

- from the git menu clone the project
- New project from VCS - > https://github.com/emsan2210/Skillo-project
- save the Project at your preferable location.

on each run reports and screenshots folders are created if they are not existing\
on error screenshot is added in the screenshots folder\
after all tests finish, reports are added in the reports folder

# List of test cases:

- Register User Test - ensures user can register
- Login User Test - ensures user can login
- Login User Error Test (invalid credentials) - ensure user can not login with invalid credentials
- Create Post Test - ensures logged user can create post
- Comment Post Test - ensures logged user can comment post
- Delete Post Test - ensures logged user can delete own post

# Contacts:

- Email: [Emil Todorov](mailto:emo2210@abv.bg)
- Project link: [Skillo-project](https://github.com/emsan2210/Skillo-project)

