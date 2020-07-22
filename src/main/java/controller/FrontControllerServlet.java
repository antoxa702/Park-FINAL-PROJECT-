package controller;

import command.Command;
import command.PageManager;
import exception.CommandException;
import exception.ServiceException;
import factory.CommandProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.language.LanguageManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static command.CommandType.CHANGE_LANGUAGE;
import static util.FrontControllerValues.ACTION;
import static util.FrontControllerValues.LANGUAGE_MANAGER;

@WebServlet (name="controller", urlPatterns="/fcs")
public class FrontControllerServlet extends HttpServlet {

	private static final Logger LOGGER = LogManager.getLogger(FrontControllerServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			CommandProvider commandProvider = CommandProvider.INSTANCE; // getting commandProvider instance
			Command command = commandProvider.getCommand(request);		// getting Command

			String path  = command.execute(request).getUrl();

			request.getRequestDispatcher(path).forward(request, response);

			if (!request.getParameter(ACTION).equals(CHANGE_LANGUAGE.getName().toLowerCase())) {
				PageManager.CHANGE_LANGUAGE.setUrl("/fcs?" + request.getQueryString());
			}
		} catch (ServiceException e) {
			LOGGER.error("ERROR : service exception");
			throw new ServletException("ERROR : while getting service", e);
		} catch (CommandException e) {
			LOGGER.error("ERROR : command exception");
			throw new ServletException("ERROR : while getting command", e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

		//		try {
//			CommandProvider commandProvider = CommandProvider.INSTANCE; // getting commandProvider instance
//			Command command = commandProvider.getCommand(request);		// getting Command
//			String path  = command.execute(request).getUrl();			// getting request object after setting attribute
//
//			response.sendRedirect(path); //TODO check to redirect
//
//		} catch (ServiceException e) {
//			LOGGER.error("ERROR : service exception");
//			throw new ServletException("ERROR : while getting service", e);
//		} catch (CommandException e) {
//			LOGGER.error("ERROR : command exception");
//			throw new ServletException("ERROR : while getting command", e);
//		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
	}

	@Override
	public void init() throws ServletException {
		getServletContext().setAttribute(LANGUAGE_MANAGER, LanguageManager.INSTANCE);
		super.init();
	}
}
