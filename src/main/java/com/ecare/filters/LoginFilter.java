package com.ecare.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import com.ecare.dao.model.User;
import com.ecare.dao.model.User.Role;
import com.ecare.dao.model.Contract;

@WebFilter("/*")
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		String loginURI = request.getContextPath() + "/login";
		String loginHandlerURI = request.getContextPath() + "/loginhandler";

		boolean loggedIn = (session != null) && (session.getAttribute("user") != null);
		boolean loginRequest = request.getRequestURI().equals(loginURI);
		boolean loginHandlerRequest = request.getRequestURI().equals(loginHandlerURI);

		/*
		 * login
		 */
		if (!loggedIn && (loginRequest || loginHandlerRequest)) {
			chain.doFilter(request, response);
			return;
		} // else response.sendRedirect(loginURI);

		// bredp@gmail.com sergeyu@gmail.com qwer 1111111111

		User sessionUser = null;
		if (loggedIn) {
			sessionUser = (User) session.getAttribute("user");

			if (sessionUser.getRole().equals(Role.EMPLOYEE)) {
				chain.doFilter(request, response);
				return;
			}
			if (sessionUser.getRole() == Role.CLIENT) {
				if (request.getRequestURI().equals(request.getContextPath() + "/edituser")
						|| request.getRequestURI().equals(request.getContextPath() + "/updateuser")
						|| request.getRequestURI().equals(request.getContextPath() + "/changeplan")
						|| request.getRequestURI().equals(request.getContextPath() + "/changeplanhandler")
						|| request.getRequestURI().equals(request.getContextPath() + "/changeoptions")
						|| request.getRequestURI().equals(request.getContextPath() + "/changeoptionshandler")) {
					boolean b = false;

					String stringIdUser = (String) request.getParameter("idUser");
					if (stringIdUser != null) {
						int idUser = Integer.parseInt(stringIdUser);
						if (sessionUser.getId() == idUser)
							b = true;
					}

					String stringIdContract = (String) request.getParameter("idContract");
					if (stringIdContract != null) {
						int idContract = Integer.parseInt(stringIdContract);
						for (Contract contract : sessionUser.getContracts()) {
							if (contract.getId() == idContract)
								b = true;
						}
					}

					if (b == true) {
						chain.doFilter(request, response);
						return;
					}

				}
			}
		}
		response.sendRedirect(loginURI);

	}

	// ...
}