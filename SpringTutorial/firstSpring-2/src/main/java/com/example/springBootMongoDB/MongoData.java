package com.example.springBootMongoDB;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document( collection = "myCollection" )
public class MongoData {
    private String first;
    private String key1; 
    private String stringKey;
    private String newkey;
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getKey1() {
		return key1;
	}
	public void setKey1(String key1) {
		this.key1 = key1;
	}
	public String getStringKey() {
		return stringKey;
	}
	public void setStringKey(String stringKey) {
		this.stringKey = stringKey;
	}
	public String getNewkey() {
		return newkey;
	}
	public void setNewkey(String newkey) {
		this.newkey = newkey;
	}
}
