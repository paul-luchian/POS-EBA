package pos.rs.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import pos.business.domains.UserType;
import pos.dtos.RegisterDto;
import pos.dtos.UserDto;
import pos.repositories.UserRepositoryImpl;
import pos.rs.api.AdminServices;

@Stateless
public class AdminServicesImpl implements AdminServices {

	@EJB(beanName = "UserRepository")
	private UserRepositoryImpl userRepo;

	@Override
	public String updateUserRequest(HttpServletRequest httpRequest, RegisterDto user) {

		JSONObject json = new JSONObject();
		long user_id = user.getId();
		System.out.println("user & profile id = " + user_id);
		json.put("userName", user.getUsername());
		json.put("role", user.getRole());
		System.out.println(json.toString());

		String url = "http://localhost:8080/POS-EBA-RSDB/server1/user/" + user_id;// put in your url
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		StringEntity entity;
		try {
			entity = new StringEntity(json.toString());
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			CloseableHttpResponse response = client.execute(httpPost);
			String body = EntityUtils.toString(response.getEntity());
			client.close();
			System.out.println("body" + body);

			JSONObject jsonObj = new JSONObject(body);
			String id = jsonObj.get("id").toString();
			System.out.println("User id=" + id);

			if (id != null) {
				CloseableHttpClient client_profile = HttpClients.createDefault();
				String url_profile = "http://localhost:8080/POS-EBA-RSDB/server1/profile/" + user_id;
				JSONObject json_profile = new JSONObject();
				json_profile.put("firstname", user.getFirstname());
				json_profile.put("lastname", user.getLastname());
				json_profile.put("email", user.getEmail());
				json_profile.put("id", id);

				System.out.println(json_profile.toString());

				entity = new StringEntity(json_profile.toString());
				HttpPost httpPost_profile = new HttpPost(url_profile);
				httpPost_profile.setEntity(entity);
				httpPost_profile.setHeader("Accept", "application/json");
				httpPost_profile.setHeader("Content-type", "application/json");
				CloseableHttpResponse response_profile = client_profile.execute(httpPost_profile);
				int status_code = response_profile.getStatusLine().getStatusCode();
				String body_profile = EntityUtils.toString(response_profile.getEntity());

				client_profile.close();
				System.out.println("Status code " + status_code);
				if (status_code == 200)
					return "ok";
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "server error";
	}

	@Override
	public String getUsersRequest(HttpServletRequest httpRequest, String username, UserType role, String password) {
		String url = "http://localhost:8080/POS-EBA-RSDB/server1/user";

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		// add request header
		// request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response;
		HttpResponse profile_response;
		try {
			response = client.execute(request);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			CloseableHttpClient client_profile = HttpClients.createDefault();
			String url_profile = "http://localhost:8080/POS-EBA-RSDB/server1/profile";
			HttpGet request_profile = new HttpGet(url_profile);
			profile_response = client_profile.execute(request_profile);

			BufferedReader rdp = new BufferedReader(new InputStreamReader(profile_response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			String linep = "";

			while ((line = rd.readLine()) != null && (linep = rdp.readLine()) != null) {

				System.out.println("linep=" + linep);

				JSONArray userArray = new JSONArray(line);
				JSONArray profileArray = new JSONArray(linep);

				if (userArray.length() == profileArray.length()) {
					for (int i = 0; i < userArray.length(); i++) {
						JSONObject user_json = userArray.getJSONObject(i);
						JSONObject profile_json = profileArray.getJSONObject(i);
						if (profile_json.get("id") == user_json.get("id")) {
							profileArray.getJSONObject(i).put("role", user_json.get("role"));
						}
					}
				}
				System.out.println(profileArray.toString());
				result.append(profileArray.toString());
			}

			return result.toString();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "error";
	}

	@Override
	public String deleteUserRequest(HttpServletRequest httpRequest, long userId) {
		HttpResponse response;
		HttpResponse profile_response;

		CloseableHttpClient client_profile = HttpClients.createDefault();
		String url_profile = "http://localhost:8080/POS-EBA-RSDB/server1/profile/" + userId;
		HttpDelete request_profile = new HttpDelete(url_profile);

		try {
			profile_response = client_profile.execute(request_profile);

			if (profile_response.getStatusLine().getStatusCode() == 204) {
				String url = "http://localhost:8080/POS-EBA-RSDB/server1/user/" + userId;

				HttpClient client = HttpClientBuilder.create().build();
				HttpDelete request = new HttpDelete(url);

				response = client.execute(request);
				System.out.println("delete code="+response.getStatusLine().getStatusCode());
				if (response.getStatusLine().getStatusCode() == 204) {

					return "ok";

				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "error";

	}
}
