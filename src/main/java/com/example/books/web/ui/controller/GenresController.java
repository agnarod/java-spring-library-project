package com.example.books.web.ui.controller;

import java.lang.reflect.Type;
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

import com.example.books.web.services.GenreService;
import com.example.books.web.shared.dtos.GenreDto;
import com.example.books.web.ui.model.requests.GenreRequestModel;
import com.example.books.web.ui.model.responses.GenreResponseModel;

@RestController
@RequestMapping("/genres")
public class GenresController {
	
	@Autowired
	GenreService genreService;

	@GetMapping
	public List<GenreResponseModel> getGenres(@RequestParam(name="page", defaultValue= "0") int page, @RequestParam(name="limit", defaultValue="10") int limit) {
		
		List<GenreDto> genres = genreService.getGenres(page, limit);

		Type listType = new TypeToken<List<GenreResponseModel>>() {
		}.getType();
		List<GenreResponseModel> returnValue = new ModelMapper().map(genres, listType);
		
		return returnValue;
	}
	
	@GetMapping(path="/{id}")
	public GenreResponseModel getGenre(@PathVariable String id) {
		GenreResponseModel returnValue = new GenreResponseModel();
		GenreDto genre = genreService.getGenreByGenderId(id);
		
		BeanUtils.copyProperties(genre, returnValue);
		
		return returnValue;
	}
	
	
	@PostMapping
	public GenreResponseModel createGenre(@RequestBody GenreRequestModel genre) {
		

		if (genre.getName().isEmpty())
			throw new NullPointerException("The genre name is empty");
		
		GenreResponseModel returnValue = new GenreResponseModel();
		
		GenreDto genreDto = new GenreDto();
		BeanUtils.copyProperties(genre, genreDto);
		
		GenreDto storedGenre = genreService.createGenre(genreDto);
		BeanUtils.copyProperties(storedGenre, returnValue);
		
		return returnValue;
	}
	
	
	@PutMapping(path="/{id}")
	public GenreResponseModel updateGenre(@RequestBody GenreRequestModel genre, @PathVariable String id) {
		if (genre.getName().isEmpty())
			throw new NullPointerException("The genre name is empty");
		GenreResponseModel returnValue = new GenreResponseModel();
		
		GenreDto genreDto = new GenreDto();
		BeanUtils.copyProperties(genre, genreDto);
		
		GenreDto updatedDetails = genreService.updateGenre(id, genreDto);
		BeanUtils.copyProperties(updatedDetails, returnValue);
		
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}")
	public String delete(@PathVariable String id) {
		
		genreService.deleteGenre(id);
		
		return "Genre successfuly delete";
	}
	
	
	

}
