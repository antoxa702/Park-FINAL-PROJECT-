package controller;

import command.Command;
import exception.CommandException;
import exception.ServiceException;
import factory.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name="controller", urlPatterns="/fcs")
public class FrontControllerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			CommandProvider commandProvider = CommandProvider.INSTANCE; // getting commandProvider instance
			Command command = commandProvider.getCommand(request);		// getting Command
			command.execute(request);									// getting request object after setting attribute

			String path  = command.execute(request).getUrl();
			request.getRequestDispatcher(path).forward(request, response);

		} catch (ServiceException e) {
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
