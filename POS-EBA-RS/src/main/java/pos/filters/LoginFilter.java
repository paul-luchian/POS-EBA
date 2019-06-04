/*package pos.filters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import pos.utils.TokenContext;

public class LoginFilter implements Filter {
	private static final String LOGIN = "login";
	private static final String SEPARATOR = "/";
	private static final String TOKEN_CHECK = "http://localhost:8080/POS-EBA-RSDB/server1/tokens/check";

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;
			System.out.println(request.getRequestURI());
			String loginUri = String.join(SEPARATOR, request.getContextPath(), LOGIN);

			TokenContext tCtxt = TokenContext.from(request);
			if (TokenContext.isSetted(tCtxt)) {
				// token is setted -> check token
				if (checkToken(request)) {
					response.sendRedirect(request.getRequestURI());
				} else {
					response.sendRedirect(loginUri);
				}
				// chain.doFilter(req, res);

			} else {
				// token not setted.
				response.sendRedirect(loginUri);

			}

		}

	}

	private boolean checkToken(HttpServletRequest request) {
		boolean check = false;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = buildReq(request);
		try {
			CloseableHttpResponse response = client.execute(httpPost);
			String body = EntityUtils.toString(response.getEntity());
			JSONObject jsonObj = new JSONObject(body);
			check = Boolean.getBoolean(jsonObj.get("check").toString());
		} catch (Exception e) {

		}
		return check;
	}

	private HttpPost buildReq(HttpServletRequest httpRequest) {
		HttpPost httpPost = new HttpPost(TOKEN_CHECK);
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

}
*/