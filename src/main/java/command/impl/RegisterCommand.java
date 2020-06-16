package command.impl;

import command.Command;
import command.PageManager;
import exception.ParkServiceException;
import exception.UserServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

	@Override
	public PageManager execute(HttpServletRequest request) throws ParkServiceException, UserServiceException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String repeatPassword = request.getParameter("repeat_password");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String phoneNumber = request.getParameter("phone_number");
		String email = request.getParameter("email");
		String parkName = request.getParameter("park");
		String userType = request.getParameter("user_type");

		return null;


	}
}
