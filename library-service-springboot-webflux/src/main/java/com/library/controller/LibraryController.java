package com.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.model.Book;
import com.library.service.LibraryServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class LibraryController {
	
	@Autowired
	private LibraryServiceImpl libraryService;

	@PostMapping
	public ResponseEntity<Mono<Book>> create(@RequestBody Book book) {
		System.out.println("Thread accept the client request(controller): " 
				+ Thread.currentThread().getName());
		return new ResponseEntity<>(libraryService.create(book), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<Flux<Book>> getAll() {
		return new ResponseEntity<>(libraryService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{bid}")
	public ResponseEntity<Mono<Book>> get(@PathVariable("bid") int bookId) {
		return new ResponseEntity<>(libraryService.get(bookId), HttpStatus.OK);
	}

	@PutMapping("/{bookId}")
	public ResponseEntity<Mono<Book>> update(@RequestBody Book book, @PathVariable int bookId) {
		return new ResponseEntity<>(libraryService.update(book, bookId), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{bookId}")
	public ResponseEntity<Mono<Void>> delete(@PathVariable int bookId) {
		return new ResponseEntity<>(libraryService.delete(bookId), HttpStatus.OK);
	}

	@GetMapping("/{titleKeyword}") //need to check this
	public ResponseEntity<Flux<Book>> search(@PathVariable String titleKeyword) {
		return new ResponseEntity<>(libraryService.search(titleKeyword), HttpStatus.OK);
	}

}
