package servlet;

import entity.Park;
import exception.CommandException;
import exception.ParkServiceException;
import factory.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (name="list", urlPatterns="/list")
public class FirstServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = "index2.jsp";
		List<Park> parkList = null;
		try {
			parkList = new CommandProvider(request, response).getCommand().execute();
			request.setAttribute("parks", parkList);
			request.getRequestDispatcher(path).forward(request, response);
		} catch (ParkServiceException e) {
			e.printStackTrace();
		} catch (CommandException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
