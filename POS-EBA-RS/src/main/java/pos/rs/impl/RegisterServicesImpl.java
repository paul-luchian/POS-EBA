package pos.rs.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Entity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.sun.tools.javac.util.ArrayUtils;

import pos.dtos.UserDto;
import pos.entities.User;
import pos.rs.api.RegisterServices;

@Stateless
public class RegisterServicesImpl implements RegisterServices {

	@Override
	public String registerUserRequest(HttpServletRequest httpRequest, UserDto user) {
		
		/*Obs. Adaugarea unui utilizator se realizeaza daca:
		 * 1. Nu exista in baza de date
		 * 2. Campurile contin ce trebuie (Verificarile sunt facut in js)
		 * 3. Nu are rol in json. Nu isi pune el singur eventual in request sau alte chestii*/
		
		if(user.getRole() == null)
		{
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
			    System.out.println("code"+response.getStatusLine().getStatusCode());
			    System.out.println("body"+EntityUtils.toString(response.getEntity()));
			    
			    client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	    
		return "System Error!";

	}
//	public static Map<String, String[]> getQueryParameters(HttpServletRequest request) {
//	    Map<String, String[]> queryParameters = new HashMap<>();
//	    String queryString = request.getQueryString();
//
//	    if (StringUtils.isEmpty(queryString)) {
//	        return queryParameters;
//	    }
//
//	    String[] parameters = queryString.split("&");
//
//	    for (String parameter : parameters) {
//	        String[] keyValuePair = parameter.split("=");
//	        String[] values = queryParameters.get(keyValuePair[0]);
//	        values =  keyValuePair.length == 1 ? "" : keyValuePair[1]; //length is one if no value is available.
//	        queryParameters.put(keyValuePair[0], values);
//	    }
//	    return queryParameters;
//	}
}
