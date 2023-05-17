package com.autoclaim.api.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="contract")
public class ContractEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String publicId;
	
	@Column(nullable=false)
	private Date startDate;
	
	@Column(nullable=false)
	private Date endDate;
	
	@OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
	private UserEntity user;
	
	@Column
	private String VehicleRegistrationNo;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "contract_claim",
    joinColumns = @JoinColumn(name = "contract_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "claim_id", referencedColumnName = "id"))
    private Set<ClaimEntity> claims;

	public Set<ClaimEntity> getClaims() {
		return claims;
	}

	public void setClaims(Set<ClaimEntity> claims) {
		this.claims = claims;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getVehicleRegistrationNo() {
		return VehicleRegistrationNo;
	}

	public void setVehicleRegistrationNo(String vehicleRegistrationNo) {
		VehicleRegistrationNo = vehicleRegistrationNo;
	}
}