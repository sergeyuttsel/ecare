package com.ecare.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.ecare.dao.model.Option;
import com.ecare.dao.model.Plan;
import com.ecare.services.api.OptionService;
import com.ecare.exception.DaoException;
import com.ecare.exception.InputException;
import com.ecare.services.api.PlanService;
import com.ecare.util.ApplicationContext;
import com.ecare.exception.InputException;

@WebServlet({ "/allplans", "/plan", "/editplan", "/updateplan", "/createplan", "/createplanhandler" })
public class PlanController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    PlanService planService = ApplicationContext.getPlanService();
    OptionService optionService = ApplicationContext.getOptionService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jsp = null;
        String servletPath = request.getServletPath();
        // doPost(request, response);
        // Handler for display list of all plans
        if (servletPath.equals("/allplans")) {
            displayAllPlans(request, response);
        } else
        // Handler for display the plan's parameters. Including available
        // options.
        if (servletPath.equals("/plan")) {
            displayPlan(request, response);
        } else
        // Handler for edit plan's parameters. Including available options.
        if (servletPath.equals("/editplan")) {
            editPlan(request, response);
        } else
        // Handler for create plan. Send to user page for create new plan.
        if (servletPath.equals("/createplan")) {
            createPlan(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String jsp = null;
        String servletPath = request.getServletPath();

        // Handler for update plan's parameters. Including new available
        // options.
        if (servletPath.equals("/updateplan")) {
            updatePlan(request, response);

        } else
        // Handler for create plan. Including available options.
        if (servletPath.equals("/createplanhandler")) {
            createPlanHandler(request, response);
        }
    }

    protected void displayAllPlans(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        List<Plan> planList = planService.getAllPlans();
        request.setAttribute("planList", planList);
        String jspPath = "/WEB-INF/jsp/allplans.jsp";
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
        requestDispatcher.forward(request, response);
    }

    protected void displayPlan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String stringIdPlan = (String) request.getParameter("idPlan");
        int idPlan = Integer.parseInt(stringIdPlan);
        Plan plan = planService.getPlan(idPlan);
        request.setAttribute("plan", plan);
        String jspPath = "/WEB-INF/jsp/plan.jsp";
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
        requestDispatcher.forward(request, response);
    }

    protected void editPlan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String stringIdPlan = (String) request.getParameter("idPlan");
        int idPlan = Integer.parseInt(stringIdPlan);
        Plan plan = planService.getPlan(idPlan);
        request.setAttribute("plan", plan);
        List<Option> optionList = optionService.getAllOptions();
        request.setAttribute("optionList", optionList);
        String jspPath = "/WEB-INF/jsp/editplan.jsp";
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
        requestDispatcher.forward(request, response);
    }

    protected void updatePlan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String jspPath;
        try {
            // Find and update that need update.
            String stringIdPlan = (String) request.getParameter("idPlan");
            int idPlan = Integer.parseInt(stringIdPlan);
            Plan plan = planService.getPlan(idPlan);
            String name = (String) request.getParameter("name");
            if (name == null)
                throw new InputException();
            plan.setName(name);
            float price = Float.parseFloat(request.getParameter("price"));
            plan.setPrice(price);
            boolean archival = Boolean.parseBoolean(request.getParameter("archival"));
            plan.setArchival(archival);

            // Create list of new available options.
            List<Option> listAvailableOptions = new ArrayList<Option>();
            String[] availableStringIdOptions = request.getParameterValues("available");
            if (availableStringIdOptions != null) {
                for (String strIdAvailableOption : availableStringIdOptions) {
                    int idAvailableOption = Integer.parseInt(strIdAvailableOption);
                    Option availableOption = optionService.getOption(idAvailableOption);
                    listAvailableOptions.add(availableOption);
                }
            }
            try {
                planService.updatePlan(plan, listAvailableOptions);
            } catch (DaoException | InputException ex) {

            } catch (Exception ex) {

            }
            request.setAttribute("plan", plan);
            jspPath = "/WEB-INF/jsp/plan.jsp";
        }
        // If error occur redirect on page with all plans list.
        catch (InputException ex) {
            // throw new RuntimeException(ex);
            List<Plan> planList = planService.getAllPlans();
            request.setAttribute("planList", planList);
            jspPath = "/WEB-INF/jsp/allplans.jsp";
        }
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
        requestDispatcher.forward(request, response);
    }

    protected void createPlan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        List<Option> optionList = optionService.getAllOptions();
        request.setAttribute("optionList", optionList);
        String jspPath = "/WEB-INF/jsp/createplan.jsp";
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
        requestDispatcher.forward(request, response);
    }

    protected void createPlanHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String jspPath;
        try {
            Plan plan = new Plan();
            String name = (String) request.getParameter("name");
            if (name == null)
                throw new InputException();
            plan.setName(name);
            float price = Float.parseFloat(request.getParameter("price"));
            plan.setPrice(price);
            boolean archival = Boolean.parseBoolean(request.getParameter("archival"));
            plan.setArchival(archival);

            // Create list of available options.
            List<Option> listAvailableOptions = new ArrayList<Option>();
            String[] availableStringIdOptions = request.getParameterValues("available");
            if (availableStringIdOptions != null) {
                for (String strIdAvailableOption : availableStringIdOptions) {
                    int idAvailableOption = Integer.parseInt(strIdAvailableOption);
                    Option availableOption = optionService.getOption(idAvailableOption);
                    listAvailableOptions.add(availableOption);
                }
            }
            try {
                planService.createPlan(plan, listAvailableOptions);
            } catch (DaoException | InputException ex) {
            	throw new RuntimeException();
            } catch (Exception ex) {
            	throw new RuntimeException();
            }
            request.setAttribute("plan", plan);
            jspPath = "/WEB-INF/jsp/plan.jsp";
        }
        // If error occur redirect on page with all plans list.
        catch (InputException ex) {
            // throw new RuntimeException(ex);
            List<Plan> planList = planService.getAllPlans();
            request.setAttribute("planList", planList);
            jspPath = "/WEB-INF/jsp/allplans.jsp";
        }
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(jspPath);
        requestDispatcher.forward(request, response);
    }

    /*
     * protected void displayAllPlans(HttpServletRequest request,
     * HttpServletResponse response) throws ServletException, IOException {
     * 
     * RequestDispatcher requestDispatcher =
     * servletContext.getRequestDispatcher(jsp);
     * requestDispatcher.forward(request, response); }
     */
}
