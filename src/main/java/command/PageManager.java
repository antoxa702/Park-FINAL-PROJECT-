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
	THIS("");

	private String url;
	private void setUrl(String url) {
		this.url = url;
	}
	PageManager(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	/*
	private String pageUrl;
	private StringBuilder stringBuilder = new StringBuilder();
	public String buildUrl(String action){
		pageUrl = stringBuilder.append("fcs")
				.append("?")
				.append(ACTION)
				.append("=")
				.append(action).toString();
		return pageUrl;
	}
	*/
}
