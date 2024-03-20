package com.example.springBootMongoDB;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DataController {
	@Autowired
	DataRepository dataRepo;
	
	@Autowired
	private MongoTemplate mongoTemp;
	
	
	private String cName = "myCollection";
	@GetMapping("/get")
	public List<Document> getData(){
//		return dataRepo.findAll();
		return mongoTemp.findAll(Document.class, cName);
	}
	@PostMapping("/postData")
	public String postData(@RequestParam("key") String key , @RequestParam("value") String value) {
		try {
//			MongoData mongoData = new MongoData();
//			mongoData.setKey1(value);
//			dataRepo.save(mongoData);
			Document doc = new Document();
			doc.append(key, value);
			mongoTemp.insert(doc, cName );
			return "Your data is inserted";
		}catch(Exception e) {
			return e.getMessage();		}
	}  
	@PutMapping("/putData")
	public String putData(@RequestParam("id") String id , @RequestParam("newValue") String newValue) {
		try {
			MongoData updateData = dataRepo.findById(id).orElseThrow(() -> new RuntimeException("Document not found"));	
			updateData.setKey1(newValue);
			dataRepo.save(updateData);
			return "Your data is updated";
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	@DeleteMapping("/deleteData")
	public String deleteData(@RequestParam("id") String id) {
		try {
			dataRepo.deleteById(id);
			return "Your data is deleted";
		}catch(Exception e) {
			return e.getMessage();
		}
	}
}
