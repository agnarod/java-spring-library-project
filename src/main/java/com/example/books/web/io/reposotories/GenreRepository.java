package com.example.books.web.io.reposotories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.books.web.oi.entities.GenreEntity;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

	GenreEntity findByName(String name);

	GenreEntity findByGenreId(String genreId);

}
