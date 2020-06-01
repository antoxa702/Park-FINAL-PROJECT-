package command.impl;

import command.Command;
import command.PageManager;
import entity.Park;
import exception.ParkServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ParkService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static util.FrontControllerValues.*;

public class ParkListCommand implements Command {

	private static final Logger LOGGER = LogManager.getLogger(ParkListCommand.class);
	private final ParkService service = ParkService.INSTANCE;

	@Override
	public PageManager execute(HttpServletRequest request) throws ParkServiceException {

		List<Park> parkList = service.getAllParks();
		request.setAttribute(PARK_LIST_ALL, parkList);
		LOGGER.debug("DEBUG : Attribute have been set successfully");
		return PageManager.INDEX2;
	}
}
