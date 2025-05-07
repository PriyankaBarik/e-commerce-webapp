package com.pb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pb.dto.cart.AddToCartDto;
import com.pb.dto.cart.CartDto;
import com.pb.dto.cart.CartItemDto;
import com.pb.entity.Cart;
import com.pb.entity.Product;
import com.pb.entity.User;
import com.pb.exception.CustomException;
import com.pb.repo.CartRepo;

@Service
public class CartService {

	@Autowired
	ProductService productService;

	@Autowired
	CartRepo cartRepo;

	public void addToCart(AddToCartDto addToCartDto, User user) {
	    Product product = productService.findById(addToCartDto.getProductId());

	    if (product == null) {
	        return;
	    }

	    Optional<Cart> existingCartEntry = cartRepo.findByUserAndProduct(user, product);

	    if (existingCartEntry.isPresent()) {
	       
	        Cart cart = existingCartEntry.get();
	        cart.setQuantity(cart.getQuantity() + addToCartDto.getQuentity());
	        cartRepo.save(cart);
	    } else {
	       
	        Cart newCartEntry = new Cart();
	        newCartEntry.setProduct(product);
	        newCartEntry.setUser(user);
	        newCartEntry.setQuantity(addToCartDto.getQuentity());
	        newCartEntry.setCreatedDate(new Date());
	        cartRepo.save(newCartEntry);
	    }
	}


	public CartDto listCartItem(User user) {
		List<Cart> cartList = cartRepo.findAllByUserOrderByCreatedDateDesc(user);
		List<CartItemDto> cartItems = new ArrayList<>();
		double totalCost = 0;

		for (Cart cart : cartList) {
			CartItemDto cartItemDto = new CartItemDto(cart);
			totalCost += cartItemDto.getQuentity() * cart.getProduct().getPrice();
			cartItems.add(cartItemDto);
		}
		CartDto cartDto = new CartDto();
		cartDto.setTotalCost(totalCost);
		cartDto.setCartItmDto(cartItems);

		return cartDto;
	}

	public void deleteCartItem(Integer id, User user) {

		// item id belongs to user

		Optional<Cart> optionalCart = cartRepo.findById(id);

		if (optionalCart.isEmpty()) {
			throw new CustomException("cart item id is not vlid " + id);
		}

		Cart cart = optionalCart.get();

		if (cart.getUser() != user) {
			throw new CustomException("item is not belonges to user");
		}

		cartRepo.delete(cart);
	}

}
