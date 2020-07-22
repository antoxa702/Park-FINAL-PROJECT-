package command;

public enum PageManager {
	//todo add more..
	PARK_LIST("/WEB-INF/jsp/parklist.jsp"),
	MAIN_PAGE ("/WEB-INF/jsp/main.jsp"),
	ERROR_PAGE("/WEB-INF/jsp/error.jsp"),
	SIGN_PAGE("/WEB-INF/jsp/signin.jsp"),
	REGISTER_PAGE("WEB-INF/jsp/register.jsp"),
	CABINET("WEB-INF/jsp/cabinet.jsp"),
	INDEX1("index1.jsp"),
	TEST("WEB-INF/jsp/test.jsp"),
	CHANGE_LANGUAGE("");

	private String url;

	PageManager(String url) {
		this.url = url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
