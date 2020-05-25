package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.ParkDao;
import entity.Park;
import exception.ConnectionPoolException;
import exception.DAOException;
import pool.ConnectionPool;

public class ParkDaoImpl implements ParkDao {
	
	private static final Logger LOGGER = LogManager.getLogger(ParkDaoImpl.class);
	private static final String SQL_STATEMENT_INSERT_PARK = "INSERT INTO park (name, area) VALUES (?,?);";	
	
	/**
	 * Adds a record to Park database into table park.
	 */
	@Override
	public void add(Park park) throws DAOException {
		
		if (park == null) {
			LOGGER.error("ERROR : entity is null");
			throw new DAOException("ERROR : can't add park - entity is null");			
		}		
		
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_INSERT_PARK)) {
			statement.setString(1, park.getName());
			statement.setDouble(2, park.getArea());
			
			if (statement.executeUpdate() == 1) {
				LOGGER.debug("DEBUG : record added successful");
			} else {
				LOGGER.warn("WARN : record haven't been added");
				throw new SQLException("ERROR : None or few records have been inserted into park");
			}			
			
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with adding a record into table park");
			throw new DAOException("SQLException while adding a record to table park", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with adding a record into table park");
			throw new DAOException("ConnectionPoolException while adding a record to table park", e1);
		} 	
		
	}

	@Override
	public Park getById(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Park entity) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateById(int id) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Park entity) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(int id) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Park getByName(String name) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Park> getAllParks() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
