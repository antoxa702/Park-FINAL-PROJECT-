package entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {

	private int id;
	private String login;
	private char[] password;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String phoneNumber;
	private String email;
	private UserType userType;
	private Park park;

	public User(){}

	public User(int id, String login, char[] password,
				String firstName, String lastName, Date dateOfBirth,
				String phoneNumber, String email, UserType userType, Park park) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.userType = userType;
		this.park = park;
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Park getPark() {
		return park;
	}

	public void setPark(Park park) {
		this.park = park;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other == null || getClass() != other.getClass()) return false;
		User user = (User) other;



		return id == user.id &&
				Objects.equals(login, user.login) &&
				Arrays.equals(password, user.password) &&
				Objects.equals(firstName, user.firstName) &&
				Objects.equals(lastName, user.lastName) &&
				Objects.equals(dateOfBirth, user.dateOfBirth) &&
				Objects.equals(phoneNumber, user.phoneNumber) &&
				Objects.equals(email, user.email) &&
				userType == user.userType &&
				Objects.equals(park, user.park);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(id, login, firstName, lastName, dateOfBirth, phoneNumber, email, userType, park);
		result = 31 * result + Arrays.hashCode(password);
		return result;
	}

	@Override
	public String toString() {
		return "User[" +
				"id=" + id +
				", login='" + login + '\'' +
				", password=" + Arrays.toString(password) +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", dateOfBirth=" + dateOfBirth +
				", phoneNumber='" + phoneNumber + '\'' +
				", email='" + email + '\'' +
				", userType=" + userType +
				", park=" + park +
				']';
	}
}
