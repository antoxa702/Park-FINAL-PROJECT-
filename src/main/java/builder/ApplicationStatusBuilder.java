package builder;

import entity.ApplicationStatus;

public class ApplicationStatusBuilder {

	ApplicationStatus status;

	public ApplicationStatusBuilder(){
		status = new ApplicationStatus();
	}

	public ApplicationStatusBuilder withId(int id) {
		status.setId(id);
		return this;
	}

	public ApplicationStatusBuilder withStatusName(String statusName) {
		status.setStatusName(statusName);
		return this;
	}

	public ApplicationStatus build(){
		return this.status;
	}
}
