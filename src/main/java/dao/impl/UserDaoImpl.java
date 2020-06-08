package dao.impl;

import com.mysql.cj.util.StringUtils;
import dao.UserDao;
import entity.User;
import exception.ConnectionPoolException;
import exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pool.ConnectionPool;
import util.validator.UserFieldsValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public enum UserDaoImpl implements UserDao {

	INSTANCE;
	UserFieldsValidator validator = new UserFieldsValidator();

	private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
	private static final String SQL_STATEMENT_GET_ALL_USER_INFO = "SELECT * FROM user_info;";
	private static final String SQL_STATEMENT_INSERT_USER_INFO = "INSERT INTO user_info (name, area) VALUES (?,?);";
	private static final String SQL_STATEMENT_GET_BY_ID_USER_INFO = "SELECT * FROM user_info WHERE id=?;";
	private static final String SQL_STATEMENT_GET_BY_LOGIN_USER_INFO = "SELECT * FROM user_info WHERE login=?;";
	private static final String SQL_STATEMENT_UPDATE_USER_INFO = "UPDATE user_info SET user_info.login=?, " +
			"user_info.password=?, user_info.first_name=?, user_info.last_name=?, user_info.phone_number=?, " +
			"user_info.email=?, user_info.User_type_id=?, user_info.Park_id=? WHERE user_info.id=?;";
	private static final String SQL_STATEMENT_DELETE_USER_INFO = "DELETE FROM user_info WHERE user_info.id=?;";
	private static final String SQL_STATEMENT_GET_BY_PARK_ID_USER_INFO = "SELECT * FROM user_info WHERE Park_id=?;";
	private static final String SQL_STATEMENT_GET_BY_USER_TYPE_ID_USER_INFO = "SELECT * FROM user_info WHERE User_type_id=?;";


	@Override
	public List<User> getAllUsers() throws DAOException {
		return null;
	}

	@Override
	public User getByLogin(String login) throws DAOException {
		return null;
	}

	@Override
	public List<User> getUsersByParkId(int parkId) throws DAOException {
		return null;
	}

	@Override
	public List<User> getUsersByUserTypeId(int userTypeId) throws DAOException {
		return null;
	}

	@Override
	public void add(User entity) throws DAOException {

	}

	@Override
	public User getById(int id) throws DAOException {
		return null;
	}

	@Override
	public int update(User user) throws DAOException {
		if (user == null) {
			LOGGER.error ("ERROR : user is null");
			throw new DAOException("ERROR : can't update table user_info by required user");
		}

		int updatedRowsCount = 0;

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_UPDATE_USER_INFO)) {

			if (!StringUtils.isNullOrEmpty(user.getLogin())) {
				statement.setString(1, user.getLogin());
			} else {
				LOGGER.warn("WARN : login is null");
				//statement.setNull(1, Types.NULL);
			}


			statement.setInt(2, user.getId());
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



	@Override
	public boolean delete(User user) throws DAOException {
		if (user == null) {
			LOGGER.error ("ERROR : User entity is null");
			throw new DAOException("ERROR : can't update table user_info by required entity");
		}
		return deleteById(user.getId());
	}

	/**
	 *  Finds a record in table user_type by matching userType ID on entity
	 * 	and deletes from table this record.
	 * 	Returns true if deleted 1 row, false - if none have been deleted.
	 * @param id >= 0
	 * @return true if delete operation has been provided successfully, false - if not.
	 * @throws DAOException if problems with getting access to the MySQL database.
	 */
	@Override
	public boolean deleteById(int id) throws DAOException {
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_DELETE_USER_INFO)) {
			statement.setInt(1, id);
			if (statement.executeUpdate() != 1) {
				LOGGER.warn("WARN : None records have been deleted by ID");
				return false;
			}
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with deleting a record from table user_info");
			throw new DAOException("SQLException while deleting a record from table user_info", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with deleting a record from table user_info");
			throw new DAOException("ConnectionPoolException while deleting a record from table user_info", e1);
		}

		return true;
	}

	/**
	 * Throws UnsupportedOperationException
	 * @param id > 0
	 * @return nothing
	 * @throws DAOException .
	 */
	@Override
	public int updateById(int id) throws DAOException {
		throw new UnsupportedOperationException("Operation doesn't supports with user_type entity");
	}
}
