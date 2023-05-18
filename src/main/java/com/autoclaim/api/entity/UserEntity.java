package com.autoclaim.api.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String publicId;
	
	@Column(nullable=false, length=25)
	private String firstName;
	
	@Column(nullable=false, length=25)
	private String lastName;
	
	@Column(length=8)
	private String cin;
	
	@Column(nullable=false, length=64)
	private String email;
	
	@Column(length=8)
	private String telephone;
	
	@Column(length=64)
	private String address;
	
	@Column(nullable=false, length=255)
	private String encryptedPassword;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_contract",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "contract_id", referencedColumnName = "id"))
    private Set<ContractEntity> contracts;

	public Set<ContractEntity> getContracts() {
		return contracts;
	}

	public void setContracts(Set<ContractEntity> contracts) {
		this.contracts = contracts;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
}
