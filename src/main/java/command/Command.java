package command;

import java.util.List;

public interface Command <T> {
	public  List<T> execute();
}
