package com.example.books.web.ui.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.books.web.services.BookService;
import com.example.books.web.shared.dtos.BookDto;
import com.example.books.web.ui.model.requests.BookRequestModel;
import com.example.books.web.ui.model.responses.BookResponseModel;

@RestController
@RequestMapping("/books")
public class BooksController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping
	public List<BookResponseModel> getBooks(@RequestParam(value="page", defaultValue="0") int page, @RequestParam(value="limit", defaultValue="10")int limit){
		
		
		List<BookDto> books = bookService.getBooks(null, page, limit);
		
		//BeanUtils.copyProperties(books, returnValue);
		

		Type listType = new TypeToken<List<BookResponseModel> >() {
		}.getType();
		List<BookResponseModel> returnValue = new ModelMapper().map(books, listType);
		
		
		return returnValue;
	}
	
	@GetMapping(path="/{id}")
	public BookResponseModel getBook(@PathVariable String id) {
		BookResponseModel returnValue = new BookResponseModel();
		BookDto bookDto = bookService.getBookByBookId(id);
		
		BeanUtils.copyProperties(bookDto, returnValue);
		
		
		return returnValue;
	}
	
	@GetMapping(path="/title")
	public List<BookResponseModel> getBooksByTitle(String title){
		List<BookResponseModel> returnValue = new ArrayList<BookResponseModel>();
		
		return returnValue;
	}
	
	@GetMapping(path="/filters")
	public List<BookResponseModel> getBooksByFilter( @RequestParam(value="filter", defaultValue="") String filter,
			@RequestParam(value="page", defaultValue="0") int page, @RequestParam(value="limit", defaultValue="10") int limit){
		
		
		List<BookDto> books = bookService.getBooks(filter, page, limit);
		
		//BeanUtils.copyProperties(books, returnValue);
		

		Type listType = new TypeToken<List<BookResponseModel> >() {
		}.getType();
		List<BookResponseModel> returnValue = new ModelMapper().map(books, listType);
		
		
		return returnValue;
	}
	
	
	@PostMapping
	public BookResponseModel saveBook(@RequestBody BookRequestModel book) {
		

		if (book.getTitle().isEmpty() || book.getAuthor().isEmpty())
			throw new NullPointerException("The book title or author is empty");
		
		BookResponseModel returnValue = new BookResponseModel();
		
		BookDto bookDto = new BookDto();
		BeanUtils.copyProperties(book, bookDto);
		
		BookDto storedBook = bookService.saveBook(bookDto); 
		BeanUtils.copyProperties(storedBook, returnValue);
		
		
		return returnValue;
	}
	
	@PutMapping(path="/{bookId}")
	public BookResponseModel updateBook(@PathVariable String bookId,@RequestBody BookRequestModel book) {
		
		if (book.getTitle().isEmpty())
			throw new NullPointerException("The book title is empty");
		BookResponseModel returnValue = new BookResponseModel();
		
		BookDto bookDto = new BookDto();
		BeanUtils.copyProperties(book, bookDto);
		
		BookDto updatedDetails = bookService.updateBook(bookId, bookDto);
		BeanUtils.copyProperties(updatedDetails, returnValue);
		
		return returnValue;
	}
	
	
	@DeleteMapping(path="/{bookId}")
	public String deleteBook(String bookId) {
		
		bookService.delete(bookId);
		
		return "Book deleted successfully";
	}
	
	
	
}
