package pos.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

public class HttpUtility {
	public static HttpPost buildPOST(HttpServletRequest httpRequest, String path) {
		HttpPost httpPost = new HttpPost(path);
		Enumeration<String> en = httpRequest.getHeaderNames();
		while (en.hasMoreElements()) {
			String head = en.nextElement();
			httpPost.setHeader(head, httpRequest.getHeader(head));
		}
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		httpPost.removeHeaders("Content-Length");
		return httpPost;
	}

	public static HttpGet buildGET(HttpServletRequest httpRequest, String path) {
		HttpGet httpGet = new HttpGet(path);
		Enumeration<String> en = httpRequest.getHeaderNames();
		while (en.hasMoreElements()) {
			String head = en.nextElement();
			httpGet.setHeader(head, httpRequest.getHeader(head));
		}
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-type", "application/json");
		httpGet.removeHeaders("Content-Length");
		return httpGet;
	}

	public static HttpDelete buildDELETE(HttpServletRequest httpRequest, String path) {
		HttpDelete httpGet = new HttpDelete(path);
		Enumeration<String> en = httpRequest.getHeaderNames();
		while (en.hasMoreElements()) {
			String head = en.nextElement();
			httpGet.setHeader(head, httpRequest.getHeader(head));
		}
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-type", "application/json");
		httpGet.removeHeaders("Content-Length");
		return httpGet;
	}
}
