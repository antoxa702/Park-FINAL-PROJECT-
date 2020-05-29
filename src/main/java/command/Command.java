package command;

import exception.ParkServiceException;

import java.util.List;

public interface Command <T> {
	public  List<T> execute() throws ParkServiceException;
}
