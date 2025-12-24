# 📚 Online Bookstore – Spring Boot REST API

**Student Name:** Peeyush Maurya  
**Project Number & Title:** 2. Online Bookstore  
**Course:** Spring Boot  
**Submission Type:** Individual Assignment  

---

## 📖 Project Description

The **Online Bookstore** is a Spring Boot–based RESTful application that manages customers, books, orders, and order items.  
It demonstrates **full CRUD operations**, **proper JPA relationships**, **custom queries**, **transaction management**, and **environment-specific configurations** using Spring Profiles.

This project is developed strictly according to the assignment guidelines provided by the course instructor.

---

## 🧱 Entities & Relationships

The application consists of the following entities:

### 1️⃣ Customer  
- One-to-One → Address  
- One-to-Many → Orders  

### 2️⃣ Address  
- Embedded within Customer (OneToOne)

### 3️⃣ Book  
- Many-to-One ← OrderItem  

### 4️⃣ Order  
- Many-to-One → Customer  
- One-to-Many → OrderItem  

### 5️⃣ OrderItem  
- Many-to-One → Order  
- Many-to-One → Book  

All relationships are implemented using proper **JPA annotations** with correct ownership and cascading rules.

---

## 🔁 CRUD Operations

Full CRUD operations are implemented for all major entities:

- Customer
- Book
- Order
- OrderItem

Each entity follows a clean layered architecture:
```
Controller → Service → Repository
```

---

## 🔍 Custom Queries (10 Required – All Implemented)

1. Find all orders by a customer  
2. Find top 10 best-selling books  
3. Get books in a specific order  
4. Find orders placed in the last 30 days  
5. Get total revenue per book  
6. Find customers who bought a specific book  
7. Find orders with status `SHIPPED`  
8. Get books in a customer's current cart  
9. Monthly sales report  
10. Find orders above a certain total amount  

---

## 🌍 Environment Configuration (Spring Profiles)

### 🔹 dev Profile
- Database: H2 (file-based)

### 🔹 prod Profile
- Database: MySQL

Configuration files:
- `application-dev.yml`
- `application-prod.yml`

---

## 📊 Spring Boot Actuator

Enabled endpoints:
- `/actuator/health`
- `/actuator/info`
- `/actuator/metrics`
- `/actuator/env`

---

## 🧪 API Testing (Postman)

### 🔗 Postman Collection (Public Link)

https://breadonlaptop-3120722.postman.co/workspace/Dev~2a3c0c9f-f9c0-492c-862b-eeb25adecf45/collection/50293727-efcffba2-8c59-4091-a764-717c01583e78?action=share&source=copy-link&creator=50293727

---

## 🚀 How to Run

### Dev Profile
```
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Prod Profile
```
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

---

## 📂 Project Structure

```
src/main/java/com/peeyush/bookstore
├── controller
├── service
│   └── impl
├── repository
├── entity
├── enums
├── exception
└── BookstoreApplication.java
```

---

## ✅ Submission Checklist

✔ Full CRUD operations  
✔ 4+ entities with relationships  
✔ 10 custom queries  
✔ Spring Profiles  
✔ Actuator  
✔ Postman collection  

---

**— End of README**
