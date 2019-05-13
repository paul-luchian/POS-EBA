package pos.rs.impl;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import pos.entities.User;
import pos.rs.api.RegisterServices;


public class RegisterServicesImpl implements RegisterServices {

	@Override
	public String registerUserRequest(HttpServletRequest httpRequest, User user) {

		String postUrl = "http://localhost:8080/POS-EBA-RSDB/server1/user";// put in your url

//		Client client = ClientBuilder.newClient();
//		WebTarget target = client.target(postUrl);
//		String response = target.request(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				.post(Entity.json(user), String.class);
//		
//		return response;
		
		Client client = ClientBuilder.newClient();

		WebTarget resource = client.target(postUrl);;
		Invocation.Builder builder = resource.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Response resp = builder.post(Entity.json(user));
		int status = resp.getStatus();
		Object x=resp.getEntity();
		String p=resp.toString();
		JSONObject body = new JSONObject(resp.getEntity());
		System.out.println(body);
		System.out.println("");
		System.out.println("Status code="+status);
		return resp.toString();
	}
}
