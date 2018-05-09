package com.ecare.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.ecare.dao.model.Contract;
import com.ecare.dao.model.Option;
import com.ecare.dao.model.Plan;
import com.ecare.dao.model.User;
import com.ecare.services.api.ContractService;
import com.ecare.services.api.OptionService;
import com.ecare.exception.DaoException;
import com.ecare.exception.InputException;
import com.ecare.services.api.PlanService;
import com.ecare.services.api.UserService;
import com.ecare.util.ApplicationContext;
import com.ecare.exception.InputException;

@WebServlet({ "/createcontract", "/editcontract", "/changeplan", "/changeplanhandler", "/changeoptions", "/changeoptionshandler", "/createcontracthandler", "/removecontracthandler" })
public class ContractController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PlanService planService = ApplicationContext.getPlanService();
	OptionService optionService = ApplicationContext.getOptionService();
	UserService userService = ApplicationContext.getUserService();
	ContractService contractService = ApplicationContext.getContractService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		// Handler for display list of all options
		if (servletPath.equals("/createcontract")) {
			createContract(request, response);
		} else if (servletPath.equals("/editcontract")) {
			editContract(request, response);
		} else if (servletPath.equals("/changeplan")) {
			changePlan(request, response);
		} else if (servletPath.equals("/changeoptions")) {
			changeOptions(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String jsp = null;
		String servletPath = request.getServletPath();
		if (servletPath.equals("/usercontracts")) {
			userContracts(request, response);
		} else if (servletPath.equals("/changeplanhandler")) {
			changePlanHandler(request, response);
		} else if (servletPath.equals("/changeoptionshandler")) {
			changeOptionsHandler(request, response);
		} else if (servletPath.equals("/createcontracthandler")) {
			createContractHandler(request, response);
		} else if (servletPath.equals("/removecontracthandler")) {
			removeContractHandler(request, response);
		}
	}

	protected void createContract(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String jspPath;
		String stringIdUser = (String) request.getParameter("idUser");
		int idUser = Integer.parseInt(stringIdUser);
		User user = userService.getUser(idUser);
		request.setAttribute("user", user);
		List<Plan> planList = planService.getNotArchival();

		request.setAttribute("planList", planList);
		List<Option> optionList = optionService.getAllOptions();
		request.setAttribute("optionList", optionList);
		jspPath = "/WEB-INF/jsp/createcontract.jsp";
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
		requestDispatcher.forward(request, response);
	}

	protected void editContract(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String jspPath;
		String stringIdContract = (String) request.getParameter("idContract");
		int idContract = Integer.parseInt(stringIdContract);
		Contract contract = contractService.getContract(idContract);
		request.setAttribute("contract", contract);
		List<Plan> planList = planService.getAllPlans();
		request.setAttribute("planList", planList);
		jspPath = "/WEB-INF/jsp/editcontract.jsp";
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
		requestDispatcher.forward(request, response);
	}

	protected void changePlan(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String jspPath;
		String stringIdContract = (String) request.getParameter("idContract");
		int idContract = Integer.parseInt(stringIdContract);
		Contract contract = contractService.getContract(idContract);
		request.setAttribute("contract", contract);
		List<Plan> planList = planService.getNotArchival();
		request.setAttribute("planList", planList);
		jspPath = "/WEB-INF/jsp/changeplan.jsp";
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
		requestDispatcher.forward(request, response);
	}

	protected void changePlanHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String jspPath;
		String stringIdContract = (String) request.getParameter("idContract");
		int idContract = Integer.parseInt(stringIdContract);
		Contract contract = contractService.getContract(idContract);
		String stringIdPlan = (String) request.getParameter("idPlan");
		int idPlan = Integer.parseInt(stringIdPlan);
		Plan plan = planService.getPlan(idPlan);
		contract.setPlan(plan);
		contractService.clearContractOptions(contract);
		
		//request.setAttribute("contract", contract);
		
		try {
			contractService.updateContract(contract);
		} catch (DaoException | InputException ex) {

		} catch (Exception ex) {

		}

		//List<Plan> planList = planService.getNotArchival();
		//request.setAttribute("planList", planList);
		User user = contract.getUser();
		request.setAttribute("user", user);
		jspPath = "/WEB-INF/jsp/edituser.jsp";
		
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
		requestDispatcher.forward(request, response);
	}

	protected void changeOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String jspPath;
		String stringIdContract = (String) request.getParameter("idContract");
		int idContract = Integer.parseInt(stringIdContract);
		Contract contract = contractService.getContract(idContract);
		request.setAttribute("contract", contract);
		List<Option> optionList = contractService.getAvailableOptions(contract);
		request.setAttribute("optionList", optionList);
		jspPath = "/WEB-INF/jsp/changeoptions.jsp";
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
		requestDispatcher.forward(request, response);
	}
	
	protected void changeOptionsHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String jspPath;
		String stringIdContract = (String) request.getParameter("idContract");
		int idContract = Integer.parseInt(stringIdContract);
		Contract contract = contractService.getContract(idContract);
		
		List<Option> listConnectedOptions = new ArrayList<Option>();
		String[] stringConnectedIdOptions = request.getParameterValues("connected");
		if (stringConnectedIdOptions != null) {
            for (String strIdConnectedOption : stringConnectedIdOptions) {
                int idConnectedOption = Integer.parseInt(strIdConnectedOption);
                Option connectedOption = optionService.getOption(idConnectedOption);
                listConnectedOptions.add(connectedOption);
            }
        }
		
        try {
        	if (planService.isOptionsCompatible(listConnectedOptions) == false) throw new InputException();
            contract.setContractOptions(listConnectedOptions);
            contractService.updateContract(contract);
        } catch (DaoException | InputException ex) {
        	throw new RuntimeException();
        } catch (Exception ex) {
        	throw new RuntimeException();
        }
        User user = contract.getUser();
		request.setAttribute("user", user);
		request.setAttribute("user", user);
		jspPath = "/WEB-INF/jsp/edituser.jsp";
		
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
		requestDispatcher.forward(request, response);
	}
	
	protected void createContractHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String jspPath;
		try {
		String stringIdUser = (String) request.getParameter("idUser");
		if (stringIdUser == null)
			throw new InputException();
		int idUser = Integer.parseInt(stringIdUser);
		User user = userService.getUser(idUser);
		Contract contract = new Contract();
		//request.setAttribute("user", contract);
		String phoneNumber = (String) request.getParameter("phoneNumber");
		if (phoneNumber == null)
			throw new InputException();
		List<Plan> listPlan = planService.getAllPlans();
		if (contractService.getByPhoneNumber(phoneNumber) != null) throw new InputException();
		contract.setPhoneNumber(phoneNumber);
		contract.setPlan(listPlan.get(0));
		contract.setClientBlock(false);
		contract.setEmployeeBlock(false);
		//user.getContracts().add(contract);
		contract.setUser(user);
		try {
            contractService.createContract(contract);
			//userService.updateUser(user);
        } catch (DaoException | InputException ex) {
        	throw new RuntimeException();
        }
		request.setAttribute("user", user);
		jspPath = "/WEB-INF/jsp/edituser.jsp";
		}
		catch (InputException ex) {
            //throw new RuntimeException(ex);
            List<User> userList = userService.getAllClients();
            request.setAttribute("userList", userList);
            jspPath = "/WEB-INF/jsp/allclients.jsp";
        }
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
		requestDispatcher.forward(request, response);
	}
	
	// All user contracts �������� ��� ����� �������
	protected void userContracts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String jspPath;
		String stringIdUser = (String) request.getParameter("idUser");
		int idUser = Integer.parseInt(stringIdUser);
		User user = userService.getUser(idUser);
		request.setAttribute("user", user);
		jspPath = "/WEB-INF/jsp/editcontracts.jsp";
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
		requestDispatcher.forward(request, response);
	}

	protected void removeContractHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String jspPath;
		try {
		String stringIdContract = (String) request.getParameter("idContract");
		if (stringIdContract == null)
			throw new InputException();
		int idcontract = Integer.parseInt(stringIdContract);
		Contract contract = contractService.getContract(idcontract);
		User user = contract.getUser();
		contractService.removeContract(contract);
		request.setAttribute("user", user);
		jspPath = "/WEB-INF/jsp/edituser.jsp";
		}
		catch (InputException | DaoException ex) {
            //throw new RuntimeException(ex);
            List<User> userList = userService.getAllClients();
            request.setAttribute("userList", userList);
            jspPath = "/WEB-INF/jsp/allclients.jsp";
        }
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
		requestDispatcher.forward(request, response);
	}
	
}
