package dao.impl;

import builder.UserBuilder;
import com.mysql.cj.util.StringUtils;
import dao.UserDao;
import entity.Park;
import entity.User;
import entity.UserType;
import exception.ConnectionPoolException;
import exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pool.ConnectionPool;
import util.validator.FieldsValidator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.DbInitValues.*;

public enum UserDaoImpl implements UserDao {

	INSTANCE;
	FieldsValidator validator = new FieldsValidator();
	UserTypeDaoImpl userTypeDao = UserTypeDaoImpl.INSTANCE;
	ParkDaoImpl parkDao = ParkDaoImpl.INSTANCE;

	private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
	private static final String SQL_STATEMENT_GET_ALL_USER_INFO = "SELECT * FROM user_info;";
	private static final String SQL_STATEMENT_GET_BY_PARK_ID_USER_INFO = "SELECT * FROM user_info WHERE user_info.Park_id=?;";
	private static final String SQL_STATEMENT_GET_BY_USER_TYPE_ID_USER_INFO = "SELECT * FROM user_info WHERE user_info.User_type_id=?;";
	private static final String SQL_STATEMENT_GET_BY_ID_USER_INFO = "SELECT * FROM user_info WHERE id=?;";
	private static final String SQL_STATEMENT_GET_BY_LOGIN_USER_INFO = "SELECT * FROM user_info WHERE user_info.login=?;";
	private static final String SQL_STATEMENT_INSERT_USER_INFO = "INSERT INTO user_info (login, password, first_name," +
			"last_name, phone_number, email, Park_id, User_type_id) VALUES (?,?,?,?,?,?,?,?);";
	private static final String SQL_STATEMENT_UPDATE_USER_INFO = "UPDATE user_info SET user_info.login=?, " +
			"user_info.password=?, user_info.first_name=?, user_info.last_name=?, user_info.phone_number=?, " +
			"user_info.email=?, user_info.Park_id=?, user_info.User_type_id=? WHERE user_info.id=?;";
	private static final String SQL_STATEMENT_DELETE_USER_INFO = "DELETE FROM user_info WHERE user_info.id=?;";

