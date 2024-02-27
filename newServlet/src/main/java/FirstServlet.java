import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017"); 
		MongoDatabase db = mongo.getDatabase("firstJavaDB");
		MongoCollection<Document> collection = db.getCollection("myCollection");
		ArrayList<Document>docs = new ArrayList<>();
		for(Document document : collection.find()) {
			docs.add(document);
		}
		PrintWriter pw = res.getWriter();

		pw.println(docs);

		pw.close();

	}
	
	public void doDelete(HttpServletRequest req , HttpServletResponse res)throws ServletException , IOException {
		res.setContentType("text/html");
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017"); 
		MongoDatabase db = mongo.getDatabase("firstJavaDB");
		MongoCollection<Document> collection = db.getCollection("myCollection");
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		collection.deleteOne(Filters.eq(key , value));
		PrintWriter pw = res.getWriter();
		pw.println("Your Data is Deleted!!");
		pw.close();
	}
	
	public void doPost(HttpServletRequest req , HttpServletResponse res )throws ServletException , IOException {
		res.setContentType("text/html");
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase db = mongo.getDatabase("firstJavaDB");
		MongoCollection<Document> collection = db.getCollection("myCollection");
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		Document doc = new Document(key , value);
		collection.insertOne(doc);
		PrintWriter pw = res.getWriter();
		pw.println("Your Data is inserted!!");
		pw.close();
	}
	
	public void doUpdate(HttpServletRequest req , HttpServletResponse res)throws ServletException , IOException{
		res.setContentType("text/html");
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase db = mongo.getDatabase("firstJavaDB");
		MongoCollection<Document> collection = db.getCollection("myCollection");
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		String newKey = req.getParameter("newKey"  );
		String newValue = req.getParameter("newValue");
		collection.updateOne(Filters.eq(key , value) , Updates.set(newKey, newValue) );
		
		PrintWriter pw = res.getWriter();
		pw.println("Your Data is Updated!!");
		pw.close();
	}
}
