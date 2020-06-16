package command.impl;

import command.Command;
import command.PageManager;
import entity.User;
import exception.UserServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(SignCommand.class);
	UserService service = UserService.INSTANCE;

	@Override
	public PageManager execute(HttpServletRequest request) throws UserServiceException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		request.setAttribute("login", login);
		request.setAttribute("password", password);

		System.out.println("login=" + login + ", password=" + password);
		User user = service.getUser(login, password);

		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
		} else {
			LOGGER.warn("WARN : Can't find user by login and password");
		}
		return PageManager.TEST;
	}
}
