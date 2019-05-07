package pos.rs.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import pos.entities.User;
import pos.rs.api.RegisterServices;


public class RegisterServicesImpl implements RegisterServices {

	@Override
	public String registerUserRequest(HttpServletRequest httpRequest, User user) {

		String postUrl = "http://localhost:8080/POS-EBA-RSDB/server1/user";// put in your url

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(postUrl);
		String response = target.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.json(user), String.class);
		return response;
	}
}
