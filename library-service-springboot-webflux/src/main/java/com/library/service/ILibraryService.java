package com.library.service;

import com.library.model.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ILibraryService {
	public Mono<Book> create(Book book) throws InterruptedException;
	public Flux<Book> getAll();
	public Mono<Book> get(int bookId);
	public Mono<Book> update(Book book, int bookId);
	public Mono<Void> delete(int bookId);
	public Flux<Book> search(String query);
	
}
