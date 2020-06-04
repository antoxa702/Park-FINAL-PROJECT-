package builder;

import entity.User;

import java.util.Date;

public class UserBuilder {

	private User user;

	public UserBuilder(){
		user = new User();
	}

	public UserBuilder withId(int id) {
		user.setId(id);
		return this;
	}

	public UserBuilder withLogin(String login) {
		user.setLogin(login);
		return this;
	}

	public UserBuilder withPassword(String password) {
		user.setPassword(password.toCharArray());
		return this;
	}

	public UserBuilder withFirstName(String firstName) {
		user.setFirstName(firstName);
		return this;
	}

	public UserBuilder withLastName(String lastName) {
		user.setLastName(lastName);
		return this;
	}

	public UserBuilder withDateOfBirth(Date dateOfBirth) {
		user.setDateOfBirth(dateOfBirth);
		return this;
	}

	public UserBuilder withPhoneNumber(String phoneNumber) {
		user.setPhoneNumber(phoneNumber);
		return this;
	}

	public UserBuilder withEmail(String email) {
		user.setEmail(email);
		return this;
	}

	public UserBuilder withUserType(String userType) {
		user.setLogin(login);
		return this;
	}
	public UserBuilder withLogin(String login) {
		user.setLogin(login);
		return this;
	}

}
