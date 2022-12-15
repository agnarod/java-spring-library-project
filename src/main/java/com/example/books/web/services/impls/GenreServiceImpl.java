package com.example.books.web.services.impls;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.books.web.io.reposotories.GenreRepository;
import com.example.books.web.oi.entities.GenreEntity;
import com.example.books.web.services.GenreService;
import com.example.books.web.shared.Utils;
import com.example.books.web.shared.dtos.GenreDto;




@Service
public class GenreServiceImpl implements GenreService {
	
	
	@Autowired
	GenreRepository genreRepository;
	
	
	@Autowired
	Utils utils;

	
	@Override
	public GenreDto getGenreByGenderId(String genreId) {
		GenreDto returnValue = new GenreDto();
		
		GenreEntity genreEntity = genreRepository.findByGenreId(genreId);
		

		if(genreEntity == null) throw new RuntimeException("genre with id " + genreId + " not found ");
		
		BeanUtils.copyProperties(genreEntity, returnValue);
		
		return returnValue;
	}
	
	@Override
	public List<GenreDto> getGenres(int page, int limit){
		if(page>0) page--;
		if(page<0)page = 0;
		
		Pageable pageableRequest = (Pageable)PageRequest.of(page, limit);
		Page<GenreEntity> genrePage = genreRepository.findAll(pageableRequest);
		List<GenreEntity> genres =  genrePage.getContent();

		Type listType = new TypeToken<List<GenreDto> >() {
		}.getType();
		List<GenreDto> returnValue = new ModelMapper().map(genres, listType);
		
		return returnValue;
		
		
	}

	@Override
	public GenreDto createGenre(GenreDto genre) {
		if(genreRepository.findByName(genre.getName())!= null)
			throw new RuntimeException("Record already exist");
		
		
		GenreDto returnValue = new GenreDto();
		GenreEntity genreEntity = new GenreEntity();
		BeanUtils.copyProperties(genre, genreEntity);
		genreEntity.setGenreId(utils.generateId(30));
		
		GenreEntity storedEntity = genreRepository.save(genreEntity);
		
		BeanUtils.copyProperties(storedEntity, returnValue);
		
		// TODO Auto-generated method stub
		return returnValue;
	}

	@Override
	public GenreDto updateGenre(String genreId, GenreDto genre) {
		
		GenreEntity genreEntity = genreRepository.findByGenreId(genreId);

		if(genreEntity == null)
			throw new RuntimeException("genre with id " + genreId + " not found ");
		
		GenreDto returnValue = new GenreDto();
		genreEntity.setName(genre.getName());
		genreEntity.setDescription(genre.getDescription());
		
		GenreEntity updatedDetaisl = genreRepository.save(genreEntity);
		
		BeanUtils.copyProperties(updatedDetaisl, returnValue);
		
		// TODO Auto-generated method stub
		return returnValue;
	}

	@Override
	public void deleteGenre(String genreId) {

		GenreEntity genreEntity = genreRepository.findByGenreId(genreId);
		
		if(genreEntity == null)
			throw new RuntimeException("genre with id " + genreId + " not found");
		
		genreRepository.delete(genreEntity);

		
	}
	
	

}
