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
	private static final String SQL_STATEMENT_INSERT_PARK = "INSERT INTO park ('name', 'area') VALUES (?,?)";
	
	
	/**
	 * Adds a record to Park database into table park.
	 */
	@Override
	public void add(Park entity) throws DAOException {
		
		if (entity == null) {
			LOGGER.error("ERROR : entity is null");
			throw new DAOException("ERROR : can't add park - entity is null");			
		}
		
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_INSERT_PARK)) {
			statement.setString()
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionPoolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		return null;
	}

	@Override
	public int updateById(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
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
