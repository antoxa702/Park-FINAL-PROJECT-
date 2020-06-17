package service;

import dao.impl.ParkDaoImpl;
import entity.Park;
import exception.DAOException;
import exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public enum ParkService {
	INSTANCE;
	private static final Logger LOGGER = LogManager.getLogger(ParkService.class);
	ParkDaoImpl parkDao = ParkDaoImpl.INSTANCE;

	public List<Park> getAllParks() throws ServiceException {
		try {
			return parkDao.getAllParks();
		} catch (DAOException e) {
			LOGGER.error("ERROR : cached DaoException trying getAllParks");
			throw new ServiceException("ERROR : DaoException", e);
		}
	}

	public Park getByName (String name) throws ServiceException {
		List<Park> parks = getAllParks();
		Park parkByName = null;
		for(Park park : parks) {
			if (park.getName().equals(name)) {
				parkByName =  park;
				break;
			}
		}
		return parkByName;
	}
}
