package command.impl;

import command.Command;
import command.PageManager;
import exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ParkService;

import javax.servlet.http.HttpServletRequest;

import static util.FrontControllerValues.PARK_LIST_ALL;

public class RegisterPageCommand implements Command {

	private static final Logger LOGGER = LogManager.getLogger(RegisterPageCommand.class);
	ParkService parkService = ParkService.INSTANCE;

	@Override
	public PageManager execute(HttpServletRequest request) throws ServiceException {
		request.setAttribute(PARK_LIST_ALL, parkService.getAllParks());
		LOGGER.debug("DEBUG : Forwarding to REGISTER page");
		return PageManager.REGISTER_PAGE;
	}
}