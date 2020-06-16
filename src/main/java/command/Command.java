package command;

import exception.ParkServiceException;
import exception.UserServiceException;

import javax.servlet.http.HttpServletRequest;

public interface

Command {
	PageManager execute(HttpServletRequest request) throws ParkServiceException, UserServiceException;
}
