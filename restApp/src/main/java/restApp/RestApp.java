package restApp;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import java.util.ArrayList;
import org.bson.Document;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/restApp")
public class RestApp {
	Main main = new Main();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMsg() {
		ArrayList<Document>docs = main.getData();
		return Response.status(200).entity(docs).build();
	}
	
	@POST
	@Path("/{key}/{value}")
	public String insertData(@PathParam("key")String key , @PathParam("value")String value) {
		main.insertData(key, value);
		return "Your Data is inserted";
	}
	
	@PUT
	@Path("/{key}/{value}/{newKey}/{newValue}")
	public String updateData(@PathParam("key")String key ,@PathParam("value")String value,@PathParam("newKey")String newKey,@PathParam("newValue")String newValue ) {
		main.updateData(key, value, newKey, newValue);
		return "Your Data is updated";
	}
	
	@DELETE
	@Path("/{key}/{value}")
	public String deleteData(@PathParam("key")String key , @PathParam("value")String value) {
		main.deleteData(key, value);
		return "Your Data is Deleted";
	}
	
	
}
