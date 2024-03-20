package com.example.springBootPostgres;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class DataController {
	
	@Autowired
	DataRepositary dataRepo;
	
	@GetMapping("/getData")
	public Iterable<DataEntity> getData() {
		return dataRepo.findAll();
	}
	
	@GetMapping("/getName")	
	public Object getName(@RequestParam("name") String name) {
		return dataRepo.findById(name);
	}
	
	@GetMapping("/getAge")
	public List<DataEntity> getAge(@RequestParam("age") int age){
		return dataRepo.findByAge(age);
	}
	
	@GetMapping("/getByNameAndPhone")
	public List<DataEntity> getNameAndPhone(@RequestBody DataEntity dataEntity){
		return dataRepo.findByNameAndPhone(dataEntity.getName(), dataEntity.getPhone());
	}
	
	@PostMapping("/postData")
	public String postData(@RequestBody DataEntity dataEntity) {
		dataEntity.setName(dataEntity.getName());
		dataEntity.setAge(dataEntity.getAge());
		dataEntity.setPhone(dataEntity.getPhone());
		dataEntity.setYear(dataEntity.getYear());
		dataEntity.setEmail(dataEntity.getEmail());
		dataRepo.save(dataEntity);
		return "your data is inserted";
	}
	@PutMapping("/putData")
	public String putData(@RequestBody DataEntity dataEntity) {
	    DataEntity data = dataRepo.findById(dataEntity.getName())
	                              .orElseThrow(() -> new RuntimeException("Document is not found"));
	    
	    if (dataEntity.getNewAge() != 0) {
	        data.setAge(dataEntity.getNewAge());
	    }
	    
	    dataRepo.save(data);
	    return "Your data is updated";
	}

	
	
	@DeleteMapping("/deleteData")
	public String deleteData(@RequestParam("name") String name) {
		dataRepo.deleteById(name);
		return "Your data is deleted";
		
	}
	
}
