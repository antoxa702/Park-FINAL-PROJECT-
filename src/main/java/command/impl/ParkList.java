package command.impl;

import java.util.ArrayList;
import java.util.List;

import command.Command;

public class ParkList<Park> implements Command {
	
	@Override
	public List<Park> execute(){
		List<Park> parkList = new ArrayList<>();
		
		return parkList;
	}
}
