package com.pb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pb.common.ApiResponce;
import com.pb.dto.cart.AddToCartDto;
import com.pb.dto.cart.CartDto;
import com.pb.entity.Cart;
import com.pb.entity.User;
import com.pb.service.AuthenticationTokenService;
import com.pb.service.CartService;
import com.pb.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;
	@Autowired
	AuthenticationTokenService authenticaiontokenService;
    @Autowired
    ProductService productService;
	
	//post cart
    @PostMapping("/add")
	public ResponseEntity<ApiResponce> addToCart(@RequestBody AddToCartDto addToCartDto,@RequestParam("token")String token){
		
		authenticaiontokenService.autenticate(token);
		User user=authenticaiontokenService.getUser(token);
	
		cartService.addToCart(addToCartDto,user);
		return new ResponseEntity<>(new ApiResponce(true, "Add to cart"),HttpStatus.OK);
		
	}
	
	
	
	// get cart item
	
	@GetMapping("/getCartItem")
	public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token){
		authenticaiontokenService.autenticate(token);
		User user=authenticaiontokenService.getUser(token);
		
		CartDto cartDto=cartService.listCartItem(user);
		return new ResponseEntity<>(cartDto, HttpStatus.OK);
	}
	
	// delete cart
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponce> deleteCartItem(@PathVariable("id") Integer id,@RequestParam("token") String token){
		authenticaiontokenService.autenticate(token);
		User user=authenticaiontokenService.getUser(token);
		
		cartService.deleteCartItem(id,user);
		
		return new ResponseEntity<>(new ApiResponce(true, "deleted from  cart"),HttpStatus.OK);
	}
	
}
