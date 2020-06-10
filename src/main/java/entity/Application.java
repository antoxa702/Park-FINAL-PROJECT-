package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Application implements Serializable {

	private static final long serialVersionUID = 1L;

	int id;
	Date startDate;
	Date endDate;
	boolean isApproved;
	String ownerComment;
	String employeeComment;
	WorkType workType;
	PlantType plantType;
	Park park;
	ApplicationStatus status;
	User owner;
	User employee;

	public Application (){}

	public Application ( int id, Date startDate, Date endDate, boolean isApproved, String ownerComment, String employeeComment,
					   WorkType workType, PlantType plantType, Park park, ApplicationStatus status, User owner,
					   User employee) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isApproved = isApproved;
		this.ownerComment = ownerComment;
		this.employeeComment = employeeComment;
		this.workType = workType;
		this.plantType = plantType;
		this.park = park;
		this.status = status;
		this.owner = owner;
		this.employee = employee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean approved) {
		isApproved = approved;
	}

	public String getOwnerComment() {
		return ownerComment;
	}

	public void setOwnerComment(String ownerComment) {
		this.ownerComment = ownerComment;
	}

	public String getEmployeeComment() {
		return employeeComment;
	}

	public void setEmployeeComment(String employeeComment) {
		this.employeeComment = employeeComment;
	}

	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}

	public PlantType getPlantType() {
		return plantType;
	}

	public void setPlantType(PlantType plantType) {
		this.plantType = plantType;
	}

	public Park getPark() {
		return park;
	}

	public void setPark(Park park) {
		this.park = park;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Application that = (Application) o;
		return id == that.id &&
				isApproved == that.isApproved &&
				startDate.equals(that.startDate) &&
				Objects.equals(endDate, that.endDate) &&
				Objects.equals(ownerComment, that.ownerComment) &&
				Objects.equals(employeeComment, that.employeeComment) &&
				workType.equals(that.workType) &&
				plantType.equals(that.plantType) &&
				park.equals(that.park) &&
				status.equals(that.status) &&
				owner.equals(that.owner) &&
				employee.equals(that.employee);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, startDate, endDate, isApproved, ownerComment, employeeComment, workType, plantType,
				park, status, owner, employee);
	}

	@Override
	public String toString() {
		return "Application[" +
				"id=" + id +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", isApproved=" + isApproved +
				", ownerComment='" + ownerComment + '\'' +
				", employeeComment='" + employeeComment + '\'' +
				", workType=" + workType.getTypeName() +
				", plantType=" + plantType.getTypeName() +
				", park=" + park.getName() +
				", status=" + status.getStatusName() +
				", owner=" + owner.getFirstName() + " " + owner.getLastName() +
				", employee=" + employee.getFirstName() + " " + employee.getLastName() +
				']';
	}
}
