package command;

import static util.FrontControllerValues.ACTION;

public enum PageManager {
	//add more..
	INDEX2 ("/WEB-INF/intro/index2.jsp"),
	MAIN_PAGE ("/WEB-INF/jsp/main.jsp"),
	ERROR_PAGE("/WEB-INF/jsp/error.jsp"),
	SIGN_PAGE("/WEB-INF/jsp/signin.jsp"),
	REGISTER_PAGE("WEB-INF/jsp/register.jsp"),
	CABINET("WEB-INF/jsp/cabinet.jsp"),
	INDEX1("index1.jsp"),
	TEST("WEB-INF/jsp/test.jsp"),
	THIS("");

	private String url;

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	private String pageUrl;
	private StringBuilder stringBuilder = new StringBuilder();

	PageManager(String url) {
		this.url = url;
	}

	private void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public String buildUrl(String action){
		pageUrl = stringBuilder.append("fcs")
				.append("?")
				.append(ACTION)
				.append("=")
				.append(action).toString();
		return pageUrl;
	}
}
