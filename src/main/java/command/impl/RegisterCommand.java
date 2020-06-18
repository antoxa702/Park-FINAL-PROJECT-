package command.impl;

import builder.UserBuilder;
import command.Command;
import command.PageManager;
import entity.User;
import exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ParkService;
import service.UserService;
import service.UserTypeService;

import javax.servlet.http.HttpServletRequest;

import static util.FrontControllerValues.*;

public class RegisterCommand implements Command {
	private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);
	ParkService parkService = ParkService.INSTANCE;
	UserTypeService userTypeService = UserTypeService.INSTANCE;
	UserService userService = UserService.INSTANCE;

	@Override
	public PageManager execute(HttpServletRequest request) throws ServiceException {
		String repeatPassword = request.getParameter(REPEAT_PASSWORD);
		String key = request.getParameter(KEY);

		User userFromRegistrationForm = createUserFromRegisterForm(request);

		if (userFromRegistrationForm.getPassword().equals(repeatPassword)) {
			if (userFromRegistrationForm.getUserType().getNameType().equals("owner") && !key.equals("secret")){
			LOGGER.warn("WARN : UserType owner secret key doesn't match SECRET_KEY");
			return PageManager.ERROR_PAGE;
			}

			if (userService.registerUser(userFromRegistrationForm)) {
				request.getSession().setAttribute(USER, userFromRegistrationForm);
				return PageManager.MAIN_PAGE; //TODO redirect to the cabinet jsp
			}
		}
		LOGGER.warn ("WARN : User haven't been registered");
		return PageManager.ERROR_PAGE;
	}

	private User createUserFromRegisterForm(HttpServletRequest request) throws ServiceException {
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		String firstName = request.getParameter(FIRST_NAME);
		String lastName = request.getParameter(LAST_NAME);
		String phoneNumber = request.getParameter(PHONE_NUMBER);
		String email = request.getParameter(EMAIL);
		String parkName = request.getParameter(PARKS);
		String userType = request.getParameter(USER_TYPE);
		String key = request.getParameter(KEY);

		System.out.println("login=" + login);
		System.out.println("password=" + password);
		System.out.println("firstName=" + firstName);
		System.out.println("lastName=" + lastName);
		System.out.println("phone=" + phoneNumber);
		System.out.println("email=" + email);
		System.out.println("parkName=" + parkName);
		System.out.println("userType=" + userType);
		System.out.println("key=" + key);


		return new UserBuilder().withLogin(login).withPassword(password).withFirstName(firstName).
				withLastName(lastName).withPhoneNumber(phoneNumber).withEmail(email).
				withUserType(userTypeService.getByName(userType)).
				withPark(parkService.getByName(parkName)).build();
	}
}
