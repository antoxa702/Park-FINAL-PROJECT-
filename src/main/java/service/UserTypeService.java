package service;

import dao.impl.UserTypeDaoImpl;
import entity.UserType;
import exception.DAOException;
import exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum UserTypeService {
	INSTANCE;
	private static final Logger LOGGER = LogManager.getLogger(UserTypeService.class);

	public UserType getByName(String name) throws ServiceException {
		UserType userType = null;
		try {
			userType = UserTypeDaoImpl.INSTANCE.getByName(name);
		} catch (DAOException e) {
			LOGGER.error("ERROR : DAO exception getting UserType");
			throw new ServiceException("ERROR : Service Exception getting UserType", e);
		}
		return userType;
	}
}
