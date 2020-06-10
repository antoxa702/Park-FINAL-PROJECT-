package util.validator;

import com.mysql.cj.util.StringUtils;
import entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldsValidator {

	private static final Logger LOGGER = LogManager.getLogger(FieldsValidator.class);

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

		if (!validateId(park.getId()) || StringUtils.isNullOrEmpty(park.getName()) || park.getArea() < 0.0) {
			LOGGER.warn("WARN : Park object is incorrect");
			return false;
		}
		LOGGER.debug("DEBUG : correct Park object");
		return true;
	}

	public boolean validateUserType(UserType userType){
		if (userType == null) {
			LOGGER.warn("WARN : UserType object is null");
			return false;
		}
		if (!validateId(userType.getId()) || StringUtils.isNullOrEmpty(userType.getNameType())) {
			LOGGER.warn("WARN : ID or NAME userType is incorrect");
		}
		LOGGER.debug("DEBUG : UserType object is correct");
		return true;
	}

	public boolean validatePlantType(PlantType plantType){
		if (plantType == null) {
			LOGGER.warn("WARN : PlantType object is null");
			return false;
		}
		if (!validateId(plantType.getId()) || StringUtils.isNullOrEmpty(plantType.getTypeName())) {
			LOGGER.warn("WARN : ID or NAME plantType is incorrect");
		}
		LOGGER.debug("DEBUG : plantType object is correct");
		return true;
	}

	public boolean validateWorkType(WorkType workType){
		if (workType == null) {
			LOGGER.warn("WARN : WorkType object is null");
			return false;
		}
		if (!validateId(workType.getId()) || StringUtils.isNullOrEmpty(workType.getTypeName())) {
			LOGGER.warn("WARN : ID or NAME workType is incorrect");
		}
		LOGGER.debug("DEBUG : workType object is correct");
		return true;
	}

	public boolean validateApplicationStatus(ApplicationStatus status){
		if (status == null) {
			LOGGER.warn("WARN : ApplicationStatus object is null");
			return false;
		}
		if (!validateId(status.getId()) || StringUtils.isNullOrEmpty(status.getStatusName())) {
			LOGGER.warn("WARN : ID or NAME ApplicationStatus is incorrect");
		}
		LOGGER.debug("DEBUG : applicationStatus object is correct");
		return true;
	}

	public boolean validateUser(User user){
		if (user == null) {
			LOGGER.warn("WARN : User object is null");
			return false;
		}
		if (!validateId(user.getId()) || !validateLogin(user.getLogin()) ||
				!validatePassword(user.getPassword()) || StringUtils.isNullOrEmpty(user.getFirstName()) ||
				StringUtils.isNullOrEmpty(user.getLastName()) || !validateUserType(user.getUserType()) ||
				!validatePark(user.getPark()) || (!StringUtils.isNullOrEmpty(user.getPhoneNumber()) &&
				!validatePhoneNumber(user.getPhoneNumber())) || (!StringUtils.isNullOrEmpty(user.getEmail()) &&
				!validateEmail(user.getEmail()))) {
			LOGGER.warn("WARN : Some fields of User are incorrect");
		}
		LOGGER.debug("DEBUG : User object is correct");
		return true;
	}

	public boolean validateApplication(Application application){
		if (application == null) {
			LOGGER.warn("WARN : Application object is null");
			return false;
		}
		if (!validateId(application.getId()) || !validateDate(application.getStartDate()) ||
				!validateWorkType(application.getWorkType()) || !validatePlantType(application.getPlantType()) ||
				!validatePark(application.getPark()) || !validateApplicationStatus(application.getStatus()) ||
				!validateUser(application.getOwner()) || !validateUser(application.getEmployee())) {
			LOGGER.warn("WARN : Some fields of Application are incorrect");
		}
		LOGGER.debug("DEBUG : Application object is correct");
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

	public boolean validateDate(Date date) {
		if (date == null) {
			LOGGER.warn("WARN : Date object is null");
			return false;
		}
		LOGGER.debug("DEBUG : not null Date object");
		return true;
	}
}
