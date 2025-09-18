## Overview
The Library Management System is designed to manage books,authors,publishers, members, borrowing transactions
and able get book with publisher and get on book,member and borrow_date ,due date,return_date from borrow transaction
in a secure and organized manner.


## The system supports:

Multiple authors per book and VisaVersa and a lot of relations

Category/genre classification with a hierarchical structure

Role-based access control (Administrator, Librarian, Staff)

Secure user authentication and password storage


## Database Schema

Store information about book.

book(id,title,isbn,edition,language,publication_year,summary,cover_image,publisher_id)

Stores information about book authors.

author(id,name)

Hierarchical structure for book categories.

category(id,name,parent_id)

Stores information about book publisher.

publisher(id,name)

Stores borrower information.

member(id,full_name,email,phone,address,membership_date)

Stores system users with role-based access control.

system_users(id,username,password,full_name,role)

Tracks book borrowing and returns.

borrowtransaction(id,book_id,member_id,proceedbyuser_id,borrow_date,due_date,return_date )




## Entity Relationships

Book ↔ Author: Many-to-Many

Book ↔ Category: Many-to-Many (each book belongs to one category, categories can be hierarchical)

Book ↔ Publisher: Many-to-One

Member ↔ BorrowingTransaction: One-to-Many

Book ↔ BorrowingTransaction: One-to-Many

Member ↔ Book : Many-to-Many

borrowtransaction ↔ SystemUser :Many-to-One 


## Key Design Decisions

Normalization: Avoided redundancy by separating authors, publishers, and categories.

Hierarchical Categories: parent_id allows nested subcategories.

Role-based Access: Three roles with controlled API endpoints:

Admin: Full access

Librarian: Manage books,authors members, and transactions

Staff: Limited read all/ read borrow transaction

Secure Password Storage: Passwords stored hashed (BCrypt recommended)