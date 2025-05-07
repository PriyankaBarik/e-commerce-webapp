package com.pb.repo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pb.entity.Catagory;

@Repository
public interface CatagoryRepo extends JpaRepository<Catagory, Integer>{

}
