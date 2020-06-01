package command;

public enum PageManager {
	//add more..
	INDEX2 ("/WEB-INF/intro/index2.jsp");

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
