package command.impl;

import command.Command;
import command.PageManager;
import exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

import static util.FrontControllerValues.COLOR;

public class CabinetCommand implements Command {
	@Override
	public PageManager execute(HttpServletRequest request) throws ServiceException, ServiceException {
		String color = (String) request.getSession().getAttribute(COLOR);
		System.out.println("attribute COLOR=" + color);
		request.setAttribute(COLOR, color);
		return PageManager.CABINET;
	}

	//TODO see? if needed
}
