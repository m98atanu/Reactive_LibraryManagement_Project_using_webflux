package com.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("book_details")
public class Book {
	@Id
	private int bookId;
	private String bookName;
	private String author;
	private String publisher;
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Book(int bookId, String bookName, String author, String publisher) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.publisher = publisher;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
