package builder;

import entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.validator.FieldsValidator;

import java.util.Date;

public class ApplicationBuilder {

	private static final Logger LOGGER = LogManager.getLogger(ApplicationBuilder.class);

	Application application;
	FieldsValidator validator = new FieldsValidator();

	public ApplicationBuilder(){
		application = new Application();
	}

	public ApplicationBuilder withId(int id) {
		if (validator.validateId(id)){
			application.setId(id);
		} else {
			LOGGER.warn("WARN : ID is incorrect");
		}
		application.setId(id);
		return this;
	}

	public ApplicationBuilder withStartDate(Date startDate) {
		application.setStartDate(startDate);
		return this;
	}

	public ApplicationBuilder withEndDate (Date endDate) {
		application.setEndDate(endDate);
		return this;
	}

	public ApplicationBuilder withIsApproved(boolean isApproved) {
		application.setApproved(isApproved);
		return this;
	}

	public ApplicationBuilder withOwnerComment(String ownerComment){
		application.setOwnerComment(ownerComment);
		return this;
	}

	public ApplicationBuilder withEmployeeComment(String employeeComment){
		application.setEmployeeComment(employeeComment);
		return this;
	}

	public ApplicationBuilder withWorkType(WorkType workType) {
		application.setWorkType(workType);
		return this;
	}

	public ApplicationBuilder withPlantType(PlantType plantType) {
		application.setPlantType(plantType);
		return this;
	}

	public ApplicationBuilder withPark (Park park) {
		application.setPark(park);
		return this;
	}

	public ApplicationBuilder withApplicationStatus(ApplicationStatus status) {
		application.setStatus(status);
		return this;
	}

	public ApplicationBuilder withOwner (User owner) {
		application.setOwner(owner);
		return this;
	}

	public ApplicationBuilder withEmployee (User employee) {
		application.setEmployee(employee);
		return this;
	}

	public Application build() {
		return application;
	}
}
