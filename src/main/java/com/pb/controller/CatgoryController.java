
package com.pb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.pb.common.ApiResponce;
import com.pb.entity.Catagory;
import com.pb.service.CatagoryService;

@RestController("/catgory")
public class CatgoryController {
	@Autowired
	CatagoryService catgoryService;
	

	@PostMapping("/create")
	public ResponseEntity<ApiResponce> CreateCatagory(@RequestBody Catagory catagory) {
		catgoryService.createCatagory(catagory);
		return new ResponseEntity<ApiResponce>(new ApiResponce(true,"new category created"),HttpStatus.OK);
	}

	@GetMapping("/list")
	public List<Catagory> listCatagory() {
		return catgoryService.listCatagory();
		
	}
	

	@PostMapping(value = "/update/{id}")
	public ResponseEntity<ApiResponce> updateCategogy(@PathVariable("id") int id,@RequestBody Catagory category) {
	if(!catgoryService.findById(id)) {
		return new ResponseEntity<ApiResponce>(new ApiResponce(false," category id not found"),HttpStatus.NOT_FOUND);
		
	}
		catgoryService.editCategory(id, category);
		return new ResponseEntity<ApiResponce>(new ApiResponce(true," category updated"),HttpStatus.OK);
	}

}
