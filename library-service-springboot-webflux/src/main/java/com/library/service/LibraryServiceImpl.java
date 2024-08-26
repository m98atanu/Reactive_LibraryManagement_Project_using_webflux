package com.library.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.model.Book;
import com.library.repository.IBookRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LibraryServiceImpl implements ILibraryService {
	
	@Autowired 
	private IBookRepository bookRepo;

	@Override
	public Mono<Book> create(Book book) {
		System.out.println("Thread takes request from controller: "
				+ Thread.currentThread().getName()); //to understand internal work with thread
		Mono<Book> createdBook = bookRepo.save(book)
				.doOnNext(data->{
					System.out.println("Thread that is handling db part: " 
							+ Thread.currentThread().getName()); //to understand internal work with thread
				});
		return createdBook;
	}

	@Override
	public Flux<Book> getAll() {
		System.out.println("Thread handling: getALL(): "+ Thread.currentThread().getName());//to understand internal work with thread
		
		Flux<Book> allBooks = bookRepo.findAll()
				.delayElements(Duration.ofSeconds(1))
				.log()
				.map(book->{
					book.setBookName(book.getBookName().toUpperCase());
					return book;
				});
		return allBooks;
	}

	@Override
	public Mono<Book> get(int bookId) {
		Mono<Book> book = bookRepo.findById(bookId);
		return book;
	}

	@Override
	public Mono<Book> update(Book book, int bookId) {
		Mono<Book> bookInDb = bookRepo.findById(bookId);
		Mono<Book> updatedBook = bookInDb.flatMap(book1->{
			book1.setBookId(bookId);
			book1.setBookName(book.getBookName());
			book1.setAuthor(book.getAuthor());
			book1.setPublisher(book.getPublisher());
			return bookRepo.save(book1);
		});
		return updatedBook;
	}

	@Override
	public Mono<Void> delete(int bookId) {
		return bookRepo.deleteById(bookId);
	}

	@Override
	public Flux<Book> search(String titleKeyword) {
		return bookRepo.searchBookByTitle("%"+titleKeyword+"%");
	}

}
