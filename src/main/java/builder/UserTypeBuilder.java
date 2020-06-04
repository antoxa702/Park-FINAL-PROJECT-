package builder;

import entity.UserType;

public class UserTypeBuilder {

	private UserType userType;

	public UserTypeBuilder() {
		userType = new UserType();
	}

	public UserTypeBuilder withId(int id){
		userType.setId(id);
		return this;
	}

	public UserTypeBuilder withName(String name) {
		userType.setNameType(name);
		return this;
	}

	public UserType build() {
		return this.userType;
	}
}
