package com.pb.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pb.entity.Cart;
import com.pb.entity.Product;
import com.pb.entity.User;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {

	
	List<Cart> findAllByUserOrderByCreatedDateDesc(User user);

	Optional<Cart> findByUserAndProduct(User user, Product product);


}
