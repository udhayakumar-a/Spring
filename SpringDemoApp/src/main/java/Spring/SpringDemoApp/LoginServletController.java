package Spring.SpringDemoApp;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServletController extends HttpServlet {
	
	LoginService service = new LoginService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
				request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		System.out.println(name+" "+password);
		boolean isValidUser = Objects.nonNull(name) && password.equals("password");

		if (isValidUser) {
			request.setAttribute("name", name);
			try {
				System.out.println("Getting from DB");
				System.out.println(service.TestData());
				request.setAttribute("result", service.TestData());
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(
					request, response);
		} else {
			request.setAttribute("errorMessage", "Invalid Credentials!!");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
					request, response);
		}
	}

}
