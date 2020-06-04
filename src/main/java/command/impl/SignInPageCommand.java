package command.impl;

import command.Command;
import command.PageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SignInPageCommand implements Command {

	private static final Logger LOGGER = LogManager.getLogger(SignInPageCommand.class);

	@Override
	public PageManager execute(HttpServletRequest request) {
		LOGGER.debug("DEBUG : Forwarding to SIGN_IN page");
		return PageManager.SIGN_PAGE;
	}
}
