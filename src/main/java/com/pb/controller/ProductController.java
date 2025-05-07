package com.pb.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pb.common.ApiResponce;
import com.pb.dto.ProductDto;
import com.pb.entity.Catagory;
import com.pb.repo.CatagoryRepo;
import com.pb.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
    
	@Autowired
	CatagoryRepo categoryRepo;
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponce> createProduct(@RequestBody ProductDto productDto){
		Optional<Catagory> optionalCategory=categoryRepo.findById(productDto.getCategoryId());
		if(!optionalCategory.isPresent()) {
			return new ResponseEntity<ApiResponce>(new ApiResponce(false, "Not found"),HttpStatus.NOT_FOUND);
		}
		
		productService.createProduct(productDto,optionalCategory.get());
		return new ResponseEntity<ApiResponce>(new ApiResponce(true, "product created"),HttpStatus.OK);
		
	}
	
	@GetMapping("/getallproducts")
	public ResponseEntity<List<ProductDto>> getProduct(){
		
		List<ProductDto> products=productService.getAllProduct();
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	@GetMapping("/getallproducts/getid/{id}")
	public ResponseEntity<List<ProductDto>> getById(@PathVariable Integer id){
		
		List<ProductDto> products=(List<ProductDto>) productService.getById(id);
		return new ResponseEntity<>(products,HttpStatus.OK);
       }
	
	@GetMapping("/getallproducts/{categoryId}")
	public ResponseEntity<List<ProductDto>> getByCategoryId(@PathVariable Integer categoryId){
			
			List<ProductDto> products=productService.getByCategoryId(categoryId);
			return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	// edit product
	@PostMapping("/update/{id}")
	public ResponseEntity<ApiResponce> updateProduct(@PathVariable(value = "id") Integer id,@RequestBody ProductDto productDto) throws Exception{
		Optional<Catagory> optionalCategory=categoryRepo.findById(productDto.getCategoryId());
		if(!optionalCategory.isPresent()) {
			return new ResponseEntity<ApiResponce>(new ApiResponce(false, "Not found"),HttpStatus.NOT_FOUND);
		}
		
		productService.updateProduct(productDto,id);
		return new ResponseEntity<ApiResponce>(new ApiResponce(true, "product created"),HttpStatus.OK);
		
	}
	
	
}
