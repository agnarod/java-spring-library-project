package com.example.books.web.io.reposotories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.books.web.oi.entities.BookEntity;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
	
	BookEntity findByTitle(String title);
	List<BookEntity> findByTitleLike(String title);
	List<BookEntity> findByAuthorLike(String author);
	BookEntity findByBookId(String bookId);
	
	@Query(value="FROM books")
	List<BookEntity> findAllBooks(Pageable pageable);
	
	

	@Query(value = "FROM books b WHERE UPPER(b.title) LIKE CONCAT('%', UPPER(:filter), '%') OR UPPER(b.author) LIKE CONCAT('%', UPPER(:filter), '%')")
	List<BookEntity> findAllBooks(@Param("filter")String filter, Pageable pageable);

}
