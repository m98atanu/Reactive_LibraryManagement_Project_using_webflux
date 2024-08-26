use myDatabase;

CREATE TABLE book_details(
	book_Id INT PRIMARY KEY AUTO_INCREMENT,
    book_name VARCHAR(50),
    description VARCHAR(100),
    author VARCHAR(50),
    publisher VARCHAR(50)
);