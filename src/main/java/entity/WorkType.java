package entity;

import java.io.Serializable;
import java.util.Objects;

public class WorkType implements Serializable {
	private static final long serialVersionUID = 1l;

	private int id;
	private String typeName;

	public WorkType(){}

	public WorkType(int id, String typeName) {
		this.id = id;
		this.typeName = typeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WorkType workType = (WorkType) o;
		return id == workType.id &&
				Objects.equals(typeName, workType.typeName);
	}

	@Override
	public int hashCode() {
		int result = 31;
		result = 31*result + id;
		result =typeName != null ? (31*result + typeName.hashCode()) : (31*result);
		return result;
	}

	@Override
	public String toString() {
		return "WorkType[id=" + id +", typeName=" + typeName + "]";
	}
}
