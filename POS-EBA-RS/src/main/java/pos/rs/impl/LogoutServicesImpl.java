package pos.rs.impl;

import java.io.IOException;
import java.util.Enumeration;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import pos.rs.api.LogoutServices;

@Stateless
public class LogoutServicesImpl implements LogoutServices {

	@Override
	public String logoutRequest(HttpServletRequest httpRequest) {

		String header = httpRequest.getHeader("Authorization");
		System.out.println("header=" + header);

		HttpResponse response;

		CloseableHttpClient client = HttpClients.createDefault();
		String url = "http://localhost:8080/POS-EBA-RSDB/server1/tokens";
		HttpDelete request = new HttpDelete(url);

		Enumeration<String> en = httpRequest.getHeaderNames();
		while (en.hasMoreElements()) {
			String head = en.nextElement();
			request.setHeader(head, httpRequest.getHeader(head));
		}
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");
		request.removeHeaders("Content-Length");
		try {
			response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
		
				return "ok";
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
