package service;

import dao.impl.UserDaoImpl;
import entity.User;
import exception.DAOException;
import exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public enum UserService {
	INSTANCE;
	private static final Logger LOGGER = LogManager.getLogger(UserService.class);
	UserDaoImpl userDao = UserDaoImpl.INSTANCE;

	public User getUser(String login, String password) throws ServiceException {
		User loggedUser;
		try {
			if ((loggedUser = userDao.getByLogin(login)) != null && password.equals(loggedUser.getPassword())) {
				return loggedUser;
			} else {
				LOGGER.warn("WARN : can't find user or password is incorrect");
				return null;
			}
		} catch (DAOException e) {
			LOGGER.error("ERROR : DaoException getting user from database");
			throw new ServiceException("ERROR : getting user from userService", e);
		}
	}

	public boolean registerUser (User user) {
		try {
			if (!isRegisteredUser(user)) {
				userDao.add(user);
			}
		} catch (ServiceException | DAOException e) {
			LOGGER.error("ERROR : UserDao exception");
			return false;
		}
		return true;
	}

	private boolean isRegisteredUser(User user) throws ServiceException {
		try {
			List<User> registeredUsers = userDao.getAllUsers();
			for (User rUser : registeredUsers) {
				if (rUser.getLogin().equals(user.getLogin()) || rUser.getEmail().equals(user.getEmail()) ||
						rUser.getPhoneNumber().equals(user.getPhoneNumber())) {
					LOGGER.warn("WARN : user with this LOGIN or EMAIL or PHONE_NUMBER have been registered already");
					return true;
				}
			}

		} catch (DAOException e) {
			LOGGER.error("ERROR : UserDAO exception");
			throw new ServiceException("ERROR : DaoException getting users from database", e);
		}
		return false;
	}

}
