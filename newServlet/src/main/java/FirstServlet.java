import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.bson.Document;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {
	Main main = new Main(); 
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		ArrayList<Document>docs = main.getData();
		PrintWriter pw = res.getWriter();
		pw.println(docs);
		pw.close();
	}
	
	public void doDelete(HttpServletRequest req , HttpServletResponse res)throws ServletException , IOException {
		res.setContentType("text/html");
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		main.deleteData(key, value);
		PrintWriter pw = res.getWriter();
		pw.println("Your Data is Deleted!!");
		pw.close();
	}
	
	public void doPost(HttpServletRequest req , HttpServletResponse res )throws ServletException , IOException {
		res.setContentType("text/html");
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		main.insertData(key, value);
		PrintWriter pw = res.getWriter();
		pw.println("Your Data is inserted!!");
		pw.close();
	}
	
	public void doUpdate(HttpServletRequest req , HttpServletResponse res)throws ServletException , IOException{
		res.setContentType("text/html");
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		String newKey = req.getParameter("newKey"  );
		String newValue = req.getParameter("newValue");
		main.updateData(key, value, newKey, newValue);
		PrintWriter pw = res.getWriter();
		pw.println("Your Data is Updated!!");
		pw.close();
	}
}
