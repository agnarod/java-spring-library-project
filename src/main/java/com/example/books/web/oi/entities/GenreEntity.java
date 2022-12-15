package com.example.books.web.oi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "genres")
public class GenreEntity implements Serializable {

	private static final long serialVersionUID = -7746133742656987874L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false, length=30)
	private String genreId;

	@Column(nullable = false, length=20)
	private String name;

	@Column(nullable = false, length=150)
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGenreId() {
		return genreId;
	}

	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
