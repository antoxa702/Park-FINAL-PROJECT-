package entity;

import java.io.Serializable;
import java.util.Objects;

public class ApplicationStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String statusName;

	public ApplicationStatus(){}

	public ApplicationStatus(int id, String statusName) {
		this.id = id;
		this.statusName = statusName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ApplicationStatus that = (ApplicationStatus) o;
		return id == that.id &&
				Objects.equals(statusName, that.statusName);
	}

	@Override
	public int hashCode() {
		int result = 31;
		result = 31*result + id;
		result = statusName != null ? (31*result + statusName.hashCode()) : (31*result);
		return result;
	}

	@Override
	public String toString() {
		return "PlantType[id=" + id +", typeName=" + statusName + "]";
	}
}
