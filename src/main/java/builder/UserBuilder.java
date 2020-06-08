package builder;

import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.validator.UserFieldsValidator;

import java.util.Date;

public class UserBuilder {

	private static final Logger LOGGER = LogManager.getLogger(UserBuilder.class);

	private User user;
	UserFieldsValidator validator = new UserFieldsValidator();

	public UserBuilder(){
		user = new User();
	}

	public UserBuilder withId(int id) {
		user.setId(id);
		return this;
	}

	public UserBuilder withLogin(String login) {
		if (validator.validateLogin(login)){
			user.setLogin(login);
		} else {
			LOGGER.warn("WARN : Login contains incorrect symbols");
		}
		return this;
	}

	public UserBuilder withPassword(String password) {
		if (validator.validatePassword(password)){
			user.setPassword(password.toCharArray());
		} else{
			LOGGER.warn("WARN : Password contains incorrect symbols");
		}
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
		if (validator.validatePhoneNumber(phoneNumber)){
			user.setPhoneNumber(phoneNumber);
		} else {
			LOGGER.warn("WARN : Phone number is incorrect");
		}
		return this;
	}

	public UserBuilder withEmail(String email) {
		if (validator.validateEmail(email)){
			user.setEmail(email);
		} else {
			LOGGER.warn("WARN : Email is incorrect");
		}
		return this;
	}

	public UserBuilder withUserTypeId(int userTypeId) {
		if (validator.validateId(userTypeId)){
			user.setUserTypeId(userTypeId);
		} else {
			LOGGER.warn("WARN : UserTypeId is incorrect");
		}
		return this;
	}
	public UserBuilder withParkId(int parkId) {
		if (validator.validateId(parkId)){
			user.setParkId(parkId);
		} else{
			LOGGER.warn("WARN : Park ID is incorrect");
		}
		return this;
	}

	public User build(){
		return this.user;
	}
}
