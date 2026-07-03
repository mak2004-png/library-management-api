# 📚 Library Management REST API

A RESTful API for library management built with Java Spring Boot and MySQL. Implements a clean 3-layer architecture (Controller → Service → Repository) with full CRUD operations.

## 🛠️ Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot 4.1.0
- **Database:** MySQL
- **ORM:** Spring Data JPA / Hibernate
- **Build Tool:** Maven
- **Testing:** Postman

## 🏗️ Architecture

```
Controller Layer  →  handles HTTP requests/responses
Service Layer     →  contains business logic
Repository Layer  →  communicates with the database
```

## 📁 Project Structure

```
src/main/java/library_management/
├── controller/
│   └── BookController.java     # REST endpoints
├── model/
│   └── Book.java               # Entity / database table
├── repository/
│   └── BookRepository.java     # Database operations
├── service/
│   └── BookService.java        # Business logic
└── LibraryManagementApplication.java
```

## 🚀 API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/books` | Get all books |
| GET | `/books/{id}` | Get book by ID |
| POST | `/books` | Add a new book |
| DELETE | `/books/{id}` | Delete a book |

## 📋 Sample Request

**Add a new book (POST /books):**
```json
{
    "title": "Clean Code",
    "author": "Robert Martin",
    "isbn": "978-0132350884",
    "available": true
}
```

**Response:**
```json
{
    "id": 1,
    "title": "Clean Code",
    "author": "Robert Martin",
    "isbn": "978-0132350884",
    "available": true
}
```

## ⚙️ How to Run

### Prerequisites
- Java 17
- MySQL Server
- Maven

### Setup

```bash
# Clone the repository
git clone https://github.com/mak2004-png/library-management-api.git

# Navigate into the project
cd library-management-api
```

Create a MySQL database:
```sql
CREATE DATABASE library_db;
```

Update `src/main/resources/application.properties` with your MySQL credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=root
spring.datasource.password=your_password
```

```bash
# Run the application
./mvnw spring-boot:run
```

API will be available at `http://localhost:8080`

## 🔮 Future Improvements
- [ ] Member management (register, update, delete members)
- [ ] Book issuing and return system
- [ ] Search books by title or author
- [ ] Exception handling and proper HTTP status codes
- [ ] JWT authentication
- [ ] Deployment on Railway/Render

## 👤 Author

**Affan Khan**
- GitHub: [@mak2004-png](https://github.com/mak2004-png)
- LinkedIn: [affan-khan-98472a36b](https://www.linkedin.com/in/affan-khan-98472a36b)
