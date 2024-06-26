package com.example.infofusionback.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Inheritance (strategy = InheritanceType.JOINED )
@Table(	name = "users", 
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "email") 
})
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_user")
	private Long id;

	@Column
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@Column
	@NotBlank
	@Size(max = 120)
	private String password;

	@Column
	private LocalDateTime d;

	@Column(name = "role")
	private String role;


	public User() {

	}

	public User(String email, String password, LocalDateTime d, String role/*, Client client, Shop shop*/) {
		super();
		this.email = email;
		this.password = password;
		this.d = d;
		this.role = role;
		//this.client = client;
		//this.shop = shop;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getD() {
		return d;
	}

	public void setD(LocalDateTime localDateTime) {
		this.d = localDateTime;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/*public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}*/
}
