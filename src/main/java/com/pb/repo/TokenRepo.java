package com.pb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pb.entity.AuthenticationToken;
import com.pb.entity.User;


@Repository
public interface TokenRepo extends JpaRepository<AuthenticationToken, Integer> {
  AuthenticationToken findByUser(User user);

AuthenticationToken findByToken(String token);
}
