Library Management System API
A comprehensive RESTful API for managing library operations including books, members, and transactions.
🚀 Features

Book Management: CRUD operations with search and availability tracking
Member Management: Registration and profile management with membership types
Transaction Management: Issue/return books with automatic fine calculation
Input Validation: Comprehensive validation with proper error messages
Exception Handling: Global exception handler with proper HTTP status codes
Database Operations: JPA/Hibernate with optimized entity relationships
Pagination Support: Efficient data retrieval for large datasets

📋 Prerequisites

Java 17 or higher
Maven 3.6+
MySQL 8.0+
Postman (for API testing)

🛠️ Technologies Used

Spring Boot 3.2.0: Application framework
Spring Data JPA: Database operations
Hibernate: ORM framework
MySQL: Relational database
Lombok: Reduce boilerplate code
Bean Validation: Input validation

📁 Project Structure
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
│   └── test/
└── pom.xml
⚙️ Setup Instructions
1. Clone the Repository
bashgit clone https://github.com/yourusername/library-management-api.git
cd library-management-api
2. Configure MySQL Database
Create a MySQL database:
sqlCREATE DATABASE library_db;
Update application.properties:
propertiesspring.datasource.url=jdbc:mysql://localhost:3306/library_db?createDatabaseIfNotExist=true
spring.datasource.username=your_username
spring.datasource.password=your_password
3. Build the Project
bashmvn clean install
4. Run the Application
bashmvn spring-boot:run
The API will be available at: http://localhost:8080
📚 API Endpoints
Book Management (9 endpoints)
MethodEndpointDescriptionPOST/api/booksCreate a new bookGET/api/booksGet all booksGET/api/books/paginatedGet books with paginationGET/api/books/{id}Get book by IDGET/api/books/isbn/{isbn}Get book by ISBNPUT/api/books/{id}Update bookDELETE/api/books/{id}Delete bookGET/api/books/search?title=&author=Search booksGET/api/books/availableGet available books
Member Management (7 endpoints)
MethodEndpointDescriptionPOST/api/membersRegister new memberGET/api/membersGet all membersGET/api/members/{id}Get member by IDGET/api/members/email/{email}Get member by emailPUT/api/members/{id}Update memberDELETE/api/members/{id}Delete memberGET/api/members/search?name=Search members by name
Transaction Management (9 endpoints)
MethodEndpointDescriptionPOST/api/transactions/issueIssue book to memberPUT/api/transactions/{id}/returnReturn bookGET/api/transactionsGet all transactionsGET/api/transactions/{id}Get transaction by IDGET/api/transactions/member/{memberId}Get member's transactionsGET/api/transactions/book/{bookId}Get book's transactionsGET/api/transactions/overdueGet overdue transactionsGET/api/transactions/member/{memberId}/activeGet active transactionsGET/api/transactions/status/{status}Get transactions by status
Total: 25+ Endpoints
🧪 Sample API Requests
Create Book
bashPOST http://localhost:8080/api/books
Content-Type: application/json

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
Create Member
bashPOST http://localhost:8080/api/members
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "9876543210",
  "membershipDate": "2024-01-15",
  "membershipType": "PREMIUM"
}
Issue Book
bashPOST http://localhost:8080/api/transactions/issue?bookId=1&memberId=1
Return Book
bashPUT http://localhost:8080/api/transactions/1/return
📊 Database Schema
Books Table

id (PK)
title
author
isbn (unique)
publisher
published_year
total_copies
available_copies
category

Members Table

id (PK)
name
email (unique)
phone (unique)
membership_date
membership_type

Transactions Table

id (PK)
book_id (FK)
member_id (FK)
issue_date
due_date
return_date
status
fine

✅ Features Implemented

 RESTful API with 25+ endpoints
 JPA/Hibernate for database operations
 Optimized entity relationships (One-to-Many)
 Input validation with Bean Validation
 Global exception handling
 Proper HTTP status codes (200, 201, 400, 404, 500)
 Custom exceptions
 Pagination support
 Search functionality
 Automatic fine calculation for overdue books
 Transaction management

🔍 Testing
Use Postman or cURL to test the endpoints. Import the provided Postman collection for easy testing.
📝 Notes

Default borrowing period: 14 days
Fine per day for overdue books: ₹5.00
Membership types: STANDARD, PREMIUM, STUDENT
Transaction status: ISSUED, RETURNED, OVERDUE

🤝 Contributing

Fork the repository
Create your feature branch
Commit your changes
Push to the branch
Create a Pull Request


Spring Boot Documentation
Hibernate Documentation
MySQL Documentation
