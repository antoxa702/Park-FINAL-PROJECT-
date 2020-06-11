package dao.impl;

import builder.ApplicationStatusBuilder;
import com.mysql.cj.util.StringUtils;
import dao.ApplicationStatusDao;
import entity.ApplicationStatus;
import exception.ConnectionPoolException;
import exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.DbInitValues.TABLE_APPLICATION_STATUS_ID;
import static util.DbInitValues.TABLE_APPLICATION_STATUS_NAME;

public enum ApplicationStatusDaoImpl implements ApplicationStatusDao {

	INSTANCE;

	private static final Logger LOGGER = LogManager.getLogger(ApplicationStatusDaoImpl.class);
	private static final String SQL_STATEMENT_GET_ALL_APPLICATION_STATUS = "SELECT * FROM application_status;";
	private static final String SQL_STATEMENT_INSERT_APPLICATION_STATUS = "INSERT INTO application_status (status_name) VALUES (?);";
	private static final String SQL_STATEMENT_GET_BY_ID_APPLICATION_STATUS = "SELECT * FROM application_status WHERE id=?;";
	private static final String SQL_STATEMENT_GET_BY_NAME_APPLICATION_STATUS = "SELECT * FROM application_status WHERE status_name=?;";
	private static final String SQL_STATEMENT_UPDATE_APPLICATION_STATUS = "UPDATE application_status SET application_status.status_name=? WHERE application_status.id=?;";
	private static final String SQL_STATEMENT_DELETE_APPLICATION_STATUS = "DELETE FROM application_status WHERE application_status.id=?;";

