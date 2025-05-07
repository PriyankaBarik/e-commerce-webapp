package com.pb.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pb.entity.User;
import com.pb.entity.WishList;

@Repository
public interface WishListRepo extends JpaRepository<WishList, Integer> {
	List<WishList> findAllUserOrderByCreatedDate(User user);
}
