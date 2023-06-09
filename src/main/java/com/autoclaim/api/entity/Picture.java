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
public class Picture {
	
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String publicId;
	
	@Column(nullable=false)
	private String path;
	
	@ManyToOne
	@JoinTable(name = "claim_picture",
    joinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "claim_id", referencedColumnName = "id"))
    private Claim claim;

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}
}
