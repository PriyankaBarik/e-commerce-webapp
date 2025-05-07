package com.pb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pb.entity.Catagory;
import com.pb.repo.CatagoryRepo;

@Service
public class CatagoryService {

	@Autowired
	CatagoryRepo catagoryRepo;

	public void createCatagory(Catagory catagory) {
		catagoryRepo.save(catagory);
	}

	public List<Catagory> listCatagory() {
		return catagoryRepo.findAll();
	}

	public void editCategory(int id, Catagory updatecatgory) {
		Catagory category = catagoryRepo.findById(id).get();
		category.setCatagoryName(updatecatgory.getCatagoryName());
		category.setDescription(updatecatgory.getDescription());
		category.setImageUrl(updatecatgory.getImageUrl());
		catagoryRepo.save(category);
	}

	public boolean findById(int id) {
		
		return catagoryRepo.findById(id).isPresent();
	}
}
