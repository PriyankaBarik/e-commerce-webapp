package com.pb.dto.cart;

import javax.validation.constraints.NotNull;

public class AddToCartDto {

	private Integer id;
	private @NotNull Integer productId;
	private @NotNull Integer quentity;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQuentity() {
		return quentity;
	}
	public void setQuentity(Integer quentity) {
		this.quentity = quentity;
	}
	
	
}
