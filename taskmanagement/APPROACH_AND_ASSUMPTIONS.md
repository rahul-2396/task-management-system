Approach :-

-> Objective: 

Developed a comprehensive Task Management System utilizing Java Spring Boot for backend 
development. The system integrates MySQL for data storage, Spring Security for user authentication 
and authorization.

-> Backend Development: 

Framework: Implemented using Java with Spring Boot.

Data Access: Utilized Hibernate ORM with MySQL, for efficient data handling and persistence.

Security: Integrated Spring Security to manage user authentication and authorization, ensuring secure access to 
different parts of the application.

-> Database Management:

Database: Used MySQL for data storage. Configured Hibernate ORM for object-relational mapping to 
facilitate interactions between the Java application and MySQL database.

Schema: Created database schemas for user management, task management, and role-based access control.

-> API Documentation:

Swagger (OpenAPI): Incorporated Swagger for interactive API documentation.

-> Development Tools:

Docker: Dockerized the application to ensure consistency across different environments.

-> Testing:

Unit and Integration Tests: Implemented comprehensive testing across all layers.


Assumptions :-

-> Database Configuration

The task_management_db database is created in MySQL.
The database credentials (username, password) are correctly configured in the application.properties file.

-> Environment Setup:

Requires MySQL and Maven to be installed and configured on the local machine. MySQL should be running,
and Maven should be properly set up to build and manage the application.
Java 21 is installed and properly configured for running the Spring Boot application.

-> Port Configuration:

Port 8080 is available and not being used by another application. If necessary, adjust the 
port configuration in the application.properties file to avoid conflicts.

-> Swagger Access:

Swagger UI is accessible at http://localhost:8080/swagger-ui/index.html for exploring API endpoints.
OpenAPI JSON documentation is available at http://localhost:8080/v3/api-docs, for JSON format.