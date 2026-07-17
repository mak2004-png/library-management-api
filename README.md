# 📚 Library Management REST API

A RESTful API for library management built with Java Spring Boot and MySQL. Implements a clean 3-layer architecture (Controller → Service → Repository) with full CRUD operations, entity relationships, and centralized exception handling.

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
Exception Layer   →  centralized error handling across all controllers
```

## 📁 Project Structure

```
src/main/java/library_management/
├── controller/
│   ├── BookController.java
│   ├── MemberController.java
│   └── IssuedBookController.java
├── model/
│   ├── Book.java
│   ├── Member.java
│   └── IssuedBook.java          # links a Book and a Member (@ManyToOne)
├── repository/
│   ├── BookRepository.java
│   ├── MemberRepository.java
│   └── IssuedBookRepository.java
├── service/
│   ├── BookService.java
│   ├── MemberService.java
│   └── IssuedBookService.java
├── exception/
│   ├── ResourceNotFoundException.java
│   ├── DuplicateIsbnException.java
│   ├── DuplicateEmailException.java
│   ├── BookUnavailableException.java
│   ├── BookAlreadyReturnedException.java
│   └── GlobalExceptionHandler.java
└── LibraryManagementApplication.java
```

## 🚀 API Endpoints

### Books
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/books` | Get all books |
| GET | `/books/{id}` | Get book by ID |
| POST | `/books` | Add a new book |
| PUT | `/books/{id}` | Update a book |
| DELETE | `/books/{id}` | Delete a book |

### Members
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/members` | Get all members |
| GET | `/members/{id}` | Get member by ID |
| POST | `/members` | Register a new member |
| PUT | `/members/{id}` | Update a member |
| DELETE | `/members/{id}` | Delete a member |

### Issued Books
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/issued-books` | Get all issued book records |
| POST | `/issued-books?bookId={id}&memberId={id}` | Issue a book to a member |
| PUT | `/issued-books/{id}/return` | Return an issued book |

## ⚠️ Error Handling

All errors are handled centrally via a `@RestControllerAdvice` global exception handler, returning clean JSON responses instead of raw stack traces.

| Status | Scenario |
|--------|----------|
| `404 Not Found` | Book, member, or issued-book record doesn't exist |
| `409 Conflict` | Duplicate ISBN, duplicate email, book already checked out, or book already returned |
| `400 Bad Request` | Malformed JSON in request body |

## 📋 Sample Requests

**Add a new book** — `POST /books`
```json
{
    "title": "Clean Code",
    "author": "Robert Martin",
    "isbn": "978-0132350884",
    "available": true
}
```

**Issue a book** — `POST /issued-books?bookId=1&memberId=1`
```json
{
    "id": 1,
    "book": {
        "id": 1,
        "title": "Clean Code",
        "available": false
    },
    "member": {
        "id": 1,
        "name": "Ali Khan"
    },
    "issuedDate": "2026-07-17",
    "returnDate": null
}
```

## ⚙️ How to Run

### Prerequisites
- Java 17
- MySQL Server
- Maven

### Setup

**1. Clone the repository**
```bash
git clone https://github.com/mak2004-png/library-management-api.git
cd library-management-api
```

**2. Create a MySQL database**
```sql
CREATE DATABASE library_db;
```

**3. Configure your credentials**

Copy `src/main/resources/application.properties.example` to `application.properties` and fill in your MySQL credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=root
spring.datasource.password=your_password
```

**4. Run the application**
```bash
./mvnw spring-boot:run
```

API will be available at `http://localhost:8080`

## 🔮 Future Improvements

- [ ] Search books by title or author
- [ ] Input validation (required fields, email format)
- [ ] Automated tests (JUnit/Mockito)
- [ ] Pagination and sorting on list endpoints
- [ ] JWT authentication
- [ ] Deployment on Railway/Render

## 👤 Author

**Affan Khan**
- GitHub: [@mak2004-png](https://github.com/mak2004-png)
- LinkedIn: [affan-khan-98472a36b](https://linkedin.com/in/affan-khan-98472a36b)