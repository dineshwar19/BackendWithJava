package com.example.demo;



import java.util.ArrayList;
import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class Main {
	MongoClient mongo = MongoClients.create("mongodb://localhost:27017"); 
	MongoDatabase db = mongo.getDatabase("firstJavaDB");
	MongoCollection<Document> collection = db.getCollection("myCollection");
	
	public ArrayList<Document> getData() {
		ArrayList<Document>docs = new ArrayList<>();
		for(Document document : collection.find()) {
			docs.add(document);
		}
		return docs;
	}
	public void insertData(String key , String value) {
		Document doc = new Document(key , value);
		collection.insertOne(doc);
	}
	
	public void updateData(String key ,String value ,  String newKey , String newValue) {
		collection.updateOne(Filters.eq(key , value) , Updates.set(newKey, value) );
	}
	
	public void deleteData(String key , String value) {
		collection.deleteOne(Filters.eq(key , value));
	}
	
}
