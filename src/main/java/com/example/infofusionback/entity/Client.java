package com.example.infofusionback.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn ( name ="id_user")
public class Client extends User {

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String phone ;

	@Column
	private Date birthdate;



	public Client() {

	}

	public Client(String email, String password, LocalDateTime d, String role) {
		super(email, password, d, role);
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/*public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/
	@OneToMany
	@JoinColumn(name="id.client")
	private Set<Panier> paniers = new HashSet<Panier>();
	public Set<Panier> getPaniers (){
		return this.paniers;
	}

	public void setPaniers (Set <Panier> p) {
		this.paniers=p;
	}
	@OneToOne(mappedBy = "client" , cascade = CascadeType.ALL)
	private Panier panier;
}
