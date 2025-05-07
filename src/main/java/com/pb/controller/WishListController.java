package com.pb.controller;

import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pb.common.ApiResponce;
import com.pb.dto.ProductDto;
import com.pb.entity.Product;
import com.pb.entity.User;
import com.pb.entity.WishList;
import com.pb.service.AuthenticationTokenService;
import com.pb.service.WishListService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/wishlist")
public class WishListController {
   
	@Autowired
	WishListService wishListService;
	 
	@Autowired
	AuthenticationTokenService authenticaiontokenService;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponce>  addToWishList(@RequestBody Product product,@RequestParam("token")String token)  {
		
		authenticaiontokenService.autenticate(token);
		User user=authenticaiontokenService.getUser(token);
		 WishList wishList= new WishList(user,product);
		 
		 wishListService.createWishList(wishList);
		 
		 ApiResponce apiResponce=new ApiResponce(true, "Add to wishlist");
		return new ResponseEntity<>(apiResponce,HttpStatus.CREATED);
		
	}
	
	
	// get all product from wishlist

	@GetMapping("/{token}")
	public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) {
		authenticaiontokenService.autenticate(token);
		User user=authenticaiontokenService.getUser(token);
		
	List<ProductDto> wishListForUser= wishListService.getWishListForUSer(user);
	
	return new ResponseEntity<>(wishListForUser,HttpStatus.OK);
	}
	
	
}
