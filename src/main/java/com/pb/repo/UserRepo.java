package com.pb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pb.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	User findByEmail(String email);
}
