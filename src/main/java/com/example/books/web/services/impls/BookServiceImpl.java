package com.example.books.web.services.impls;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.books.web.io.reposotories.BookRepository;
import com.example.books.web.oi.entities.BookEntity;
import com.example.books.web.services.BookService;
import com.example.books.web.shared.Utils;
import com.example.books.web.shared.dtos.BookDto;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	Utils utils;

	@Override
	public List<BookDto> getBooks(String filter, int page, int limit) {
		// TODO Auto-generated method stub
		if(page > 0 ) page--;
		if(page < 0 ) page = 0 ;
		List<BookEntity> books = new ArrayList<BookEntity>();
		
		Pageable pageableRequest = (Pageable)PageRequest.of(page, limit);
		if(filter != null && !filter.isEmpty())
			books = bookRepository.findAllBooks(pageableRequest);
		else {
			books = bookRepository.findAllBooks(filter, pageableRequest);
			//books = bookRepository.findBooksByFilter(filter);
		}

		Type listType = new TypeToken<List<BookDto> >() {
		}.getType();
		List<BookDto> returnValue = new ModelMapper().map(books, listType);
		// TODO Auto-generated method stub
		return returnValue;
	}
	

	@Override
	public BookDto getBookByBookId(String bookId) {
		BookDto returnValue = new BookDto();
		
		BookEntity bookEntity = bookRepository.findByBookId(bookId);
		
		if(bookEntity == null) throw new RuntimeException("Book with id " + bookId + " not found ");
		
		BeanUtils.copyProperties(bookEntity, returnValue);
		
		// TODO Auto-generated method stub
		return returnValue;
	}
	
	@Override
	public BookDto saveBook(BookDto bookDto) {
		if(bookRepository.findByTitle(bookDto.getTitle())!= null)
			throw new RuntimeException("Record already exist");

		BookDto returnValue = new BookDto();
		
		BookEntity bookEntity = new BookEntity();
		BeanUtils.copyProperties(bookDto, bookEntity);
		
		bookEntity.setBookId(utils.generateId(30));
		
		BookEntity storedEntity = bookRepository.save(bookEntity);
		BeanUtils.copyProperties(storedEntity, returnValue);
		
		// TODO Auto-generated method stub
		return returnValue;
	}

	@Override
	public BookDto updateBook(String bookId, BookDto bookDto) {
		BookEntity bookEntity = bookRepository.findByBookId(bookId);
		
		if(bookEntity == null)
			throw new RuntimeException("Book");
		BookDto returnValue = new BookDto();
		
		bookEntity.setTitle(bookDto.getTitle());
		bookEntity.setAuthor(bookDto.getAuthor());
		
		BookEntity updatedEntity = bookRepository.save(bookEntity);
		BeanUtils.copyProperties(updatedEntity, returnValue);
		
		// TODO Auto-generated method stub
		return returnValue;
	}

	@Override
	public void delete(String bookId) {
		// TODO Auto-generated method stub


		BookEntity bookEntity = bookRepository.findByBookId(bookId);
		
		if(bookEntity == null)
			throw new RuntimeException("book with id " + bookId + " not found");
		
		bookRepository.delete(bookEntity);

		
	}


	

}
