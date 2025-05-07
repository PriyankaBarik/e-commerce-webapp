package com.pb.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pb.entity.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
	
	 List<Product> findByCategoryId(Integer categoryId);
	 
	
	
	
	


}
