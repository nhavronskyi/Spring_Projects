package com.example.mySite.clients;

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
			name = "clients_sequence",
			sequenceName = "clients_sequence",
			allocationSize = 1
			)
	
	
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "clients_sequence"
			)
	private long id;
	
	private String login;
	
	private String pass;
	
	public Client() {
		
	}
	
	public Client(String login, String pass) {
		this.login = login;
		this.pass = pass;
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

	@Override
	public String toString() {
		return "Client [id=" + id + ", login=" + login + ", pass=" + pass + "]";
	}
}
