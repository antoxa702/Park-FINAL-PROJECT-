package util.validator;

import entity.Park;
import entity.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserFieldsValidator {

	private static final Logger LOGGER = LogManager.getLogger(UserFieldsValidator.class);

	private static final String LOGIN_REGEX = "\\w+";
	private static final String PASSWORD_REGEX = "[0-9a-zA-Z!@#$%^&*]{6,}"; // not less than 6 symbols
	private static final String PHONE_NUMBER_REGEX = "[\\d]{3,12}";
	private static final String EMAIL_REGEX = "\\w+@\\w+(\\.\\w+)+";

	private Pattern pattern;
	private Matcher matcher;

	public boolean validateLogin(String login){
		pattern = Pattern.compile(LOGIN_REGEX);
		matcher = pattern.matcher(login);
		if (matcher.matches()){
			LOGGER.debug("DEBUG : Correct login");
			return true;
		}
		LOGGER.warn("WARN : Login does not matches LOGIN_REGEX");
		return false;
	}

	public boolean validatePassword(String password){
		pattern = Pattern.compile(PASSWORD_REGEX);
		matcher = pattern.matcher(password);
		if (matcher.matches()){
			LOGGER.debug("DEBUG : Correct password");
			return true;
		}
		LOGGER.warn("WARN : Password does not matches PASSWORD_REGEX");
		return false;
	}

	public boolean validatePhoneNumber(String phoneNumber){
		pattern = Pattern.compile(PHONE_NUMBER_REGEX);
		matcher = pattern.matcher(phoneNumber);
		if (matcher.matches()){
			LOGGER.debug("DEBUG : Correct phone number");
			return true;
		}
		LOGGER.warn("WARN : Phone number does not matches PHONE_NUMBER_REGEX");
		return false;
	}

	public boolean validateEmail(String email){
		pattern = Pattern.compile(EMAIL_REGEX);
		matcher = pattern.matcher(email);
		if (matcher.matches()){
			LOGGER.debug("DEBUG : Correct email");
			return true;
		}
		LOGGER.warn("WARN : Email does not matches EMAIL_REGEX");
		return false;
	}

	public boolean validatePark(Park park) {
		if (park == null) {
			LOGGER.warn("WARN : Park object is null");
			return false;
		}
		LOGGER.debug("DEBUG : not NULL Park object");
		return true;
	}

	public boolean validateUserType(UserType userType){
		if (userType == null) {
			LOGGER.warn("WARN : UserType object is null");
			return false;
		}
		LOGGER.debug("DEBUG : not null UserType object");
		return true;
	}

	public boolean validateId(int id) {
		if (id >= 0) {
			LOGGER.debug("DEBUG : ID is correct");
			return true;
		}
		LOGGER.warn("WARN : ID is incorrect");
		return false;
	}
}
