package br.com.caelum.ingresso.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.hibernate.validator.constraints.Email;

@Entity
public class Token {
	@Id
	private String uuid;
	@Email
	private String email;

	public Token() {
	}

	public Token(String email) {
		this.email = email;
	}

	@PrePersist
	public void prePersist() {
		uuid = UUID.randomUUID().toString();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
