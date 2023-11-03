package com.example.infofusionback.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
	@Column(name="id")
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
	
	 @Column(name = "role", nullable = false, columnDefinition = "varchar(255) default 'ROLE_CLIENT'", insertable = false)
	  private String role;

	
	 public User() {
	
		}
    
    public User(Long id, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password,
    		LocalDateTime d, String role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.d = d;
		this.role= role;
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
	
	
}
