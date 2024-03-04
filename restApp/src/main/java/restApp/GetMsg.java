package restApp;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.Response;
@Path("/hello")
public class GetMsg {
	@GET
	public Response getMsg() {
		return Response.status(200).entity("hello").build();
	}
}
