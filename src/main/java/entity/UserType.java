package entity;

import java.io.Serializable;
import java.util.Objects;

public class UserType implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String nameType;

	public UserType(){}

	public UserType(int id, String nameType) {
		this.id = id;
		this.nameType = nameType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameType() {
		return nameType;
	}

	public void setNameType(String nameType) {
		this.nameType = nameType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserType userType = (UserType) o;
		return id == userType.id &&
				Objects.equals(nameType, userType.nameType);
	}

	@Override
	public int hashCode() {
		int result = 31;
		result = 31*result + id;
		result = nameType != null ? (31*result + nameType.hashCode()) : (31*result);
		return result;
	}

	@Override
	public String toString() {
		return "UserType [id=" + id +", nameType=" + nameType + "]";
	}
}
