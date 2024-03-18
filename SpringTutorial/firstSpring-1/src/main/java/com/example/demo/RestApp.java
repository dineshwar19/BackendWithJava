package com.example.demo;

import java.util.ArrayList;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restApp")
public class RestApp {
	Main main = new Main();
	@GetMapping
	public ResponseEntity<ArrayList<Document>> getMsg() {
		ArrayList<Document>docs = main.getData();
		return new ResponseEntity<>(docs ,HttpStatus.OK );
	}
	
	@PostMapping("/postData")
	public String insertData(@RequestParam String key , @RequestParam String value) {
		main.insertData(key, value);
		return "Your Data is inserted";
	}
	

	@PutMapping("/putData")
	public String updateData(@RequestParam String key ,@RequestParam String value,@RequestParam String newKey,@RequestParam String newValue ) {
		main.updateData(key, value, newKey, newValue);
		return "Your Data is updated";
	}
	

	@DeleteMapping("/deleteData")
	public String deleteData(@RequestParam String key , @RequestParam String value) {
		main.deleteData(key, value);
		return "Your Data is Deleted";
	}
}
