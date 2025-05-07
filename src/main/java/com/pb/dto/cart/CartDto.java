package com.pb.dto.cart;

import java.util.List;

public class CartDto {
	
	List<CartItemDto> cartItmDto;
	
	private Double totalCost;

	public CartDto() {
		super();
		
	}

	public List<CartItemDto> getCartItmDto() {
		return cartItmDto;
	}

	public void setCartItmDto(List<CartItemDto> cartItmDto) {
		this.cartItmDto = cartItmDto;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	

}
