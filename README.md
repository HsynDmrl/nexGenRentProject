# NexGenRentProject 🚗

## Project Description 📚 

NexGenRentProject is an application developed within the scope of the Tobeto course. It is a system developed to manage car rental processes. The system provides different roles and permissions for different user types and includes management of vehicles, reservations, invoices, and user accounts.

---

## User Roles and Permissions
- **Administrator**: Manages the entire system, has the authority to manage users, vehicles, reservations, and invoices.
- **Staff**: Manages vehicles, reservations, and invoices. Can view customer accounts but does not have editing permissions.
- **Customer**: Can view vehicles, make reservations, and manage their own account settings.

## Contributors 🛠️

The contributors to this project are listed below. Thanks to everyone who contributed to the project!

| [![Hüseyin Demirel](https://github.com/hsyndmrl.png)](https://github.com/HsynDmrl) | [![Yasin Yıldız](https://github.com/PickerWork.png)](https://github.com/yasinydz) | [![Mustafa Tayyar](https://github.com/urmustafa.png)](https://github.com/urmustafa) |
| --- | --- | --- |
| Hüseyin Demirel 🤝 | Yasin Yıldız 🤝 | Mustafa Tayyar 🤝|

--- 

## Database ER Diagram 📊 

Below is the ER (Entity-Relationship) diagram created on PostgreSQL:

![ER Diagram](https://github.com/HsynDmrl/nexGenRentProject/blob/main/ERD.PNG)

This diagram illustrates the database structure of the project. Please click [here](https://github.com/HsynDmrl/nexGenRentProject/blob/main/ERD.PNG) for details.

---

<details>
<summary>Core Functions of the Project</summary>

The project aims to manage car rental processes and streamline them for users. By providing specific roles and permissions for different user types, the system offers a customized experience to meet user needs. For example, while administrators oversee the entire system, staff manage vehicles, reservations, and invoices, and customers can manage their own accounts.

The project structure is built using Spring Boot and follows a modular approach. Controllers route HTTP requests and invoke business logic, the service layer implements business logic and performs database operations, the repository layer contains components for performing database operations, and the model layer contains database objects and data transfer objects (DTOs). Additionally, configuration files are present for project configuration.

Technologies used in the project include Spring Boot, Spring Security, Spring Data JPA, PostgreSQL, and Maven among others. Tools such as Lombok are also used to automatically generate boilerplate code.

Installation and running of the project is straightforward. After cloning the relevant GitHub repository, PostgreSQL database setup is performed, and the configuration file is edited before running the project. This allows the application to be viewed in the browser.

Sample classes and functions within the project serve various purposes in different areas and are used to ensure the correctness of the overall logic. These classes include functions for managing administrators and users, vehicle management, invoice management, and more.

</details>

<details>
<summary>Project Updates</summary>

- Implementation of "Model Mapper" for auto mapping has been added to the project. Now, when listing vehicle data, plate, mileage, price, year, model name, and color name information is displayed in order.

- Request-Response pattern has been adhered to for all operations.

- Spaces in the "Plate" section are removed when adding a vehicle.

- Relevant coding has been done in the ModelService. One service calls another service instead of the repository of another entity.

- ER Diagram has been added to the project.

- Swagger support has been added to the system.

- Necessary coding has been done in the Service layer to validate the existence of the model associated with ModelId in the database. This made GetAll and Add operations for the Car entity more reliable.

- Coding for the "Car" entity in the Service layer has been completed to perform CRUD operations. Through these codings, relevant endpoints are connected in the controller layer.

- Checks and validations during addition and update operations include:
  - The "Mileage" field cannot be less than 0.
  - Compliance of the "Plate" field with Turkish plate is checked with Regex.
  - The year information must be between 2005 and 2024.
  - DailyPrice cannot be less than 0.
  - ModelId and ColorId cannot be less than 0.
  - Spaces in the "Plate" section are removed when adding a vehicle.
  - The given ModelId must exist in the database (coding done in ModelService).
  - The given ColorId must exist in the database.
  - Another vehicle with the same plate should not exist.

With these updates, the system operates more reliably and healthily.

</details>

## Features 💡

- **Layered Architecture:** The project uses a layered architecture, ensuring modularity and ease of maintenance.
- **Controllers:** Controllers that handle HTTP requests and invoke business logic.
- **Model Layer:** Models that contain database objects and data transfer objects (DTOs).
- **Configuration Files:** Files containing application settings and configurations.
- **Database Entities:** All database entities representing tables in the database and associated with business logic.
- **Repository Layer:** Repository layer for all database entities, managing database operations (add, update, delete, query, etc.).
- **Service Layer:** The service layer, where business logic is applied and service functions are located. Services in abstracts, managers in concretes, and request and response objects in DTOs. This layer manages the functionality of the overall project.
- **Mappers:** Mappers in Core.Utilities are used to perform data transformations between database entities and DTOs.

## Technologies and Dependencies Used
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL (as the database)
- Maven (for dependency management)
- Lombok (for automatically generating boilerplate code such as getters, setters, constructors, etc.)

## Installation and Running
1. Clone the project repository: `git clone https://github.com/HsynDmrl/nexGenRentProject.git`
2. Install and configure the PostgreSQL database.
3. Edit the `application.properties` file to configure the database connection.
4. Navigate to the project directory and run the command `mvn spring-boot:run`.
5. Visit `http://localhost:8080` in your browser to view the application.
