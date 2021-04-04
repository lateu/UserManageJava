package com.lateu.entity;

import java.time.LocalDateTime;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class ConfirmationToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String token ;
	private LocalDateTime expireAt;
	private LocalDateTime confirmAt;
	private LocalDateTime createAt;
	
	@ManyToOne
	@JoinColumn(nullable = false,name = "user_id")
	private User user;

	public ConfirmationToken() {
		// TODO Auto-generated constructor stub
	}

	public ConfirmationToken(Long id, String token, LocalDateTime expireAt, LocalDateTime confirmAt,
			LocalDateTime createAt,User user) {
		super();
		this.id = id;
		this.token = token;
		this.expireAt = expireAt;
		this.confirmAt = confirmAt;
		this.createAt = createAt;
		this.user=user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(LocalDateTime expireAt) {
		this.expireAt = expireAt;
	}

	public LocalDateTime getConfirmAt() {
		return confirmAt;
	}

	public void setConfirmAt(LocalDateTime confirmAt) {
		this.confirmAt = confirmAt;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
