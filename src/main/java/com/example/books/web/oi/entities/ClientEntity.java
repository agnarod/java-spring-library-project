package com.example.books.web.oi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "clients")
public class ClientEntity implements Serializable {
	private static final long serialVersionUID = -4164493378127224523L;

	@Id
	@GeneratedValue
	private long id;

	@Column(length = 30, nullable = false)
	private String clientId;

	@Column(length = 50, nullable = false)
	private String firstName;

	@Column(length = 60, nullable = false)
	private String lastName;

	@Column(length = 15, nullable = false)
	private String birthDate;

	@Column(length = 50, nullable = false)
	private String email;

	@Column(length = 15, nullable = false)
	private String phoneNumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
