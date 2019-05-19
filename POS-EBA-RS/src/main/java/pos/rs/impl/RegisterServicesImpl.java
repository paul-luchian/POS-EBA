package pos.rs.impl;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import pos.dtos.RegisterDto;
import pos.rs.api.RegisterServices;

@Stateless
public class RegisterServicesImpl implements RegisterServices {

	@Override
	public String registerUserRequest(HttpServletRequest httpRequest, RegisterDto user) {

		/*
		 * Obs. Adaugarea unui utilizator se realizeaza daca: 1. Nu exista in baza de
		 * date 2. Campurile contin ce trebuie (Verificarile sunt facut in js) 3. Nu are
		 * rol in json. Nu isi pune el singur eventual in request sau alte chestii
		 */

		if (user.getRole() == null) {
			JSONObject json = new JSONObject();
			json.put("userName", user.getUsername());
			json.put("password", user.getPassword());
			json.put("role", "USER");

			String url = "http://localhost:8080/POS-EBA-RSDB/server1/user";// put in your url
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
					String url_profile = "http://localhost:8080/POS-EBA-RSDB/server1/profile";
					JSONObject json_profile = new JSONObject();
					json_profile.put("userName", user.getUsername());
					json_profile.put("firstname", user.getFirstname());
					json_profile.put("lastname", user.getLastname());
					json_profile.put("email", user.getEmail());
					json_profile.put("id", id);

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
		}

		return "server error";
	}
}
