package pos.rs.impl;

import java.io.IOException;
import java.util.Enumeration;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import pos.dtos.UserDto;
import pos.external.RestServerPaths;
import pos.rs.api.LoginServices;

@Stateless
public class LoginServicesImpl implements LoginServices {

	@Override
	public String loginRequest(HttpServletRequest httpRequest, UserDto user) {
		JSONObject json = new JSONObject();
		if (user != null && (user.getUsername() != null && user.getPassword() != null)) {
			json.put("userName", user.getUsername());
			json.put("password", user.getPassword());
		}

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(RestServerPaths.DB_TOKEN);
		StringEntity entity;
		try {
			entity = new StringEntity(json.toString());
			httpPost.setEntity(entity);

			Enumeration<String> en = httpRequest.getHeaderNames();
			while (en.hasMoreElements()) {
				String head = en.nextElement();
				httpPost.setHeader(head, httpRequest.getHeader(head));
			}
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			httpPost.removeHeaders("Content-Length");
			CloseableHttpResponse response = client.execute(httpPost);
			System.out.println("code" + response.getStatusLine().getStatusCode());
			System.out.println("body" + EntityUtils.toString(response.getEntity()));

			client.close();
			ResponseBuilder r = Response.status(response.getStatusLine().getStatusCode());
			Header[] h = response.getAllHeaders();
			for (int i = 0; i < h.length; i++) {
				r.header(h[i].getName(), h[i].getValue());
			}
			JSONObject jsonObj = new JSONObject(r.build());
			
			return jsonObj.toString();//r.build().toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(400).build().toString();
		}

		/*
		 * System.out.println("\n\n loginRequest"); WebClient client =
		 * PosAccess.buildRestClient(RestServerPaths.DB_TOKEN); Response restResponse =
		 * client.get(); System.out.println(restResponse.readEntity(String.class));
		 */

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

		/*
		 * String url = "http://localhost:8080/POS-EBA-RSDB/server1/tokens";
		 * CloseableHttpClient httpclient = HttpClients.createDefault(); HttpGet httpget
		 * = new HttpGet(url); // System.out.println("Executing request " +
		 * httpget.getRequestLine());
		 * 
		 * CloseableHttpResponse response; try { response = httpclient.execute(httpget);
		 * System.out.println("Response :  " + response.toString()); } catch
		 * (ClientProtocolException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */

	}

}
