package command;

import exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface

Command {
	PageManager execute(HttpServletRequest request) throws ServiceException, ServiceException;
}
