package factory;

import command.Command;
import command.impl.ErrorPageCommand;
import command.impl.MainPageCommand;
import command.impl.ParkListCommand;
import exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static command.CommandType.*;
import static util.FrontControllerValues.*;

public enum CommandProvider {

	INSTANCE;

	private static final Logger LOGGER = LogManager.getLogger(CommandProvider.class);
	private Map<String, Command> repository = new HashMap<>();

	CommandProvider() {
		repository.put(GET_PARK_LIST.name(), new ParkListCommand());
		repository.put(MAIN_PAGE.name(), new MainPageCommand());
		repository.put(ERROR_PAGE.name(), new ErrorPageCommand());
		// more ..
	}

	public Command getCommand(HttpServletRequest request) throws CommandException {
		if (request == null) {
			LOGGER.error("ERROR : request is null");
			throw new CommandException("ERROR : no such request");
		}

		String commandName = request.getParameter(ACTION).toUpperCase();
		if (repository.get(commandName) == null) {
			commandName = ERROR_PAGE.name();
		}
		return repository.get(commandName);
	}
}
