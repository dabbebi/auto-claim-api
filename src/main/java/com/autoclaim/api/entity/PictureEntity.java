package com.autoclaim.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="picture")
public class PictureEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String path;
	
	@ManyToOne
	@JoinTable(name = "claim_picture",
    joinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "claim_id", referencedColumnName = "id"))
    private ClaimEntity claim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ClaimEntity getClaim() {
		return claim;
	}

	public void setClaim(ClaimEntity claim) {
		this.claim = claim;
	}
}
