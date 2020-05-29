package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name="do", urlPatterns= "/controller") 
public class FrontController extends	HttpServlet {
		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String height = request.getParameter("height");
        String weight = request.getParameter("weight");

        try {
            double bmi = calculateBMI(
              Double.parseDouble(weight),
              Double.parseDouble(height));             
            String action = request.getParameter("action");
            switch (action) {
            case "1" : 
            	request.getSession().setAttribute("controller1", bmi);
            break;
            case "2" : request.getSession().setAttribute("controller2", bmi);
            break;
            default : break;
            }
            /*
            response.setHeader("Test", "Success");
            response.setHeader("BMI", String.valueOf(bmi));        
            */
            RequestDispatcher dispatcher
              = request.getRequestDispatcher("testing.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("testing.jsp");
        }
    }

    private Double calculateBMI(Double weight, Double height) {
        return weight/(height * height);    
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(request, response);
	}

}
