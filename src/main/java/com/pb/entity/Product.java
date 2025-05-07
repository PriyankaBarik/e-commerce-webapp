package com.pb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "product_name")
	private @NotBlank String productName;

	@Column(name = "description")
	private @NotBlank String description;

	@Column(name = "price")
	private @NotBlank double price;

	@Column(name = "image_url")
	private @NotBlank String imageUrl;

	// Many to one relationship

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "category_id")
	Catagory category;

	public Product() {
		super();
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String catagoryName) {
		this.productName = catagoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Catagory getCategory() {
		return category;
	}

	public void setCategory(Catagory category) {
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
