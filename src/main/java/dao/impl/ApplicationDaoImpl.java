package dao.impl;

import builder.ApplicationBuilder;
import com.mysql.cj.util.StringUtils;
import dao.ApplicationDao;
import entity.*;
import exception.ConnectionPoolException;
import exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pool.ConnectionPool;
import util.validator.FieldsValidator;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static util.DbInitValues.*;

public enum ApplicationDaoImpl implements ApplicationDao {

	INSTANCE;

	FieldsValidator validator = new FieldsValidator();
	ParkDaoImpl parkDao = ParkDaoImpl.INSTANCE;
	PlantTypeDaoImpl plantTypeDao = PlantTypeDaoImpl.INSTANCE;
	ApplicationStatusDaoImpl applicationStatusDao = ApplicationStatusDaoImpl.INSTANCE;
	UserDaoImpl userDao = UserDaoImpl.INSTANCE;
	WorkTypeDaoImpl workTypeDao = WorkTypeDaoImpl.INSTANCE;

	private static final Logger LOGGER = LogManager.getLogger(ApplicationDaoImpl.class);
	private static final String SQL_STATEMENT_GET_ALL_APPLICATION = "SELECT * FROM application;";
	private static final String SQL_STATEMENT_GET_BY_OWNER_ID_APPLICATION = "SELECT * FROM application " +
																				"WHERE application.Owner_id=?;";
	private static final String SQL_STATEMENT_GET_BY_EMPLOYEE_ID_APPLICATION = "SELECT * FROM application " +
																				"WHERE application.Employee_id=?;";
	private static final String SQL_STATEMENT_GET_BY_STATUS_ID_APPLICATION = "SELECT * FROM application " +
																				"WHERE application.Status_id=?;";
	private static final String SQL_STATEMENT_GET_BY_PARK_ID_APPLICATION = "SELECT * FROM application " +
																			"WHERE application.Park_id=?;";
	private static final String SQL_STATEMENT_GET_BY_IS_APPROVED_APPLICATION = "SELECT * FROM application " +
																				"WHERE application.is_approved=?;";
	private static final String SQL_STATEMENT_INSERT_APPLICATION = "INSERT INTO application (start_date, end_date," +
			"is_approved, owner_comment, employee_comment, Type_of_work_id, Plants_type_id, Park_id, Status_id," +
			"Owner_id, Employee_id) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
	private static final String SQL_STATEMENT_GET_BY_ID_APPLICATION = "SELECT * FROM application WHERE id=?;";
	private static final String SQL_STATEMENT_UPDATE_APPLICATION = "UPDATE application SET application.start_date=?, " +
			"application.end_date=?, application.is_approved=?, application.owner_comment=?," +
			" application.employee_comment=?, application.Type_of_work_id=?, application.Plants_type_id=?," +
			"application.Park_id=?, application.Status_id=?, application.Owner_id=?," +
			"application.Employee_id=? WHERE application.id=?;";
	private static final String SQL_STATEMENT_DELETE_APPLICATION = "DELETE FROM application WHERE application.id=?;";

