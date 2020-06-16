package service;

import dao.impl.UserDaoImpl;
import entity.User;
import exception.DAOException;
import exception.UserServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum UserService {
	INSTANCE;
	private static final Logger LOGGER = LogManager.getLogger(UserService.class);
	UserDaoImpl userDao = UserDaoImpl.INSTANCE;

	public User getUser(String login, String password) throws UserServiceException {
		User user;
		try {
			if ((user = userDao.getByLogin(login)) != null && password.equals(user.getPassword())) {
				return user;
			} else {
				LOGGER.warn("WARN : can't find user or password is incorrect");
			}
		} catch (DAOException e) {
			LOGGER.error("ERROR : DaoException getting user from database");
			throw new UserServiceException("ERROR : getting user from userService", e);
		}
		return null;
	}

}
