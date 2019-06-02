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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import pos.business.domains.UserType;
import pos.dtos.UserDto;
import pos.repositories.UserRepositoryImpl;
import pos.rs.api.AdminServices;

@Stateless
public class AdminServicesImpl implements AdminServices {

	@EJB(beanName = "UserRepository")
	private UserRepositoryImpl userRepo;

	@Override
	public UserDto getUserByIdRequest(HttpServletRequest httpRequest, long userId) {
		UserDto user = userRepo.selectUserDtoById(userId);
		return userRepo.selectUserDtoById(userId);
	}

	@Override
	public String updateUserRequest(HttpServletRequest httpRequest, UserDto user, long userId) {
		return "error";
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
				// line = line.replace("[", "");
				// line = line.replace("]", "");
				// linep = linep.replace("[", "");
				// linep = linep.replace("]", "");

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
	public void deleteUserRequest(HttpServletRequest httpRequest, long userId) {
		userRepo.deleteUser(userId);

	}

}
