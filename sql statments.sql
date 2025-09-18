CREATE database lib;
use lib;

CREATE TABLE books (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,        -- Book ID
    title VARCHAR(255) NOT NULL,                 -- Book title
    isbn VARCHAR(20) UNIQUE,                     -- ISBN number
    edition VARCHAR(50),                         -- Edition
    summary TEXT,                                -- Summary of the book
    cover_image VARCHAR(255),                    -- Cover image path
    publication_year INT,                        -- Year of publication
    language VARCHAR(50),                        -- Language of the book
    publisher_id BIGINT,                         -- Publisher reference
    FOREIGN KEY (publisher_id) REFERENCES publishers(id)
);


create table authors(
   id BIGINT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(255) NOT NULL
);


Create table book_author(

   book_id BIGINT NOT NULL,
   author_id BIGINT NOT NULL,
   PRIMARY KEY(book_id,author_id),
   FOREIGN KEY (book_id) REFERENCES books(id),
   FOREIGN KEY (author_id) REFERENCES authors(id)




);

CREATE TABLE publishers (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);


CREATE TABLE categories (

     id BIGINT PRIMARY KEY AUTO_INCREMENT,
     name VARCHAR(255) NOT NULL,
     parent_id BIGINT,
     FOREIGN KEY (parent_id)  REFERENCES categories(id)

);



Create table book_category(

    book_id BIGINT Not NULL,
    category_id BIGINT  NOT NULL,
    PRIMARY KEY (book_id,category_id),
    FOREIGN key (book_id) REFERENCES books(id),
    FOREIGN key (category_id) REFERENCES categories(id)


);


CREATE TABLE system_users (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,        -- User ID
    username VARCHAR(100) UNIQUE NOT NULL,       -- Login username
    password VARCHAR(255) NOT NULL,              -- Encrypted password
    full_name VARCHAR(255) NOT NULL,             -- Full name
    role ENUM('ADMIN', 'LIBRARIAN', 'STAFF') NOT NULL, -- Role
   created_at TIMESTAMP NOT NULL

);



CREATE TABLE members(
   id BIGINT PRIMARY KEY AUTO_INCREMENT,
   full_name VARCHAR(255) NOT NULL,
   email VARCHAR(100) UNIQUE NOT NULL,
   phone VARCHAR(20),
   address VARCHAR(255),
   membership_date DATE 
);


CREATE TABLE borrow_transactions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,        -- Transaction ID
    book_id BIGINT NOT NULL,                     -- Book being borrowed
    member_id BIGINT NOT NULL,                   -- Member who borrowed
    processed_by_user_id BIGINT NOT NULL,        -- User (Admin/Librarian/Staff) who processed
    borrow_date DATE NOT NULL,                   -- Date borrowed
    due_date DATE NOT NULL,                      -- Due date
    return_date DATE,                            -- Date returned (nullable)
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (member_id) REFERENCES members (id),
    FOREIGN KEY (processed_by_user_id) REFERENCES system_users(id)
);


DROP DATABASE lib;
use lib;
delete from lib.system_users;

SET SQL_SAFE_UPDATES = 0;

DELETE FROM lib.system_users;

SET SQL_SAFE_UPDATES = 1;


delete from lib.system_users where id>0;

DELETE FROM borrow_transactions WHERE processed_by_user_id = 1;
DELETE FROM system_users WHERE id = 1; 


-- ==========================
-- Authors
-- ==========================
INSERT INTO authors (name) VALUES
('George Orwell'),
('J.K. Rowling'),
('Yuval Noah Harari');

-- ==========================
-- Publishers
-- ==========================
INSERT INTO publishers (name) VALUES
('Penguin Books'),
('Bloomsbury Publishing'),
('HarperCollins');

-- ==========================
-- Categories
-- ==========================
INSERT INTO categories (name, parent_id) VALUES
('programing', NULL),
('DB', NULL),
('sql', 2),
('java', 1);

-- ==========================
-- Books
-- ==========================
INSERT INTO books (title, isbn, edition, summary, cover_image, publication_year, language, publisher_id) VALUES
('1984', '9780451524935', '1st', 'Dystopian social science fiction novel', '1984.jpg', 1949, 'English', 1),
('Harry Potter and the Philosopher''s Stone', '9780747532699', '1st', 'First book in the Harry Potter series', 'hp1.jpg', 1997, 'English', 2),
('Sapiens: A Brief History of Humankind', '9780062316097', '1st', 'Explores the history of humankind', 'sapiens.jpg', 2011, 'English', 3);

-- ==========================
-- Book ↔ Author
-- ==========================
INSERT INTO book_author (book_id, author_id) VALUES
(1, 1),
(2, 2),
(3, 3);

-- ==========================
-- Book ↔ Category
-- ==========================
INSERT INTO book_category (book_id, category_id) VALUES
(1, 1), -- 1984 is Fiction
(2, 4), -- Harry Potter is Fantasy
(3, 3); -- Sapiens is History

-- ==========================
-- Members
-- ==========================
INSERT INTO members (full_name, email, phone, address,membership_date) VALUES
('Ahmed', 'ahmed@example.com', 'ahmed@gmail.com', '123 Main St',CURDATE()),
('nour ', 'nour@example.com', 'nour@gmail.com', '456 Elm St',CURDATE());

-- ==========================
-- System Users
-- ==========================
INSERT INTO system_users (username, password, full_name, role,created_at) VALUES
('talal', '212kk231@32', 'tala noah', 'ADMIN',now()),
('ali', '2123rfr@24', 'ali mamdouh', 'LIBRARIAN',now()),
('aml', '66@deef3', 'aml salah', 'STAFF',NOW());

-- ==========================
-- Borrow Transactions
-- ==========================
INSERT INTO borrow_transactions (book_id, member_id, processed_by_user_id, borrow_date, due_date, return_date) VALUES
(1, 1, 2, '2025-09-01', '2025-09-15', '2025-09-10'),  -- Alice borrowed 1984, processed by Librarian
(2, 2, 3, '2025-09-05', '2025-09-20', NULL);         -- Bob borrowed Harry Potter, not yet returned






-- 1️⃣ تعطيل مؤقتًا Safe Updates
-- تعطيل فحص الـ FK مؤقتًا
SET FOREIGN_KEY_CHECKS = 0;

-- مسح جداول الربط أولاً
TRUNCATE TABLE book_author;
TRUNCATE TABLE book_category;

-- مسح البيانات من الجداول الأساسية
TRUNCATE TABLE borrow_transactions;
TRUNCATE TABLE members;
TRUNCATE TABLE books;
TRUNCATE TABLE authors;
TRUNCATE TABLE categories;
TRUNCATE TABLE publishers;
TRUNCATE TABLE system_users;

-- إعادة تفعيل فحص الـ FK
SET FOREIGN_KEY_CHECKS = 1;









