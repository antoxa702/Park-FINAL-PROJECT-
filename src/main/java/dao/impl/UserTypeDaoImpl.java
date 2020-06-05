package dao.impl;

import builder.UserTypeBuilder;
import com.mysql.cj.util.StringUtils;
import dao.UserTypeDao;
import entity.UserType;
import exception.ConnectionPoolException;
import exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.DbInitValues.TABLE_USER_TYPE_ID;
import static util.DbInitValues.TABLE_USER_TYPE_NAME;

public enum UserTypeDaoImpl implements UserTypeDao {

	INSTANCE;

	private static final Logger LOGGER = LogManager.getLogger(UserTypeDaoImpl.class);
	private static final String SQL_STATEMENT_GET_ALL_USER_TYPE = "SELECT * FROM user_type;";
	private static final String SQL_STATEMENT_INSERT_USER_TYPE = "INSERT INTO user_type (type_name) VALUES (?);";
	private static final String SQL_STATEMENT_GET_BY_ID_USER_TYPE = "SELECT * FROM user_type WHERE id=?;";
	private static final String SQL_STATEMENT_GET_BY_NAME_USER_TYPE = "SELECT * FROM user_type WHERE type_name=?;";
	private static final String SQL_STATEMENT_UPDATE_USER_TYPE = "UPDATE user_type SET user_type.type_name=? WHERE user_type.id=?;";
	private static final String SQL_STATEMENT_DELETE_USER_TYPE = "DELETE FROM user_type WHERE user_type.id=?;";

	/**
	 * Returns a list of UserType entities according to database's table user_type.
	 */
	@Override
	public List<UserType> getAllUserTypes() throws DAOException {
		List<UserType> userTypeList = new ArrayList<>();
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_ALL_USER_TYPE)) {

			ResultSet resultSet = statement.executeQuery();
			int id;
			String typeName;

			while(resultSet.next()) {
				id = resultSet.getInt(TABLE_USER_TYPE_ID);
				typeName = resultSet.getString(TABLE_USER_TYPE_NAME);
				userTypeList.add(new UserTypeBuilder().withId(id).withName(typeName).build());
			}
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting records from table user_type");
			throw new DAOException("SQLException while getting records from table user_type", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting records from table user_type");
			throw new DAOException("ConnectionPoolException while getting records from table user_type", e1);
		}
		return userTypeList;
	}

	/**
	 * Finds a record in table user_type by type_name and returns (if it exists) a UserType entity
	 * according to this database record.
	 */
	@Override
	public UserType getByName(String name) throws DAOException {
		UserType userType = null;
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_BY_NAME_USER_TYPE)) {
			statement.setString(1, name.trim());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt(TABLE_USER_TYPE_ID);
				String nameType = resultSet.getString(TABLE_USER_TYPE_NAME);
				userType = new UserTypeBuilder().withId(id).withName(nameType).build();
			} else {
				LOGGER.warn("WARN : Can't find userType by name");
				throw new DAOException("Error : no such userType by name");
			}
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting a record from table user_type");
			throw new DAOException("SQLException while getting a record from table user_type", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting a record from table user_type");
			throw new DAOException("ConnectionPoolException while getting a record from table user_type", e1);
		}

		return userType;
	}

	/**
	 * Adds a record to Park database into table user_type.
	 */
	@Override
	public void add(UserType userType) throws DAOException {

		if (userType == null) {
			LOGGER.error("ERROR : userType is null");
			throw new DAOException("ERROR : can't add userType - userType is null");
		}

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_INSERT_USER_TYPE)) {

			if (!StringUtils.isNullOrEmpty(userType.getNameType())) {
				statement.setString(1, userType.getNameType());
			} else {
				LOGGER.warn("WARN : nameType is null");
				statement.setNull(1, Types.NULL);
			}

			if (statement.executeUpdate() == 1) {
				LOGGER.debug("DEBUG : record added successfully");
			} else {
				LOGGER.warn("WARN : record haven't been added");
				throw new SQLException("ERROR : None or few records have been inserted into user_type");
			}

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with adding a record into table user_type");
			throw new DAOException("SQLException while adding a record into table user_type", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with adding a record into table user_type");
			throw new DAOException("ConnectionPoolException while adding a record into table user_type", e1);
		}
	}

	/**
	 * Finds a record in table user_type by ID and returns (if it exists) a UserType entity
	 * according to this database record.
	 */
	@Override
	public UserType getById(int id) throws DAOException {
		UserType userType = null;

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_BY_ID_USER_TYPE)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String nameType = resultSet.getString(TABLE_USER_TYPE_NAME);
				userType = new UserTypeBuilder().withId(id).withName(nameType).build();
			} else {
				LOGGER.error("ERROR : Can't find userType by ID");
				throw new DAOException("Error : no such userType by ID");
			}

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting a record from table userType");
			throw new DAOException("SQLException while getting a record from table userType", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting a record from table userType");
			throw new DAOException("ConnectionPoolException while getting a record from table userType", e1);
		}
		return userType;
	}

	/**
	 * Finds a record in table user_type by matching user_type ID on entity and table record
	 * and returns number of updated rows.
	 */
	@Override
	public int update(UserType userType) throws DAOException {
		if (userType == null) {
			LOGGER.error ("ERROR : userType is null");
			throw new DAOException("ERROR : can't update table user_type by required userType");
		}

		int updatedRowsCount = 0;

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_UPDATE_USER_TYPE)) {

			if (!StringUtils.isNullOrEmpty(userType.getNameType())) {
				statement.setString(1, userType.getNameType());
			} else {
				LOGGER.warn("WARN : nameType is null");
				statement.setNull(1, Types.NULL);
			}


			statement.setInt(2, userType.getId());
			updatedRowsCount = statement.executeUpdate();

			if (updatedRowsCount == 1) {
				LOGGER.debug("DEBUG : record updated successful");
			} else {
				LOGGER.warn("WARN : record haven't been updated");
				throw new SQLException("ERROR : None records have been updated into user_type");
			}

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with updating a record into table user_type");
			throw new DAOException("SQLException while updating a record into table user_type", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with updating a record into table user_type");
			throw new DAOException("ConnectionPoolException while updating a record into table user_type", e1);
		}

		return updatedRowsCount;
	}

	/**
	 * Finds a record in table user_type by matching userType ID on entity
	 * and deletes from table this record.
	 * Returns true if deleted 1 row, false - if none have been deleted.
	 */
	@Override
	public boolean delete(UserType userType) throws DAOException {
		if (userType == null) {
			LOGGER.error ("ERROR : UserType entity is null");
			throw new DAOException("ERROR : can't update table user_type by required entity");
		}

		return deleteById(userType.getId());
	}

	/**
	 * Finds a record in table user_type by matching userPark ID on entity
	 * and deletes from table this record.
	 * Returns true if deleted 1 row, false - if none have been deleted.
	 */
	@Override
	public boolean deleteById(int id) throws DAOException {

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_DELETE_USER_TYPE)) {
			statement.setInt(1, id);
			if (statement.executeUpdate() != 1) {
				LOGGER.warn("WARN : None records have been deleted by ID");
				return false;
			}
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with deleting a record from table user_type");
			throw new DAOException("SQLException while deleting a record from table user_type", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with deleting a record from table user_type");
			throw new DAOException("ConnectionPoolException while deleting a record from table user_type", e1);
		}

		return true;
	}

	/**
	 * Unsupported operation
	 */
	@Override
	public int updateById(int id) throws DAOException {
		throw new UnsupportedOperationException("Operation doesn't supports with user_type entity");
	}
}
