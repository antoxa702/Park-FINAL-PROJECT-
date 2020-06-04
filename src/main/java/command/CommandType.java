package command;

public enum CommandType {

	GET_PARK_LIST("GET_PARK_LIST"),
	MAIN("MAIN_PAGE"),
	ERROR("ERROR_PAGE"),
	SIGN_IN("SIGN_IN");

	public final String name;

	CommandType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