	/**
	 * @return List<User> according to the database Park table user_info
	 * @throws DAOException .
	 */
	@Override
	public List<User> getAllUsers() throws DAOException {
		List<User> userList;
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_ALL_USER_INFO)) {

			ResultSet resultSet = statement.executeQuery();
			userList = buildUserList(resultSet);

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting records from table user_info");
			throw new DAOException("SQLException while getting records from table user_info", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting records from table user_info");
			throw new DAOException("ConnectionPoolException while getting records from table user_info", e1);
		}
		return userList;
	}

	/**
	 * @param login - login
	 * @return new User;
	 * @throws DAOException .
	 */
	@Override
	public User getByLogin(String login) throws DAOException {
		List<User> userList;
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_BY_LOGIN_USER_INFO)) {
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			userList = buildUserList(resultSet);

			if (userList.size() == 0) {
				LOGGER.warn("WARN : Can't find user by login");
				return null;
			}

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting records from table user_info");
			throw new DAOException("SQLException while getting records from table user_info", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting records from table user_info");
			throw new DAOException("ConnectionPoolException while getting records from table user_info", e1);
		}
		return userList.get(0);
	}

	/**
	 * @param parkId .
	 * @return List<User> userList by Park_id
	 * @throws DAOException .
	 */
	@Override
	public List<User> getUsersByParkId(int parkId) throws DAOException {
		return getUsersById(parkId, SQL_STATEMENT_GET_BY_PARK_ID_USER_INFO);
	}

	/**
	 * @param userTypeId .
	 * @return userList by userTypeId
	 * @throws DAOException .
	 */
	@Override
	public List<User> getUsersByUserTypeId(int userTypeId) throws DAOException {
		return getUsersById(userTypeId, SQL_STATEMENT_GET_BY_USER_TYPE_ID_USER_INFO);
	}

	/**
	 * @param id - the identifying id
	 * @param sqlStatement - to get resultSet
	 * @return userList
	 * @throws DAOException .
	 */
	private List<User> getUsersById(int id, String sqlStatement) throws DAOException {
		List<User> userList;
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(sqlStatement)) {
			statement.setInt(1,id);
			ResultSet resultSet = statement.executeQuery();
			userList = buildUserList(resultSet);

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting records from table user_info");
			throw new DAOException("SQLException while getting records from table user_info", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting records from table user_info");
			throw new DAOException("ConnectionPoolException while getting records from table user_info", e1);
		}
		return userList;
	}

	/**
	 * @param user - to fill table user_info with a new record
	 * @throws DAOException .
	 */
	@Override
	public void add(User user) throws DAOException {
		if (user == null) {
			LOGGER.error("ERROR : user is null");
			throw new DAOException("ERROR : can't add user - user is null");
		}

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_INSERT_USER_INFO)) {
			fillInStatement(user, statement);
			if (statement.executeUpdate() == 1) {
				LOGGER.debug("DEBUG : record added successfully");
			} else {
				LOGGER.warn("WARN : record haven't been added");
				throw new SQLException("ERROR : None or few records have been inserted into user_info");
			}

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with adding a record into table user_info");
			throw new DAOException("SQLException while adding a record into table user_info", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with adding a record into table user_info");
			throw new DAOException("ConnectionPoolException while adding a record into table user_info", e1);
		}
	}

	@Override
	public User getById(int id) throws DAOException {
		List<User> userList = getUsersById(id,SQL_STATEMENT_GET_BY_ID_USER_INFO);
		if (userList.size() == 0) {
			LOGGER.warn("WARN : Can't find user by id");
			return null;
		}
		return userList.get(0);
	}

	@Override
	public int update(User user) throws DAOException {
		if (user == null) {
			LOGGER.error ("ERROR : user is null");
			throw new DAOException("ERROR : can't update table user_info by required user");
		}

		int updatedRowsCount;

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_UPDATE_USER_INFO)) {

			fillInStatement(user, statement);

			if (validator.validateId(user.getId())) {
				statement.setInt(9, user.getId());
			} else {
				LOGGER.warn("WARN : id is incorrect");
				statement.setNull(9, Types.NULL);
			}

			updatedRowsCount = statement.executeUpdate();

			if (updatedRowsCount == 1) {
				LOGGER.debug("DEBUG : record updated successful");
			} else {
				LOGGER.warn("WARN : record haven't been updated");
				throw new SQLException("ERROR : None records have been updated into user_info");
			}

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with updating a record into table user_info");
			throw new DAOException("SQLException while updating a record into table user_info", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with updating a record into table user_info");
			throw new DAOException("ConnectionPoolException while updating a record into table user_info", e1);
		}

		return updatedRowsCount;
	}

	/**
	 * Saves from duplicate code at ADD and UPDATE methods
	 * @param user - entity
	 * @param statement - statement
	 * @throws SQLException .
	 */
	private void fillInStatement(User user, PreparedStatement statement) throws SQLException {
		if (validator.validateLogin(user.getLogin())) {
			statement.setString(1, user.getLogin());
		} else {
			LOGGER.warn("WARN : login is incorrect");
			statement.setNull(1, Types.NULL);
		}

		if (validator.validatePassword(user.getPassword())) {
			statement.setString(2, user.getPassword());
		} else {
			LOGGER.warn("WARN : password is incorrect");
			statement.setNull(2, Types.NULL);
		}

		if (!StringUtils.isNullOrEmpty(user.getFirstName())) {
			statement.setString(3, user.getFirstName());
		} else {
			LOGGER.warn("WARN : firstName is null or incorrect");
			statement.setNull(3, Types.NULL);
		}

		if (!StringUtils.isNullOrEmpty(user.getLastName())) {
			statement.setString(4, user.getLastName());
		} else {
			LOGGER.warn("WARN : lastName is null or incorrect");
			statement.setNull(4, Types.NULL);
		}

		if (validator.validatePhoneNumber(user.getPhoneNumber())) {
			statement.setString(5, user.getPhoneNumber());
		} else {
			LOGGER.warn("WARN : phoneNumber is incorrect");
			statement.setNull(5, Types.NULL);
		}

		if (validator.validateEmail(user.getEmail())) {
			statement.setString(6, user.getEmail());
		} else {
			LOGGER.warn("WARN : email is incorrect");
			statement.setNull(6, Types.NULL);
		}

		if (validator.validateId(user.getPark().getId())) {
			statement.setInt(7, user.getPark().getId());
		} else {
			LOGGER.warn("WARN : parkId is incorrect");
			statement.setNull(7, Types.NULL);
		}

		if (validator.validateId(user.getUserType().getId())) {
			statement.setInt(8, user.getUserType().getId());
		} else {
			LOGGER.warn("WARN : userTypeId is incorrect");
			statement.setNull(8, Types.NULL);
		}
	}

	/**
	 * @param user - entity, what matches to the user_info record (or not).
	 * @return true if record have been deleted successfully, false - if something goes wrong.
	 * @throws DAOException .
	 */
	@Override
	public boolean delete(User user) throws DAOException {
		if (user == null) {
			LOGGER.error ("ERROR : User entity is null");
			throw new DAOException("ERROR : can't update table user_info by required entity");
		}
		return deleteById(user.getId());
	}

	/**
	 *  Finds a record in table user_type by matching user ID on entity
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
	 */
	@Override
	public int updateById(int id) {
		throw new UnsupportedOperationException("Operation doesn't supports with user_info entity");
	}

	/**
	 * @param resultSet to create userList
	 * @return userList
	 * @throws SQLException .
	 */
	private List<User> buildUserList(ResultSet resultSet) throws SQLException, DAOException {
		List <User> userList = new ArrayList<>();
		int id;
		Park park;
		UserType userType;
		String login;
		String password;
		String firstName;
		String lastName;
		String phoneNumber;
		String email;

		while(resultSet.next()) {
			id = resultSet.getInt(TABLE_USER_INFO_ID);
			login = resultSet.getString(TABLE_USER_INFO_LOGIN);
			password = resultSet.getString(TABLE_USER_INFO_PASSWORD);
			firstName = resultSet.getString(TABLE_USER_INFO_FIRST_NAME);
			lastName = resultSet.getString(TABLE_USER_INFO_LAST_NAME);
			phoneNumber = resultSet.getString(TABLE_USER_INFO_PHONE_NUMBER);
			email = resultSet.getString(TABLE_USER_INFO_EMAIL);
			park = parkDao.getById(resultSet.getInt(TABLE_USER_INFO_PARK_ID));
			userType = userTypeDao.getById(resultSet.getInt(TABLE_USER_INFO_USER_TYPE_ID));
			userList.add(new UserBuilder().withId(id).withLogin(login).withPassword(password).
					withFirstName(firstName).withLastName(lastName).withPhoneNumber(phoneNumber).withEmail(email).
					withPark(park).withUserType(userType).build());
		}
		return userList;
	}

	//TODO check USER DAO and OTHERS DAO. Fill in the tables  about status? types and so on...

}
