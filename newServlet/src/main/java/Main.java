
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import org.bson.Document;

public class Main {

	public ArrayList<Document> getData() {
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017"); 
		MongoDatabase db = mongo.getDatabase("firstJavaDB");
		MongoCollection<Document> collection = db.getCollection("myCollection");
		ArrayList<Document>docs = new ArrayList<>();
		for(Document document : collection.find()) {
			docs.add(document);
		}
		return docs;
	}
	public void insertData(String key , String value) {
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017"); 
		MongoDatabase db = mongo.getDatabase("firstJavaDB");
		MongoCollection<Document> collection = db.getCollection("myCollection");
		Document doc = new Document(key , value);
		collection.insertOne(doc);
	}
	
	public void updateData(String key ,String value ,  String newKey , String newValue) {
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017"); 
		MongoDatabase db = mongo.getDatabase("firstJavaDB");
		MongoCollection<Document> collection = db.getCollection("myCollection");
		collection.updateOne(Filters.eq(key , value) , Updates.set(newKey, value) );
	}
	
	public void deleteData(String key , String value) {
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017"); 
		MongoDatabase db = mongo.getDatabase("firstJavaDB");
		MongoCollection<Document> collection = db.getCollection("myCollection");
		collection.deleteOne(Filters.eq(key , value));
	}
	
}
