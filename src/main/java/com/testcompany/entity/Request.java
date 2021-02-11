package com.testcompany.entity;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the request database table.
 *
 */
@Entity
@Table(name="request")
public class Request implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_request", unique = true, nullable = false)
	private Integer idRequest;

	@OneToOne
	private Book book;

	private String username;

	@Column(name="request_date")
	private String requestDate;

	public Request() {
	}

	public Request(Book book, String username, String requestDate) {
		this.book = book;
		this.username = username;
		this.requestDate = requestDate;
	}

	public Integer getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(Integer idRequest) {
		this.idRequest = idRequest;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	@Override
	public String toString() {
		return "Request{" +
				"idRequest=" + idRequest +
				", book=" + book +
				", username='" + username + '\'' +
				", requestDate='" + requestDate + '\'' +
				'}';
	}
}