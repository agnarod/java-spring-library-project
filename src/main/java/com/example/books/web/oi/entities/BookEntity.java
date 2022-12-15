package com.example.books.web.oi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "books")
public class BookEntity implements Serializable {
	private static final long serialVersionUID = 4621185793164385339L;

	@Id
	@GeneratedValue
	private long id;

	@Column(length = 30, nullable = false)
	private String bookId;

	@Column(length = 100, nullable = false)
	private String title;

	@Column(length = 150, nullable = false)
	private String author;

	@Column(nullable = false)
	private int releaseYear;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

}
