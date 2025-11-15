**Library Management System API** 

---

# 📚 **Library Management System API**

A comprehensive **RESTful API** for managing library operations including books, members, and transactions.

---

## 🚀 **Features**

### 📘 Book Management

* Full CRUD operations
* Search by title/author
* ISBN lookup
* Availability tracking

### 👤 Member Management

* Member registration & profile management
* Membership types
* Search members

### 🔄 Transaction Management

* Issue & return books
* Automatic fine calculation
* Overdue tracking

### ⚙️ Additional Features

* Input validation (Bean Validation)
* Global exception handling
* JPA/Hibernate ORM
* Pagination support
* Clean layered architecture

---

## 📋 **Prerequisites**

* **Java 17+**
* **Maven 3.6+**
* **MySQL 8.0+**
* **Postman** (optional for testing)

---

## 🛠️ **Technologies Used**

| Technology      | Purpose            |
| --------------- | ------------------ |
| Spring Boot 3.2 | Core framework     |
| Spring Data JPA | DB operations      |
| Hibernate       | ORM                |
| MySQL           | Database           |
| Lombok          | Reduce boilerplate |
| Bean Validation | Input validation   |

---

## 📁 **Project Structure**

```
library-management-api/
├── src/
│   ├── main/
│   │   ├── java/com/library/
│   │   │   ├── LibraryManagementApplication.java
│   │   │   ├── controller/
│   │   │   │   ├── BookController.java
│   │   │   │   ├── MemberController.java
│   │   │   │   └── TransactionController.java
│   │   │   ├── service/
│   │   │   │   ├── BookService.java
│   │   │   │   ├── MemberService.java
│   │   │   │   └── TransactionService.java
│   │   │   ├── repository/
│   │   │   │   ├── BookRepository.java
│   │   │   │   ├── MemberRepository.java
│   │   │   │   └── TransactionRepository.java
│   │   │   ├── entity/
│   │   │   │   ├── Book.java
│   │   │   │   ├── Member.java
│   │   │   │   └── Transaction.java
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   ├── BadRequestException.java
│   │   │   │   ├── DuplicateResourceException.java
│   │   │   │   └── BookNotAvailableException.java
│   │   │   └── dto/
│   │   │       └── ErrorResponse.java
│   │   └── resources/
│   │       └── application.properties
└── pom.xml
```

---

## ⚙️ **Setup Instructions**

### **1. Clone the Repository**

```bash
git clone https://github.com/yourusername/library-management-api.git
cd library-management-api
```

---

### **2. Create MySQL Database**

```sql
CREATE DATABASE library_db;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db?createDatabaseIfNotExist=true
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### **3. Build the Project**

```bash
mvn clean install
```

### **4. Run the Application**

```bash
mvn spring-boot:run
```

Base URL:

```
http://localhost:8080
```

---

# 📚 **API Endpoints (25+ Total)**

---

## **📘 Book Management**

| Method | Endpoint                         | Description      |
| ------ | -------------------------------- | ---------------- |
| POST   | /api/books                       | Create new book  |
| GET    | /api/books                       | Get all books    |
| GET    | /api/books/paginated             | Paginated books  |
| GET    | /api/books/{id}                  | Get book by ID   |
| GET    | /api/books/isbn/{isbn}           | Get book by ISBN |
| PUT    | /api/books/{id}                  | Update book      |
| DELETE | /api/books/{id}                  | Delete book      |
| GET    | /api/books/search?title=&author= | Search           |
| GET    | /api/books/available             | Available books  |

---

## **👤 Member Management**

| Method | Endpoint                   | Description     |
| ------ | -------------------------- | --------------- |
| POST   | /api/members               | Register member |
| GET    | /api/members               | Get all members |
| GET    | /api/members/{id}          | Member by ID    |
| GET    | /api/members/email/{email} | Member by email |
| PUT    | /api/members/{id}          | Update          |
| DELETE | /api/members/{id}          | Delete          |
| GET    | /api/members/search?name=  | Search by name  |

---

## **🔄 Transaction Management**

| Method | Endpoint                                   | Description           |
| ------ | ------------------------------------------ | --------------------- |
| POST   | /api/transactions/issue                    | Issue book            |
| PUT    | /api/transactions/{id}/return              | Return book           |
| GET    | /api/transactions                          | All transactions      |
| GET    | /api/transactions/{id}                     | Transaction by ID     |
| GET    | /api/transactions/member/{memberId}        | Member’s transactions |
| GET    | /api/transactions/book/{bookId}            | Book’s transactions   |
| GET    | /api/transactions/overdue                  | Overdue               |
| GET    | /api/transactions/member/{memberId}/active | Active                |
| GET    | /api/transactions/status/{status}          | By status             |

---

## 🧪 **Sample API Requests**

### **Create Book**

```json
POST /api/books
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "isbn": "978-0132350884",
  "publisher": "Prentice Hall",
  "publishedYear": 2008,
  "totalCopies": 5,
  "availableCopies": 5,
  "category": "Programming"
}
```

### **Create Member**

```json
POST /api/members
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "9876543210",
  "membershipDate": "2024-01-15",
  "membershipType": "PREMIUM"
}
```

### **Issue Book**

```
POST /api/transactions/issue?bookId=1&memberId=1
```

### **Return Book**

```
PUT /api/transactions/1/return
```

---

## 📊 **Database Schema**

### **Books**

* id (PK)
* title
* author
* isbn (unique)
* publisher
* published_year
* total_copies
* available_copies
* category

### **Members**

* id (PK)
* name
* email (unique)
* phone (unique)
* membership_date
* membership_type

### **Transactions**

* id (PK)
* book_id (FK)
* member_id (FK)
* issue_date
* due_date
* return_date
* status
* fine

---

## ✅ **Features Implemented**

* 25+ REST API endpoints
* JPA/Hibernate CRUD operations
* Clean layered architecture
* Global exception handling
* Input validation
* Pagination & search
* Automatic fine calculation
* Overdue tracking

---

## 🤝 **Contributing**

1. Fork the repo
2. Create feature branch
3. Commit changes
4. Push
5. Open a Pull Request

---


