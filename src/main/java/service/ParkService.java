package service;

import dao.impl.ParkDaoImpl;
import entity.Park;
import exception.DAOException;
import exception.ParkServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ParkService {
	private static final Logger LOGGER = LogManager.getLogger(ParkService.class);
	ParkDaoImpl parkDao = ParkDaoImpl.INSTANCE;

	public List<Park> getAllParks() throws ParkServiceException{
		try {
			return parkDao.getAllParks();
		} catch (DAOException e) {
			LOGGER.error("ERROR : cached DaoException trying getAllParks");
			throw new ParkServiceException("ERROR : DaoException", e);
		}
	}
}
