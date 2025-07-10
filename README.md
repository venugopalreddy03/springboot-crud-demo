Product Management System - Spring Boot CRUD Application with MySQL (AWS RDS)
![image](https://github.com/user-attachments/assets/4ef8d9ff-05b2-446f-ab87-f460e7db1a2b)
Project Structure
![Screenshot 2025-07-10 112225](https://github.com/user-attachments/assets/59fc9cd6-ea14-4d0e-8399-f6d6832540c9)

Overview
This is a complete Spring Boot application demonstrating CRUD (Create, Read, Update, Delete) operations with:

*RESTful APIs

*MySQL database hosted on AWS RDS

*HTML frontend using Thymeleaf

*Comprehensive exception handling

*JUnit tests

Features
Product Management:

Create new products

View all products

View single product details

Update existing products

Delete products

Two Interfaces:

REST API endpoints (for programmatic access)

HTML web interface (for browser access)

Error Handling:

Custom exceptions

Global exception handler

Proper HTTP status codes

Technologies Used
Backend:

Java 17

Spring Boot 3.2.0

Spring Data JPA

Spring Web

Thymeleaf (for HTML templates)

Database:

MySQL (hosted on AWS RDS)

Testing:

JUnit 5

Mockito

Tools:

Postman (for API testing)

Maven (for dependency management)

API Endpoints
Method	Endpoint	Description

GET	/api/products	Get all products

GET	/api/products/{id}	Get a single product by ID

POST	/api/products	Create a new product

PUT	/api/products/{id}	Update an existing product

DELETE	/api/products/{id}	Delete a product

Web Interface Routes
Route	Description

/products	View all products (homepage)

/products/new	Form to create new product

/products/edit/{id}	Form to edit existing product

/products/delete/{id}	Delete a product

Setup Instructions
Prerequisites
Java 17 JDK

MySQL (or AWS RDS MySQL instance)

Maven

Postman (for API testing)

1. Database Setup
Create a MySQL database (either locally or on AWS RDS)

Update the database configuration in src/main/resources/application.properties:

properties
spring.datasource.url=jdbc:mysql://<your-rds-endpoint>:3306/<database-name>?useSSL=false&serverTimezone=UTC
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
2. Running the Application
Clone the repository:

bash
git clone <repository-url>
cd springboot-crud-demo
Build and run the application:

bash
mvn spring-boot:run
The application will be available at:

Web interface: http://localhost:8080/products

API base URL: http://localhost:8080/api/products

3. Testing
API Testing with Postman
Import the Postman collection (provided in the repository)

Test all endpoints:

GET all products

POST new product

GET single product

PUT update product

DELETE product

Running Unit Tests
bash

mvn test





