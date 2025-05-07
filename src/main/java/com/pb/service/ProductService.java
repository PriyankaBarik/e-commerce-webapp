package com.pb.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pb.dto.ProductDto;
import com.pb.entity.Catagory;
import com.pb.entity.Product;
import com.pb.exception.ProductNotFoundException;

import com.pb.repo.ProductRepo;

@Service
public class ProductService {

	@Autowired
	ProductRepo productRepo;

	public void createProduct(ProductDto productDto, Catagory catagory) {
		Product product = new Product();
		product.setProductName(productDto.getProductName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setImageUrl(productDto.getImageUrl());
		product.setCategory(catagory);
		productRepo.save(product);
	}

	public ProductDto getProductDto(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setProductName(product.getProductName());
		productDto.setDescription(product.getDescription());
		productDto.setPrice(product.getPrice());
		productDto.setImageUrl(product.getImageUrl());
		productDto.setCategoryId(product.getCategory().getId());
		productDto.setId(product.getId());
		return productDto;
	}

	public List<ProductDto> getAllProduct() {

		List<Product> products = productRepo.findAll();
		List<ProductDto> productDtos = new ArrayList<>();
		for(Product product : products) {
			productDtos.add(getProductDto(product));
		}
		return productDtos;
	}

	public void updateProduct(ProductDto productDto, Integer id) throws Exception {
		Optional<Product> optionalProduct=productRepo.findById(id);
		if(!optionalProduct.isPresent()) {
			throw new Exception("Product is not present");
			
		}
		Product products=optionalProduct.get();
		products.setProductName(productDto.getProductName());
		products.setDescription(productDto.getDescription());
		products.setPrice(productDto.getPrice());
		products.setImageUrl(productDto.getImageUrl());
		
		productRepo.save(products);
	}

	public Product findById(Integer productId) {
	Optional<Product> product= productRepo.findById(productId);
	if(Objects.isNull(product)) {
		throw new ProductNotFoundException("Product is not found");
	}
	
	return product.get();
		
	}
	


	public List<ProductDto> getByCategoryId(Integer categoryId) {
	    List<Product> products = productRepo.findByCategoryId(categoryId);
	    List<ProductDto> productDtos = new ArrayList<>();
	    
	    for (Product product : products) {
	        productDtos.add(getProductDto(product));
	    }
	    
	    return productDtos;
	}

	public List<ProductDto> getById(Integer id) {
	    Optional<Product> productOptional = productRepo.findById(id);
	    List<ProductDto> productDtos = new ArrayList<>(); 
	    if (productOptional.isPresent()) {
	        Product product = productOptional.get();
	        productDtos.add(getProductDto(product));
	    }

	    return productDtos;
	}
	
	
}