# Instructions to Run the Application Locally

Clone the repository to your local machine and navigate to the project directory:

git clone [YOUR_REPOSITORY_URL]
cd [YOUR_PROJECT_DIRECTORY]

Set Up MySQL Database:

Ensure MySQL is installed and running.
Create a new database for the project:
CREATE DATABASE task_management_db;

Configure Application Properties:

Open src/main/resources/application.properties
Update the following properties with your MySQL database credentials:
spring.datasource.url=jdbc:mysql://localhost:3306/task_management_db
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD

Build the Project:

mvn clean install

Run the Application:

mvn spring-boot:run

Access Swagger UI:

http://localhost:8080/swagger-ui/index.html


