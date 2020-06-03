package command.impl;
import command.Command;
import command.PageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class MainPageCommand implements Command {

	private static final Logger LOGGER = LogManager.getLogger(MainPageCommand.class);

	@Override
	public PageManager execute(HttpServletRequest request) {
		LOGGER.debug("DEBUG : Forwarding to main page");
		return PageManager.MAIN_PAGE;
	}
}


