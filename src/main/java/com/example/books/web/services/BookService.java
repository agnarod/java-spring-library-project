package com.example.books.web.services;

import java.util.List;

import com.example.books.web.shared.dtos.BookDto;

public interface BookService {

	BookDto getBookByBookId(String bookId);

	List<BookDto> getBooks(String filter, int page, int limit);

	BookDto saveBook(BookDto bookDto);
	
	BookDto updateBook(String bookId, BookDto bookDto);
	
	void delete(String bookId);

}
