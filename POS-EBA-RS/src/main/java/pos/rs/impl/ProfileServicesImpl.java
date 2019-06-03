package pos.rs.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import pos.repositories.ProfileRepositoryImpl;
import pos.repositories.UserRepositoryImpl;
import pos.rs.api.ProfileServices;
import pos.util.BCrypt;

@Stateless
public class ProfileServicesImpl implements ProfileServices {

	@EJB(beanName = "UserRepository")
	private UserRepositoryImpl userRepo;

	@EJB(beanName = "ProfileRepository")
	private ProfileRepositoryImpl profileRepo;

	public String[] decryptToken(String jwtToken) {
		Decoder decoder = Base64.getUrlDecoder();
		String[] parts = jwtToken.split("\\."); // split out the "parts" (header, payload and signature)

		String headerJson = new String(decoder.decode(parts[0]));
		String payloadJson = new String(decoder.decode(parts[1]));
		String signatureJson = new String(decoder.decode(parts[2]));

		// System.out.println("header" + headerJson);
		System.out.println("payload" + payloadJson);
		// System.out.println("signature" + signatureJson);
		String ret[] = { headerJson, payloadJson, signatureJson };
		return ret;
	}

	@Override
	public String getprofileRequest(HttpServletRequest httpRequest) {

		String token = httpRequest.getHeader("Authorization");

		String[] result = decryptToken(token.substring(7));
		System.out.println("token = " + result[1]);
		JSONObject token_json = new JSONObject(result[1]);

		HttpResponse profile_response;

		CloseableHttpClient client_profile = HttpClients.createDefault();
		String url_profile = "http://localhost:8080/POS-EBA-RSDB/server1/profile/" + token_json.get("userid");
		HttpGet request_profile = new HttpGet(url_profile);

		try {
			profile_response = client_profile.execute(request_profile);

			BufferedReader rd = new BufferedReader(new InputStreamReader(profile_response.getEntity().getContent()));

			String line = "";

			while ((line = rd.readLine()) != null) {

				System.out.println("line=" + line);

				JSONObject profile_json = new JSONObject(line);

				JSONObject response = new JSONObject();
				response.put("firstname", profile_json.get("firstname"));
				response.put("lastname", profile_json.get("lastname"));
				response.put("email", profile_json.get("email"));
				response.put("username", profile_json.get("username"));
				return response.toString();
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String getRequestBody(final HttpServletRequest request) {
		final StringBuilder builder = new StringBuilder();
		try (final BufferedReader reader = request.getReader()) {

			if (reader == null) {
				System.out.println("Request body could not be read because it's empty.");
				return null;
			}
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			return builder.toString();
		} catch (final Exception e) {
			System.out.println("Could not obtain the same request body from the http request");
			return null;
		}
	}

	@Override
	public String updatePasswordRequest(HttpServletRequest httpRequest) {

		String token = httpRequest.getHeader("Authorization");
		String[] result = decryptToken(token.substring(7));
		JSONObject token_json = new JSONObject(result[1]);
		System.out.println("token = " + token_json);

		String tmp = getRequestBody(httpRequest);
		System.out.println("pass" + tmp);

		JSONObject json = new JSONObject(tmp);
		String password = json.get("password").toString();
		String newPassword = json.get("newpassword").toString();

		CloseableHttpClient client_profile = HttpClients.createDefault();
		String url = "http://localhost:8080/POS-EBA-RSDB/server1/user/" + token_json.get("userid");
		HttpGet request_user = new HttpGet(url);

		HttpResponse user_response;
		try {
			user_response = client_profile.execute(request_user);

			BufferedReader rd = new BufferedReader(new InputStreamReader(user_response.getEntity().getContent()));

			String line = "";

			while ((line = rd.readLine()) != null) {

				System.out.println(" request from user =" + line);
				JSONObject _json = new JSONObject(line);
				System.out.println("response password " + _json.get("password"));

				if (pos.util.BCrypt.checkpw(password, _json.get("password").toString())) {

					// old password ok, update with the new one

					CloseableHttpClient client = HttpClients.createDefault();
					String url_update = "http://localhost:8080/POS-EBA-RSDB/server1/user/" + _json.get("id");
					HttpPost httpPost = new HttpPost(url_update);

					JSONObject jsonPost = new JSONObject();
					String hashed = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
					jsonPost.put("password", hashed);
					StringEntity entity = new StringEntity(jsonPost.toString());
					httpPost.setEntity(entity);
					httpPost.setHeader("Accept", "application/json");
					httpPost.setHeader("Content-type", "application/json");
					CloseableHttpResponse response = client.execute(httpPost);
					String body = EntityUtils.toString(response.getEntity());
					client.close();
					System.out.println(" response update  body" + body);

					JSONObject jsonUserId = new JSONObject(body);
					String id = jsonUserId.get("id").toString();
					System.out.println("User id=" + id);
					if (id != null) {
						return "ok";
					} else {
						return "Error on update";
					}
				} else {
					System.out.println("Wrong old password ! Please retry ! ");
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public String updateProfileRequest(HttpServletRequest httpRequest) {

		System.out.println("updateProfileRequest");


		String token = httpRequest.getHeader("Authorization");
		String[] result = decryptToken(token.substring(7));
		JSONObject token_json = new JSONObject(result[1]);
		System.out.println("token = " + token_json);

		String tmp = getRequestBody(httpRequest);
		System.out.println("first name last name " + tmp);

		JSONObject json = new JSONObject(tmp);
		String first = json.get("firstname").toString();
		String last = json.get("lastname").toString();
		System.out.println("first="+first);
		System.out.println("last="+last);
		
		
		HttpResponse user_response;
		try {
			
			CloseableHttpClient client = HttpClients.createDefault();
			String url = "http://localhost:8080/POS-EBA-RSDB/server1/profile/" + token_json.get("userid");
			HttpPost httpPost = new HttpPost(url);

			JSONObject jsonPost = new JSONObject();
			jsonPost.put("firstname", first);
			jsonPost.put("lastname", last);
			jsonPost.put("id", token_json.get("userid"));
			
			System.out.println(jsonPost.toString());
			StringEntity entity;

			entity = new StringEntity(jsonPost.toString());

			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			CloseableHttpResponse response = client.execute(httpPost);
			String body = EntityUtils.toString(response.getEntity());
			client.close();
			System.out.println(" response update  body" + body);

			JSONObject jsonUserId = new JSONObject(body);
			String id = jsonUserId.get("id").toString();
			System.out.println("Profile id=" + id);
			if (id != null) {
				return "ok";
			} else {
				return "Error on update";
			}
		} catch ( ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