	@Override
	public List<Application> getAllApplications() throws DAOException {
		List<Application> applicationList;
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_ALL_APPLICATION)) {

			ResultSet resultSet = statement.executeQuery();
			applicationList = buildApplicationList(resultSet);

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting records from table user_info");
			throw new DAOException("SQLException while getting records from table user_info", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting records from table user_info");
			throw new DAOException("ConnectionPoolException while getting records from table user_info", e1);
		}
		return applicationList;
	}

	/**
	 * @param owner .
	 * @return List<Application> applicationList by Owner_id
	 * @throws DAOException .
	 */
	@Override
	public List<Application> getAllApplicationsByOwner(User owner) throws DAOException {
		return getApplicationById(owner.getId(), SQL_STATEMENT_GET_BY_OWNER_ID_APPLICATION);
	}

	/**
	 * @param employee .
	 * @return List<Application> applicationList by Employee_id
	 * @throws DAOException .
	 */
	@Override
	public List<Application> getAllApplicationsByEmployee(User employee) throws DAOException {
		return getApplicationById(employee.getId(), SQL_STATEMENT_GET_BY_EMPLOYEE_ID_APPLICATION);
	}

	/**
	 * @param status .
	 * @return List<Application> applicationList by Status_id
	 * @throws DAOException .
	 */
	@Override
	public List<Application> getAllApplicationsByStatus(ApplicationStatus status) throws DAOException {
		return getApplicationById(status.getId(), SQL_STATEMENT_GET_BY_STATUS_ID_APPLICATION);
	}

	/**
	 * @param park .
	 * @return List<Application> applicationList by Park_id
	 * @throws DAOException .
	 */
	@Override
	public List<Application> getAllApplicationsByPark(Park park) throws DAOException {
		return getApplicationById(park.getId(), SQL_STATEMENT_GET_BY_PARK_ID_APPLICATION);
	}

	/**	 *
	 * @param isApproved - boolean
	 * @return List<Application> applicationList by is_approved
	 * @throws DAOException .
	 */
	@Override
	public List<Application> getAllApplicationsIsApproved(boolean isApproved) throws DAOException {
		int isApprovedId = isApproved ? 0 : 1;
		return getApplicationById(isApprovedId, SQL_STATEMENT_GET_BY_IS_APPROVED_APPLICATION);
	}

	/**
	 * @param application - to add a new record into table application
	 * @throws DAOException .
	 */
	@Override
	public void add(Application application) throws DAOException {
		if (application == null) {
			LOGGER.error("ERROR : application is null");
			throw new DAOException("ERROR : can't add application record - application is null");
		}

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_INSERT_APPLICATION)) {
			fillInStatement(application, statement);
			if (statement.executeUpdate() == 1) {
				LOGGER.debug("DEBUG : record added successfully");
			} else {
				LOGGER.warn("WARN : record haven't been added");
				throw new SQLException("ERROR : None or few records have been inserted into application");
			}

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with adding a record into table application");
			throw new DAOException("SQLException while adding a record into table application", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with adding a record into table application");
			throw new DAOException("ConnectionPoolException while adding a record into table application", e1);
		}
	}

	/**
	 * @param id is apllication ID
	 * @return application object
	 * @throws DAOException .
	 */
	@Override
	public Application getById(int id) throws DAOException {
		List<Application> applicationList = getApplicationById(id,SQL_STATEMENT_GET_BY_ID_APPLICATION);
		if (applicationList.size() == 0){
			LOGGER.warn ("WARN : no application found by this ID");
			return null;
		}
		return applicationList.get(0);
	}

	@Override
	public int update(Application application) throws DAOException {
		int updatedRowsCount;

		if (validator.validateApplication(application)) {
			try(Connection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_UPDATE_APPLICATION)) {

				fillInStatement(application, statement);
				statement.setInt(12, application.getId());
				updatedRowsCount = statement.executeUpdate();

				if (updatedRowsCount == 1) {
					LOGGER.debug("DEBUG : record updated successful");
				} else {
					LOGGER.warn("WARN : record haven't been updated");
					throw new SQLException("ERROR : None records have been updated into application");
				}

			} catch (SQLException e) {
				LOGGER.error("ERROR : problems with updating a record into table application");
				throw new DAOException("SQLException while updating a record into table application", e);
			} catch (ConnectionPoolException e1) {
				LOGGER.error("ERROR : problems with updating a record into table application");
				throw new DAOException("ConnectionPoolException while updating a record into table application", e1);
			}
		} else {
			LOGGER.error ("ERROR : application is null or has incorrect fields values");
			throw new DAOException("ERROR : can't update table application by required application");
		}
		return updatedRowsCount;
	}

	/**
	 * Throws UnsupportedOperationException
	 * @param id > 0
	 * @return nothing
	 */
	@Override
	public int updateById(int id) {
		throw new UnsupportedOperationException("Operation doesn't supports with Application entity");
	}

	/**
	 * @param application - entity, what matches to the application record (or not).
	 * @return true if record have been deleted successfully, false - if something goes wrong.
	 * @throws DAOException .
	 */
	@Override
	public boolean delete(Application application) throws DAOException {
		if (application == null) {
			LOGGER.error ("ERROR : Application entity is null");
			throw new DAOException("ERROR : can't update table application by required entity");
		}
		return deleteById(application.getId());
	}

	/**
	 *  Finds a record in table application by matching application ID on entity
	 * 	and deletes from table this record.
	 * 	Returns true if deleted 1 row, false - if none have been deleted.
	 * @param id >= 0
	 * @return true if delete operation has been provided successfully, false - if not.
	 * @throws DAOException if problems with getting access to the MySQL database.
	 */
	@Override
	public boolean deleteById(int id) throws DAOException {
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_DELETE_APPLICATION)) {
			statement.setInt(1, id);
			if (statement.executeUpdate() != 1) {
				LOGGER.warn("WARN : None records have been deleted by ID");
				return false;
			}
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with deleting a record from table application");
			throw new DAOException("SQLException while deleting a record from table application", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with deleting a record from table application");
			throw new DAOException("ConnectionPoolException while deleting a record from table application", e1);
		}

		return true;
	}

	/**
	 * @param resultSet to create applicationList
	 * @return applicationList
	 * @throws SQLException .
	 */
	private List<Application> buildApplicationList(ResultSet resultSet) throws SQLException, DAOException {
		List <Application> applicationList = new ArrayList<>();

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

		while(resultSet.next()) {
			id = resultSet.getInt(TABLE_APPLICATION_ID);
			startDate = resultSet.getDate(TABLE_APPLICATION_START_DATE);
			endDate = resultSet.getDate(TABLE_APPLICATION_END_DATE);
			isApproved = resultSet.getInt(TABLE_APPLICATION_IS_APPROVED) == 0;
			ownerComment = resultSet.getString(TABLE_APPLICATION_OWNER_COMMENT);
			employeeComment = resultSet.getString(TABLE_APPLICATION_EMPLOYEE_COMMENT);
			workType = workTypeDao.getById(resultSet.getInt(TABLE_APPLICATION_WORK_TYPE_ID));
			plantType = plantTypeDao.getById(resultSet.getInt(TABLE_APPLICATION_PLANT_TYPE_ID));
			park = parkDao.getById(resultSet.getInt(TABLE_APPLICATION_PARK_ID));
			status = applicationStatusDao.getById(resultSet.getInt(TABLE_APPLICATION_APL_STATUS_ID));
			owner = userDao.getById(resultSet.getInt(TABLE_APPLICATION_OWNER_ID));
			employee = userDao.getById(resultSet.getInt(TABLE_APPLICATION_EMPLOYEE_ID));

			applicationList.add(new ApplicationBuilder().withId(id).withStartDate(startDate).withEndDate(endDate).
					withIsApproved(isApproved).withOwnerComment(ownerComment).withEmployeeComment(employeeComment).
					withWorkType(workType).withPlantType(plantType).withPark(park).withApplicationStatus(status).
					withOwner(owner).withEmployee(employee).build());
		}
		return applicationList;
	}

	/**
	 * @param id - the identifying id
	 * @param sqlStatement - to get resultSet
	 * @return applicationList
	 * @throws DAOException .
	 */
	private List<Application> getApplicationById(int id, String sqlStatement) throws DAOException {
		List<Application> applicationList;
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
			statement.setInt(1,id);
			ResultSet resultSet = statement.executeQuery();
			applicationList = buildApplicationList(resultSet);

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting records from table application");
			throw new DAOException("SQLException while getting records from table application", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting records from table application");
			throw new DAOException("ConnectionPoolException while getting records from table application", e1);
		}
		return applicationList;
	}

	private void fillInStatement(Application application, PreparedStatement statement) throws SQLException {

		if (validator.validateApplication(application)) {
			statement.setDate(1, (java.sql.Date) application.getStartDate());

			if (application.getEndDate() != null) {
				statement.setDate(2, (java.sql.Date) application.getEndDate());
			} else {
				statement.setNull(2, Types.NULL);
			}

			statement.setInt(3, application.isApproved() ? 0 : 1);

			if (!StringUtils.isNullOrEmpty(application.getOwnerComment())) {
				statement.setString(4, application.getOwnerComment());
			} else {
				statement.setNull(4, Types.NULL);
			}

			if (!StringUtils.isNullOrEmpty(application.getEmployeeComment())) {
				statement.setString(5, application.getEmployeeComment());
			} else {
				statement.setNull(5, Types.NULL);
			}

			statement.setInt(6, application.getPlantType().getId());
			statement.setInt(7, application.getPlantType().getId());
			statement.setInt(8, application.getPark().getId());
			statement.setInt(9, application.getStatus().getId());
			statement.setInt(10, application.getOwner().getId());
			statement.setInt(11, application.getEmployee().getId());
		} else {
			throw new SQLException("ERROR : statement haven't been filled with indexes");
		}
	}
}
