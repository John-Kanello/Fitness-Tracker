# Fitness Tracker API

## Overview

The **Fitness Tracker API** is a Spring Boot application that allows users to register, track fitness activities, and integrate third-party applications with API key-based authentication.

## Features

- **Authentication & Authorization**
    - Basic Authentication for user registration and application registration.
    - API Key Authentication for accessing `/api/tracker/**` endpoints.
- **Application Categories**
    - Applications can be either **Basic** or **Premium**.
    - Basic applications are limited to **1 request per second** for `/api/tracker` endpoints through a rate-limiter.
- **RESTful API with H2 Database**
    - CRUD operations for users and fitness tracking data.

---

## Tech Stack

- **Java 21**
- **Spring Boot 3.4.4**
- **Spring Security** (Basic Auth, API Key Authentication)
- **H2 Database** (In-memory for development)
- **IntelliJ IDEA** (Development Environment)

---

## Authentication and Authorization

### **Authentication Methods**

1. **Basic Authentication** for user registration and application registration.
2. **API Key Authentication** for accessing `/api/tracker/**` endpoints.

---

## API Endpoints

### **User Management**

| Method | Endpoint                 | Description                            |
| ------ | ------------------------ | -------------------------------------- |
| `POST` | `/api/developers/signup` | Register a new user (Public)           |
| `GET`  | `/api/developers/{id}`   | Get user details (Basic Auth required) |

### **Application Registration**

| Method | Endpoint                     | Description                                                   |
| ------ | ---------------------------- | ------------------------------------------------------------- |
| `POST` | `/api/applications/register` | Register an application, get an API key (Basic Auth required) |

### **Fitness Data Tracking**

| Method | Endpoint       | Description                                    |
| ------ | -------------- | ---------------------------------------------- |
| `GET`  | `/api/tracker` | Retrieve fitness activities (API Key required) |
| `POST` | `/api/tracker` | Add new activity (API Key required)            |

---

## Installation and Running

### **1. Clone the Repository**

```sh
git clone https://github.com/yourusername/fitness-tracker.git
cd fitness-tracker
```

### **2. Build the Project**

```sh
mvn clean install
```

### **3. Run the Application**

```sh
mvn spring-boot:run
```

### **4. Testing the API**

- Use **Postman** to test authentication and API requests.
