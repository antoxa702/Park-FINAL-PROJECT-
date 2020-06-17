package command.impl;

import command.Command;
import command.PageManager;
import entity.User;
import exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static util.FrontControllerValues.*;

public class SignCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(SignCommand.class);
	UserService service = UserService.INSTANCE;

	@Override
	public PageManager execute(HttpServletRequest request) throws ServiceException {
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		request.setAttribute(LOGIN, login);
		request.setAttribute(PASSWORD, password);

		// System.out.println("login=" + login + ", password=" + password);
		User user = service.getUser(login, password);

		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute(USER, user);
		} else {
			LOGGER.warn("WARN : Can't find user by login and password");
		}
		return PageManager.TEST;
	}

	//TODO delete sout, setAttribute login, password and etc.
}
