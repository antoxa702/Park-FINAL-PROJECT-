package command;

public enum PageManager {
	//add more..
	INDEX2 ("/WEB-INF/intro/index2.jsp"),
	MAIN_PAGE ("/WEB-INF/jsp/main.jsp"),
	ERROR_PAGE("/WEB-INF/jsp/error.jsp");

	String url;

	PageManager(String url) {
		this.url = url;
	}

	private void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
