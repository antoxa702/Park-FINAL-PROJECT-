package entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

	private static final long serialVersionUID=1L;

	private int id;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private UserType userType;
	private Park park;

	public User(){}

	public User(int id, String login, String password,
				String firstName, String lastName,
				String phoneNumber, String email, UserType userType, Park park) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.userType = userType;
		this.park = park;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return id == user.id &&
				login.equals(user.login) &&
				password.equals(user.password) &&
				firstName.equals(user.firstName) &&
				lastName.equals(user.lastName) &&
				Objects.equals(phoneNumber, user.phoneNumber) &&
				Objects.equals(email, user.email) &&
				userType.equals(user.userType) &&
				park.equals(user.park);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime  + id;
		result = prime * result + (login == null ? 0 : login.hashCode());
		result = prime * result + (password == null ? 0 : password.hashCode());
		result = prime * result + (firstName == null ? 0 : firstName.hashCode());
		result = prime * result + (lastName == null ? 0 : lastName.hashCode());
		result = prime * result + (phoneNumber == null ? 0 : phoneNumber.hashCode());
		result = prime * result + (email == null ? 0 : email.hashCode());
		result = prime * result + (userType == null ? 0 : userType.hashCode());
		result = prime * result + (park == null ? 0 : park.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "User[" +
				"id=" + id +
				", login='" + login + '\'' +
				", password=" + password +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", email='" + email + '\'' +
				", userType=" + userType.getNameType() +
				", park=" + park.getName() +
				']';
	}
}
