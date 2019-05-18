package pos.rs.impl;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import pos.dtos.UserDto;
import pos.rs.api.LoginServices;

@Stateless
public class LoginServicesImpl implements LoginServices {

	@Override
	public void loginRequest(HttpServletRequest httpRequest, UserDto user) {

		System.out.println("\n\n loginRequest");

		// URIBuilder builder = new URIBuilder();
		// builder.setScheme("http").setHost("localhost:8080").setPath("/POS-EBA-RSDB/server1/tokens");
		// .setParameter("userName", user.getUsername())
		// .setParameter("password", user.getPassword());

		// URI uri;
		// try {
		// uri = builder.build();

		// JSONObject param = new JSONObject();
		// param.put("userName", user.getUsername());
		// param.put("password", user.getPassword());

		// StringEntity entity = new StringEntity(param.toString());

		//
		// HttpGet httpget = new HttpGet(uri);
		//
		// System.out.println("Requests for "+httpget.getURI());
		//
		// CloseableHttpClient httpclient = HttpClients.createDefault();
		// System.out.println("HERE");
		// CloseableHttpResponse response = httpclient.execute(httpget);
		//
		//
		// System.out.println("Response : "+ response.toString());

		// } catch (Exception e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		String url = "http://localhost:8080/POS-EBA-RSDB/server1/tokens";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		// System.out.println("Executing request " + httpget.getRequestLine());

		CloseableHttpResponse response;
		try {
			response = httpclient.execute(httpget);
			System.out.println("Response :  " + response.toString());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
