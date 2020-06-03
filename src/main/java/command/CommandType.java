package command;

public enum CommandType {

	GET_PARK_LIST ("GET_PARK_LIST"),
	MAIN_PAGE ("MAIN_PAGE"),
	ERROR_PAGE ("ERROR_PAGE");

	public final String name;

	CommandType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
