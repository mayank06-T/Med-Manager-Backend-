# 💊 Med Management Backend

This is the backend of the **Med Management System**, built using **Spring Boot** and **MySQL**. It handles all core functionalities such as managing products, sales, inventory updates, and secure API endpoints to interact with the frontend.

---

## 🚀 Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA (Hibernate)**
- **MySQL**
- **RESTful API**
- **Jackson (JSON Serialization)**

---

## 📁 Project Structure


med-management-backend/
├── src/main/java/
│ └── com/mayank/RUST/
│ ├── Controller/
│ ├── Model/
│ ├── Repository/
│ └── Service/
├── src/main/resources/
│ └── application.properties
├── pom.xml



---

---

## 📦 Features

- ✅ Product CRUD (Create, Read, Update, Delete)
- ✅ Track sales and transaction history
- ✅ Multi-item sale with inventory deduction
- ✅ JSON API integration with frontend
- ✅ Cross-origin config enabled

---

## ⚙️ Setup & Run

1. **Clone the repo**

`bash
git clone https://github.com/mayank06-T/Med-Manager-Backend-.git


2. **Create MySQL database**

CREATE DATABASE med_manager;

3. **Configure application.properties**

spring.datasource.url=jdbc:mysql://localhost:3306/med_manager
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

4.**Run the App**
./mvnw spring-boot:run


