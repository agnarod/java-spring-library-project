package com.example.books.web.services;

import java.util.List;

import com.example.books.web.shared.dtos.GenreDto;

public interface GenreService {

	GenreDto getGenreByGenderId(String genreId);
	List<GenreDto> getGenres(int page, int limit);
	GenreDto createGenre(GenreDto genre);
	GenreDto updateGenre(String genreId, GenreDto genre);
	void deleteGenre(String genreId);

}
