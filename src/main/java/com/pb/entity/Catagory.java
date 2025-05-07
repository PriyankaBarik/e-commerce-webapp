package com.pb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "catagory")
public class Catagory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "catgory_name")
	private @NotBlank String catagoryName;
	
	@Column(name = "description")
	private @NotBlank String description;
	
	@Column(name = "image_url")
	private @NotBlank String imageUrl;

	
	public Catagory(Integer id, @NotBlank String catagoryName, @NotBlank String description,
			@NotBlank String imageUrl) {
		super();
		this.id = id;
		this.catagoryName = catagoryName;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public Catagory() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCatagoryName() {
		return catagoryName;
	}

	public void setCatagoryName(String catagoryName) {
		this.catagoryName = catagoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
