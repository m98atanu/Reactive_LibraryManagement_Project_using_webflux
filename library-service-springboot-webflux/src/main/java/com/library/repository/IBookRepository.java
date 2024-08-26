package com.library.repository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.library.model.Book;

import reactor.core.publisher.Flux;

@Repository
public interface IBookRepository extends ReactiveCrudRepository<Book, Integer> {
	@Query("select * from book_details where book_name like: title")
	Flux<Book> searchBookByTitle(String title);
}
