package pos.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pos.utils.TokenContext;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String loginJsp = "/login/login.jsp";
	private static final String indexJsp = "/content/index.html";

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;

			RequestDispatcher dispatcher;
			TokenContext tCtxt = TokenContext.from(request);
			if (TokenContext.isSetted(tCtxt)) {
				dispatcher = getServletContext().getRequestDispatcher(indexJsp);
				dispatcher.forward(request, response);
			} else {
				dispatcher = getServletContext().getRequestDispatcher(loginJsp);
				dispatcher.forward(request, response);
			}
		}
	}

}
