package com.example.springBootMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface DataRepository extends MongoRepository<MongoData , String> {
	
}
