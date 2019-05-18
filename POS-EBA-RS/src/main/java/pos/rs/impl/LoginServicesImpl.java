package pos.rs.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import pos.dtos.UserDto;
import pos.rs.api.LoginServices;

public class LoginServicesImpl implements LoginServices{

	@Override
	public void loginRequest(HttpServletRequest httpRequest, UserDto user){
		System.out.println("loginRequest");

		Client client = ClientBuilder.newClient();
		
		requestUserByUsernameAndPassword(client, user.getUsername(), user.getPassword());

		
		client.close();
	}

	private void requestUserByUsernameAndPassword(Client client, String string, String string2) {
		String getUrl = "http://localhost:8080/POS-EBA-RSDB/server1/user";

		WebTarget target = client.target(getUrl);
		
		String s = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println( "response"+s );
		

	}


}
