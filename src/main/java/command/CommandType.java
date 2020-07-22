package command;

public enum CommandType {

	GET_PARK_LIST("GET_PARK_LIST"),
	MAIN("MAIN_PAGE"),
	ERROR("ERROR_PAGE"),
	SIGN_IN("SIGN_IN"),
	SIGN("SIGN"),
	REGISTER_PAGE("REGISTER_PAGE"),
	REGISTER("REGISTER"),
	CABINET("CABINET"),
	SIGN_OUT("SIGN_OUT"),
	CHANGE_LANGUAGE("CHANGE_LANGUAGE");
	//more commands...


	public final String name;

	CommandType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}