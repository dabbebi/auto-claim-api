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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.autoclaim.api.enums.ClaimStatus;

@Entity
@Table(name="claim")
public class ClaimEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String publicId;
	
	@Column(nullable=false)
	private Date accidentDate;
	
	@Column(nullable=false)
	private Date creationDate;
	
	@Column(nullable=false)
	private ClaimStatus status;
	
	@ManyToOne
	@JoinTable(name = "contract_claim",
    joinColumns = @JoinColumn(name = "claim_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "contract_id", referencedColumnName = "id"))
	private ContractEntity contract;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "claim_picture",
    joinColumns = @JoinColumn(name = "claim_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "id"))
    private Set<PictureEntity> pictures;

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

	public Date getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(Date accidentDate) {
		this.accidentDate = accidentDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public ClaimStatus getStatus() {
		return status;
	}

	public void setStatus(ClaimStatus status) {
		this.status = status;
	}

	public ContractEntity getContract() {
		return contract;
	}

	public void setContract(ContractEntity contract) {
		this.contract = contract;
	}

	public Set<PictureEntity> getPictures() {
		return pictures;
	}

	public void setPictures(Set<PictureEntity> pictures) {
		this.pictures = pictures;
	}

}
