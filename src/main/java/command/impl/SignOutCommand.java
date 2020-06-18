package command.impl;

import command.Command;
import command.PageManager;
import exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

import static util.FrontControllerValues.USER;

public class SignOutCommand implements Command {
	@Override
	public PageManager execute(HttpServletRequest request) throws ServiceException, ServiceException {
		request.getSession().setAttribute(USER, null);
		return PageManager.MAIN_PAGE;
	}
}
