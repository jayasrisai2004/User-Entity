 User Management Spring Boot Project

##  Overview

This project is a simple User Management API built using Spring Boot following a layered architecture:

1. Entity
2. DTO (Request & Response)
3. Validation
4. Global Exception Handling


##  Features

1. Create User API
2. Get User by ID API
3. Input Validation (Name, Email, Password)
4.  Global Exception Handling
5. Clean layered architecture



##  Project Structure


controller → Handles API requests
service → Business logic
repository → Database interaction
entity → Database model
dtos → Request & Response objects
exception → Global error handling




##  Technologies Used

1. Java
2. Spring Boot
3. Spring Data JPA
4. Hibernate
5. Lombok
6. H2 Database



##  How to Run the Project

### Step 1: Clone the Repository

### Step 2: Open in IDE

* Open in VS Code

### Step 3: Run Application

* Run `UserManagementApplication.java`

### Step 4: Access APIs

* Base URL:

  ```
  http://localhost:8080
  ```

---

##  API Endpoints

### 1️ Create User

**POST** `/api/users`

#### Request Body

```json
{
  "name": "Jaya",
  "email": "jaya@gmail.com",
  "password": "123456"
}
```

#### Response

```json
{
  "id": 1,
  "name": "Jaya",
  "email": "jaya@gmail.com"
}
```

---

### 2️ Get User by ID

**GET** `/api/users/{id}`

#### Response

```json
{
  "id": 1,
  "name": "Jaya",
  "email": "jaya@gmail.com"
}
```

---

##  Validation Errors

If invalid input is given:

```json
{
  "name": "Name is required",
  "email": "Invalid email format",
  "password": "Password must be at least 6 characters"
}
```

---

##  Exception Handling

* Handles:

  * User not found
  * Validation errors
* Returns proper HTTP status codes

---

##  Security (Basic)

```
username: admin
password: admin123
```

---

##  Application Flow

```
Client Request
     ↓
Controller
     ↓
Service
     ↓
Repository (DB)
     ↓
Response DTO
     ↓
JSON Response
```

---

##  Future Enhancements

* JWT Authentication
* Password Encryption (BCrypt)
* Update/Delete APIs
* Swagger Documentation

---

##  Author

Jaya Sri
