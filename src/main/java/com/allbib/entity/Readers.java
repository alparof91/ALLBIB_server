package com.allbib.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the readers database table.
 *
 */
@Entity
@Table(name="readers")
public class Readers implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_reader")
	private int idReader;

	@Column(name="first_name")
	private String firstName;

	@Column(name="second_name")
	private String secondName;

	private String address;

	private String email;

	private String phone;

	@OneToOne
	private User user;

	public Readers() {
	}

	public Readers(String firstName, String secondName, String address, String email, String phone, User user) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.user = user;
	}

	public int getIdReader() {
		return idReader;
	}

	public void setIdReader(int idReader) {
		this.idReader = idReader;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Readers{" +
				"idReader=" + idReader +
				", firstName='" + firstName + '\'' +
				", secondName='" + secondName + '\'' +
				", address='" + address + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", user=" + user +
				'}';
	}
}