package com.example.springBootPostgres;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class DataController {
	
	@Autowired
	DataRepositary dataRepo;
	
	@GetMapping("/getData")
	public List<DataEntity> getData() {
		return dataRepo.findAll();
	}
	
	@PostMapping("/postData")
	public String postData(@RequestParam("name") String name , @RequestParam("age") int age) {
		DataEntity data = new DataEntity();
		data.setName(name);
		data.setAge(age);
		dataRepo.save(data);
		return "your data is inserted";
	}
	@PutMapping("/putData")
	public String putData(@RequestParam("name") String name, @RequestParam("newAge") int newAge) {
		DataEntity data = dataRepo.findById(name).orElseThrow(() -> new RuntimeException("Document is not found"));
		data.setAge(newAge);
		dataRepo.save(data);
		return "Your data is updated";
	}
	
	
	@DeleteMapping("/deleteData")
	public String deleteData(@RequestParam("name") String name) {
		dataRepo.deleteById(name);
		return "Your data is deleted";
	}
	
}
