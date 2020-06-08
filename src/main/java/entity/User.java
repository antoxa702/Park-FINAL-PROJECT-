package entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class User implements Serializable {

	private static final long serialVersionUID=1L;

	private int id;
	private String login;
	private char[] password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private int userTypeId;
	private int parkId;

	public User(){}

	public User(int id, String login, char[] password,
				String firstName, String lastName,
				String phoneNumber, String email, int userTypeId, int parkId) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.userTypeId = userTypeId;
		this.parkId = parkId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public int getParkId() {
		return parkId;
	}

	public void setParkId(int parkId) {
		this.parkId = parkId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return id == user.id &&
				userTypeId == user.userTypeId &&
				parkId == user.parkId &&
				Objects.equals(login, user.login) &&
				Arrays.equals(password, user.password) &&
				Objects.equals(firstName, user.firstName) &&
				Objects.equals(lastName, user.lastName) &&
				Objects.equals(phoneNumber, user.phoneNumber) &&
				Objects.equals(email, user.email);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(id, login, firstName, lastName, phoneNumber, email, userTypeId, parkId);
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
				", phoneNumber='" + phoneNumber + '\'' +
				", email='" + email + '\'' +
				", userTypeId=" + userTypeId +
				", parkId=" + parkId +
				']';
	}
}