	/**
	 * Returns a list of WorkType entities according to database's table application_status.
	 */
	@Override
	public List<ApplicationStatus> getAllApplicationStatuses() throws DAOException {
		List<ApplicationStatus> applicationStatusList = new ArrayList<>();
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_ALL_APPLICATION_STATUS)) {

			ResultSet resultSet = statement.executeQuery();
			int id;
			String statusName;

			while(resultSet.next()) {
				id = resultSet.getInt(TABLE_APPLICATION_STATUS_ID);
				statusName = resultSet.getString(TABLE_APPLICATION_STATUS_NAME);
				applicationStatusList.add(new ApplicationStatusBuilder().withId(id).withStatusName(statusName).build());
			}
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting records from table application_status");
			throw new DAOException("SQLException while getting records from table application_status", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting records from table application_status");
			throw new DAOException("ConnectionPoolException while getting records from table application_status", e1);
		}
		return applicationStatusList;
	}

	/**
	 * Finds a record in table application_status by status_name and returns (if it exists) a ApplicationStatus entity
	 * according to this database record.
	 */
	@Override
	public ApplicationStatus getByName(String name) throws DAOException {
		ApplicationStatus applicationStatus = null;
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_BY_NAME_APPLICATION_STATUS)) {
			statement.setString(1, name.trim());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt(TABLE_APPLICATION_STATUS_ID);
				String statusName = resultSet.getString(TABLE_APPLICATION_STATUS_NAME);
				applicationStatus = new ApplicationStatusBuilder().withId(id).withStatusName(statusName).build();
			} else {
				LOGGER.warn("WARN : Can't find applicationStatus by name");
				throw new DAOException("Error : no such applicationStatus by name");
			}
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting a record from table application_status");
			throw new DAOException("SQLException while getting a record from table application_status", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting a record from table application_status");
			throw new DAOException("ConnectionPoolException while getting a record from table application_status", e1);
		}

		return applicationStatus;
	}

	/**
	 * Adds a record to Park database into table application_status.
	 */
	@Override
	public void add(ApplicationStatus applicationStatus) throws DAOException {

		if (applicationStatus == null) {
			LOGGER.error("ERROR : applicationStatus is null");
			throw new DAOException("ERROR : can't add applicationStatus - applicationStatus is null");
		}

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_INSERT_APPLICATION_STATUS)) {

			if (!StringUtils.isNullOrEmpty(applicationStatus.getStatusName())) {
				statement.setString(1, applicationStatus.getStatusName());
			} else {
				LOGGER.warn("WARN : typeName is null");
				statement.setNull(1, Types.NULL);
			}

			if (statement.executeUpdate() == 1) {
				LOGGER.debug("DEBUG : record added successfully");
			} else {
				LOGGER.warn("WARN : record haven't been added");
				throw new SQLException("ERROR : None or few records have been inserted into application_status");
			}

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with adding a record into table application_status");
			throw new DAOException("SQLException while adding a record into table application_status", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with adding a record into table application_status");
			throw new DAOException("ConnectionPoolException while adding a record into table application_status", e1);
		}
	}

	/**
	 * Finds a record in table application_status by ID and returns (if it exists) a ApplicationStatus entity
	 * according to this database record.
	 */
	@Override
	public ApplicationStatus getById(int id) throws DAOException {
		ApplicationStatus applicationStatus = null;

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_BY_ID_APPLICATION_STATUS)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String statusName = resultSet.getString(TABLE_APPLICATION_STATUS_NAME);
				applicationStatus = new ApplicationStatusBuilder().withId(id).withStatusName(statusName).build();
			} else {
				LOGGER.error("ERROR : Can't find applicationStatus by ID");
				throw new DAOException("Error : no such applicationStatus by ID");
			}

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting a record from table application_status");
			throw new DAOException("SQLException while getting a record from table application_status", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting a record from table application_status");
			throw new DAOException("ConnectionPoolException while getting a record from table application_status", e1);
		}
		return applicationStatus;
	}

	/**
	 * Finds a record in table application_status by matching application_status ID on entity and table record
	 * and returns number of updated rows.
	 */
	@Override
	public int update(ApplicationStatus applicationStatus) throws DAOException {
		if (applicationStatus == null) {
			LOGGER.error ("ERROR : applicationStatus is null");
			throw new DAOException("ERROR : can't update table application_status by required applicationStatus");
		}

		int updatedRowsCount = 0;

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_UPDATE_APPLICATION_STATUS)) {

			if (!StringUtils.isNullOrEmpty(applicationStatus.getStatusName())) {
				statement.setString(1, applicationStatus.getStatusName());
			} else {
				LOGGER.warn("WARN : statusName is null");
				statement.setNull(1, Types.NULL);
			}

			statement.setInt(2, applicationStatus.getId());
			updatedRowsCount = statement.executeUpdate();

			if (updatedRowsCount == 1) {
				LOGGER.debug("DEBUG : record have been updated successfully");
			} else {
				LOGGER.warn("WARN : record haven't been updated");
				throw new SQLException("ERROR : None records have been updated into application_status");
			}

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with updating a record into table application_status");
			throw new DAOException("SQLException while updating a record into table application_status", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with updating a record into table application_status");
			throw new DAOException("ConnectionPoolException while updating a record into table application_status", e1);
		}

		return updatedRowsCount;
	}

	/**
	 * Finds a record in table application_status by matching applicationStatus ID on entity
	 * and deletes from table this record.
	 * Returns true if deleted 1 row, false - if none have been deleted.
	 */
	@Override
	public boolean delete(ApplicationStatus applicationStatus) throws DAOException {
		if (applicationStatus == null) {
			LOGGER.error ("ERROR : ApplicationStatus entity is null");
			throw new DAOException("ERROR : can't update table application_status by required entity");
		}

		return deleteById(applicationStatus.getId());
	}

	/**
	 * Finds a record in table application_status by matching ApplicationStatus ID on entity
	 * and deletes from table this record.
	 * Returns true if deleted 1 row, false - if none have been deleted.
	 */
	@Override
	public boolean deleteById(int id) throws DAOException {

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_DELETE_APPLICATION_STATUS)) {
			statement.setInt(1, id);
			if (statement.executeUpdate() != 1) {
				LOGGER.warn("WARN : None records have been deleted by ID");
				return false;
			}
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with deleting a record from table application_status");
			throw new DAOException("SQLException while deleting a record from table application_status", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with deleting a record from table application_status");
			throw new DAOException("ConnectionPoolException while deleting a record from table application_status", e1);
		}

		return true;
	}

	/**
	 * Unsupported operation
	 */
	@Override
	public int updateById(int id) throws DAOException {
		throw new UnsupportedOperationException("Operation doesn't supports with ApplicationStatus entity");
	}
}
