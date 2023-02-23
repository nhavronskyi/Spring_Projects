package com.example.restSite.files;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Client {
	@Id
	@SequenceGenerator(
		name = "sequence_name",
		sequenceName = "sequence_name",
		allocationSize = 1
		)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "sequence_name"
			)
	
	private long id;
	private String login;
	private String pass;
	
	public Client() {}

	public Client(String login, String pass) {
		super();
		this.login = login;
		this.pass = pass;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
