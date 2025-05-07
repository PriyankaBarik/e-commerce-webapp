package com.pb.dto.cart;

import com.pb.entity.Cart;
import com.pb.entity.Product;

public class CartItemDto {
	
	private Integer id;
	private Integer quentity;
	private Product product;
	public CartItemDto() {
		super();
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuentity() {
		return quentity;
	}
	public void setQuentity(Integer quentity) {
		this.quentity = quentity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public CartItemDto(Cart cart) {
	
		this.id =cart.getId();
        this.quentity=cart.getQuantity();
        this.setProduct(cart.getProduct());
	} 
	
  
}
