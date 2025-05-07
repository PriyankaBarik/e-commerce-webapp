package com.pb.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.pb.dto.ProductDto;

import com.pb.entity.User;
import com.pb.entity.WishList;
import com.pb.repo.WishListRepo;

@Service
@Transactional
public class WishListService {

	@Autowired
	WishListRepo wishListRepo;

	@Autowired
	ProductService productService;

	public void createWishList(WishList wishList) {
		wishListRepo.save(wishList);

	}

	public List<ProductDto> getWishListForUSer(User user) {
		final List<WishList> wishList = wishListRepo.findAllUserOrderByCreatedDate(user);

		List<ProductDto> productDto = new ArrayList<>();
		for (WishList wishList1 : wishList) {
			productDto.add(productService.getProductDto(wishList1.getProduct()));
		}

		return productDto;
	}

}
