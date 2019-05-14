//package pos.rs.impl;
//
//import java.io.IOException;
//
//import javax.ejb.Stateless;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import pos.rs.api.SigninService;
//
//@Stateless
//public class SigninServiceImpl implements SigninService{
//	
//	@Override
//	public void signinRequest(HttpServletRequest request, HttpServletResponse response)
//	{
//		 RequestDispatcher rd;
//		 rd = request.getRequestDispatcher("/POS-EBA/POS-EBA-PF/WebContent/login.jsp");
//		 try {
//			rd.forward(request, response);
//		} catch (ServletException e) {
//			
//			e.printStackTrace();
//		} catch (IOException e) {
//		
//			e.printStackTrace();
//		}
//		System.out.println("Got the service!");
//	}
//}
