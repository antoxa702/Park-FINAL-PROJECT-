package dao.impl;

import builder.WorkTypeBuilder;
import com.mysql.cj.util.StringUtils;
import dao.WorkTypeDao;
import entity.WorkType;
import exception.ConnectionPoolException;
import exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.DbInitValues.TABLE_WORK_TYPE_ID;
import static util.DbInitValues.TABLE_WORK_TYPE_NAME;

public enum WorkTypeDaoImpl implements WorkTypeDao {

	INSTANCE;

	private static final Logger LOGGER = LogManager.getLogger(WorkTypeDaoImpl.class);
	private static final String SQL_STATEMENT_GET_ALL_WORK_TYPE = "SELECT * FROM work_type;";
	private static final String SQL_STATEMENT_INSERT_WORK_TYPE = "INSERT INTO work_type (type_name) VALUES (?);";
	private static final String SQL_STATEMENT_GET_BY_ID_WORK_TYPE = "SELECT * FROM work_type WHERE id=?;";
	private static final String SQL_STATEMENT_GET_BY_NAME_WORK_TYPE = "SELECT * FROM work_type WHERE type_name=?;";
	private static final String SQL_STATEMENT_UPDATE_WORK_TYPE = "UPDATE work_type SET work_type.type_name=? WHERE work_type.id=?;";
	private static final String SQL_STATEMENT_DELETE_WORK_TYPE = "DELETE FROM work_type WHERE work_type.id=?;";

	/**
	 * Returns a list of WorkType entities according to database's table work_type.
	 */
	@Override
	public List<WorkType> getAllWorkTypes() throws DAOException {
		List<WorkType> workTypeList = new ArrayList<>();
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_ALL_WORK_TYPE)) {

			ResultSet resultSet = statement.executeQuery();
			int id;
			String typeName;

			while(resultSet.next()) {
				id = resultSet.getInt(TABLE_WORK_TYPE_ID);
				typeName = resultSet.getString(TABLE_WORK_TYPE_NAME);
				workTypeList.add(new WorkTypeBuilder().withId(id).withTypeName(typeName).build());
			}
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting records from table work_type");
			throw new DAOException("SQLException while getting records from table work_type", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting records from table work_type");
			throw new DAOException("ConnectionPoolException while getting records from table work_type", e1);
		}
		return workTypeList;
	}

	/**
	 * Finds a record in table work_type by type_name and returns (if it exists) a WorkType entity
	 * according to this database record.
	 */
	@Override
	public WorkType getByName(String name) throws DAOException {
		WorkType workType = null;
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_BY_NAME_WORK_TYPE)) {
			statement.setString(1, name.trim());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt(TABLE_WORK_TYPE_ID);
				String typeName = resultSet.getString(TABLE_WORK_TYPE_NAME);
				workType = new WorkTypeBuilder().withId(id).withTypeName(typeName).build();
			} else {
				LOGGER.warn("WARN : Can't find workType by name");
				throw new DAOException("Error : no such workType by name");
			}
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting a record from table work_type");
			throw new DAOException("SQLException while getting a record from table work_type", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting a record from table work_type");
			throw new DAOException("ConnectionPoolException while getting a record from table work_type", e1);
		}

		return workType;
	}

	/**
	 * Adds a record to Park database into table work_type.
	 */
	@Override
	public void add(WorkType workType) throws DAOException {

		if (workType == null) {
			LOGGER.error("ERROR : workType is null");
			throw new DAOException("ERROR : can't add workType - workType is null");
		}

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_INSERT_WORK_TYPE)) {

			if (!StringUtils.isNullOrEmpty(workType.getTypeName())) {
				statement.setString(1, workType.getTypeName());
			} else {
				LOGGER.warn("WARN : typeName is null");
				statement.setNull(1, Types.NULL);
			}

			if (statement.executeUpdate() == 1) {
				LOGGER.debug("DEBUG : record added successfully");
			} else {
				LOGGER.warn("WARN : record haven't been added");
				throw new SQLException("ERROR : None or few records have been inserted into work_type");
			}

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with adding a record into table work_type");
			throw new DAOException("SQLException while adding a record into table work_type", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with adding a record into table work_type");
			throw new DAOException("ConnectionPoolException while adding a record into table work_type", e1);
		}
	}

	/**
	 * Finds a record in table work_type by ID and returns (if it exists) a WorkType entity
	 * according to this database record.
	 */
	@Override
	public WorkType getById(int id) throws DAOException {
		WorkType workType = null;

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_BY_ID_WORK_TYPE)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String typeName = resultSet.getString(TABLE_WORK_TYPE_NAME);
				workType = new WorkTypeBuilder().withId(id).withTypeName(typeName).build();
			} else {
				LOGGER.error("ERROR : Can't find workType by ID");
				throw new DAOException("Error : no such workType by ID");
			}

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting a record from table work_type");
			throw new DAOException("SQLException while getting a record from table work_type", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting a record from table work_type");
			throw new DAOException("ConnectionPoolException while getting a record from table work_type", e1);
		}
		return workType;
	}

	/**
	 * Finds a record in table work_type by matching work_type ID on entity and table record
	 * and returns number of updated rows.
	 */
	@Override
	public int update(WorkType workType) throws DAOException {
		if (workType == null) {
			LOGGER.error ("ERROR : workType is null");
			throw new DAOException("ERROR : can't update table work_type by required workType");
		}

		int updatedRowsCount = 0;

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_UPDATE_WORK_TYPE)) {

			if (!StringUtils.isNullOrEmpty(workType.getTypeName())) {
				statement.setString(1, workType.getTypeName());
			} else {
				LOGGER.warn("WARN : typeName is null");
				statement.setNull(1, Types.NULL);
			}

			statement.setInt(2, workType.getId());
			updatedRowsCount = statement.executeUpdate();

			if (updatedRowsCount == 1) {
				LOGGER.debug("DEBUG : record have been updated successfully");
			} else {
				LOGGER.warn("WARN : record haven't been updated");
				throw new SQLException("ERROR : None records have been updated into work_type");
			}

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with updating a record into table work_type");
			throw new DAOException("SQLException while updating a record into table work_type", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with updating a record into table work_type");
			throw new DAOException("ConnectionPoolException while updating a record into table work_type", e1);
		}

		return updatedRowsCount;
	}

	/**
	 * Finds a record in table work_type by matching workType ID on entity
	 * and deletes from table this record.
	 * Returns true if deleted 1 row, false - if none have been deleted.
	 */
	@Override
	public boolean delete(WorkType workType) throws DAOException {
		if (workType == null) {
			LOGGER.error ("ERROR : WorkType entity is null");
			throw new DAOException("ERROR : can't update table work_type by required entity");
		}

		return deleteById(workType.getId());
	}

	/**
	 * Finds a record in table work_type by matching WorkPark ID on entity
	 * and deletes from table this record.
	 * Returns true if deleted 1 row, false - if none have been deleted.
	 */
	@Override
	public boolean deleteById(int id) throws DAOException {

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_DELETE_WORK_TYPE)) {
			statement.setInt(1, id);
			if (statement.executeUpdate() != 1) {
				LOGGER.warn("WARN : None records have been deleted by ID");
				return false;
			}
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with deleting a record from table work_type");
			throw new DAOException("SQLException while deleting a record from table work_type", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with deleting a record from table work_type");
			throw new DAOException("ConnectionPoolException while deleting a record from table work_type", e1);
		}

		return true;
	}

	/**
	 * Unsupported operation
	 */
	@Override
	public int updateById(int id) throws DAOException {
		throw new UnsupportedOperationException("Operation doesn't supports with work_type entity");
	}
}
