package command.impl;
import command.Command;
import command.PageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ErrorPageCommand implements Command {

	private static final Logger LOGGER = LogManager.getLogger(ErrorPageCommand.class);

	@Override
	public PageManager execute(HttpServletRequest request) {
		LOGGER.debug("DEBUG : Forwarding to error page");
		return PageManager.ERROR_PAGE;
	}
}


