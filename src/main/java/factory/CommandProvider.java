package factory;

import command.Command;
import command.impl.ParkListCommand;
import exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static command.CommandType.*;

public class CommandProvider {

	private static final Logger LOGGER = LogManager.getLogger(CommandProvider.class);
	private Map<String, Command> repository = new HashMap<>();
	HttpServletRequest request;
	HttpServletResponse response;

	public CommandProvider(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		repository.put(GET_PARK_LIST.name(), new ParkListCommand());
		// more ..
	}

	public Command getCommand() throws CommandException {
		String commandName = request.getParameter("action").toUpperCase();
		return repository.get(commandName);
	}
}
