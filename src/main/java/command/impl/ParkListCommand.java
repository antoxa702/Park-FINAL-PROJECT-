package command.impl;

import command.Command;
import exception.ParkServiceException;
import service.ParkService;

import java.util.List;

public class ParkListCommand<Park> implements Command {
	ParkService service = new ParkService();
	@Override
	public List<Park> execute() throws ParkServiceException {
		List<Park> parkList = (List<Park>) service.getAllParks();
		return parkList;
	}
}
